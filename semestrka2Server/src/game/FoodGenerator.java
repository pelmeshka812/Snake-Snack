package game;



import com.google.gson.Gson;
import socket.Server;

import java.awt.*;
import java.util.Random;


public class FoodGenerator extends Thread {
    private Server socket;
    public FoodGenerator(Server socket) {
        super(() -> {
            int x = new Random().nextInt(1280);
            int y = new Random().nextInt(720);
            Point point = new Point(x, y);
            Gson gson = new Gson();
            socket.sendMessage(gson.toJson(point)); });
        this.socket = socket;

    }



}
