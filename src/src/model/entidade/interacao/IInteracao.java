package src.model.entidade.interacao;

import src.model.entidade.dinamica.IEntidadeViva;

public interface IInteracao {
    public String interagir(IEntidadeViva e1, IEntidadeViva e2);
}