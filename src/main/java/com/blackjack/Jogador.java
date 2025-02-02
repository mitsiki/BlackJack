package com.blackjack;

import java.util.ArrayList;


/**
 * Classe que simula a mão de cartas de um jogador durante um jogo de cartas.
 */
public class Jogador {
    // Array que armazena a mão de cartas do jogador.
    private ArrayList<Carta> mao;

    // Soma dos valores das cartas na mão do jogador.
    private int pontuacao;

    // Constrói a mão e pontuação iniciais do jogador.
    public Jogador() {
        mao = new ArrayList<>();
        pontuacao = 0;
    }

    // Adiciona uma carta na mão do jogador.
    public void receberCarta(Carta carta) {
        mao.add(carta);
        pontuacao += carta.getValor();
        
        // Ajusta o valor do Ás se a pontuação ultrapassar 21
        if (pontuacao > 21) {
            for (Carta c : mao) {
                if (c.getNaipe().equals("Ás") && c.getValor() == 11) {
                    c = new Carta(c.getNaipe(), c.getSimbolo(), 1); // Ás agora vale 1
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
