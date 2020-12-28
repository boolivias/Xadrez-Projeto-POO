
/**Classe Tabuleiro
 * Classe responável por criar o "escopo" de um tabuleiro de xadrez,
 * tendo uma visão das posições do tabuleiro
 * 
 * @author Jean Wylmer Flores Mendoza
 */

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

        if (!this.dentroLimiteTabuleiro(pos_destino.getLinha(), pos_destino.getColuna()))
            return false;

        Peca p = pos_origem.getPecaPresente();
        Peca p_destino = pos_destino.getPecaPresente();

        if (p_destino != null && HelperPadrao.corIgual(p.getCor(), p_destino.getCor()))
            return false;

        if (!p.checaMovimento(pos_origem.getLinha(), pos_origem.getColuna(), pos_destino.getLinha(),
                pos_destino.getColuna()))
            return false;

        this.definePecaPosicao(pos_origem.getLinha(), pos_origem.getColuna(), pos_destino.getLinha(),
                pos_destino.getColuna());

        return true;
    }

    private boolean dentroLimiteTabuleiro(int linha, char coluna) {
        return (HelperPadrao.padronizaCharColuna(coluna) >= HelperPadrao.padronizaCharColuna('A')
                && HelperPadrao.padronizaCharColuna(coluna) <= HelperPadrao.padronizaCharColuna('H') && linha >= 0
                && linha <= 7);
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
     * Ocupa a posição com a peça passada por parâmetro
     * 
     * @param linhaDestino  <code>int</code> - Linha da posição a ser ocupada
     * @param colunaDestino <code>char</code> - Coluna da posição a ser ocupada
     * @param p             <code>Peca</code> - Peça a ocupar a posição
     */
    public void definePecaPosicao(int linhaDestino, char colunaDestino, Peca p) {
        this.posicao[linhaDestino][HelperPadrao.colunaCharToInt(colunaDestino)].ocupa(p);
    }

    /**
     * Movimenta a peça da posição especificada
     * 
     * @param linhaOrigem   <code>int</code> - Linha da posição onde está a peça
     * @param colunaOrigem  <code>char</code> - Coluna da posição onde está a peça
     * @param linhaDestino  <code>int</code> - Linha da posição destino da peça
     * @param colunaDestino <code>char</code> - Coluna da posição destino da peça
     */
    public void definePecaPosicao(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        this.definePecaPosicao(linhaDestino, colunaDestino,
                this.posicao[linhaOrigem][HelperPadrao.colunaCharToInt(colunaOrigem)].getPecaPresente());
        this.definePecaPosicao(linhaOrigem, colunaOrigem);
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