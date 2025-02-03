package com.blackjack;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Classe que simula um baralho de cartas.
 */
public class Baralho {
    // Array de objetos do tipo Carta que compõe o baralho.
    private ArrayList<Carta> baralho;
    
    // Construtor que cria um baralho embaralhado.
    public Baralho() {
        baralho = new ArrayList<>();
        inicializarBaralho();
        Collections.shuffle(baralho);
    }

    private void inicializarBaralho() {
        String[] naipes = {"hearts", "spades", "diamonds", "clubs"};
        String[] simbolos = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] pontuacoes = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}; // Ás vale 11 por padrão

        for (String naipe : naipes) {
            for (int i = 0; i < simbolos.length; i++) {
                baralho.add(new Carta(naipe, simbolos[i], pontuacoes[i]));
            }
        }
    }

    // Retira a carta no início do baralho.
    public Carta pegarCarta() {
        if (baralho.isEmpty()) {
            throw new IllegalStateException("O baralho está vazio!");
        }
        return baralho.remove(0);
    }
}