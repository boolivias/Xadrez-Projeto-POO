package Pecas;

import Util.Constantes;

public class Dama extends Peca {
    public Dama(char cor) {
        super(cor);
    }

    public char desenho() {
        return this.cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_DAMA : Constantes.B_UNICODE_DAMA;
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
