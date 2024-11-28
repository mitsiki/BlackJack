import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class BlackjackFX extends Application {
    private int jogadorPontuacao = 0;
    private int dealerPontuacao = 0;
    private List<String> deck = new ArrayList<>();
    private VBox jogoLayout = new VBox(10);
    private Label lblStatus = new Label("Bem-vindo ao Blackjack!");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blackjack");

        // Menu principal
        VBox menuLayout = new VBox(10);
        menuLayout.setAlignment(Pos.CENTER);
        Label lblTitulo = new Label("Blackjack em Java");
        Button btnJogar = new Button("Jogar");
        Button btnInstrucoes = new Button("Instruções");
        Button btnSair = new Button("Sair");

        btnJogar.setOnAction(e -> iniciarJogo(primaryStage));
        btnInstrucoes.setOnAction(e -> mostrarInstrucoes(primaryStage));
        btnSair.setOnAction(e -> primaryStage.close());

        menuLayout.getChildren().addAll(lblTitulo, btnJogar, btnInstrucoes, btnSair);
        Scene menuScene = new Scene(menuLayout, 400, 300);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private void iniciarJogo(Stage primaryStage) {
        jogadorPontuacao = 0;
        dealerPontuacao = 0;
        criarDeck();
        embaralharDeck();

        jogoLayout.setAlignment(Pos.CENTER);
        lblStatus.setText("Sua vez de jogar!");
        Button btnPedir = new Button("Pedir Carta");
        Button btnParar = new Button("Parar");

        btnPedir.setOnAction(e -> {
            int carta = pegarCarta();
            jogadorPontuacao += carta;
            lblStatus.setText("Você recebeu " + carta + ". Sua pontuação: " + jogadorPontuacao);

            if (jogadorPontuacao > 21) {
                lblStatus.setText("Você perdeu! Pontuação acima de 21.");
                finalizarJogo(false);
            }
        });

        btnParar.setOnAction(e -> turnoDealer());

        jogoLayout.getChildren().setAll(lblStatus, btnPedir, btnParar);
        Scene jogoScene = new Scene(jogoLayout, 400, 300);
        primaryStage.setScene(jogoScene);
    }

    private void mostrarInstrucoes(Stage primaryStage) {
        VBox instrucoesLayout = new VBox(10);
        instrucoesLayout.setAlignment(Pos.CENTER);

        Label lblInstrucoes = new Label("Instruções do Blackjack:\n"
                + "- O objetivo é chegar o mais próximo possível de 21 sem ultrapassar.\n"
                + "- Você pode 'Pedir Carta' para somar pontos ou 'Parar' para encerrar seu turno.\n"
                + "- O dealer joga após você e segue as mesmas regras.\n"
                + "- Boa sorte!");

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> start(primaryStage));

        instrucoesLayout.getChildren().addAll(lblInstrucoes, btnVoltar);
        Scene instrucoesScene = new Scene(instrucoesLayout, 400, 300);
        primaryStage.setScene(instrucoesScene);
    }

    private void criarDeck() {
        deck.clear();
        String[] naipes = {"Copas", "Espadas", "Ouros", "Paus"};
        for (String naipe : naipes) {
            for (int i = 2; i <= 10; i++) {
                deck.add(i + " de " + naipe);
            }
            deck.add("Valete de " + naipe);
            deck.add("Dama de " + naipe);
            deck.add("Rei de " + naipe);
            deck.add("Ás de " + naipe);
        }
    }

    private void embaralharDeck() {
        Collections.shuffle(deck);
    }

    private int pegarCarta() {
        if (deck.isEmpty()) {
            criarDeck();
            embaralharDeck();
        }
        String carta = deck.remove(0);
        return valorCarta(carta);
    }

    private int valorCarta(String carta) {
        if (carta.startsWith("Ás")) return 11;
        if (carta.startsWith("Valete") || carta.startsWith("Dama") || carta.startsWith("Rei")) return 10;
        return Integer.parseInt(carta.split(" ")[0]);
    }

    private void turnoDealer() {
        Random random = new Random();
        while (dealerPontuacao < 17) {
            int carta = pegarCarta();
            dealerPontuacao += carta;
        }

        if (dealerPontuacao > 21 || jogadorPontuacao > dealerPontuacao) {
            lblStatus.setText("Parabéns! Você venceu com " + jogadorPontuacao + " pontos!");
        } else if (jogadorPontuacao == dealerPontuacao) {
            lblStatus.setText("Empate! Ambos com " + jogadorPontuacao + " pontos.");
        } else {
            lblStatus.setText("Você perdeu! Dealer fez " + dealerPontuacao + " pontos.");
        }
        finalizarJogo(dealerPontuacao <= 21 && dealerPontuacao >= jogadorPontuacao);
    }

    private void finalizarJogo(boolean dealerGanhou) {
        Button btnVoltarMenu = new Button("Voltar ao Menu");
        btnVoltarMenu.setOnAction(e -> start(new Stage()));
        jogoLayout.getChildren().setAll(lblStatus, btnVoltarMenu);
    }
}