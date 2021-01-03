import java.util.Scanner;

import Util.Constantes;

public class Gerenciador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do jogador1: ");
        String n_jogador1 = sc.next();
        int op_cor;
        do {
            System.out.println("1 - Branco");
            System.out.println("2 - Preto");
            System.out.print("Escolha uma cor para o jogador1: ");
            op_cor = sc.nextInt();
        } while (op_cor != 1 && op_cor != 2);
        System.out.print("Digite o nome do jogador2: ");
        String n_jogador2 = sc.next();

        char cor_jogador1 = op_cor == 1 ? Constantes.COR_BRANCO : Constantes.COR_PRETO;
        char cor_jogador2 = op_cor == 1 ? Constantes.COR_PRETO : Constantes.COR_BRANCO;
        Jogo jogo = new Jogo(n_jogador1, n_jogador2, cor_jogador1, cor_jogador2);
        jogo.run();
    }

}
