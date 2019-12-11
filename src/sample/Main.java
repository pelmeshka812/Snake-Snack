package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.SnakeUtils;


public class Main extends Application {
    final int size = 500;
    final int dot_size = 10;
    final int up = 1;
    final int right = 2;
    final int down = 3;
    final int left = 4;
    int delay = 50;
    int length = 3;
    int dir = 2;
    int food_x;
    int food_y;
    SnakeUtils snake;
    int x[] = new int[size*size];
    int y[] = new int[size*size];
    Thread game;
    boolean lost = false;
    Canvas canvas;
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        canvas = new Canvas(size, size);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);
        startGame();
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                KeyCode key = e.getCode();
                if (key.equals(KeyCode.UP)) dir = up;
                if (key.equals(KeyCode.DOWN)) dir = down;
                if (key.equals(KeyCode.LEFT)) dir = left;
                if (key.equals(KeyCode.RIGHT)) dir = right;
            }

        });
        Scene scene = new Scene(root, size, size);

        primaryStage.setTitle("Snake war!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, size, size);
        if (lost) {
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("Game Over", size / 2 - 50, size / 2 - 15);
            game.stop();
        }
    }
        private void drawDot (GraphicsContext gc){
            for (int i = 0; i < Math.random() * 10; i++) {
                food_x = (int) (Math.random() * ((size / dot_size) - 1)) * dot_size;
                food_y = (int) (Math.random() * ((size / dot_size) - 1)) * dot_size;
                if (!lost) {
                    gc.setFill(Paint.valueOf("green"));
                    gc.fillOval(food_x, food_y, dot_size, dot_size);

                }
            }
        }

        public static void main (String[]args){
            launch(args);
        }

        private void startGame () {
            length = 3;
            for (int i = 0; i < length; i++) {
                x[i] = 50 - i * dot_size;
                y[i] = 50;
            }
            locateFood();

            game = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (!lost) {
                            checkFood();
                            checkCollision();
                            move();
                            snake.draw(gc, length, x, y);
                        }

                        draw(gc);


                        drawDot(gc);


                        try {
                            Thread.sleep(delay);
                        } catch (Exception e) {
                        }
                    }
                }


            });
            game.start();
        }

        private void locateFood () {
            food_x = (int) (Math.random() * ((size / dot_size) - 1)) * dot_size;
            food_y = (int) (Math.random() * ((size / dot_size) - 1)) * dot_size;
        }

        private void checkFood () {
            if (x[0] == food_x && y[0] == food_y) {
                length++;
                locateFood();
            }
        }

        private void checkCollision () {
            if (x[0] >= size) lost = true;
            if (y[0] >= size) lost = true;
            if (x[0] < 0) lost = true;
            if (y[0] < 0) lost = true;
            for (int i = 3; i < length; i++)
                if (x[0] == x[i] && y[0] == y[i]) lost = true;
        }

        private void move () {
            for (int i = length - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (dir == up) y[0] -= dot_size;
            if (dir == down) y[0] += dot_size;
            if (dir == right) x[0] += dot_size;
            if (dir == left) x[0] -= dot_size;
        }
    }
