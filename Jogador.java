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
