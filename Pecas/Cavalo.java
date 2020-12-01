package Pecas;

import Util.Constantes;

public class Cavalo {
    private char cor;
    private boolean ativo;

    public Cavalo(char cor) {
        this.ativo = true;
        this.cor = cor;
    }

    public boolean estaAtivo() {
        return this.ativo;
    }

    public void desativa() {
        this.ativo = false;
    }

    public char desenho() {
        return cor == Constantes.COR_PRETO ? Constantes.P_UNICODE_CAVALO : Constantes.B_UNICODE_CAVALO;
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = (int) (colunaOrigem) - (int) (colunaDestino);
        if (Math.abs(diffLinha) + Math.abs(diffColuna) == 3
                && Math.abs(Math.abs(diffLinha) - Math.abs(diffColuna)) == 1)
            return true;

        return false;
    }
}
