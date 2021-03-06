package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.utils.observer.Observer;

public class Sala implements ISala{
    private ICelula[][] celulas;
    private int ID, tamX, tamY;

    public Sala(int ID, int tamX, int tamY) {
        this.ID = ID;
        this.tamX = tamX;
        this.tamY = tamY;
        
        this.celulas = new ICelula[tamY][tamX];
    }
    
    public ICelula getCelula(int x, int y) {
    	if(outOfBounds(x, y))
    		throw new RuntimeException("Tentando acessar uma célula que não existe! Posicao: " + x + ":" + y);
        return celulas[y][x];
    }

    public void setCelula(int x, int y, ICelula c) {
    	celulas[y][x] = c;
    }

    public IEntidadeDinamica removeEntidade(int x, int y) {
    	return getCelula(x, y).popEntidade();
    }
    
    public void addEntidade(int x, int y, IEntidadeDinamica e) {
    	getCelula(x, y).pushEntidade(e);
    }
    
    public String[] estado(int x, int y) {
    	return getCelula(x, y).estado();
    }
    
    public void inativar() {
    	for(int i = 0; i < getTamX(); i++) {
    		for(int j = 0; j < getTamY(); j++) {
    			getCelula(i, j).inativar();
    		}
    	}
    }
    
    public int getID() {
        return ID;
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

    public boolean outOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= tamX || y >= tamY);
    }
    
    public void subToLocal(int x, int y, Observer e) {
    	getCelula(x, y).subscribe(e);
    }

	public void destroy() {
		for(int x = 0; x < tamX; x++) {
			for(int y = 0; y < tamY; y++) {
				getCelula(x, y).destroy();
			}
		}
		celulas = null;
	}
}
