package model;

import annotation.MyAnno;
import game.PlayingField;
import javafx.scene.paint.Color;
import model.Direction;
import model.Dot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Snake  {

    private static final int DOT_RADIUS = 8;
    @MyAnno(name = "body")
    ArrayList <Dot> body = new ArrayList<>();

    private PlayingField playingField;
    private Direction direction = Direction.RIGHT;

    public Snake(PlayingField playingField, Point position) {
        this.playingField = playingField;
        Dot dot = new Dot(playingField, position, DOT_RADIUS);
        dot.setFill(Color.BLUE);
        body.add(dot);
        for (int i = 1; i < 3; i++) {
            position = new Point(position.x - DOT_RADIUS * 2, position.y);
            dot = new Dot(playingField, position, DOT_RADIUS);
            dot.setFill(Color.YELLOW);
            body.add(dot);
        }
    }

    public void setDirection(Direction direction) {
        boolean isDirectionToInside = this.direction == Direction.RIGHT && direction == Direction.LEFT;
        if (!isDirectionToInside) isDirectionToInside = this.direction == Direction.LEFT && direction == Direction.RIGHT;
        if (!isDirectionToInside) isDirectionToInside = this.direction == Direction.UP && direction == Direction.DOWN;
        if (!isDirectionToInside) isDirectionToInside = this.direction == Direction.DOWN && direction == Direction.UP;

        if (!isDirectionToInside) this.direction = direction;
    }

    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setCenterX(body.get(i - 1).getCenterX());
            body.get(i).setCenterY(body.get(i - 1).getCenterY());
        }
        Dot head = body.get(0);
        if (direction == Direction.UP) {
            head.setCenterY(head.getCenterY() - DOT_RADIUS * 2);
        } else if (direction == Direction.DOWN) {
            head.setCenterY(head.getCenterY() + DOT_RADIUS * 2);
        } else if (direction == Direction.RIGHT) {
            head.setCenterX(head.getCenterX() + DOT_RADIUS * 2);
        } else if (direction == Direction.LEFT) {
            head.setCenterX(head.getCenterX() - DOT_RADIUS * 2);
        }

        for (int i = 1; i < body.size(); i++) {
            if (head.getCenterX() == body.get(i).getCenterX() && head.getCenterY() == body.get(i).getCenterY()) {
                playingField.gameOver();
            }
        }
    }

    public void grow() {
        Dot prelast = body.get(body.size() - 2);
        Dot last = body.get(body.size() - 1);

        Point position = new Point((int) last.getCenterX(), (int) last.getCenterY());
        if (prelast.getCenterX() < last.getCenterX()) {
            position.x += DOT_RADIUS * 2;
        } else if (prelast.getCenterX() > last.getCenterX()) {
            position.x -= DOT_RADIUS * 2;
        } else if (prelast.getCenterY() < last.getCenterY()) {
            position.y += DOT_RADIUS * 2;
        } else if (prelast.getCenterY() > last.getCenterY()) {
            position.y -= DOT_RADIUS * 2;
        }
        Dot dot = new Dot(playingField, position, DOT_RADIUS);
        dot.setFill(Color.YELLOW);
        body.add(dot);
    }

    public void eatIfCan(List<Dot> food) {
        Dot head = body.get(0);
        int xMin = (int) (head.getCenterX() - head.getRadius());
        int xMax = (int) (head.getCenterX() + head.getRadius());
        int yMin = (int) (head.getCenterY() - head.getRadius());
        int yMax = (int) (head.getCenterY() + head.getRadius());
        for (Dot dot : food){
            int foodXMin = (int) (dot.getCenterX() - dot.getRadius());
            int foodXMax = (int) (dot.getCenterX() + dot.getRadius());
            int foodYMin = (int) (dot.getCenterY() - dot.getRadius());
            int foodYMax = (int) (dot.getCenterY() + dot.getRadius());

            if (foodXMax >= xMin && foodXMin <= xMax && foodYMax >= yMin && foodYMin <= yMax ){
                playingField.getChildren().remove(dot);
                grow();

            }
        }
    }

}
