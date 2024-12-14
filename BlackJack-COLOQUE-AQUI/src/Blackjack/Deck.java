package Blackjack;

import Blackjack.Exceptions.DeckEmptyException;

import java.util.Arrays;
import java.util.Collections;

/**
 * Classe que simula um baralho de cartas.
 */
public class Deck {
    /**
     * Array de objetos Card que compõem o baralho.
     */
    private Card[] deck = new Card[52];

    /**
     * Posição atual no baralho para retirar cartas.
     */
    private int currentPos;

    /**
     * Construtor que cria um baralho embaralhado de 52 cartas.
     */
    public Deck() {
        // preenche o array do baralho com cartas em ordem (não embaralhado)
        for (int i = 0; i < deck.length; i++) {
            if (i < 13) {
                deck[i] = new Card(i % 13 + 1, "spades"); // espadas
            }

            else if (i < 26) {
                deck[i] = new Card(i % 13 + 1, "hearts"); // copas
            }

            else if (i < 39) {
                deck[i] = new Card(i % 13 + 1, "clubs"); // paus
            }

            else {
                deck[i] = new Card(i % 13 + 1, "diamonds"); // ouros
            }
        }

        // embaralha o baralho
        Collections.shuffle(Arrays.asList(deck));

        // define a posição atual no baralho para o início
        currentPos = 0;
    }

    /**
     * Retira uma única carta do topo do baralho.
     *
     * @return Um objeto Card retirado do topo do baralho.
     * @throws DeckEmptyException Quando o baralho está vazio e não é possível retirar mais cartas.
     */
    public Card draw() {
        // verifica se o baralho está vazio
        if (currentPos >= deck.length) {
            throw new DeckEmptyException();
        }

        // retorna a próxima carta E então incrementa a posição atual
        return deck[currentPos++];
    }

    /**
     * Obtém a quantidade de cartas no baralho.
     *
     * @return O tamanho do baralho como um inteiro.
     */
    public int getSize() {
        return deck.length;
    }
}
