package sample;

public class Game {
    private static int size = 500;
    private static  int dot_size =10;

    public static int getSize() {
        return size;
    }

    public static int getDot_size() {
        return dot_size;
    }

    public static void setDot_size(int dot_size) {
        Game.dot_size = dot_size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
