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

    public void startGame() {
        Card.buildDeck();
        Card.shuffleDeck();
        // colocar aqui inicializador do SceneBuilder
    
    }

    public static void main( String[] args ) {
        new Game();
    }

}
