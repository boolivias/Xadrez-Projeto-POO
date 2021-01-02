
/**Classe Tabuleiro
 * Classe responável por criar o "escopo" de um tabuleiro de xadrez,
 * tendo uma visão das posições do tabuleiro
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Pecas.Cavalo;
import Pecas.Peao;
import Pecas.Peca;
import Util.Constantes;
import Util.HelperPadrao;

public class Tabuleiro {
    private Posicao[][] posicao;

    public Tabuleiro() {
        this.posicao = new Posicao[8][8];

        char cor = Constantes.COR_BRANCO;
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                this.posicao[i][j] = new Posicao(i, HelperPadrao.colunaIntToChar(j), cor);
                cor = inverteCor(cor);
            }
            // O primeiro quadrado da linha é da mesma cor que o ultimo da linha anterior
            cor = inverteCor(cor);
        }
    }

    /**
     * Verifica se o movimento é válido e pode ser realizado
     * 
     * @return <code> boolean </code> - <b>true</b> Se movimento válido,
     *         <b>false</b> se inválido
     */
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        Posicao pos_origem = this.posicao[linhaOrigem][HelperPadrao.colunaCharToInt(colunaOrigem)];
        Posicao pos_destino = this.posicao[linhaDestino][HelperPadrao.colunaCharToInt(colunaDestino)];

        if (!this.dentroLimiteTabuleiro(pos_destino.getLinha(), pos_destino.getColuna()) || pos_origem == pos_destino)
            return false;

        Peca p = pos_origem.getPecaPresente();
        Peca p_destino = pos_destino.getPecaPresente();

        if (p_destino != null && HelperPadrao.corIgual(p.getCor(), p_destino.getCor()))
            return false;

        if (p instanceof Peao && p_destino != null)
            ((Peao) p).ativaMovimentoCaptura();

        if (!p.checaMovimento(pos_origem.getLinha(), pos_origem.getColuna(), pos_destino.getLinha(),
                pos_destino.getColuna())) {
            if (p instanceof Peao && p_destino != null)
                ((Peao) p).desativaMovimentoCaptura();
            return false;
        }

        if (!(p instanceof Cavalo)) { // Cavalo pula as peças no caminho
            Posicao pos_atual = pos_origem;
            do {
                pos_atual = this.proximaPosicaoCaminho(pos_atual, pos_destino, p);
                if (pos_atual.ehOcupada() && pos_atual != pos_destino)
                    return false;
            } while (pos_atual != pos_destino);
        }

        return true;
    }

    private Posicao proximaPosicaoCaminho(Posicao atual, Posicao dest, Peca p) {
        switch (p.getClass().getName()) {
            case "Pecas.Bispo":
                return this.proximaPosicaoDiagonal(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                        dest.getColuna());

            case "Pecas.Peao":
                return ((Peao) p).getMovimentoCaptura() && Math.abs(atual.getLinha() - dest.getLinha()) > 0
                        ? this.proximaPosicaoDiagonal(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                                dest.getColuna())
                        : this.proximaPosicaoVertical(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                                dest.getColuna());

            case "Pecas.Torre":
                if (atual.getColuna() == dest.getColuna())
                    return this.proximaPosicaoVertical(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                            dest.getColuna());
                else
                    return this.proximaPosicaoHorizontal(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                            dest.getColuna());

            case "Pecas.Rei":
            case "Pecas.Dama":
                if (atual.getColuna() == dest.getColuna())
                    return this.proximaPosicaoVertical(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                            dest.getColuna());
                else if (atual.getLinha() == dest.getLinha())
                    return this.proximaPosicaoHorizontal(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                            dest.getColuna());
                else
                    return this.proximaPosicaoDiagonal(atual.getLinha(), atual.getColuna(), dest.getLinha(),
                            dest.getColuna());

            default:
                // ----------------> LANÇAR EXCEPTION
                return null;
        }
    }

    private Posicao proximaPosicaoDiagonal(int linhaAtual, char colunaAtual, int linhaDestino, char colunaDestino) {
        linhaAtual = linhaAtual > linhaDestino ? --linhaAtual : ++linhaAtual;
        colunaAtual = colunaAtual > colunaDestino ? --colunaAtual : ++colunaAtual;

        return this.posicao[linhaAtual][HelperPadrao.colunaCharToInt(colunaAtual)];
    }

    private Posicao proximaPosicaoVertical(int linhaAtual, char colunaAtual, int linhaDestino, char colunaDestino) {
        return linhaAtual < linhaDestino ? this.posicao[linhaAtual + 1][HelperPadrao.colunaCharToInt(colunaAtual)]
                : this.posicao[linhaAtual - 1][HelperPadrao.colunaCharToInt(colunaAtual)];
    }

    private Posicao proximaPosicaoHorizontal(int linhaAtual, char colunaAtual, int linhaDestino, char colunaDestino) {
        return colunaAtual > colunaDestino
                ? this.posicao[linhaAtual][HelperPadrao.colunaCharToInt((char) (colunaAtual - 1))]
                : this.posicao[linhaAtual][HelperPadrao.colunaCharToInt((char) (colunaAtual + 1))];
    }

    private boolean dentroLimiteTabuleiro(int linha, char coluna) {
        return (HelperPadrao.padronizaCharColuna(coluna) >= HelperPadrao.padronizaCharColuna('A')
                && HelperPadrao.padronizaCharColuna(coluna) <= HelperPadrao.padronizaCharColuna('H') && linha >= 0
                && linha <= 7);
    }

    public Posicao posicaoPeca(Peca p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.posicao[i][j].ehOcupada() && this.posicao[i][j].getPecaPresente() == p)
                    return this.posicao[i][j];
            }
        }

        return null;
    }

    /**
     * Desocupa a posição
     * 
     * @param linhaDestino  <code>int</code> - Linha da posição a ser desocupada
     * @param colunaDestino <code>int</code> - Coluna da posição a ser desocupada
     */
    public void definePecaPosicao(int linhaDestino, char colunaDestino) {
        this.posicao[linhaDestino][HelperPadrao.colunaCharToInt(colunaDestino)].desocupa();
    }

    /**
     * Ocupa a posição com a peça passada por parâmetro, se houver outra peça na
     * posição de destino, ela fica inativa
     * 
     * @param linhaDestino  <code>int</code> - Linha da posição a ser ocupada
     * @param colunaDestino <code>char</code> - Coluna da posição a ser ocupada
     * @param p             <code>Peca</code> - Peça a ocupar a posição
     */
    public void definePecaPosicao(int linhaDestino, char colunaDestino, Peca p) {
        Posicao pos = this.posicao[linhaDestino][HelperPadrao.colunaCharToInt(colunaDestino)];
        if (pos.ehOcupada()) {
            pos.getPecaPresente().desativa();
            pos.desocupa();
        }

        pos.ocupa(p);
    }

    /**
     * Movimenta a peça da posição especificada, se houver peça no destino, ela é
     * capturada
     * 
     * @param linhaOrigem   <code>int</code> - Linha da posição onde está a peça
     * @param colunaOrigem  <code>char</code> - Coluna da posição onde está a peça
     * @param linhaDestino  <code>int</code> - Linha da posição destino da peça
     * @param colunaDestino <code>char</code> - Coluna da posição destino da peça
     */
    public void definePecaPosicao(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        Peca p = this.posicao[linhaOrigem][HelperPadrao.colunaCharToInt(colunaOrigem)].getPecaPresente();
        this.definePecaPosicao(linhaOrigem, colunaOrigem);
        this.definePecaPosicao(linhaDestino, colunaDestino, p);

        if (p instanceof Peao)
            ((Peao) p).moveu();
    }

    /**
     * Verifica se tem peça na posição passada por parâmetro e retorna a peça
     * 
     * @return <code>Peca</code> - Peça presente / <code>null</code> Se não tiver
     *         peça
     */
    public Peca temPeca(int linha, char coluna) {
        return (this.dentroLimiteTabuleiro(linha, coluna)
                && posicao[linha][HelperPadrao.colunaCharToInt(coluna)].ehOcupada())
                        ? posicao[linha][HelperPadrao.colunaCharToInt(coluna)].getPecaPresente()
                        : null;
    }

    public void imprimir() {
        // Impressão superior dos indices das colunas
        System.out.print("\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + HelperPadrao.colunaIntToChar(i) + " ");
        }
        System.out.print("\n");

        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + "\t"); // Impressão dos indices das linhas no lado esquerdo
            for (int j = 0; j < 8; j++) {
                this.posicao[i][j].imprimir();
            }
            System.out.println("\t" + (i + 1)); // Impressão dos indices das linhas no lado direito
        }

        // Impressão inferior dos indices das colunas
        System.out.print("\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + HelperPadrao.colunaIntToChar(i) + " ");
        }
        System.out.print("\n\n");
    }

    private char inverteCor(char cor) {
        return HelperPadrao.ehBranco(cor) ? Constantes.COR_PRETO : Constantes.COR_BRANCO;
    }
}