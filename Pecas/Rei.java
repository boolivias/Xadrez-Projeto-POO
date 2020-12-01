package Pecas;

import Util.Constantes;

public class Rei extends Peca {
    public Rei(char cor) {
        super(cor);
    }

    public char desenho() {
        return this.cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_REI : Constantes.B_UNICODE_REI;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = (int) (colunaOrigem) - (int) (colunaDestino);
        if (Math.abs(diffColuna) <= 1 && Math.abs(diffLinha) <= 1 && (diffLinha != 0 || diffColuna != 0)) {
            return true;
        }

        return false;
    }
}
