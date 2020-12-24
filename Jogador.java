
/**
 * Classe Jogador
 * Classe responsável por realizar todas as ações e armazenar as informações do jogador
 * 
 * @author Jean Wylmer Flores Mendoza
 */

import Pecas.Peca;

public class Jogador {
    private String nome;
    private char cor;
    private Peca pecas[];

    public Jogador(String nome, char cor, Peca p[]) {
        this.nome = nome;
        this.cor = cor;
        this.pecas = p;
    }

    public char getCor() {
        return this.cor;
    }

    public String getNome() {
        return this.nome;
    }
}
