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
        atualizarInterface(false); // Inicia sem revelar as cartas do dealer
        btnNovoJogo.setVisible(false);
    }

    @FXML
    private void pedirCarta() {
        mesa.jogadorPedeCarta();
        atualizarInterface(false);

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

        // O dealer joga
        mesa.dealerJoga();

        // Atualiza a interface para revelar as cartas do dealer
        atualizarInterface(true);

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

    private void atualizarInterface(boolean revelarCartasDealer) {
        // Limpa as cartas exibidas
        dealerCartas.getChildren().clear();
        jogadorCartas.getChildren().clear();
    
        // Exibe as cartas do dealer
        for (int i = 0; i < mesa.getDealer().getMao().size(); i++) {
            Carta carta = mesa.getDealer().getMao().get(i);
            ImageView imageView;
    
            if (revelarCartasDealer || i == 0) {
                // Mostra a carta normalmente se o jogo terminou ou se for a primeira carta
                imageView = criarImageView(carta);
            } else {
                // Mostra o fundo da carta para as cartas restantes
                URL url = getClass().getResource("/com/blackjack/cards/back.png");
                if (url == null) {
                    System.err.println("Arquivo não encontrado: back.png");
                    imageView = new ImageView(); // Retorna um ImageView vazio
                } else {
                    Image imagemCarta = new Image(url.toExternalForm());
                    imageView = new ImageView(imagemCarta);
                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);
                }
            }
            dealerCartas.getChildren().add(imageView);
        }
    
        // Exibe as cartas do jogador
        for (Carta carta : mesa.getJogador().getMao()) {
            ImageView imageView = criarImageView(carta);
            jogadorCartas.getChildren().add(imageView);
        }
    
        // Atualiza as pontuações
        jogadorLabel.setText("Você: " + mesa.getJogador().getPontuacao());
    
        if (revelarCartasDealer) {
            // Mostra a pontuação total do dealer se o jogo terminou
            dealerLabel.setText("Dealer: " + mesa.getDealer().getPontuacao());
        } else {
            // Mostra a pontuação parcial do dealer (apenas a primeira carta)
            int pontuacaoDealer = mesa.getDealer().getMao().get(0).getValor();
            dealerLabel.setText("Dealer: " + pontuacaoDealer + " + ?");
        }

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
