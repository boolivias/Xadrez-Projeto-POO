
/**
 * Classe Jogo
 * Classe responsável por ter uma visão "geral" do jogo, realiza também as "conexões",
 * tudo que acontece no jogo deve partir dessa classe.
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import java.util.ArrayList;
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
        this.jogadores = new Jogador[2];
        jogadores[0] = new Jogador(nomeJogador1, HelperPadrao.padronizaCor(corJogador1),
                this.pecasCor(corJogador1, false));
        jogadores[1] = new Jogador(nomeJogador2, HelperPadrao.padronizaCor(corJogador2),
                this.pecasCor(corJogador2, false));
        this.vezJogador = HelperPadrao.ehBranco(corJogador1) ? 0 : 1;
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
            op = 0;
            this.verificaVitoria();
            System.out.println("Status: " + this.statusToString());
            System.out.println("Ação do jogador: " + this.vezJogador().getNome());
            if (this.status != Constantes.FIM) {
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
                if (origem.length() == 2) {
                    linhaOrigem = HelperPadrao.linhaCharToInt(origem.charAt(1));
                    colunaOrigem = origem.charAt(0);
                    pecaOrigem = this.tabuleiro.temPeca(linhaOrigem, colunaOrigem);
                }
                if (pecaOrigem == null) {
                    System.out.println("Não foi possível encontrar uma peça nessa posição, tente novmante.");
                    continue;
                }
                if (!HelperPadrao.corIgual(pecaOrigem.getCor(), this.vezJogador().getCor())) {
                    pecaOrigem = null;
                    System.out.println("Não é possível movimentar as peças do outro jogador, tente novamente.");
                }
            }

            do {
                System.out.println("Digite:");
                System.out.println(
                        "\t- A posição de destino (exemplo: D7)\n\t- \"0\" para escolher nova peça para mover");
                destino = sc.next();
                if (destino.length() != 2 && !destino.equals("0"))
                    System.out.println("Posição incorreta. Tente novamente.");
            } while (destino.length() == 2 || destino.equals("0"));
            if (destino.equals("0")) {
                pecaOrigem = null;
                continue;
            }

            linhaDestino = HelperPadrao.linhaCharToInt(destino.charAt(1));
            colunaDestino = destino.charAt(0);

            if (this.tabuleiro.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                this.tabuleiro.definePecaPosicao(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
                movimentou = true;
            } else
                System.out.println("Jogada incorreta! Tente novamente.");
        } while (!movimentou);
        this.atualizaStatus();
    }

    public Jogador vezJogador() {
        return this.jogadores[this.vezJogador];
    }

    public void alteraVezJogador() {
        this.vezJogador = this.vezJogador == 1 ? 0 : 1;
    }

    public void verificaVitoria() {
        char cor_jogador = this.vezJogador().getCor();
        if (this.status == Constantes.XEQUE_MATE_BRANCO || this.status == Constantes.XEQUE_MATE_PRETO
                || (this.status == Constantes.XEQUE_BRANCO && !HelperPadrao.ehBranco(cor_jogador))
                || (this.status == Constantes.XEQUE_PRETO && HelperPadrao.ehBranco(cor_jogador))) {

            if ((this.status == Constantes.XEQUE_MATE_BRANCO && !HelperPadrao.ehBranco(cor_jogador))
                    || (this.status == Constantes.XEQUE_MATE_PRETO && HelperPadrao.ehBranco(cor_jogador)))
                this.alteraVezJogador();

            this.status = Constantes.FIM;
            System.out.println("O jogador " + this.vezJogador().getNome() + " Venceu!");

            System.exit(0);
        }
    }

    public void atualizaStatus() {
        if (verificaXequeMate(Constantes.COR_BRANCO)) {
            this.status = Constantes.XEQUE_MATE_BRANCO;
            return;
        }

        if (verificaXequeMate(Constantes.COR_PRETO)) {
            this.status = Constantes.XEQUE_MATE_PRETO;
            return;
        }

        if (verificaXeque(Constantes.COR_BRANCO)) {
            this.status = Constantes.XEQUE_BRANCO;
            return;
        }

        if (verificaXeque(Constantes.COR_PRETO)) {
            this.status = Constantes.XEQUE_PRETO;
            return;
        }

        this.status = Constantes.ANDAMENTO;
    }

    /**
     * Retorna uma string de acordo com o status atual da partida.
     * 
     * @return
     */
    public String statusToString() {
        switch (this.status) {
            case Constantes.ANDAMENTO:
                return "Em andamento";

            case Constantes.FIM:
                return "Jogo encerrado";

            case Constantes.XEQUE_PRETO:
                return "Jogador preto em xeque";

            case Constantes.XEQUE_BRANCO:
                return "Jogador branco em xeque";

            case Constantes.XEQUE_MATE_PRETO:
                return "Jogador preto em xeque mate";

            case Constantes.XEQUE_MATE_BRANCO:
                return "Jogador branco em xeque mate";

            default:
                return "";
        }
    }

    /**
     * Verifica se o jogador da cor especificada está em xeque mate
     * 
     * @param cor
     * @return
     */
    private boolean verificaXequeMate(char cor) {
        Peca[] pecas_jogador = pecasCor(cor, false);
        Peca[] pecas_adversario = pecasCor(HelperPadrao.ehBranco(cor) ? Constantes.COR_PRETO : Constantes.COR_BRANCO,
                false);
        Peca rei = rei(cor);
        Posicao pos_rei = this.tabuleiro.posicaoPeca(rei);

        if (!this.tabuleiro.podeSerAtacada(pos_rei, pecas_adversario))
            return false;

        for (int i = pos_rei.getLinha() - 1; i <= pos_rei.getLinha() + 1; i++) {
            for (int j = HelperPadrao.colunaCharToInt(pos_rei.getColuna()) - 1; j <= HelperPadrao
                    .colunaCharToInt(pos_rei.getColuna()) + 1; j++) {
                if (this.tabuleiro.checaMovimento(pos_rei.getLinha(), pos_rei.getColuna(), i,
                        HelperPadrao.colunaIntToChar(j))) {
                    if (this.tabuleiro.podeSerAtacada(i, HelperPadrao.colunaIntToChar(j), pecas_adversario))
                        for (Peca p : pecas_jogador) {
                            if (!(p instanceof Rei)) {
                                Posicao pos_origem = this.tabuleiro.posicaoPeca(p);
                                if (this.tabuleiro.checaMovimento(pos_origem.getLinha(), pos_origem.getColuna(), i,
                                        HelperPadrao.colunaIntToChar(j)))
                                    return false;
                            }
                        }
                    else
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se o jogador da cor especificada está em xeque
     * 
     * @param cor
     * @return
     */
    private boolean verificaXeque(char cor) {
        Peca[] pecas = pecasCor(HelperPadrao.ehBranco(cor) ? Constantes.COR_PRETO : Constantes.COR_BRANCO, false);
        Peca rei = rei(cor);
        if (this.tabuleiro.podeSerAtacada(this.tabuleiro.posicaoPeca(rei), pecas))
            return true;

        return false;
    }

    /**
     * Retorna o rei da cor especificada
     * 
     * @param cor
     * @return
     */
    private Peca rei(char cor) {
        Peca[] pecas = pecasCor(cor, true);
        for (Peca peca : pecas) {
            if (peca instanceof Rei)
                return peca;
        }

        return null;
    }

    /**
     * Retorna um vetor com as peças da cor especificada
     * 
     * @param cor
     * @param desativadas <code>boolean</code> - Se <code>true</code> retorna as
     *                    peças desativadas também, se <code>false</code> retorna
     *                    somente as ativas
     * @return
     */
    private Peca[] pecasCor(char cor, boolean desativadas) {
        Peca[] pecas = HelperPadrao.ehBranco(cor) ? Arrays.copyOfRange(this.pecas, 0, 15)
                : Arrays.copyOfRange(this.pecas, 16, 32);
        if (desativadas)
            return pecas;

        ArrayList<Peca> pecasAtivas = new ArrayList();
        for (Peca p : pecas) {
            if (p.estaAtivo())
                pecasAtivas.add(p);
        }
        return pecasAtivas.toArray(new Peca[pecasAtivas.size()]);
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