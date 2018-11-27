package Server;

import UserInterface.Cell;
import Utilities.Coordinate;
import Utilities.GameState;

import java.awt.*;
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
    //private Cell[][] board;
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
        // TODO: Make checks to ensure player is allowed to place on that cell
        System.out.println("Player(" + playerNumber + ") Moved: " + coord.getxPos() + ", " + coord.getyPos());
        Cell newCell = new Cell(coord.getxPos(), coord.getyPos(), playerNumber);
        gameState.board[coord.getxPos()][coord.getyPos()] = newCell;
        return true;
    }

    // This will send a message to the client
    public void sendMessage(String message){
        System.out.println(message);
        if(message.startsWith("LEGAL")){
            int xPos = Character.getNumericValue(message.charAt(6));
            int yPos = Character.getNumericValue(message.charAt(7));
            output.println("LEGAL " + xPos + yPos + " " + playerNumber);
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

                    // If the move is accepted, send out the message to all players
                    if(isMoveLegal(new Coordinate(xPos, yPos))){
                        //server.setMessage("LEGAL " + xPos + yPos);
                        gameState.setMessage("LEGAL " + xPos + yPos);
                        //System.out.println("Message: " + server.message);
                        //output.println("LEGAL " + xPos + yPos + " " + playerNumber);
                        gameState.board[xPos][yPos].setPlayerNum(playerNumber);
                    }
                }

                if(command.startsWith("QUIT")){
                    return;
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

    public void setPlayerNumber(char playerNumber) {
        this.playerNumber = playerNumber;
    }
}
