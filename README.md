# Projeto - BlackJack

## Objetivos do Sistema
O objetivo principal do sistema é conseguir, através da linguagem Java, replicar um jogo padrão de Blackjack ou 21. Que por sua vez, possui como finalidade ter mais pontos do que o adversário, mas sem ultrapassar os 21 pontos.

Para alcançar nosso objetivo principal, temos como objetivo secundário: a realização e implementação das funcionalidades do jogo descritas no tópico abaixo.


### Funcionalidades
Com a finalidade final de se conseguir implementar um jogo padrão completo de Blackjack, temos como finalidades do nosso sistema a presença de uma Interface, com:
- Menu do jogo;
- Tabuleiro;
- Sistema de pontuação padrão de um Jogo de BlackJack:
  1. 21 pontos: Vence a mesa instantaneamente, no caso da mesa não conseguir 21 pontos também. Nesse caso, a mesa vence;
  2. Acima de 21 pontos: Perda instantânea (estouro);
  3. Abaixo de 21 pontos: Vitória no caso da mesa estourar ou pegar um número menor de pontos e derrota no caso da mesa pegar um número maior, abaixo ou igual a 21.
- Botões com opções de “bater” ou “manter”;
- Algoritmo de aleatoriedade no emparelhamento de cartas.


## Principais Classes e seus principais Métodos e Atributos
O sistema possui como sendo as principais classes:
- Game.java -> Classe que vai tratar da dinâmica do jogo;
- Card.java -> Classe que vai tratar da dinâmica das cartas;
- CartaMesa -> Classe que trata da mão da mesa;
- CartaJogador -> Classe que trata da mão do jogador;
- UI.java -> Classe que vai tratar da interface do jogo;
- ActionHandler.java -> Classe que vai tratar das ações dos botões.
