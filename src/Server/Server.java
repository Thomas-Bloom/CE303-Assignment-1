package Server;

import assignment.GameState;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(16000);
        System.out.println("CE303 Server Running");

        try{
            while(true){
                GameState gameState = new GameState();
                Player player0 = new Player(serverSocket.accept(), '0');
                Player player1 = new Player(serverSocket.accept(), '1');
                Player player2 = new Player(serverSocket.accept(), '2');
                Player player3 = new Player(serverSocket.accept(), '3');
                Player player4 = new Player(serverSocket.accept(), '4');

                gameState.currentPlayer = player0;
                player0.start();

            }
        }
        finally {
            serverSocket.close();
        }

    }
}