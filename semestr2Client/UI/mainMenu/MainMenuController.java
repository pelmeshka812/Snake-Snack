package UI.mainMenu;

import UI.helpers.SceneSetterHelper;
import UI.helpers.WindowSettingsHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {

    private String playerNickName;
    private SceneSetterHelper sceneSetterHelper = new SceneSetterHelper();
    public static Stage primaryStage;

    @FXML
    private Button mainMenuPlayButton;

    @FXML
    private Button mainMenuQuitButton;

    @FXML
    private Label mainMenuHelloTextLabel;

    @FXML
    void mainMenuPlayButtonPressed(ActionEvent event) throws IOException {
        sceneSetterHelper.setScene("gameModeChooser");
    }

    @FXML
    void mainMenuQuitButtonPressed(ActionEvent event) {
        mainMenuQuitButton.getScene().getWindow().hide();
    }

    public void configure(String playerNickName) {
        this.playerNickName = playerNickName;
        mainMenuHelloTextLabel.setText(mainMenuHelloTextLabel.getText() + playerNickName);
    }

    @FXML
    void mainMenuLeaderboardButtonPressed(ActionEvent event) throws IOException {
        sceneSetterHelper.setScene("endGameResults");
    }

}

