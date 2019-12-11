package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class SnakeUtils {

    int size = Game.getSize();

    int dot_size = Game.getDot_size();

    public void draw(GraphicsContext gc, int length, int x[], int y[]) {
        gc.setFill(Paint.valueOf("blue"));
        gc.fillOval(x[0], y[0], dot_size, dot_size);
        for (int i = 0; i < length; i++) {
            gc.setFill(Paint.valueOf("yellow"));
            gc.fillOval(x[i], y[i], dot_size, dot_size);
        }
    }


}
