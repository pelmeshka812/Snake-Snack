package UI.helpers;

import UI.mainMenu.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSetterHelper {

    private WindowSettingsHelper settingsHelper = new WindowSettingsHelper();
    private Stage primaryStage = MainMenuController.primaryStage;

    public SceneSetterHelper() {}

    public void setScene(String scene) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        switch (scene) {
            case "mainMenu":
                loader.setLocation(getClass().getResource("/UI/mainMenu/mainMenu.fxml"));
                Parent mainMenu = loader.load();
                Scene mainMenuScene = new Scene(mainMenu);
                primaryStage.setResizable(false);
                primaryStage.setScene(mainMenuScene);
                primaryStage = settingsHelper.configure(primaryStage);
                break;

            case "gameModeChooser":
                loader.setLocation(getClass().getResource("/UI/mainMenu/GameModeChooser/gameModeChooser.fxml"));
                Parent gameModeChooser = loader.load();
                Stage gameModeChooserStage = new Stage();
                gameModeChooserStage.initModality(Modality.APPLICATION_MODAL);
                gameModeChooserStage.setResizable(false);
                gameModeChooserStage.setScene(new Scene(gameModeChooser));
                gameModeChooserStage = settingsHelper.configure(gameModeChooserStage, "Game Mode Chooser");
                gameModeChooserStage.showAndWait();
                break;

            case "endGameResults":
                loader.setLocation(getClass().getResource("/UI/endGameResults/endGameResults.fxml"));
                Parent endGameResults = loader.load();
                Stage endGameResultsStage = new Stage();
                endGameResultsStage.initModality(Modality.APPLICATION_MODAL);
                endGameResultsStage.setResizable(false);
                endGameResultsStage.setScene(new Scene(endGameResults));
                endGameResultsStage = settingsHelper.configure(endGameResultsStage, "GAME OVER");
                endGameResultsStage.showAndWait();
                break;
        }
    }
}
