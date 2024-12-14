package Blackjack;

import Blackjack.Exceptions.BetTooBigException;
import Blackjack.Exceptions.InvalidBetException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 * Controlador para gerenciar o estado do jogo e a interface gráfica (GUI).
 */
public class Controller {

    // variáveis membro para elementos da GUI

    /**
     * Campo de texto para inserir apostas. Pressionar 'Enter' ou o botão 'betButton' enviará a aposta.
     */
    @FXML
    private TextField betTextField;

    /**
     * Botão para enviar uma aposta inserida.
     */
    @FXML
    private Button betButton;

    /**
     * Botão para solicitar mais uma carta para o jogador.
     */
    @FXML
    private Button hitButton;

    /**
     * Botão para "parar" e permitir que o dealer realize sua jogada.
     */
    @FXML
    private Button stayButton;

    /**
     * Rótulo que exibe o dinheiro do dealer.
     */
    @FXML
    private Label dealerMoneyLabel;

    /**
     * Rótulo que exibe o dinheiro do jogador.
     */
    @FXML
    private Label playerMoneyLabel;

    /**
     * Rótulo que exibe a aposta atual inserida.
     */
    @FXML
    private Label currentBetLabel;

    /**
     * Rótulo que exibe o total atual da mão do dealer.
     */
    @FXML
    private Label dealerTotalLabel;

    /**
     * Rótulo que exibe o total atual da mão do jogador.
     */
    @FXML
    private Label playerTotalLabel;

    /**
     * Título acima do 'dealerFlowPane'.
     */
    @FXML
    private Label dealerFlowPaneTitle;

    /**
     * Botão que inicia um novo jogo quando pressionado.
     */
    @FXML
    private Button newGameButton;

    /**
     * Botão que avança o jogo para a próxima rodada.
     */
    @FXML
    private Button nextRoundButton;

    /**
     * Rótulo que exibe o vencedor ao final de cada rodada.
     */
    @FXML
    private Label winnerLabel;

    /**
     * Rótulo que aparece quando o jogo termina (alguém ficou sem dinheiro).
     */
    @FXML
    private Label gameOverLabel;

    /**
     * FlowPane para conter as imagens da mão do dealer.
     */
    @FXML
    private FlowPane dealerFlowPane;

    /**
     * Título acima do 'playerFlowPane'.
     */
    @FXML
    private Label playerFlowPaneTitle;

    /**
     * FlowPane para conter as imagens da mão do jogador.
     */
    @FXML
    private FlowPane playerFlowPane;

    /**
     * Array de objetos ImageView para armazenar até 12 imagens das cartas da mão do dealer.
     */
    private ImageView[] dealerImageView;

    /**
     * Array de objetos ImageView para armazenar até 12 imagens das cartas da mão do jogador.
     */
    private ImageView[] playerImageView;

    // variáveis membro para jogadores e suas carteiras

    /**
     * A mão de cartas do dealer.
     */
    private Hand dealerHand;

    /**
     * A mão de cartas do jogador.
     */
    private Hand playerHand;

    /**
     * A carteira do dealer.
     */
    private Wallet dealerWallet;

    /**
     * A carteira do jogador.
     */
    private Wallet playerWallet;

    // inicializa o estado do jogo

