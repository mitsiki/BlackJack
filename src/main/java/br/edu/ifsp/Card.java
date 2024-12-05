package br.edu.ifsp;

import java.util.ArrayList;
import java.util.Random;

/*
 * Classe que vai tratar da dinâmica das cartas
 */
public class Card {
    String value;
    String type;

    Card(String value, String type) {
        this.value = value;
        this.type = type;
    }
    //teste
    ArrayList<Card> deck;

    public static void buildDeck() {
        deck = new ArrayList<Card>();
        String[] values = {"A", "2", "3", "4" ,"5", "6", "7", "8", "9", "J", "Q", "K"}; // Valores das cartas
        String[] types = { "C", "D", "H", "S"}; // Tipos (ou naipes) das cartas

        // Cria o baralho
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(values[j], types[i]);
                deck.add(card);
            }
        }

        // Teste pra ver se fez o baralho certo
        System.out.println("Build deck:");
        System.out.println(deck);

    }
    
    public static void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }

        // Teste para ver se embaralhou certo
        System.out.println("After shuffle");
        System.out.println(deck);
    }

}

/*
* Tipos da carta:
* C "clubs" - paus
* D "diamonds" - ouros
* H "hearts" - copas
* S "spades" - espadas
*/