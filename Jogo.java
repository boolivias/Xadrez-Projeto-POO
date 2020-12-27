
/**
 * Classe Jogo
 * Classe responsável por ter uma visão "geral" do jogo, realiza também as "conexões",
 * tudo que acontece no jogo deve partir dessa classe.
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import java.util.Arrays;
import java.util.Scanner;
import Pecas.*;
import Util.Constantes;
import Util.HelperPadrao;

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
        jogadores[0] = new Jogador(nomeJogador1, HelperPadrao.padronizaCor(corJogador1),
                HelperPadrao.ehBranco(corJogador1) ? pBrancas : pPretas);
        jogadores[1] = new Jogador(nomeJogador2, HelperPadrao.padronizaCor(corJogador2),
                HelperPadrao.ehBranco(corJogador2) ? pBrancas : pPretas);
        this.vezJogador = 0;
        this.tabuleiro = new Tabuleiro();
        this.posicionaPecas();
        this.status = 0;
        this.run();
    }

    /** Começa a execução do jogo, lê as entradas dos jogadores e age de acordo. */
    public void run() {
        int op;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Ação do jogador: " + this.vezJogador().getNome());
            System.out.println("\t1 - Realizar uma jogada\n\t0 - Abandonar o Jogo");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    this.jogada();
                    this.alteraVezJogador();
                    break;

                default:
                    break;
            }
        } while (op != 0);
    }

    private void jogada() {
        Scanner sc = new Scanner(System.in);
        this.tabuleiro.imprimir();
        String origem = "00", destino = "00";
        int linhaOrigem = 0, linhaDestino = 0;
        char colunaOrigem = 'I', colunaDestino = 'I';
        boolean movimentou = false;
        Peca pecaOrigem = null;
        do {
            while (pecaOrigem == null) {
                System.out.print("Digite a posição da peça que quer mover (exemplo: D7): ");
                origem = sc.next();
                linhaOrigem = HelperPadrao.linhaCharToInt(origem.charAt(1));
                colunaOrigem = origem.charAt(0);
                pecaOrigem = this.tabuleiro.temPeca(linhaOrigem, colunaOrigem);
                if (pecaOrigem == null)
                    System.out.println("Não foi possível encontrar uma peça nessa posição, tente novmante.");
                if (!HelperPadrao.corIgual(pecaOrigem.getCor(), this.vezJogador().getCor())) {
                    pecaOrigem = null;
                    System.out.println("Não é possível movimentar as peças do outro jogador, tente novamente.");
                }
            }

            System.out.println("Digite:");
            System.out.println("\t- A posição de destino (exemplo: D7)\n\t- \"0\" para escolher nova peça para mover");
            destino = sc.next();
            if (destino.equals("0")) {
                pecaOrigem = null;
                continue;
            }

            linhaDestino = HelperPadrao.linhaCharToInt(destino.charAt(1));
            colunaDestino = destino.charAt(0);

            movimentou = this.tabuleiro.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
            if (!movimentou) {
                System.out.println("Jogada incorreta! Tente novamente.");
            }

        } while (!movimentou);
    }

    public Jogador vezJogador() {
        return this.jogadores[this.vezJogador];
    }

    public void alteraVezJogador() {
        this.vezJogador = this.vezJogador == 1 ? 0 : 1;
    }

    /**
     * Cria a instância das peças brancas e pretas, uma alteração neste método pode
     * quebrar o posicionamento inicial das peças
     * 
     * @return Peca
     */
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

    /**
     * Posiciona as peças na posição inicial do jogo
     */
    private void posicionaPecas() {
        int lin = 0;
        int indexPecas = 0;
        while (true) {
            for (int col = 0; col < 8; col++) {
                this.tabuleiro.definePecaPosicao(lin, HelperPadrao.colunaIntToChar(col), this.pecas[indexPecas++]);
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