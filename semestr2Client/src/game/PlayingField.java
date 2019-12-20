package game;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Direction;
import model.Dot;
import model.Snake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayingField extends Pane {

    private int width, height;
    private Snake snake;
    private List<Dot> food = new ArrayList<>();
    private Thread snakeMoverThread = new Thread(() -> {
        while (true) {
            Platform.runLater(() -> {
                snake.move();
                snake.eatIfCan(food);
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

   /* private Thread foodGeneratorThread = new Thread(() -> {
        while (true) {
            Platform.runLater(this::generateFood);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });*/


    public PlayingField(int width, int height) {
        this.width = width;
        this.height = height;

        snake = new Snake(this, new Point(width / 2, height / 2));
        snakeMoverThread.start();
        //foodGeneratorThread.start();
    }

    public void changeSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    /*private void generateFood() {
        int x = new Random().nextInt(width);
        int y = new Random().nextInt(height);
        Dot dot = new Dot(this, new Point(x, y));
        dot.setFill(Color.GREEN);
        food.add(dot);
    }*/


    private void addFood(Point point){
        Dot dot = new Dot( this, point);
        dot.setFill(Color.GREEN);
        food.add(dot);
    }
    public void gameOver() {
        snakeMoverThread.stop();
        //foodGeneratorThread.stop();

        getChildren().clear();
        setStyle("-fx-background-color: #000000");
        Text text = new Text("GAME OVER");
        text.setFill(Color.RED);
        getChildren().add(text);
    }

}
