package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread{
    private char playerNumber;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public Player(Socket socket, char num){
        this.socket = socket;
        this.playerNumber = num;

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