package UI.helpers;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WindowSettingsHelper {

    public static Stage configure(Stage stage, String name) {

        stage.getIcons().add(new Image("UI/globalAssets/snakeShackIcon.png"));
        stage.setTitle(name);
        return stage;
    }

    public static Stage configure(Stage stage) {

        stage.getIcons().add(new Image("UI/globalAssets/snakeShackIcon.png"));
        stage.setTitle("Snake Shack");
        return stage;
    }
}
