package src.model.entidade.itens;

public class Capacete extends Item {
    private int defesa;

    public Capacete(boolean coletado, boolean equipado, int defesa) {
        super(coletado, equipado);
        this.defesa = defesa;
    }

    public int getDefesa() {
        return defesa;
    }
}
