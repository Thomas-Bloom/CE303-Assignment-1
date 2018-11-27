package Utilities;


import UserInterface.Cell;
import java.awt.*;
import java.util.Random;

public class GameState {
    public static final int ROWS = 6;
    public static final int COLUMNS = 10;
    private String message;

    // This is the board the server sees
    public Cell[][] board;

    public GameState(){
        message = "";
        board = new Cell[ROWS][COLUMNS];

        for(int x = 0; x < COLUMNS; x++){
            for(int y = 0; y < ROWS; y++){

            }
        }

        //placePlayerStarts();
    }

    public void placePlayerStarts(){
        Random random = new Random();
        int randXPlayer0 = random.nextInt(ROWS);
        int randYPlayer0 = random.nextInt(COLUMNS);
        Cell randPlayer0 = new Cell(randXPlayer0, randYPlayer0, '0');
        randPlayer0.setCellColor(Color.red);

        int randXPlayer1 = random.nextInt(ROWS);
        int randYPlayer1 = random.nextInt(COLUMNS);
        Cell randPlayer1 = new Cell(randXPlayer1, randYPlayer1, '1');
        randPlayer1.setCellColor(Color.pink);

        int randXPlayer2 = random.nextInt(ROWS);
        int randYPlayer2 = random.nextInt(COLUMNS);
        Cell randPlayer2 = new Cell(randXPlayer2, randYPlayer2, '2');
        randPlayer2.setCellColor(Color.orange);

        int randXPlayer3 = random.nextInt(ROWS);
        int randYPlayer3 = random.nextInt(COLUMNS);
        Cell randPlayer3 = new Cell(randXPlayer3, randYPlayer3, '3');
        randPlayer3.setCellColor(Color.green);

        int randXPlayer4 = random.nextInt(ROWS);
        int randYPlayer4 = random.nextInt(COLUMNS);
        Cell randPlayer4 = new Cell(randXPlayer4, randYPlayer4, '4');
        randPlayer4.setCellColor(Color.blue);

        board[randXPlayer0][randYPlayer0] = randPlayer0;
        board[randXPlayer1][randYPlayer1] = randPlayer1;
        board[randXPlayer2][randYPlayer2] = randPlayer2;
        board[randXPlayer3][randYPlayer3] = randPlayer3;
        board[randXPlayer4][randYPlayer4] = randPlayer4;
        System.out.println("Starting RED at " + randXPlayer0 + ", " + randYPlayer0);
        System.out.println("Starting PINK at " + randXPlayer1 + ", " + randYPlayer1);
        System.out.println("Starting ORANGE at " + randXPlayer2 + ", " + randYPlayer2);
        System.out.println("Starting GREEN at " + randXPlayer3 + ", " + randYPlayer3);
        System.out.println("Starting BLUE at " + randXPlayer4 + ", " + randYPlayer4);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
