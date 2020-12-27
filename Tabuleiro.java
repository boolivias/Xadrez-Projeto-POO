
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
        if (!this.dentroLimiteTabuleiro(linhaDestino, colunaDestino))
            return false;

        Peca p = this.posicao[linhaOrigem][HelperPadrao.colunaCharToInt(colunaOrigem)].getPecaPresente();

        if (!p.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino))
            return false;

        //////////////////////////// VERIFICAR SE NÃO HÁ PEÇAS NO CAMINHO

        this.definePecaPosicao(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
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
     * Verifica se na posição especificada possui alguma peça
     * 
     * @return <code>boolean</code> - <code>true</code> se possui /
     *         <code>false</code> se vazia.
     */
    public boolean temPeca(int linha, char coluna) {
        return this.dentroLimiteTabuleiro(linha, coluna)
                && posicao[linha][HelperPadrao.colunaCharToInt(coluna)].ehOcupada();
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