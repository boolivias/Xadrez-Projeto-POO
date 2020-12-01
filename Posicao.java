import Util.Constantes;

public class Posicao {
    private char coluna;
    private int linha;
    private char cor;

    public Posicao(int linha, int coluna, char cor) {
        this.coluna = (char) coluna;
        this.linha = linha;
        this.cor = cor;
    }

    public void imprimir() {
        System.out.print(this.cor == Constantes.COR_PRETO ? Constantes.BG_PRETO : Constantes.BG_BRANCO);

        System.out.print("   ");

        System.out.print(Constantes.BG_RESETA);
    }
}
