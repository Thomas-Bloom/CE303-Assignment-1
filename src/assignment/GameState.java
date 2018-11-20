package assignment;

import Server.Player;

import java.awt.*;
import java.util.Set;

// This class (not yet fully implemented) will give access to the current state of the game.
public final class GameState {
    public static final int ROWS = 6;
    public static final int COLUMNS = 10;

    public int numberOfConnectedPlayers;
    public static int currentPlayerNum;

    public static Color[] playerColors = {Color.red, Color.blue, Color.green, Color.pink, Color.yellow};

    public static Color currentColor = Color.red;
    private int[][] board = new int[ROWS][COLUMNS];

    public GameState(){
        numberOfConnectedPlayers = 0;
    }

    public static void nextPlayer(){
        currentPlayerNum++;
        if(currentPlayerNum == playerColors.length)
            currentPlayerNum = 0;
        currentColor = playerColors[currentPlayerNum];
    }

    // Returns a rectangular matrix of board cells, with six rows and ten columns.
    // Zeros indicate empty cells.
    // Non-zero values indicate stones of the corresponding player.  E.g., 3 means a stone of the third player.
    public int[][] getBoard() {
        return board;
    }

    // Returns the set of influence cards available to the given player.
    public Set<InfluenceCard> getAvailableInfluenceCards(int player) {
        return null;
    }

    // Checks if the specified move is allowed for the given player.
    public synchronized boolean isMoveAllowed(Coordinates coord, Player player) {
        System.out.println("test");
        // If current player's turn and the cell is empty
        if(player.playerNumber == currentPlayerNum && board[coord.getX()][coord.getY()] == 0){
            board[coord.getX()][coord.getY()] = player.playerNumber;
            nextPlayer();
            return false;
        }
        return false;
    }
}

