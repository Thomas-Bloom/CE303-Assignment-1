package Server;

import UserInterface.Cell;
import UserInterface.Window;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Window window;
    private Cell[][] cellBoard = new Cell[6][10];

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

                    System.out.println("Position (" + xPos + ", " + yPos + ") is legal");
                    //cellBoard[xPos][yPos] = new Cell(xPos, yPos, playerNum);
                    //cellBoard[xPos][yPos].setCellColor(Color.red);
                    window.cellBoard[xPos][yPos].setCellColor(Color.red);

                    output.println("UPDATE " + xPos + yPos);
                }

                if(response.startsWith("ENEMY_MOVED")){
                    System.out.println("Received message from enemy");

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
