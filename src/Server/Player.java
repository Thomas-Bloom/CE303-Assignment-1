package Server;

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

    public Player(Socket socket, char num){
        this.socket = socket;
        playerNumber = num;

        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            output.println("WELCOME " + playerNumber);
            output.println("MESSAGE Waiting for more players");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            output.println("MESSAGE Enough players connected");

            if(playerNumber == 0){
                output.println("MESSAGE You go first");
            }

            while(true){
                // Get the incoming command from the client
                String command = input.readLine();

                // Client issued a move command
                if(command.startsWith("MOVE")){
                    System.out.println("Player Moved");
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