    /**
     * Define o estado inicial do jogo quando o aplicativo é aberto. Este método é chamado automaticamente uma vez e sempre que o botão 'newGameButton' é pressionado.
     */
    public void initialize() {
        // se o botão "novo jogo" for pressionado, redefinir certos objetos que podem estar desconfigurados
        Hand.forceNewDeck();                    // garante que um novo baralho seja criado
        dealerFlowPane.getChildren().clear();   // limpa o FlowPane para remover imagens das cartas
        playerFlowPane.getChildren().clear();   // limpa o FlowPane para remover imagens das cartas
        playerFlowPaneTitle.setVisible(true);   // precisa ser redefinido caso "novo jogo" seja pressionado após "gameOver"
        dealerFlowPaneTitle.setVisible(true);   // precisa ser redefinido caso "novo jogo" seja pressionado após "gameOver"
        dealerFlowPane.setVisible(true);        // precisa ser redefinido caso "novo jogo" seja pressionado após "gameOver"
        playerFlowPane.setVisible(true);        // precisa ser redefinido caso "novo jogo" seja pressionado após "gameOver"
        nextRoundButton.setDisable(false);      // precisa ser redefinido caso "novo jogo" seja pressionado após "gameOver"

        // cria todas as variáveis membro
        dealerHand = new Hand();
        playerHand = new Hand();
        dealerWallet = new Wallet(1000);    // o dealer começa com mais dinheiro
        playerWallet = new Wallet();
        dealerImageView = new ImageView[12];
        playerImageView = new ImageView[12];

        // define os valores iniciais dos rótulos
        dealerMoneyLabel.setText("R$" + String.valueOf(dealerWallet.getMoney()));
        playerMoneyLabel.setText("R$" + String.valueOf(playerWallet.getMoney()));
        currentBetLabel.setText("R$" + String.valueOf(playerWallet.getBet()));

        // não exibe os totais do dealer ou do jogador até que uma aposta seja feita
        dealerTotalLabel.setText("N/A");
        playerTotalLabel.setText("N/A");

        // desabilita os botões de hit e stay até que uma aposta seja feita
        hitButton.setDisable(true);
        stayButton.setDisable(true);

        // não exibe certos botões e rótulos até que sejam necessários
        winnerLabel.setVisible(false);
        newGameButton.setVisible(false);
        nextRoundButton.setVisible(false);
        gameOverLabel.setVisible(false);

        // garante que a área de aposta esteja habilitada e com foco
        betButton.setDisable(false);
        betTextField.setDisable(false);
        betTextField.requestFocus();
    }

    // métodos para gerenciar elementos da GUI

    /**
     * Executa quando o botão de aposta é pressionado. Obtém a aposta inserida no campo de texto e distribui as cartas para o dealer e o jogador.
     */
    @FXML
    private void betPressed() {
        try {
            // define a aposta do jogador com base no valor inserido no betTextField
            playerWallet.setBet(Integer.parseInt(betTextField.getText()));

            // garante que a aposta não seja maior que o dinheiro disponível do dealer
            if (playerWallet.getBet() > dealerWallet.getMoney()) {
                throw new BetTooBigException();
            }

            // atualiza o currentBetLabel
            currentBetLabel.setText("R$" + String.valueOf(playerWallet.getBet()));

            // desabilita o botão e o campo de texto de aposta até a próxima rodada
            betButton.setDisable(true);
            betTextField.setDisable(true);

            // habilita os botões de hit e stay
            hitButton.setDisable(false);
            stayButton.setDisable(false);

            // atualiza o dealerTotalLabel para mostrar o valor da segunda carta do dealer

            // se a segunda carta do dealer for um ás
            if (dealerHand.getCard(1).getSymbol().equals("A")) {
                // define o rótulo como 11/1 para indicar que o ás pode valer 11 ou 1
                dealerTotalLabel.setText("11/1 + ??");
            } else {
                // define o rótulo com o valor da carta
                dealerTotalLabel.setText(String.valueOf(dealerHand.getCard(1).getValue()) + " + ??");
            }

            // chama os métodos 'sum' agora (mais eficiente do que chamá-los várias vezes)
            int playerSum = playerHand.sum();
            int dealerSum = dealerHand.sum();

            // atualiza o playerTotalLabel
            playerTotalLabel.setText(String.valueOf(playerSum));

            // verifica se o dealer ou jogador têm Blackjack
            if (dealerSum == 21 || playerSum == 21) {
                stayButton.fire();
            } else {
                // exibe as mãos do dealer e do jogador
                // nota: isso está dentro do 'else' porque seria redundante executar de outra forma
                updateDealerFlowPane(true);
                updatePlayerFlowPane();
            }
        } catch (NumberFormatException | InvalidBetException e) {
            // entrada inválida (aposta não é um número inteiro ou <= 0), foca no erro e tenta novamente
            betTextField.setText("Aposta Inválida");
            betTextField.selectAll();
            betTextField.requestFocus();
        } catch (BetTooBigException e) {
            // entrada inválida (aposta > dinheiro do jogador ou dealer), foca no erro e tenta novamente
            betTextField.setText("Aposta muito alta");
            betTextField.selectAll();
            betTextField.requestFocus();
        }
    }



