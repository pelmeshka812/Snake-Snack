package UI.mainMenu.nickNameChooser;

import UI.helpers.WindowSettingsHelper;
import UI.mainMenu.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NickNameChooserController {

    @FXML
    private Button nickNameChooserQuitButton;

    @FXML
    private TextField nickNameChooserTextField;

    @FXML
    private Button nickNameChooserEnterButton;

    @FXML
    void nickNameChooserEnterButtonPressed(ActionEvent event) throws IOException {
        if (!nickNameChooserTextField.getText().equals("")) {

            if (nickNameChooserTextField.getText().length() > 12) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Nickname length");
                alert.setContentText("Nickname cannot be more than 12 characters");
                nickNameChooserTextField.setText("");
                alert.showAndWait();
            } else {

                MainMenuController.primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/UI/mainMenu/mainMenu.fxml"));
                Parent scene = loader.load();
                MainMenuController.primaryStage.setScene(new Scene(scene));
                MainMenuController.primaryStage = WindowSettingsHelper.configure(MainMenuController.primaryStage);
                MainMenuController controller = loader.getController();
                controller.configure(nickNameChooserTextField.getText());
                nickNameChooserTextField.getScene().getWindow().hide();
                MainMenuController.primaryStage.show();
            }
        }
    }

    @FXML
    void nickNameChooserQuitButtonPressed(ActionEvent event) {
        nickNameChooserQuitButton.getScene().getWindow().hide();
    }

}

