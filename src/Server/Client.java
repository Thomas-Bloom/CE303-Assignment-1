package Server;

import UserInterface.Window;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Window window;

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private final int PORT = 17000;

    public Client(){
        try{
            socket = new Socket("localhost", PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void play(){
        // Response from server
        String response;

        try{
            response = input.readLine();
            if(response.startsWith("WELCOME")){
                char playerNum = response.charAt(8);
                System.out.println("Player " + playerNum + " has connected");
                window = new Window("CE303 Assignment", 6, 10, input, output, playerNum);
                window.setTitle("CE303 Assignment: Player " + playerNum);

                // This is where certain cells can be changed based on information sent from the server
                // E.g. this sets the first cell to be blue
                //window.cellBoard[0][0].setCellColor(Color.blue);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        while(true){
            try{
                response = input.readLine();

                if(response.startsWith("LEGAL")){
                    int xPos = Character.getNumericValue(response.charAt(6));
                    int yPos = Character.getNumericValue(response.charAt(7));

                    //cellBoard[xPos][yPos] = new Cell(xPos, yPos, playerNum);
                    //cellBoard[xPos][yPos].setCellColor(Color.red);
                    //System.out.println("xpos: " + xPos + ", yPos: " + yPos);
                    window.cellBoard[xPos][yPos].setCellColor(Color.red);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.play();
    }
}