    /**
     * Executado quando o botão "hit" é pressionado. Distribui uma carta ao jogador.
     */
    @FXML
    private void hitPressed() {
        // Distribui uma carta ao jogador
        playerHand.hit();

        // chama o método 'sum' agora (mais eficiente do que chamá-lo várias vezes)
        int playerSum = playerHand.sum();

        // atualiza o total do jogador
        playerTotalLabel.setText(String.valueOf(playerSum));

        // atualiza as imagens da mão do jogador
        updatePlayerFlowPane();

        // verifica se o jogador estourou ou tem Blackjack e "stay"
        if (playerSum >= 21) {
            stayButton.fire();
        }

        // desabilita o botão "hit" quando o jogador tiver 12 cartas
        if (playerHand.getSize() >= 12) {
            hitButton.setDisable(true);
        }
    }

    /**
     * Executado quando o botão "stay" é pressionado. O jogador "fica", processa a vez do dealer, descobre quem ganhou e resolve as apostas.
     */
    @FXML
    private void stayPressed() {
        // desabilita todos os botões e campos de texto até que a rodada reinicie
        betTextField.setDisable(true);
        betButton.setDisable(true);
        hitButton.setDisable(true);
        stayButton.setDisable(true);

        // para evitar chamar o método 'sum' várias vezes, armazena as somas como variáveis locais (mais eficiente)
        int playerSum = playerHand.sum();

        // verifica se o jogador tem um Blackjack natural (2 cartas no valor de 21)
        boolean playerHasBlackjack = playerHand.getSize() == 2 && playerSum == 21;

        // o dealer só distribui cartas se o jogador não estourou e não tem Blackjack
        if (playerSum <= 21 && !playerHasBlackjack) {
            // o dealer distribui cartas até ter pelo menos 17
            while (dealerHand.sum() < 17) {
                dealerHand.hit();

                // se o dealer tem um "soft 17", distribui mais uma carta
                if (dealerHand.sum() == 17 && dealerHand.hasAce()) {
                    dealerHand.hit();
                }
            }
        }

        // atualiza o dealerTotalLabel
        dealerTotalLabel.setText(String.valueOf(dealerHand.sum()));

        // atualiza as imagens para ambas as mãos
        updateDealerFlowPane(false);
        updatePlayerFlowPane();

        // verifica o vencedor
        String winner = getWinner();

        // resolve as apostas

        // se o jogador ganhar com Blackjack
        if (winner.equals("Jogador blackjack")) {
            // altera a aposta para 150% do valor, já que o jogador tem Blackjack
            playerWallet.adjustBetForBlackjack();

            // transfere a aposta do dealer para o jogador
            playerWallet.modifyMoney(dealerWallet.transferMoney(playerWallet.getBet()));

            // zera a aposta do jogador
            playerWallet.resetBet();

            // define a mensagem de vencedor
            winnerLabel.setText("Jogador ganhou com Blackjack!");
        }

        // se o dealer ganhar
        if (winner.equals("crûpie") || winner.equals("dealer blackjack")) {
            // transfere a aposta do jogador para o dealer
            dealerWallet.modifyMoney(playerWallet.transferMoney(playerWallet.getBet()));

            // zera a aposta do jogador
            playerWallet.resetBet();

            // define a mensagem de vencedor
            if (winner.equals("crûpie blackjack")) {
                winnerLabel.setText("Crûpie ganhou com Blackjack!");
            } else {
                winnerLabel.setText("Crûpie Ganhou!");
            }
        }

        // se o jogador ganhar
        if (winner.equals("Jogador")) {
            // transfere a aposta do dealer para o jogador
            playerWallet.modifyMoney(dealerWallet.transferMoney(playerWallet.getBet()));

            // zera a aposta do jogador
            playerWallet.resetBet();

            // define a mensagem de vencedor
            winnerLabel.setText("Jogador Ganhou!");
        }

        // se houver empate
        if (winner.equals("empate")) {
            // devolve a aposta ao jogador
            playerWallet.resetBet();

            // define a mensagem de vencedor
            winnerLabel.setText("Empate!");
        }

        // atualiza os rótulos de dinheiro e aposta

        dealerMoneyLabel.setText("R$" + String.valueOf(dealerWallet.getMoney()));
        playerMoneyLabel.setText("R$" + String.valueOf(playerWallet.getMoney()));
        currentBetLabel.setText("R$" + String.valueOf(playerWallet.getBet()));

        // exibe a mensagem do vencedor, botão de novo jogo e botão de próxima rodada
        winnerLabel.setVisible(true);
        newGameButton.setVisible(true);
        nextRoundButton.setVisible(true);

        // se alguém estiver sem dinheiro, encerra o jogo
        if (playerWallet.getMoney() <= 0 || dealerWallet.getMoney() <= 0) {
            gameOver();
        }
    }

