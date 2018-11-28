package Server;

import UserInterface.Cell;
import Utilities.GameState;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Make send method to send to all players

public class Server {
    public static final int PORT = 17000;
    private static List<Player>playerList = new ArrayList<Player>();

    private static String redStartPos;
    private static String blueStartPos;

    public Server(){
        //placePlayerStarts();
    }

    public static void main(String[] args) {
        try{

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is now running");

            try{
                Random random = new Random();
                int randXPlayer0 = random.nextInt(6);
                int randYPlayer0 = random.nextInt(10);
                //redStartPos = "LEGAL " + randXPlayer0 + randYPlayer0;
                redStartPos = "LEGAL " + randXPlayer0 + randYPlayer0 + "0";

                int randXPlayer1 = random.nextInt(6);
                int randYPlayer1 = random.nextInt(10);
                //blueStartPos = "LEGAL " + randXPlayer1 + randYPlayer1;
                blueStartPos = "LEGAL " + randXPlayer1 + randYPlayer1 + "1";
                
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

                    // Sends messages to place starting cells

                    for(int i = 0; i < playerList.size(); i++){
                        playerList.get(i).sendMessage(redStartPos);
                        playerList.get(i).sendMessage(blueStartPos);
                    }

                    // Send all game messages
                    for(int i = 0; i < playerList.size(); i++){
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

    public void placePlayerStarts(){
        Random random = new Random();
        int randXPlayer0 = random.nextInt(6);
        int randYPlayer0 = random.nextInt(10);
        //redStartPos = "LEGAL " + randXPlayer0 + randYPlayer0;
        redStartPos = "LEGAL 00 0";

        int randXPlayer1 = random.nextInt(6);
        int randYPlayer1 = random.nextInt(10);
        //blueStartPos = "LEGAL " + randXPlayer1 + randYPlayer1;
        blueStartPos = "LEGAL 01 1";

        //gameState.board[randXPlayer0][randYPlayer0].setCellColor(Color.red);
        //gameState.board[randXPlayer1][randYPlayer1].setCellColor(Color.blue);

        System.out.println("Starting RED at " + randXPlayer0 + ", " + randYPlayer0);
        System.out.println("Starting BLUE at " + randXPlayer1 + ", " + randYPlayer1);
    }
}
