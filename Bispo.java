public class Bispo {
    private char cor;
    private boolean ativo;

    public Bispo(char cor) {
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
        return cor == 'p' ? '\u265D' : '\u2657';
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        final int A_CODE = 68;
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = ((int) (colunaOrigem) - A_CODE) - ((int) (colunaDestino) - A_CODE);

        if (Math.abs(diffLinha) == Math.abs(diffColuna))
            return true;

        return false;
    }
}
