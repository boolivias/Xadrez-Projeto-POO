public class Jogador {
    private String nome;
    private char cor;

    /**
     * A classe peça só será implementada na segunda parte do projeto
     */
    // private int[] indexPecas;

    // public int[] pecasAtivas(Peca[] pecas) {
    /**
     * percorre a lista de pecas de acordo com os valores em indexPecas chamando o
     * método estaAtivo da peca, armazenando o index de todas as pecas ativas e
     * retornando o vetor com o index de todas as pecas ativas
     */
    // }

    public Jogador(String nome, char cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public char getCor() {
        return this.cor;
    }

    public String getNome() {
        return this.nome;
    }
}
