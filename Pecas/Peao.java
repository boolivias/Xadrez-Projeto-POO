package Pecas;

/**Classe Peao
 * Classe responsável por definir as "particularidades" da peça Peao de Xadrez
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Util.Constantes;
import Util.HelperPadrao;

public class Peao extends Peca {
    private boolean primeiroMovimento;

    public Peao(char cor) {
        super(cor);
        this.primeiroMovimento = true;
    }

    public void moveu() {
        this.primeiroMovimento = false;
    }

    public char desenho() {
        return HelperPadrao.ehBranco(cor) ? Constantes.B_UNICODE_PEAO : Constantes.P_UNICODE_PEAO;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = HelperPadrao.colunaCharToInt(colunaOrigem) - HelperPadrao.colunaCharToInt(colunaDestino);
        if (diffColuna == 0) {
            if (((diffLinha == -2 && HelperPadrao.ehBranco(cor)) || (diffLinha == 2 && !HelperPadrao.ehBranco(cor)))
                    && this.primeiroMovimento) {
                return true;
            }
            if ((diffLinha == 1 && HelperPadrao.ehBranco(cor)) || (diffLinha == -1 && !HelperPadrao.ehBranco(cor))) {
                return true;
            }
        }
        return false;
    }
}
