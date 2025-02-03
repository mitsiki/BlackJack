package com.blackjack;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * Controlador interface gráfica (GUI).
 */
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

    @FXML
    private Button btnNovoJogo;

    @FXML
    private Label ganhadorLabel;


    private Mesa mesa;

    @FXML
    public void initialize() {
        mesa = new Mesa();
        mesa.iniciarJogo();
        atualizarInterface();
        btnNovoJogo.setVisible(false);
    }

    @FXML
    private void pedirCarta() {
        mesa.jogadorPedeCarta();
        atualizarInterface();

        // Verifica se o jogador estourou
        if (mesa.getJogador().getPontuacao() > 21) {
            ganhadorLabel.setText("Dealer venceu! Você estourou.");
            btnPedirCarta.setDisable(true);
            btnManter.setDisable(true);
            btnNovoJogo.setVisible(true);

        } else if (mesa.getJogador().getPontuacao() == 21) {
            btnManter.fire();
        }
    }

    @FXML
    private void manter() {

        btnPedirCarta.setDisable(true);
        btnManter.setDisable(true);
        btnNovoJogo.setVisible(true);

        mesa.dealerJoga();
        atualizarInterface();

        // Determina o vencedor
        String resultado = mesa.verificarVencedor();
        ganhadorLabel.setText(resultado);
        
    }

    @FXML
    private void novoJogo() {
        initialize();
        btnPedirCarta.setDisable(false);
        btnManter.setDisable(false);
        ganhadorLabel.setText(null);
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
        String nomeArquivo = carta.getSimbolo() + carta.getNaipe() + ".png";
    
        // Carrega a imagem da carta
        URL url = getClass().getResource("/com/blackjack/cards/" + nomeArquivo);
        if (url == null) {
            System.err.println("Arquivo não encontrado: " + nomeArquivo);
            return new ImageView(); // Retorna um ImageView vazio
        }
        Image imagemCarta = new Image(url.toExternalForm());
        ImageView imageView = new ImageView(imagemCarta);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        return imageView;
    }

}
