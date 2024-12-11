package br.edu.ifsp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaInicialController {

    /*@FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }*/

    @FXML
    private Button botaoComecar;

    /*@FXML
    private void comecarPartida() throws IOException {
        if(botaoComecar.onMouseClickedProperty()){
            App.setRoot("Tela partida");
        }
    }*/

    @FXML
    private void trocaParaTelaPartida() throws IOException {
        App.setRoot("TelaPartida");
    }
}
