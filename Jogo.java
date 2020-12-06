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
        jogadores[0] = new Jogador(nomeJogador1, corJogador1,
                corJogador1 == Constantes.COR_BRANCO ? pBrancas : pPretas);
        jogadores[1] = new Jogador(nomeJogador2, corJogador2,
                corJogador2 == Constantes.COR_BRANCO ? pBrancas : pPretas);
        this.vezJogador = 0;
        this.tabuleiro = new Tabuleiro();
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
            i *= 8;

            for (int j = 0; j < 8; j++) {
                peca[j + i] = new Peao(cor);
            }

            for (int j = 0; j < 2; j++) {
                // Pulo entre as peças que se repetem
                j *= 3;

                peca[(8 + j) + i] = new Torre(cor);
                peca[(9 + j) + i] = new Cavalo(cor);
                peca[(10 + j) + i] = new Bispo(cor);
            }

            peca[14 + i] = new Dama(cor);
            peca[15 + i] = new Rei(cor);

            cor = Constantes.COR_PRETO;
        }
        return peca;
    }
}