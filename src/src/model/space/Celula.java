package src.model.space;

import java.util.Random;
import java.util.Stack;

import src.model.entidade.dinamica.EntidadeViva;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.Inimigo;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.itens.Flecha;
import src.utils.observer.EventCreator;

public class Celula extends EventCreator implements ICelula{
    private Stack<IEntidadeDinamica> actors;
    private IEntidadeEstatica background;
    private boolean visivel = false;
    private int posX, posY;

    public Celula(int x, int y, IEntidadeEstatica background) {
        this.posX = x;
        this.posY = y;
        
        this.actors = new Stack<IEntidadeDinamica>();
        this.background = background;
    }

    
    public IEntidadeEstatica getBackground() {
    	return background;
    }
    
    public void setBackground(IEntidadeEstatica e) {
    	this.background = e;
    }

    public void pushEntidade(IEntidadeDinamica ent) {
        popEntidade();

        this.actors.push(ent);
        
        ent.setPosX(posX);
        ent.setPosY(posY);

        if (ent instanceof Flecha) ((Flecha) ent).addFlechas(new Random().nextInt(2) + 1);
        
        if (ent instanceof EntidadeViva)
            ((EntidadeViva) ent).interagirComEntidadeEstatica(getBackground());
        
        onUpdate();
    }

    public IEntidadeDinamica popEntidade() {
    	if(this.actors.empty())
    		return null;
    	
    	IEntidadeDinamica e = this.actors.pop();
        
        onUpdate();
        return e;
    }

    public IEntidadeDinamica peekEntidade() {
    	if(this.actors.empty())
    		return null;
    	return actors.peek();
    }
    
    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
        
        onUpdate();
    }
    
    public String[] estado() {
    	String[] res = new String[3];
    	
    	res[0] = getBackground() != null ? getBackground().toString() : "null";
    	res[1] = peekEntidade() != null ? peekEntidade().toString() : "null";
    	res[2] = isVisivel() ? "true" : "false";
    	
    	return res;
    }
    
    
    public void inativar() {
        if (peekEntidade() instanceof Inimigo)
            ((Inimigo) peekEntidade()).desalertar();
    	super.onUpdate(true);
    }

    public int distanciaAte(int x, int y) {
        return Math.abs(posX - x) + Math.abs(posY - y);
    }
}
