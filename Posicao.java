
/**Classe Posição
 * Classe responsável por "simular" uma posição do tabuleiro de xadrez
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Pecas.Peca;
import Util.Constantes;
import Util.HelperPadrao;

public class Posicao {
    private char coluna;
    private int linha;
    private char cor;
    private Peca pecaPresente;

    public Posicao(int linha, int coluna, char cor) {
        this._constructor(linha, coluna, cor, null);
    }

    public Posicao(int linha, int coluna, char cor, Peca peca) {
        this._constructor(linha, coluna, cor, peca);
    }

    public int getLinha() {
        return this.linha;
    }

    public char getColuna() {
        return this.coluna;
    }

    public Peca getPecaPresente() {
        return this.pecaPresente;
    }

    public void ocupa(Peca p) {
        this.pecaPresente = p;
    }

    public void desocupa() {
        this.pecaPresente = null;
    }

    public boolean ehOcupada() {
        return this.pecaPresente != null;
    }

    /**
     * Método chamado pelos construtores para definir os valores do atributo
     * 
     * @param linha  <code>int</code> - Linha da posição
     * @param coluna <code>int</code> - Coluna da posição
     * @param cor    <code>char</code> - Cor da posição
     * @param peca   <code>peca</code> - Peça presente na posição
     */
    private void _constructor(int linha, int coluna, char cor, Peca peca) {
        this.coluna = (char) coluna;
        this.linha = linha;
        this.cor = HelperPadrao.padronizaCor(cor);
        this.pecaPresente = peca;
    }

    public void imprimir() {
        System.out.print(HelperPadrao.ehBranco(this.cor) ? Constantes.BG_BRANCO : Constantes.BG_PRETO);

        System.out.print(" ");
        System.out.print(pecaPresente != null ? pecaPresente.desenho() : " ");
        System.out.print(" ");

        System.out.print(Constantes.BG_RESETA);
    }
}
