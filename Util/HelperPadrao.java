package Util;

import Util.Constantes;

public class HelperPadrao {
    public static char padronizaCharColuna(char col) {
        return Character.toUpperCase(col);
    }

    public static char colunaIntToChar(int col) {
        return padronizaCharColuna((char) (col + Constantes.A_ASCII));
    }

    public static int colunaCharToInt(char col) {
        return Constantes.A_ASCII - padronizaCharColuna(col);
    }

    public static char padronizaCor(char cor) {
        if (cor == 'p' || cor == 'P')
            return Constantes.COR_PRETO;
        else
            return Constantes.COR_BRANCO;
    }

    public static boolean ehBranco(char cor) {
        return Character.toLowerCase(cor) == Constantes.COR_BRANCO;
    }
}
