package UI.mainMenu.GameModeChooser;

import UI.helpers.SceneSetterHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class GameModeChooserController {

    private SceneSetterHelper sceneSetterHelper = new SceneSetterHelper();

    @FXML
    private Button gameModeChooserSingleplayerButton;

    @FXML
    private Button gameModeChooserMultiplayerButton;

    @FXML
    private Button gameModeChooserBackButton;

    @FXML
    void gameModeChooserBackButtonPressed(ActionEvent event) {
        gameModeChooserBackButton.getScene().getWindow().hide();
    }

    @FXML
    void gameModeChooserMultiplayerButtonPressed(ActionEvent event) {

    }

    @FXML
    void gameModeChooserSingleplayerButtonPressed(ActionEvent event) {

    }

}