    /**
     * Avança para a próxima rodada quando o botão "next round" é pressionado.
     */
    @FXML
    private void nextRoundPressed() {
        // reinicia certos objetos que podem ter sido corrompidos
        dealerFlowPane.getChildren().clear();   // limpa os flow panes para remover as imagens das cartas
        playerFlowPane.getChildren().clear();   // limpa os flow panes para remover as imagens das cartas
        dealerImageView = new ImageView[12];    // cria novos arrays de ImageView para que novas cartas possam ser adicionadas
        playerImageView = new ImageView[12];    // cria novos arrays de ImageView para que novas cartas possam ser adicionadas

        // distribui novas mãos
        dealerHand = new Hand();
        playerHand = new Hand();

        // define todos os rótulos para seus valores iniciais
        dealerMoneyLabel.setText("R$" + String.valueOf(dealerWallet.getMoney()));
        playerMoneyLabel.setText("R$" + String.valueOf(playerWallet.getMoney()));
        currentBetLabel.setText("R$" + String.valueOf(playerWallet.getBet()));

        // não exibe os totais do dealer ou jogador até que uma aposta seja feita
        dealerTotalLabel.setText("N/A");
        playerTotalLabel.setText("N/A");

        // desabilita os botões hit e stay até que uma aposta seja feita
        hitButton.setDisable(true);
        stayButton.setDisable(true);

        // não exibe certos botões e rótulos até que sejam necessários
        winnerLabel.setVisible(false);
        newGameButton.setVisible(false);
        nextRoundButton.setVisible(false);
        gameOverLabel.setVisible(false);

        // garante que a área da aposta esteja habilitada e com foco
        betButton.setDisable(false);
        betTextField.setDisable(false);
        betTextField.requestFocus();
    }

    // métodos para tratar a lógica do jogo

    /**
     * Descobre quem venceu e se houve empate.
     *
     * @return String com o vencedor. Retorna um dos seguintes valores: 'dealer blackjack', 'player blackjack', 'player', 'dealer' ou 'tie'.
     */
    private String getWinner() {
        // para evitar chamar o método 'sum' várias vezes, armazena as somas como variáveis locais (mais eficiente)
        int dealerSum = dealerHand.sum();
        int playerSum = playerHand.sum();

        // verifica se o dealer e o jogador têm Blackjack (2 cartas no valor de 21)
        boolean dealerHasBlackjack = dealerHand.getSize() == 2 && dealerSum == 21;
        boolean playerHasBlackjack = playerHand.getSize() == 2 && playerSum == 21;

        // o dealer tem Blackjack e o jogador não
        if (dealerHasBlackjack && !playerHasBlackjack) {
            return "crûpie blackjack";
        }

        // o jogador tem Blackjack e o dealer não
        if (playerHasBlackjack && !dealerHasBlackjack) {
            return "jogador blackjack";
        }

        // jogador estourou
        if (playerSum > 21) {
            return "crûpie";
        }

        // dealer estourou
        if (dealerSum > 21) {
            return "jogador";
        }

        // dealer tem a mão maior
        if (dealerSum > playerSum) {
            return "crûpie";
        }

        // jogador tem a mão maior
        if (playerSum > dealerSum) {
            return "jogador";
        }

        if (playerSum == dealerSum) {
            return "empate";
        }

        // nunca deveria chegar aqui
        return "ERRO";
    }

