package br.edu.ifsp;

import java.util.ArrayList;
import java.util.Random;

/* 
 * Classe que vai tratar da dinâmica do jogo
*/
public class Game {
    
    // Construtor
    Game() {
        startGame();
    }

    ArrayList<Card> deck;
    Random random = new Random(); // embaralha o baralho

    // mesa
    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerAceCount;

    // jogador
    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;


    public void startGame() {

        buildDeck();
        shuffleDeck();
        // colocar aqui inicializador do SceneBuilder

        // mesa
        dealerHand = new ArrayList<Card>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size()-1); //remove card at last index
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card card = deck.remove(deck.size()-1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);

        System.out.println("DEALER:");
        System.out.println(hiddenCard);
        System.out.println(dealerHand);
        System.out.println(dealerSum);
        System.out.println(dealerAceCount);


        // jogador
        playerHand = new ArrayList<Card>();
        playerSum = 0;
        playerAceCount = 0;

        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size()-1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        System.out.println("PLAYER: ");
        System.out.println(playerHand);
        System.out.println(playerSum);
        System.out.println(playerAceCount);
    
    }

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
    
    public void shuffleDeck() {
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

public static void main( String[] args ) {
    new Game();
}


