package com.blackjack;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Classe que simula um baralho de cartas.
 */
public class Baralho {
    private ArrayList<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
        inicializarBaralho();
    }

    private void inicializarBaralho() {
        String[] naipes = {"Copas", "Espadas", "Ouros", "Paus"};
        String[] valores = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};
        int[] pontuacoes = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}; // Ás vale 11 por padrão

        for (String naipe : naipes) {
            for (int i = 0; i < valores.length; i++) {
                cartas.add(new Carta(naipe, valores[i], pontuacoes[i]));
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta pegarCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("O baralho está vazio!");
        }
        return cartas.remove(0);
    }
}