    /**
     * Cria/atualiza as imagens das cartas representando a mão do dealer dentro do contêiner 'dealerFlowPane'.
     *
     * @param showFirstCard Exibe a primeira carta quando true, substituindo-a por um verso quando false.
     */
    private void updateDealerFlowPane(boolean showFirstCard) {
        // se 'showFirstCard' for true: a primeira carta exibida é o verso da carta em vez da carta real
        if (showFirstCard) {
            Image back = new Image("file:cards/back.png");
            dealerImageView[0] = new ImageView();
            dealerImageView[0].setImage(back);
            dealerImageView[0].setPreserveRatio(true);
            dealerImageView[0].setSmooth(true);
            dealerImageView[0].setCache(true);
            dealerImageView[0].setFitHeight(160);
            dealerFlowPane.getChildren().add(dealerImageView[0]);
        }

        // substitui a carta virada com a carta real limpando as variáveis para forçar o próximo loop a rodar completamente
        else if (dealerImageView[0] != null) {
            dealerFlowPane.getChildren().clear();
            dealerImageView = new ImageView[12];
        }

        // adiciona todas as cartas na mão do dealer ao FlowPane como imagens
        for (int i = 0; i < dealerHand.getSize(); i++) {
            // adiciona a carta apenas quando ainda não foi criada (mais eficiente do que sobrescrever a mesma imagem toda vez)
            if (dealerImageView[i] == null) {
                Image card = new Image("file:cards/" + dealerHand.getCard(i).getSymbol() + dealerHand.getCard(i).getSuit() + ".png");
                dealerImageView[i] = new ImageView();
                dealerImageView[i].setImage(card);
                dealerImageView[i].setPreserveRatio(true);
                dealerImageView[i].setSmooth(true);
                dealerImageView[i].setCache(true);
                dealerImageView[i].setFitHeight(160);
                dealerFlowPane.getChildren().add(dealerImageView[i]);
                if (i != 0) {
                    FlowPane.setMargin(dealerImageView[i], new Insets(0, 0, 0, -75));
                }
            }
        }
    }

    /**
     * Cria/atualiza as imagens das cartas representando a mão do jogador dentro do contêiner 'playerFlowPane'.
     */
    private void updatePlayerFlowPane() {
        // adiciona todas as cartas na mão do jogador ao FlowPane como imagens
        for (int i = 0; i < playerHand.getSize(); i++) {
            // adiciona a carta apenas quando ainda não foi criada (mais eficiente do que sobrescrever a mesma imagem toda vez)
            if (playerImageView[i] == null) {
                Image card = new Image("file:cards/" + playerHand.getCard(i).getSymbol() + playerHand.getCard(i).getSuit() + ".png");
                playerImageView[i] = new ImageView();
                playerImageView[i].setImage(card);
                playerImageView[i].setPreserveRatio(true);
                playerImageView[i].setSmooth(true);
                playerImageView[i].setCache(true);
                playerImageView[i].setFitHeight(160);
                playerFlowPane.getChildren().add(playerImageView[i]);
                if (i != 0) {
                    FlowPane.setMargin(playerImageView[i], new Insets(0, 0, 0, -75));
                }
            }
        }
    }

    /**
     * Quando alguém ficar sem dinheiro, encerra o jogo.
     */
    private void gameOver() {
        // libera espaço na cena tornando itens desnecessários invisíveis
        playerFlowPaneTitle.setVisible(false);
        dealerFlowPaneTitle.setVisible(false);
        dealerFlowPane.setVisible(false);
        playerFlowPane.setVisible(false);

        // define os totais das mãos como 'N/A' já que as imagens foram removidas e não faz sentido manter os totais
        dealerTotalLabel.setText("N/A");
        playerTotalLabel.setText("N/A");

        // desabilita o botão da próxima rodada
        nextRoundButton.setDisable(true);

        // exibe a mensagem de game over

        // se o dealer estiver sem dinheiro
        if (dealerWallet.getMoney() == 0) {
            gameOverLabel.setText("GAME OVER\n\nCrûpie está sem dinheiro!");
        }

        // se o jogador estiver sem dinheiro
        if (playerWallet.getMoney() == 0) {
            gameOverLabel.setText("GAME OVER\n\nJogador está sem dinheiro!");
        }

        // exibe a mensagem de game over
        gameOverLabel.setVisible(true);
    }
}