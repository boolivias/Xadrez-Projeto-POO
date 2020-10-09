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
        return cor == 'p' ? '\u265E' : '\u2658';
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        final int A_CODE = 68;
        int diffLinha = linhaOrigem - linhaDestino;
        int diffColuna = ((int) (colunaOrigem) - A_CODE) - ((int) (colunaDestino) - A_CODE);
        if (Math.abs(diffLinha) + Math.abs(diffColuna) == 3 && Math.abs(diffLinha) - Math.abs(diffColuna) == 1)
            return true;

        return false;
    }
}
