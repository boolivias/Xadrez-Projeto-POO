package Util;

/**Classe responsável por padronizar e auxiliar as comparações e conversões
 * @author Jean Wylmer Flores Mendoza
 */

import Util.Constantes;

public class HelperPadrao {
    public static char padronizaCharColuna(char col) {
        char coluna = Character.toUpperCase(col);
        if (coluna < 'A' || coluna > 'H')
            throw new Error("Coluna inválida");
        return coluna;
    }

    public static char colunaIntToChar(int col) {
        return padronizaCharColuna((char) (col + Constantes.A_ASCII));
    }

    public static int colunaCharToInt(char col) {
        return padronizaCharColuna(col) - Constantes.A_ASCII;
    }

    public static int linhaCharToInt(char lin) {
        int linha = ((int) lin) - Constantes.ZERO_ASCII - 1;
        if (linha < 0 || linha > 7)
            throw new Error("Linha inválida");
        return linha;
    }

    public static char padronizaCor(char cor) {
        if (Character.toUpperCase(cor) == 'P')
            return Constantes.COR_PRETO;
        if (Character.toUpperCase(cor) == 'B')
            return Constantes.COR_BRANCO;

        throw new Error("Cor inválida");
    }

    public static boolean ehBranco(char cor) {
        return padronizaCor(cor) == Constantes.COR_BRANCO;
    }

    public static boolean corIgual(char cor1, char cor2) {
        return padronizaCor(cor1) == padronizaCor(cor2);
    }
}
