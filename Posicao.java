public class Posicao {
    private int indexPecaPresente;
    private char linha;
    private int coluna;
    private char cor;

    public Posicao(int linha, int coluna, char cor) {
        this.linha = (char) linha;
        this.coluna = coluna;
        this.cor = cor;
        this.indexPecaPresente = -1;
    }

    public char getLinha() {
        return this.linha;
    }

    public int getColuna() {
        return this.coluna;
    }

    public char getCor() {
        return this.cor;
    }

    public void ocupa(int indexPeca) {
        this.indexPecaPresente = indexPeca;
    }

    public void desocupa() {
        this.indexPecaPresente = -1;
    }

    public boolean estaOcupada() {
        return this.indexPecaPresente != -1;
    }
}
