package com.blackjack;


/**
 * Classe que simula uma carta de baralho.
 */
public class Carta {
    // O naipe da carta. Ex: "Copas", "Espadas"
    private String naipe; 

    // O símbolo da carta. Ex: "Ás", "Rei", "10"
    private String simbolo; 

    // Valor numérico da carta. Ex: "1", "10"
    private int valor; 

    // Constrói uma carta.
    public Carta(String naipe, String simbolo, int valor) {
        this.naipe = naipe;
        this.simbolo = simbolo;
        this.valor = valor;
    }

    // Getters
    public String getNaipe() { 
        return naipe; 
    }
    public String getSimbolo() { 
        return simbolo; 
    }
    public int getValor() { 
        return valor; 
    }

    @Override
    public String toString() {
        return simbolo + " de " + naipe;
    }
}