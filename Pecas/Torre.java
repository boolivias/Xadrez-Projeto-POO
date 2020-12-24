package Pecas;

/**Classe Torre
 * Classe responsável por definir as "particularidades" da peça Torre de Xadrez
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Util.Constantes;
import Util.HelperPadrao;

public class Torre extends Peca {
    public Torre(char cor) {
        super(cor);
    }

    public char desenho() {
        return HelperPadrao.ehBranco(cor) ? Constantes.B_UNICODE_TORRE : Constantes.P_UNICODE_TORRE;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = HelperPadrao.colunaCharToInt(colunaOrigem) - HelperPadrao.colunaCharToInt(colunaDestino);

        if ((diffLinha != 0 && diffColuna == 0) || (diffLinha == 0 && diffColuna != 0))
            return true;
        else
            return false;
    }
}
