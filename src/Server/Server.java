package Server;

import assignment.GameState;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static GameState gameState;
    private static int[][] board = new int[GameState.ROWS][GameState.COLUMNS];
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(16000);
        System.out.println("CE303 Server Running");
        gameState = new GameState();

        for(int x = 0; x < GameState.ROWS; x++){
            for(int y = 0; y < GameState.COLUMNS; y++){
                board[x][y] = 0;
            }
        }

        try{
            while(true){
                for(int x = 0; x < GameState.ROWS; x++){
                    for(int y = 0; y < GameState.COLUMNS; y++){

                    }
                }
                System.out.println("Connected Players: " + gameState.numberOfConnectedPlayers);
                Player player0 = new Player(serverSocket.accept(), gameState, '0');
                Player player1 = new Player(serverSocket.accept(), gameState, '1');
                Player player2 = new Player(serverSocket.accept(), gameState, '2');
                Player player3 = new Player(serverSocket.accept(), gameState, '3');
                Player player4 = new Player(serverSocket.accept(), gameState, '4');

                gameState.currentPlayerNum = player0.playerNumber;
                player0.start();

            }
        }
        finally {
            serverSocket.close();
        }

    }
}