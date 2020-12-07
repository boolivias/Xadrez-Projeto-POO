package Pecas;

import Util.Constantes;
import Util.HelperPadrao;

public class Rei extends Peca {
    public Rei(char cor) {
        super(cor);
    }

    public char desenho() {
        return HelperPadrao.ehBranco(cor) ? Constantes.B_UNICODE_REI : Constantes.P_UNICODE_REI;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = HelperPadrao.colunaCharToInt(colunaOrigem) - HelperPadrao.colunaCharToInt(colunaDestino);
        if (Math.abs(diffColuna) <= 1 && Math.abs(diffLinha) <= 1 && (diffLinha != 0 || diffColuna != 0)) {
            return true;
        }

        return false;
    }
}
