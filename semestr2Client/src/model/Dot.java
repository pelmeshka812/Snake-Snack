package model;

import annotation.MyAnno;
import game.PlayingField;
import javafx.scene.shape.Circle;

import java.awt.*;

public class Dot extends Circle {
    @MyAnno(name = "x")
    private int x;
    @MyAnno(name = "y")
    private int y;

    public static final int RADIUS = 3;

    private PlayingField playingField;

    public Dot(PlayingField playingField, Point position) {
        this(playingField, position, RADIUS);
    }

    public Dot(PlayingField playingField, Point position, int radius) {
        super(position.x, position.y, radius);
        this.playingField = playingField;
        playingField.getChildren().add(this);
    }

    public void destroy() {
        playingField.getChildren().remove(this);
    }

}
