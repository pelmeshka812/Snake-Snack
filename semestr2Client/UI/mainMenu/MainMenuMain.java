package UI.mainMenu;

import UI.mainMenu.nickNameChooser.NickNameChooserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import UI.helpers.WindowSettingsHelper;

public class MainMenuMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage nickNameChooser = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UI/mainMenu/nickNameChooser/nickNameChooser.fxml"));
        Parent root = loader.load();
        nickNameChooser.setResizable(false);
        nickNameChooser.setScene(new Scene(root));
        nickNameChooser = WindowSettingsHelper.configure(nickNameChooser, "Nick Name Chooser");
        nickNameChooser.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
