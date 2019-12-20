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




    public  void sendMessage(String str){
        for (ClientHandler client : clients) {
            client.out.println(str);
        }
    }
    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;


        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            if (clients.size() < 5) {
                clients.add(this);
                if(clients.size() == 5){
                    sendMessage("/start");
                }

            }
            else {
                out.println("Too many snakes");
            }
        }
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                nickname = in.readLine();
                String inputLine;
                while ((inputLine = in.readLine())!= null){
                    Scanner scanner = new Scanner(inputLine);
                    if(scanner.next().startsWith("/updateRecord")) {
                        Record record = RecordsDao.get().getByNickname(nickname);
                        int balls = scanner.nextInt();
                        if (record.getValue() < balls) {
                            record.setValue(balls);
                        }
                    } else sendMessage(inputLine);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
