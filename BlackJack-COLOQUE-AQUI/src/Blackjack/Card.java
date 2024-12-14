package Blackjack;

import Blackjack.Exceptions.InvalidCardValueException;
import Blackjack.Exceptions.InvalidSuitException;

/**
 * Classe que simula uma carta de baralho.
 */
public class Card {

    /**
     * O valor da carta (usando as regras do Blackjack).
     */
    private int value;

    /**
     * O símbolo da carta (3, 10, J, K, etc).
     */
    private String symbol;

    /**
     * O naipe da carta (espadas, copas, paus ou ouros).
     */
    private String suit;

    /**
     * Constrói uma carta com base no seu valor.
     *
     * @param value Inteiro que varia de 1 a 14. 1 e 14 representam o ás.
     * @param suit  String com o naipe desejado da carta.
     * @throws InvalidCardValueException Quando o valor não está entre 1 e 14 (inclusive).
     * @throws InvalidSuitException      Quando o naipe não é um dos seguintes: espadas, copas, paus ou ouros.
     */
    public Card(int value, String suit) {
        // verifica a validade da entrada
        if (value < 1 || value > 14) {
            throw new InvalidCardValueException();
        }

        // define as variáveis da classe
        setSymbol(value);
        setValue(getSymbol());
        setSuit(suit);
    }

    /**
     * Obtém o valor da carta.
     *
     * @return O valor da carta.
     */
    public int getValue() {
        return value;
    }

    /**
     * Define o valor da carta com base no símbolo (usando as regras do Blackjack: ás sempre vale 11, cartas figuras sempre valem 10).
     *
     * @param symbol O símbolo da carta (3, 10, J, K, etc).
     */
    private void setValue(String symbol) {
        // atribui o valor com base no símbolo fornecido
        switch (symbol) {
            case "A":
                this.value = 11;
                break;
            case "J":
            case "Q":
            case "K":
                this.value = 10;
                break;
            default:
                this.value = Integer.parseInt(symbol);
        }
    }

    /**
     * Obtém o símbolo da carta.
     *
     * @return O símbolo da carta.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Define o símbolo da carta com base no valor.
     *
     * @param value Inteiro que varia de 1 a 14. 1 e 14 representam o ás.
     * @throws InvalidCardValueException Quando o valor não está entre 1 e 14 (inclusive).
     */
    private void setSymbol(int value) {
        // verifica a validade da entrada
        if (value < 1 || value > 14) {
            throw new InvalidCardValueException();
        }

        // atribui o símbolo com base no valor
        switch (value) {
            case 11:
                symbol = "J";
                break;
            case 12:
                symbol = "Q";
                break;
            case 13:
                symbol = "K";
                break;
            case 1:
            case 14:
                symbol = "A";
                break;
            default:
                symbol = String.valueOf(value);
        }
    }

    /**
     * Obtém o naipe da carta.
     *
     * @return O naipe da carta.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Define o naipe da carta.
     *
     * @param suit O naipe a ser atribuído à carta. Deve ser espadas, copas, paus ou ouros.
     * @throws InvalidSuitException Quando o naipe não é um dos seguintes: espadas, copas, paus ou ouros.
     */
    private void setSuit(String suit) {
        // verifica a validade da entrada
        if (suit.equals("spades") || suit.equals("hearts") || suit.equals("clubs") || suit.equals("diamonds")) {
            this.suit = suit;
        }

        // entrada inválida, lança uma exceção
        else {
            throw new InvalidSuitException();
        }
    }
}
