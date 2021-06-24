package src.model.entidade.dinamica;

public abstract class Inimigo extends EntidadeDinamica {
    
    protected boolean emAlerta;

    public Inimigo (int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        emAlerta = false;
    }

    public boolean emAlerta() {
        return emAlerta;
    }

    public void alertar() {
        emAlerta = true;
    }
}