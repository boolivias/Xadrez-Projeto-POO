public class Peao {
    private char cor;
    private boolean ativo;
    private boolean primeiroMovimento;

    public Peao(char cor) {
        this.primeiroMovimento = true;
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
        if (diffColuna == 0) {
            if (diffLinha == 2 && this.primeiroMovimento)
                return true;
            if ((diffLinha == 1 && cor == 'b') || (diffLinha == -1 && cor == 'p'))
                return true;
        }
        return false;
    }
}
