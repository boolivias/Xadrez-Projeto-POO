public class Dama {
    private char cor;
    private boolean ativo;

    public Dama(char cor) {
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
        return cor == 'p' ? '\u265F' : '\u2659';
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        final int A_CODE = 68;
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = ((int) (colunaOrigem) - A_CODE) - ((int) (colunaDestino) - A_CODE);
        int res = Math.abs(diffLinha) - Math.abs(diffColuna);
        // Verifica se o movimento foi na diagonal ou
        // foi uma reta na horizontal ou vertical
        if (res == 0 || res == diffLinha || res == diffColuna)
            return true;

        return false;
    }
}
