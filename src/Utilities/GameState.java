package Utilities;

import UserInterface.Cell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameState {
    public static final int ROWS = 6;
    public static final int COLUMNS = 10;

    // This is the board the server sees
    public Cell[][] board;

    private int currentPlayerNumber;

    public GameState(){
         board = new Cell[ROWS][COLUMNS];
        currentPlayerNumber = 0;
    }

    public void incrementPlayerNum(){
        currentPlayerNumber++;
    }
}