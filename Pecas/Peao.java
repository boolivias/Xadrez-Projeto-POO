package Pecas;

import Util.Constantes;

public class Peao extends Peca {
    private boolean primeiroMovimento;

    public Peao(char cor) {
        super(cor);
        this.primeiroMovimento = true;
    }

    public char desenho() {
        return this.cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_PEAO : Constantes.B_UNICODE_PEAO;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = (int) (colunaOrigem) - (int) (colunaDestino);
        if (diffColuna == 0) {
            if (((diffLinha == -2 && cor == Constantes.COR_BRANCO) || (diffLinha == 2 && cor == Constantes.COR_PRETO))
                    && this.primeiroMovimento) {
                this.primeiroMovimento = false;
                return true;
            }
            if ((diffLinha == 1 && cor == Constantes.COR_BRANCO) || (diffLinha == -1 && cor == Constantes.COR_PRETO)) {
                this.primeiroMovimento = false;
                return true;
            }
        }
        return false;
    }
}
