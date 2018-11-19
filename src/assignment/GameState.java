package assignment;

import Server.Player;

import java.awt.*;
import java.util.Set;

// This class (not yet fully implemented) will give access to the current state of the game.
public final class GameState {
    public static final int ROWS = 6;
    public static final int COLUMNS = 10;

    public Player currentPlayer;

    public static Color playerColor1 = Color.red;
    public static Color playerColor2 = Color.blue;
    public static Color playerColor3 = Color.green;
    public static Color playerColor4 = Color.pink;
    public static Color playerColor5 = Color.yellow;

    public GameState(){
    }

    // Returns a rectangular matrix of board cells, with six rows and ten columns.
    // Zeros indicate empty cells.
    // Non-zero values indicate stones of the corresponding player.  E.g., 3 means a stone of the third player.
    public int[][] getBoard() {
        int[][] board = new int[ROWS][COLUMNS];
        return board;
    }

    // Returns the set of influence cards available to the given player.
    public Set<InfluenceCard> getAvailableInfluenceCards(int player) {
        return null;
    }

    // Checks if the specified move is allowed for the given player.
    public boolean isMoveAllowed(Move move, int player) {
        return false;
    }
}

