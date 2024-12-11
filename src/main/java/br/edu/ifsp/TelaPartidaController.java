package br.edu.ifsp;

import java.io.IOException;

import javafx.fxml.FXML;

public class TelaPartidaController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}