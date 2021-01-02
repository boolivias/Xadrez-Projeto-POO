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
    private boolean movimentoCaptura;

    public Peao(char cor) {
        super(cor);
        this.primeiroMovimento = true;
        this.movimentoCaptura = false;
    }

    public void moveu() {
        this.primeiroMovimento = false;
        this.movimentoCaptura = false;
    }

    public char desenho() {
        return HelperPadrao.ehBranco(cor) ? Constantes.B_UNICODE_PEAO : Constantes.P_UNICODE_PEAO;
    }

    public boolean getMovimentoCaptura() {
        return this.movimentoCaptura;
    }

    public void ativaMovimentoCaptura() {
        this.movimentoCaptura = true;
    }

    public void desativaMovimentoCaptura() {
        this.movimentoCaptura = false;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = HelperPadrao.colunaCharToInt(colunaOrigem) - HelperPadrao.colunaCharToInt(colunaDestino);
        if (diffColuna == 0
                && ((diffLinha == -2 && HelperPadrao.ehBranco(cor)) || (diffLinha == 2 && !HelperPadrao.ehBranco(cor)))
                && this.primeiroMovimento) {
            return true;
        }
        if ((diffColuna == 0 || (movimentoCaptura && Math.abs(diffColuna) == 1))
                && ((diffLinha == -1 && HelperPadrao.ehBranco(cor))
                        || (diffLinha == 1 && !HelperPadrao.ehBranco(cor)))) {
            return true;
        }
        return false;
    }
}
