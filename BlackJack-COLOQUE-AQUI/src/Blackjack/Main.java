package Blackjack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 *   **************************************************************************
 *   *                                                                        *
 *   *                            REGRAS DO BLACKJACK                        *
 *   *                                                                        *
 *   **************************************************************************
 *
 *
 * 1. O dealer deve comprar cartas em um "soft 17".
 * 2. O pagamento para um Blackjack natural é 150% da aposta original.
 * 3. Um baralho é usado, do qual todas as mãos retiram cartas. Quando o baralho 
 *    é esvaziado, um novo baralho é usado imediatamente, sem interrupção no jogo.
 * 4. Não há aposta mínima.
 * 5. As apostas são devolvidas em caso de empate.
 */

/**
 * Classe principal que configura a cena e inicia a aplicação gráfica do Blackjack.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Blackjack.fxml"));
        primaryStage.setTitle("Blackjack da Galera");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
