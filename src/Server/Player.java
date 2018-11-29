package Server;

import UserInterface.Cell;
import Utilities.Coordinate;
import Utilities.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// This is an extension to the server/Gamestate classes

public class Player extends Thread{
    private char playerNumber;
    private Socket socket;
    // Input = Commands coming in from clients
    private BufferedReader input;
    // Output = Commands going out to the clients
    private PrintWriter output;
    private GameState gameState;

    public Player(Socket socket, char num, GameState gameState){
        this.socket = socket;
        this.gameState = gameState;
        playerNumber = num;

        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            output.println("WELCOME " + playerNumber);
            System.out.println("Player(" + playerNumber + ") has connected");
            output.println("MESSAGE Waiting for more players");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private synchronized boolean isMoveLegal(Coordinate coord){
        // If it is the correct player's turn
        if(Character.getNumericValue(playerNumber) == gameState.getPlayerTurn()){
            
            // Check to make sure there isn't a cell already there
            if(gameState.board[coord.getxPos()][coord.getyPos()].getPlayerNum() == 'n'){
                // Check to see if next to or diagonal to a cell owned by the player

                // TOP_CENTRE
                // -1 0
                if((coord.getxPos() - 1) >= 0 && (coord.getxPos() -1) <= gameState.ROWS - 1 && coord.getyPos() >= 0 && coord.getyPos() <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos() - 1][coord.getyPos()].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }

                // TOP_RIGHT
                // -1 1
                if((coord.getxPos() - 1) >= 0 && (coord.getxPos() -1) <= gameState.ROWS - 1 && (coord.getyPos() + 1) >= 0 && (coord.getyPos() + 1) <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos() - 1][coord.getyPos() + 1].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }
                // CENTRE_RIGHT
                // 0 1
                if(coord.getxPos() >= 0 && coord.getxPos() <= gameState.ROWS - 1 && (coord.getyPos() + 1) >= 0 && (coord.getyPos() + 1) <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos()][coord.getyPos() + 1].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }

                }
                // BOTTOM_RIGHT
                // 1 1
                if((coord.getxPos() + 1) >= 0 && (coord.getxPos() + 1) <= gameState.ROWS - 1 && (coord.getyPos() + 1) >= 0 && (coord.getyPos() + 1) <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos() + 1][coord.getyPos() + 1].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }
                // CENTRE_BOTTOM
                // 1 0
                if((coord.getxPos() + 1) >= 0 && (coord.getxPos() + 1) <= gameState.ROWS - 1 && coord.getyPos() >= 0 && coord.getyPos() <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos() + 1][coord.getyPos()].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }
                // BOTTOM LEFT
                // 1 -1
                if((coord.getxPos() + 1) >= 0 && (coord.getxPos() + 1) <= gameState.ROWS - 1 && (coord.getyPos() - 1) >= 0 && (coord.getyPos() - 1) <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos() + 1][coord.getyPos() - 1].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }
                // CENTRE LEFT
                // 0 -1
                if(coord.getxPos() >= 0 && coord.getxPos() <= gameState.ROWS - 1 && (coord.getyPos() - 1) >= 0 && (coord.getyPos() - 1) <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos()][coord.getyPos() - 1].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }
                // TOP LEFT
                // -1 -1
                if((coord.getxPos() - 1) >= 0 && (coord.getxPos() -1) <= gameState.ROWS - 1 && (coord.getyPos() - 1) >= 0 && (coord.getyPos() - 1) <= gameState.COLUMNS - 1){
                    if(gameState.board[coord.getxPos() - 1][coord.getyPos() - 1].getPlayerNum() == playerNumber) {
                        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
                        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
                        System.out.println("Placed");
                        return true;
                    }
                }
                // non-functioning code related to win/lose condition
                else{
                    if(playerNumber == '0'){
                        gameState.player0Blocked = true;
                        output.println("END 1");
                    }
                    if(playerNumber == '1'){
                        gameState.player1Blocked = true;
                        output.println("END 0");
                    }
                }
                return false;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    // This will send a message to the client
    public void sendMessage(String message){
        //System.out.println("Message: " + message);
        if(message.startsWith("LEGAL")){
            int xPos = Character.getNumericValue(message.charAt(6));
            int yPos = Character.getNumericValue(message.charAt(7));
            char playerNum = message.charAt(9);
            char card = message.charAt(11);
            output.println("LEGAL " + xPos + yPos + " " + playerNum + " " + card);
        }
    }

    public void run(){
        try{
            output.println("MESSAGE Enough players connected");

            if(playerNumber == '0'){
                output.println("MESSAGE You go first");
            }

            while(true){
                // Get the incoming command from the client
                String command = input.readLine();

                // Client issued a move command
                if(command.startsWith("MOVE")){
                    int xPos = Character.getNumericValue(command.charAt(5));
                    int yPos = Character.getNumericValue(command.charAt(6));
                    char num = command.charAt(8);
                    // Card codes
                    //
                    // 0 = none
                    // 1 = double
                    // 2 = replacement
                    // 3 = freedom
                    char card = command.charAt(10);

                    // If the move is accepted, send out the message to all players
                    if(isMoveLegal(new Coordinate(xPos, yPos))){
                        gameState.setMessage("LEGAL " + xPos + yPos + " " + num + " " + card);
                        if(card != '1')
                            gameState.nextPlayerTurn();
                        gameState.board[xPos][yPos].setPlayerNum(num);
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                socket.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
