package Util;

/**
 * Classe responsável por definir os valores padrões de cores, unicodes e ascii
 * 
 * @author Jean Wylmer Flores Mendoza
 */

public class Constantes {
    // ---------------> Código ascii dos números
    public final static int ZERO_ASCII = 48;

    // ---------------> Código ascii das letras
    public final static int A_ASCII = (int) 'A';

    // ---------------> Char de cor
    public final static char COR_PRETO = 'p';
    public final static char COR_BRANCO = 'b';

    // ---------------> Valores para mudar cor do fundo do terminal
    public final static String BG_RESETA = "\033[0m";
    public final static String BG_PRETO = "\033[44m";
    public final static String BG_BRANCO = "\u001B[47m";

    // ---------------> Unicode das peças
    public final static char B_UNICODE_BISPO = '\u265D';
    public final static char P_UNICODE_BISPO = '\u2657';

    public final static char B_UNICODE_CAVALO = '\u265E';
    public final static char P_UNICODE_CAVALO = '\u2658';

    public final static char B_UNICODE_DAMA = '\u265B';
    public final static char P_UNICODE_DAMA = '\u2655';

    public final static char B_UNICODE_PEAO = '\u265F';
    public final static char P_UNICODE_PEAO = '\u2659';

    public final static char B_UNICODE_REI = '\u265A';
    public final static char P_UNICODE_REI = '\u2654';

    public final static char B_UNICODE_TORRE = '\u265C';
    public final static char P_UNICODE_TORRE = '\u2656';
}
