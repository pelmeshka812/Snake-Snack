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
            Gson gson = new Gson();
            socket.sendMessage(gson.toJson("/food" + x + " " + y)); });
        this.socket = socket;

    }



}
