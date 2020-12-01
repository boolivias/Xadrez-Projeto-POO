package Pecas;

import Util.Constantes;

public class Bispo extends Peca {
    public Bispo(char cor) {
        super(cor);
    }

    public char desenho() {
        return cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_BISPO : Constantes.B_UNICODE_BISPO;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = (int) (colunaOrigem) - (int) (colunaDestino);

        if (Math.abs(diffLinha) == Math.abs(diffColuna) && diffColuna != 0)
            return true;

        return false;
    }
}
