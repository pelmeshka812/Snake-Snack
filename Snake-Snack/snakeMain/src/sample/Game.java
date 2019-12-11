package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import static sample.Direction.*;
import static sample.Direction.RIGHT;

public class Game {
    private int size = 500;

    GraphicsContext gc ;
    boolean lost = false;
    Canvas canvas;
    final int up = 1;
    final int right = 2;
    final int down = 3;
    final int left = 4;
    private Thread foodGeneratorThread = new Thread(() -> {
        while (true) {
            generateFood();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Snake snake = new Snake(getSize()/2, getSize()/2);

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        canvas = new Canvas(getSize(), getSize());
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        foodGeneratorThread.start();

        root.setOnKeyPressed(e -> {
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
            if (direction != null) snake.setDirection(direction);
        });
        Scene scene = new Scene(root, getSize(), getSize());
        primaryStage.setTitle("Snake war");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            while (true) {
                snake.move(gc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void generateFood(){
        int x = new Random().nextInt(size);
        int y = new Random().nextInt(size);
        gc.setFill(Color.rgb(0, 255, 0));
        gc.fillOval(x, y, Dot.DOT_SIZE, Dot.DOT_SIZE);
    }
}
