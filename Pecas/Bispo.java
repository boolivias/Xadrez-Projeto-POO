package Pecas;

import Util.Constantes;
import Util.HelperPadrao;

public class Bispo extends Peca {
    public Bispo(char cor) {
        super(cor);
    }

    public char desenho() {
        return HelperPadrao.ehBranco(cor) ? Constantes.B_UNICODE_BISPO : Constantes.P_UNICODE_BISPO;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = HelperPadrao.colunaCharToInt(colunaOrigem) - HelperPadrao.colunaCharToInt(colunaDestino);

        if (Math.abs(diffLinha) == Math.abs(diffColuna) && diffColuna != 0)
            return true;

        return false;
    }
}
