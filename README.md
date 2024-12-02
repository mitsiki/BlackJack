# Projeto - BlackJack

## Objetivos do Sistema
O objetivo principal do sistema é conseguir, através da linguagem Java, replicar um jogo padrão de Blackjack ou 21. Que por sua vez, possui como finalidade ter mais pontos do que o adversário, mas sem ultrapassar os 21 pontos.

Para alcançar nosso objetivo principal, temos como objetivo secundário: a realização e implementação das funcionalidades do jogo descritas no tópico abaixo.

### Funcionalidades
Com a finalidade final de se conseguir implementar um jogo padrão completo de Blackjack, temos como finalidades do nosso sistema a presença de:

**Interface Gráfica (UI)**
- Menu do jogo: Tela inicial com opções para iniciar ou encerrar o jogo.
- Tabuleiro: Apresentação clara das cartas da mesa e do jogador, além da pontuação atual de cada lado.
- Botões interativos:
    - “Bater”: Solicitar uma nova carta.
    - “Manter”: Encerrar a rodada com as cartas atuais.
  
**Sistema de pontuação padrão de um Jogo de BlackJack:**
- 21 pontos: Vitória imediata caso a mesa não alcance 21 também. Se ambos alcançarem 21 pontos, a mesa vence.
- Mais de 21 pontos: Derrota instantânea (estouro).
- Abaixo de 21 pontos:
    - Vitória: Se a mesa estourar ou obtiver uma pontuação inferior à do jogador.
    - Derrota: Se a mesa tiver pontuação maior ou igual à do jogador, mas menor ou igual a 21.
 
**Algoritmo de Aleatoriedade**
- Distribuição de cartas baseada em um algoritmo que simula a aleatoriedade típica do jogo.




