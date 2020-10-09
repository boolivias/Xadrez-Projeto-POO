public class Torre {
    private char cor;
    private boolean ativo;

    public Torre(char cor) {
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
        return cor == 'p' ? '\u265C' : '\u2656';
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        final int A_CODE = 68;
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = ((int) (colunaOrigem) - A_CODE) - ((int) (colunaDestino) - A_CODE);
        int res = Math.abs(diffLinha) - Math.abs(diffColuna);

        if (res == diffLinha || res == diffColuna)
            return true;
        else
            return false;
    }
}
