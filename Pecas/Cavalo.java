package Pecas;

import Util.Constantes;
import Util.HelperPadrao;

public class Cavalo extends Peca {
    public Cavalo(char cor) {
        super(cor);
    }

    public char desenho() {
        return HelperPadrao.ehBranco(cor) ? Constantes.B_UNICODE_CAVALO : Constantes.P_UNICODE_CAVALO;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = HelperPadrao.colunaCharToInt(colunaOrigem) - HelperPadrao.colunaCharToInt(colunaDestino);
        if (Math.abs(diffLinha) + Math.abs(diffColuna) == 3
                && Math.abs(Math.abs(diffLinha) - Math.abs(diffColuna)) == 1)
            return true;

        return false;
    }
}
