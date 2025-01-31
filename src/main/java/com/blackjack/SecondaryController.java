package com.blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SecondaryController {

    @FXML
    private Label dealerLabel;

    @FXML
    private HBox dealerCartas;

    @FXML
    private Label jogadorLabel;

    @FXML
    private HBox jogadorCartas;

    @FXML
    private Button btnPedirCarta;

    @FXML
    private Button btnManter;

    private Mesa mesa;

    @FXML
    public void initialize() {
        mesa = new Mesa();
        mesa.iniciarJogo();
        atualizarInterface();
    }

    @FXML
    private void pedirCarta() {
        mesa.jogadorPedeCarta();
        atualizarInterface();

        // Verifica se o jogador estourou
        if (mesa.getJogador().getPontuacao() > 21) {
            dealerLabel.setText("Dealer venceu! Você estourou.");
            btnPedirCarta.setDisable(true);
            btnManter.setDisable(true);
        }
    }

    @FXML
    private void manter() {
        mesa.dealerJoga();
        atualizarInterface();

        // Determina o vencedor
        String resultado = mesa.verificarVencedor();
        dealerLabel.setText(resultado);

        btnPedirCarta.setDisable(true);
        btnManter.setDisable(true);
    }

    private void atualizarInterface() {
        // Limpa as cartas exibidas
        dealerCartas.getChildren().clear();
        jogadorCartas.getChildren().clear();

        // Exibe as cartas do dealer
        for (Carta carta : mesa.getDealer().getMao()) {
            ImageView imageView = criarImageView(carta);
            dealerCartas.getChildren().add(imageView);
        }

        // Exibe as cartas do jogador
        for (Carta carta : mesa.getJogador().getMao()) {
            ImageView imageView = criarImageView(carta);
            jogadorCartas.getChildren().add(imageView);
        }

        // Atualiza as pontuações
        jogadorLabel.setText("Você: " + mesa.getJogador().getPontuacao());
        dealerLabel.setText("Dealer: " + mesa.getDealer().getPontuacao());
    }

    private ImageView criarImageView(Carta carta) {
        String nomeArquivo = carta.getValor().toLowerCase() + "_" + carta.getNaipe().toLowerCase() + ".png";
        Image imagemCarta = new Image(getClass().getResource("/com/blackjack/cartas/" + nomeArquivo).toExternalForm());
        ImageView imageView = new ImageView(imagemCarta);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}