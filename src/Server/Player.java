package Server;

import assignment.Coordinates;
import assignment.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread{
    public char playerNumber;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    GameState gameState;

    public Player(Socket socket, GameState gameState, char num){
        this.socket = socket;
        this.playerNumber = num;
        this.gameState = gameState;

        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            output.println("WELCOME " + num);
            output.println("Waiting for more players...");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            output.println("MESSAGE All players connected... Game starting");

            if(playerNumber == 0){
                output.println("You go first");
            }

            while(true){
                String command = input.readLine();
                if(command.startsWith("MOVE")){
                    int locationX = Integer.parseInt(command.substring(5));
                    int locationY = Integer.parseInt(command.substring(6));

                    Coordinates coord = new Coordinates(locationX, locationY);
                    if(gameState.isMoveAllowed(coord, this)){
                        output.println("VALID");
                        System.out.println("Valid");
                    }
                    else{
                        output.println("MESSAGE ");
                    }
                }
            }
        }
        catch(Exception e){
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