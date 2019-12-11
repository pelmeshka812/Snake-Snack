package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sample.Direction.*;

public class Main extends Application {
    Game game = new Game();
    GraphicsContext gc ;
    boolean lost = false;

    Canvas canvas;
    Direction direction;
    final int up = 1;
    final int right = 2;
    final int down = 3;
    final int left = 4;



    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game =new Game();
        game.start(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
