import java.util.Arrays;
import Pecas.*;
import Util.Constantes;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador[] jogadores;
    private Peca[] pecas;
    private int vezJogador;
    private int status;

    public Jogo(String nomeJogador1, String nomeJogador2, char corJogador1, char corJogador2) {
        this.pecas = criaPecas();
        Peca[] pBrancas = Arrays.copyOfRange(this.pecas, 0, 15);
        Peca[] pPretas = Arrays.copyOfRange(this.pecas, 16, 32);
        this.jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nomeJogador1, corJogador1,
                corJogador1 == Constantes.COR_BRANCO ? pBrancas : pPretas);
        jogadores[1] = new Jogador(nomeJogador2, corJogador2,
                corJogador2 == Constantes.COR_BRANCO ? pBrancas : pPretas);
        this.vezJogador = 0;
        this.tabuleiro = new Tabuleiro();
        posicionaPecas();
        this.status = 0;
    }

    public String vezJogador() {
        return this.jogadores[this.vezJogador].getNome();
    }

    public void alteraVezJogador() {
        this.vezJogador = this.vezJogador == 1 ? 0 : 1;
    }

    private Peca[] criaPecas() {
        Peca[] peca = new Peca[32];
        char cor = Constantes.COR_BRANCO;

        // "i" é usado para determinar "pulo" no indice do vetor
        for (int i = 0; i < 2; i++) {
            // Pulo entre as pecas pretas e brancas
            i *= 16;

            peca[0 + i] = new Torre(cor);
            peca[1 + i] = new Cavalo(cor);
            peca[2 + i] = new Bispo(cor);

            peca[3 + i] = new Rei(cor);
            peca[4 + i] = new Dama(cor);

            peca[5 + i] = new Bispo(cor);
            peca[6 + i] = new Cavalo(cor);
            peca[7 + i] = new Torre(cor);

            for (int j = 8; j < 16; j++) {
                peca[j + i] = new Peao(cor);
            }

            cor = Constantes.COR_PRETO;
        }
        return peca;
    }

    private void posicionaPecas() {
        int lin = 0;
        int indexPecas = 0;
        while (true) {
            for (int col = 0; col < 8; col++) {
                this.tabuleiro.definePecaPosicao(lin, col, this.pecas[indexPecas++]);
            }

            if (lin == 0)
                lin++;
            else if (lin == 1)
                lin = 7;
            else if (lin == 7)
                lin--;
            else
                return;
        }
    }
}