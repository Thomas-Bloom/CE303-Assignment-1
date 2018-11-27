package Server;

import Utilities.GameState;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

// Make send method to send to all players

public class Server {
    public static final int PORT = 17000;
    private static List<Player>playerList = new ArrayList<Player>();

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
                    Player player0 = new Player(serverSocket.accept(), '0', gameState);
                    Player player1 = new Player(serverSocket.accept(), '1', gameState);
                    //Player player2 = new Player(serverSocket.accept(), '2', gameState);

                    // Add players to the list
                    // Show how many players are in game
                    // Send messages to players
                    playerList.add(player0);
                    playerList.add(player1);
                    //playerList.add(player2);

                    // Start all the player threads (won't start if they are null)
                    player0.start();
                    player1.start();
                    //player2.start();


                    for(int i = 0; i < playerList.size(); i++){
                        //playerList.get(i).sendMessage("LEGAL 06");
                        //System.out.println("Test: " + i);
                        System.out.println(i);
                        playerList.get(i).sendMessage(gameState.getMessage());

                        // Keep for loop going forever
                        if(i == playerList.size() - 1){
                            i = -1;
                        }
                    }


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
