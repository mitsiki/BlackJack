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
        
        // Ajusta o valor do Ás se não for uma as duas primeiras cartas
        if (mao.size() > 2) {
            for (Carta c : mao) {
                if (c.getSimbolo().equals("A") && c.getValor() == 11) {
                    c.setValor(1); // Ajusta o valor do Ás para 1
                    System.out.println("Ajustou o valor do Ás");
                }
            }
        }
        pontuacao += carta.getValor();
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
