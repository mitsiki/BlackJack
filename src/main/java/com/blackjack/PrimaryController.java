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
            stage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
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