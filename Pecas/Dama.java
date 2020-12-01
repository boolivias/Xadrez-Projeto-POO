package Pecas;

import Util.Constantes;

public class Dama {
    private char cor;
    private boolean ativo;

    public Dama(char cor) {
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
        return cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_DAMA : Constantes.B_UNICODE_DAMA;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = (int) (colunaOrigem) - (int) (colunaDestino);
        int res = Math.abs(Math.abs(diffLinha) - Math.abs(diffColuna));

        // Verifica se o movimento foi na diagonal ou
        // foi uma reta na horizontal ou vertical
        if ((res == 0 && diffLinha != 0) || (res == Math.abs(diffLinha) && diffColuna == 0)
                || (res == Math.abs(diffColuna) && diffLinha == 0))
            return true;

        return false;
    }
}
