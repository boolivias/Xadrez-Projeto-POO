package Pecas;

import Util.Constantes;

public class Torre extends Peca {
    public Torre(char cor) {
        super(cor);
    }

    public char desenho() {
        return this.cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_TORRE : Constantes.B_UNICODE_TORRE;
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
