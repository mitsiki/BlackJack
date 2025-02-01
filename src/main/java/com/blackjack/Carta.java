package com.blackjack;


/**
 * Classe que simula uma carta de baralho.
 */
public class Carta {
    private String naipe; // Ex: "Copas", "Espadas"
    private String valor; // Ex: "Ás", "Rei", "10"
    private int pontuacao; // Valor numérico da carta

    public Carta(String naipe, String valor, int pontuacao) {
        this.naipe = naipe;
        this.valor = valor;
        this.pontuacao = pontuacao;
    }

    // Getters
    public String getNaipe() { return naipe; }
    public String getValor() { return valor; }
    public int getPontuacao() { return pontuacao; }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}