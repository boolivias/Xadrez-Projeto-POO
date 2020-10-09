public class Posicao {
    private int indexPecaPresente;
    private char coluna;
    private int linha;
    private char cor;

    public Posicao(int coluna, int linha, char cor) {
        this.coluna = (char) coluna;
        this.linha = linha;
        this.cor = cor;
        this.indexPecaPresente = -1;
    }

    public char getcoluna() {
        return this.coluna;
    }

    public int getColuna() {
        return this.linha;
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
