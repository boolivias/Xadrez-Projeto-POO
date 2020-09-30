public class Tabuleiro {
    private Posicao[][] posicao;

    public Tabuleiro() {
        this.posicao = new Posicao[8][8];

        final int A_CODE = 68;
        char cor = 'b';
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.posicao[i][j] = new Posicao(A_CODE + i, j, cor);
                cor = inverteCor(cor);
            }
            // O primeiro quadrado da linha é da mesma cor que o ultimo da linha anterior
            cor = inverteCor(cor);
        }
    }

    private char inverteCor(char cor) {
        if (cor == 'b')
            return 'p';
        else
            return 'b';
    }
}