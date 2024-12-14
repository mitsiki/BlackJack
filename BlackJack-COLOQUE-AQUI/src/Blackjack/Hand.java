package Blackjack;

import Blackjack.Exceptions.*;

/**
 * Classe que simula a mão de cartas de um jogador durante um jogo de cartas.
 */
public class Hand {
    /**
     * Baralho embaralhado do qual todas as mãos retiram cartas.
     */
    private static Deck deck;

    /**
     * Array que armazena a mão de cartas.
     */
    private Card[] hand;

    /**
     * Quantidade de cartas na mão.
     */
    private int size;

    /**
     * Construtor que define o tamanho da mão e compra essa quantidade de cartas para a mão.
     *
     * @param size O tamanho inicial da mão.
     * @throws HandBiggerThanDeckException Quando a quantidade de cartas na mão é maior que o baralho inteiro.
     * @throws InvalidHandSizeException    Quando o tamanho fornecido não é pelo menos 1.
     */
    public Hand(int size) {
        // cria um novo baralho embaralhado se ainda não existir um
        if (deck == null) {
            deck = new Deck();
        }

        // a mão não pode ser maior que o baralho
        if (size > deck.getSize()) {
            throw new HandBiggerThanDeckException();
        }

        // a mão deve ter pelo menos 1 carta
        if (size < 1) {
            throw new InvalidHandSizeException();
        }

        // a mão pode ter no máximo o tamanho do baralho
        hand = new Card[deck.getSize()];

        // compra cartas para a mão
        for (int i = 0; i < size; i++) {
            try {
                hand[i] = deck.draw();
            } catch (DeckEmptyException e) {
                // o baralho está vazio, cria um novo baralho
                deck = new Deck();

                // termina de comprar as cartas
                hand[i] = deck.draw();
            }
        }

        // salva o tamanho inicial da mão
        this.size = size;
    }

    /**
     * Construtor padrão que compra 2 cartas (mão inicial padrão do Blackjack).
     */
    public Hand() {
        this(2);
    }

    /**
     * Soma os valores das cartas na mão usando as regras do Blackjack.
     *
     * @return A soma das cartas na mão.
     */
    public int sum() {
        // rastreia o total das cartas na mão
        int result = 0;

        // rastreia os ases para ajustar o valor caso a soma ultrapasse 21
        int aceCount = 0;

        // soma as cartas na mão e conta os ases
        for (int i = 0; i < size; i++) {
            result += hand[i].getValue();
            if (hand[i].getSymbol().equals("A")) {
                aceCount++;
            }
        }

        // altera os valores dos ases de 11 para 1 se a soma ultrapassar 21
        while (result > 21 && aceCount != 0) {
            result -= 10;
            aceCount--;
        }

        return result;
    }

    /**
     * Adiciona uma única carta à mão a partir do baralho.
     *
     * @throws HandFullException Quando a mão já está cheia e não é possível adicionar mais cartas.
     */
    public void hit() {
        // verifica se há espaço na mão para adicionar uma carta
        if (size >= deck.getSize()) {
            throw new HandFullException();
        }

        // compra uma carta
        try {
            hand[size] = deck.draw();
        } catch (DeckEmptyException e) {
            // o baralho está vazio, cria um novo baralho
            deck = new Deck();

            // termina de comprar a carta
            hand[size] = deck.draw();
        }

        // incrementa o tamanho da mão
        size++;
    }

    /**
     * Obtém o tamanho da mão.
     *
     * @return Quantidade de cartas na mão.
     */
    public int getSize() {
        return size;
    }

    /**
     * Obtém uma carta da mão pelo índice.
     *
     * @param index O índice da carta a ser retornada, começando de 0.
     * @return O objeto da carta no índice fornecido.
     * @throws HandIndexOutOfBoundsException Quando o índice é menor que 0 ou maior que size - 1.
     */
    public Card getCard(int index) {
        // verifica se o índice está dentro dos limites da mão
        if (index < 0 || index >= size) {
            throw new HandIndexOutOfBoundsException();
        }

        return hand[index];
    }

    /**
     * Verifica se há um Ás na mão.
     *
     * @return True se houver um Ás, false caso contrário.
     */
    public boolean hasAce() {
        for (int i = 0; i < size; i++) {
            if (hand[i].getSymbol().equals("A")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Força a criação de um novo baralho.
     */
    public static void forceNewDeck() {
        deck = new Deck();
    }
}
