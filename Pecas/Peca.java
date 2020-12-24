package Pecas;

/**Classe Peça
 * Classe responsável por definir o "básico" e o comum das peças de Xadrez
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Util.HelperPadrao;

public abstract class Peca {
    protected char cor;
    protected boolean ativo;

    public Peca(char cor) {
        this.ativo = true;
        this.cor = HelperPadrao.padronizaCor(cor);
    }

    public boolean estaAtivo() {
        return this.ativo;
    }

    public void desativa() {
        this.ativo = false;
    }

    abstract public char desenho();

    abstract public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);
}
