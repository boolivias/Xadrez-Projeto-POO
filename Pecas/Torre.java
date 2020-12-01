package Pecas;

import Util.Constantes;

public class Torre {
    private char cor;
    private boolean ativo;

    public Torre(char cor) {
        this.ativo = true;
        this.cor = cor;
    }

    public boolean estaAtivo() {
        return this.ativo;
    }

    public void desativa() {
        this.ativo = false;
    }

    public char desenho() {
        return cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_TORRE : Constantes.B_UNICODE_TORRE;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = (int) colunaOrigem - (int) colunaDestino;

        if ((diffLinha != 0 && diffColuna == 0) || (diffLinha == 0 && diffColuna != 0))
            return true;
        else
            return false;
    }
}
