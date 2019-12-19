package game;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Direction;

import static model.Direction.*;

public class Game {

    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;

    public void start(Stage primaryStage) throws Exception {
        PlayingField root = new PlayingField(WINDOW_WIDTH, WINDOW_HEIGHT);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            Direction direction = null;
            if (key.equals(KeyCode.UP)) {
                direction = UP;
            } else if (key.equals(KeyCode.DOWN)) {
                direction = DOWN;
            } else if (key.equals(KeyCode.LEFT)) {
                direction = LEFT;
            }
            if (key.equals(KeyCode.RIGHT)) {
                direction = RIGHT;
            }
            if (direction != null) root.changeSnakeDirection(direction);
        });
        primaryStage.setTitle("Snake war");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
