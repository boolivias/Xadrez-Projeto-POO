import Pecas.Peca;
import Util.Constantes;

public class Tabuleiro {
    private Posicao[][] posicao;

    public Tabuleiro() {
        this.posicao = new Posicao[8][8];

        char cor = Constantes.COR_BRANCO;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.posicao[i][j] = new Posicao(i, Constantes.A_ASCII + j, cor);
                cor = inverteCor(cor);
            }
            // O primeiro quadrado da linha é da mesma cor que o ultimo da linha anterior
            cor = inverteCor(cor);
        }
    }

    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (colunaDestino <= 'A' || colunaDestino >= 'H' || linhaDestino <= 1 || linhaDestino >= 8)
            return false;

        Peca p = this.posicao[linhaOrigem][colunaOrigem].getPecaPresente();

        if (!p.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino))
            return false;

        //////////////////////////// VERIFICAR SE NÃO HÁ PEÇAS NO CAMINHO

        return true;
    }

    public void definePecaPosicao(int linhaDestino, char colunaDestino, Peca p) {
        this.definePecaPosicao(linhaDestino, Constantes.A_ASCII - Character.toUpperCase(colunaDestino), p);
    }

    public void definePecaPosicao(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino, Peca p) {
        this.definePecaPosicao(linhaOrigem, Constantes.A_ASCII - Character.toUpperCase(colunaOrigem), linhaDestino,
                Constantes.A_ASCII - Character.toUpperCase(colunaDestino), p);
    }

    public void definePecaPosicao(int linhaDestino, int colunaDestino, Peca p) {
        this.posicao[linhaDestino][colunaDestino].ocupa(p);
    }

    public void definePecaPosicao(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Peca p) {
        this.definePecaPosicao(linhaDestino, colunaDestino, p);
        this.posicao[linhaOrigem][colunaOrigem].desocupa();
    }

    public void imprimir() {
        // Impressão superior dos indices das colunas
        System.out.print("\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + (char) (i + Constantes.A_ASCII) + " ");
        }
        System.out.print("\n");

        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + "\t"); // Impressão dos indices das linhas no lado esquerdo
            for (int j = 0; j < 8; j++) {
                this.posicao[i][j].imprimir();
            }
            System.out.println("\t" + (i + 1)); // Impressão dos indices das linhas no lado direito
        }

        // Impressão inferior dos indices das colunas
        System.out.print("\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + (char) (i + Constantes.A_ASCII) + " ");
        }
        System.out.print("\n\n");
    }

    private char inverteCor(char cor) {
        return cor == Constantes.COR_BRANCO ? Constantes.COR_PRETO : Constantes.COR_BRANCO;
    }
}