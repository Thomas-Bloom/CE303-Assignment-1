package Server;

import UserInterface.LevelGrid;
import UserInterface.Window;
import assignment.GameState;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private Color currentColor;

    private Window window = new Window("CE303 Assignment", 6, 10);

    public Client() throws Exception{
        socket = new Socket("localhost", 16000);

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception{
        String response;
        try{
            response = input.readLine();

            if(response.startsWith("WELCOME")){
                char num = response.charAt(8);
                window.setTitle("CE303 Assignment: Player(" + num + ")");
                window.levelGrid.currentPlayerNum = num;
            }

            while(true){
                response = input.readLine();
                if(response.startsWith("VALID")){

                }
            }
        }
        finally {
            socket.close();
        }
    }

    public static void main(String[] args) throws Exception{
        Client client = new Client();
        client.play();
    }
}