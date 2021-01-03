package Util;

/**
 * Classe responsável por definir os valores padrões de cores, unicodes e ascii
 * 
 * @author Jean Wylmer Flores Mendoza
 */

public class Constantes {
    // ---------------> Status do jogo
    public final static int ANDAMENTO = 0;
    public final static int FIM = 1;
    public final static int XEQUE_PRETO = 2;
    public final static int XEQUE_BRANCO = 3;
    public final static int XEQUE_MATE_PRETO = 4;
    public final static int XEQUE_MATE_BRANCO = 5;

    // ---------------> Código ascii dos números
    public final static int ZERO_ASCII = 48;

    // ---------------> Código ascii das letras
    public final static int A_ASCII = (int) 'A';

    // ---------------> Char de cor
    public final static char COR_PRETO = 'p';
    public final static char COR_BRANCO = 'b';

    // ---------------> Valores para mudar cor do fundo do terminal
    public final static String BG_RESETA = "\033[0m";
    public final static String BG_PRETO = "\033[48;5;94m";
    public final static String BG_BRANCO = "\033[48;5;221m";

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
