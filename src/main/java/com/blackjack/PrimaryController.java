package com.blackjack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button btnComecar;

    @FXML
    private Button btnSair;

    @FXML
    private void comecarJogo() {
        // Abre a tela do jogo (secondary.fxml)
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/blackjack/secondary.fxml"));
            Stage stage = (Stage) btnComecar.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
        } catch (Exception e) {
            System.err.println("Erro ao carregar o FXML ou ao alterar a cena:");
            e.printStackTrace();
        }
    }

    @FXML
    private void sair() {
        // Fecha o aplicativo
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}