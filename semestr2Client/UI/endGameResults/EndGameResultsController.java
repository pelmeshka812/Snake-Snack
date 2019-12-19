package UI.endGameResults;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndGameResultsController {

    @FXML
    private Button endGameResultsBackToMainMenuButton;

    @FXML
    private Label endGameResultsScoreCounter;

    @FXML
    void endGameResultsBackToMainMenuButtonPressed(ActionEvent event) {
        endGameResultsBackToMainMenuButton.getScene().getWindow().hide();
    }

    public void setScore(int score) {
        this.endGameResultsScoreCounter.setText(String.valueOf(score));
    }
}

