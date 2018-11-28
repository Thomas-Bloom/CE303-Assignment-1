package Utilities;

import UserInterface.Cell;

public class GameState {
    public final int ROWS = 6;
    public final int COLUMNS = 10;
    private String message;
    private int playerTurn;

    // This is the board the server sees
    public Cell[][] board;

    public GameState(){
        message = "";
        board = new Cell[ROWS][COLUMNS];
        playerTurn = 0;

        for(int x = 0; x < ROWS; x++){
            for(int y = 0; y < COLUMNS; y++){
                board[x][y] = new Cell(x, y, 'n');
            }
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public void nextPlayerTurn(){
        playerTurn++;
        if(playerTurn > 1)
            playerTurn = 0;
    }
}
