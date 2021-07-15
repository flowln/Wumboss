package src.model.entidade.dinamica;

import src.model.IGameModel;
import src.utils.Priority;

public class Wumboss extends Inimigo {

    private Wumboss(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        this.cooldownMovimento = 2;
    }
    
    public Wumboss() {
    	this(20, 8, 4);
    }

	@Override
	public String toString() {
		return "wumboss";
	}
	
	public void revidar(IEntidadeViva agressor) {
		interagir(agressor);
	}
	
	@Override
	public void morrer() {
		super.morrer();
		
		IGameModel.sendFeedToView("Ò grande guerreiro,\n"
				+ "conquistador desta caverna.\n"
				+ "Voce agora esta livre!\n"
				+ "O que uma vez te pareceu uma\n"
				+ "ideia distante, nada mais que um sonho,\n"
				+ "agora se torna realidade.\n"
				+ "Vá, reencontre teus amigos, tua familia,\n"
				+ "e adote um gatinho fofo! ☻ ", Priority.HIGH);
	}
}
