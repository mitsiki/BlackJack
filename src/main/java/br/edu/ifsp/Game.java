package br.edu.ifsp;

import java.util.ArrayList;

/* 
 * Classe que vai tratar da dinâmica do jogo
*/
public class Game {
    
    Game() {
        startGame();
    }


    public void startGame() {
        buildDeck();
        // colocar aqui inicializador do SceneBuilder (acho)
    
    }

    ArrayList<Card> deck;
    // Obs: ver se consigo trazer esse método pra classe Card dps
    public void buildDeck() {
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

}
