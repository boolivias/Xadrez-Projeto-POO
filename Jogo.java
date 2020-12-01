public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador[] jogadores;
    private int vezJogador;
    private int status;

    public Jogo(String nomeJogador1, String nomeJogador2) {
        jogadores[0] = new Jogador(nomeJogador1, Util.Constantes.COR_BRANCO);
        jogadores[1] = new Jogador(nomeJogador2, Util.Constantes.COR_BRANCO);
        this.vezJogador = 0;
        this.tabuleiro = new Tabuleiro();
        this.status = 0;
    }

    public String vezJogador() {
        return this.jogadores[this.vezJogador].getNome();
    }

    public void alteraVezJogador() {
        this.vezJogador = this.vezJogador == 1 ? 0 : 1;
    }
}