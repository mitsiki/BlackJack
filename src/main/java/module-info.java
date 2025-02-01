module com.blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.blackjack to javafx.fxml;
    exports com.blackjack;
}
