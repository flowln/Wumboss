package src.model;

import src.utils.*;
import src.model.entidade.*;

public class Sala {
    private Celula[][] celulas;
    private int ID, tamX, tamY;
    private static Caverna cave = Caverna.getInstance();

    public Sala(int ID, int tamX, int tamY) {
        this.ID = ID;
        this.tamX = tamX;
        this.tamY = tamY;
        this.celulas = new Celula[tamY][tamX];
    }
    
    public Celula getCelula(int x, int y) {
        return celulas[y][x];
    }

    public void setCelula(int x, int y, Celula c) {
    	celulas[y][x] = c;
    }

    public void moverEntidade(int xIni, int yIni, Direcao dir) {
        // TODO: checar  se deve fazer interacao
        int xFim = xIni, yFim = yIni;
        switch (dir) {
            case NORTE:
                yFim += 1;
                break;
            case LESTE:
                xFim += 1;
                break;
            case SUL:
                yFim -= 1;
                break;
            case OESTE:
                xFim -= 1;
                break;
            default:

        }

        Celula fim = celulas[yFim][xFim];

        // Checagem de bordas
        if (!(fim.getEntidade() instanceof Parede)) {
            Entidade e = celulas[yIni][xIni].removerEntidade();
            fim.addEntidade(e);
        }
        // Checagem de passagem entre salas
        if (fim.ehPassagem()) {
            cave.moverEntidadeEntreSalas(celulas[yIni][xIni], ID, dir);
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

    public boolean ehBorda(int x, int y) {
        if (x == 0 || y == 0 || x == tamX - 1 || y == tamY - 1)
            return true;
        return false;
    }
}
