
/**
 * Classe Jogador
 * Classe responsável por realizar todas as ações e armazenar as informações do jogador
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Pecas.Peca;
import Util.HelperPadrao;

public class Jogador {
    private String nome;
    private char cor;
    private Peca pecas[];

    public Jogador(String nome, char cor, Peca p[]) {
        if (nome.length() <= 0)
            throw new IllegalArgumentException("O nome do jogador deve ser válido.");
        if (p.length == 16)
            throw new IllegalArgumentException("O jogador deve ter 16 peças");
        this.nome = nome;
        this.cor = HelperPadrao.padronizaCor(cor);
        this.pecas = p;
    }

    public char getCor() {
        return this.cor;
    }

    public String getNome() {
        return this.nome;
    }
}
