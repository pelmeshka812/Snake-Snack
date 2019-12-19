package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        Server server = new Server();
        server.start(8888);
    }

    private void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        while (true){
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
    public void sendMessage( String message){
        for (ClientHandler client: clients) {
            client.out.println(message);
        }
    }
    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;


        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            clients.add(this);
            System.out.println("New snake");
        }
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                nickname = in.readLine();
                String inputLine;
                while ((inputLine = in.readLine())!= null){
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
