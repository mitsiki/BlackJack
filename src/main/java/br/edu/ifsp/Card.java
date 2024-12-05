package br.edu.ifsp;

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

}

/*
* Tipos da carta:
* C "clubs" - paus
* D "diamonds" - ouros
* H "hearts" - copas
* S "spades" - espadas
*/