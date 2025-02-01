package com.blackjack;

import java.util.ArrayList;


/**
 * Classe que simula a mão de cartas de um jogador durante um jogo de cartas.
 */
public class Jogador {
    private ArrayList<Carta> mao;
    private int pontuacao;

    public Jogador() {
        mao = new ArrayList<>();
        pontuacao = 0;
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
        pontuacao += carta.getPontuacao();
        // Ajusta o valor do Ás se a pontuação ultrapassar 21
        if (pontuacao > 21) {
            for (Carta c : mao) {
                if (c.getValor().equals("Ás") && c.getPontuacao() == 11) {
                    c = new Carta(c.getNaipe(), c.getValor(), 1); // Ás agora vale 1
                    pontuacao -= 10; // Ajusta a pontuação
                }
            }
        }
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void limparMao() {
        mao.clear();
        pontuacao = 0;
    }
}