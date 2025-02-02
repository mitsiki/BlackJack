package com.blackjack;


/**
 * Classe que gerencia a lógica e o estado do jogo.
 */
public class Mesa {
    private Baralho baralho;
    private Jogador jogador;
    private Jogador dealer;

    public Mesa() {
        baralho = new Baralho();
        jogador = new Jogador();
        dealer = new Jogador();
    }

    public void iniciarJogo() {
        jogador.limparMao();
        dealer.limparMao();

        // Distribui 2 cartas para o jogador e o dealer
        jogador.receberCarta(baralho.pegarCarta());
        jogador.receberCarta(baralho.pegarCarta());
        dealer.receberCarta(baralho.pegarCarta());
        dealer.receberCarta(baralho.pegarCarta());
    }

    public void jogadorPedeCarta() {
        jogador.receberCarta(baralho.pegarCarta());
    }

    public void dealerJoga() {
        while (dealer.getPontuacao() < 17) {
            dealer.receberCarta(baralho.pegarCarta());
        }
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Jogador getDealer() {
        return dealer;
    }

    public String verificarVencedor() {
        int pontuacaoJogador = jogador.getPontuacao();
        int pontuacaoDealer = dealer.getPontuacao();

        if (pontuacaoJogador > 21) {
            return "Dealer venceu! Você estourou.";
        } else if (pontuacaoDealer > 21) {
            return "Você venceu! Dealer estourou.";
        } else if (pontuacaoJogador > pontuacaoDealer) {
            return "Você venceu!";
        } else if (pontuacaoJogador == pontuacaoDealer) {
            return "Empate!";
        } else {
            return "Dealer venceu!";
        }
    }
}