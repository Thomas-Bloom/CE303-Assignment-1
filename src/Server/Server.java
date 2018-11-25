package Server;

import Utilities.GameState;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static final int PORT = 17000;
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is now running");

            try{
                // Constantly look for commands coming in
                while(true){
                    GameState gameState = new GameState();

                    // Create the player objects
                    // If a client is present then a new player object is created, otherwise null
                    Player player0 = new Player(serverSocket.accept(), '0');
                    Player player1 = new Player(serverSocket.accept(), '1');

                    // Start all the player threads (won't start if they are null)
                    gameState.incrementPlayerNum();
                    player0.start();

                }
            }
            finally {
                serverSocket.close();
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
