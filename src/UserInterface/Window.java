package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Window extends JFrame {
    public static final int WIDTH = 720;
    public static final int HEIGHT = (int)(WIDTH * 0.6);

    public Cell[][] cellBoard;
    private Cell currentCell;
    private BufferedReader input;
    private PrintWriter output;
    private char playerNum;
    private int currentTurn;

    public Window(String title, int rows, int columns, BufferedReader input, PrintWriter output, char playerNum){
        super(title);

        currentTurn = 0;
        cellBoard = new Cell[rows][columns];
        this.input = input;
        this.output = output;
        this.playerNum = playerNum;

        // Add panels to the window
        add(new GridPanel(rows, columns, cellBoard), BorderLayout.CENTER);
        add(new CardPanel(), BorderLayout.SOUTH);

        // Setup window operations
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    class GridPanel extends JPanel{
        public GridPanel(int rows, int columns, Cell[][] board){
            setPreferredSize(new Dimension(300, 300));
            setLayout(new GridLayout(rows, columns));

            for(int x = 0; x < rows; x++){
                for(int y = 0; y < columns; y++){
                    final Cell cell = new Cell(x, y, 'n');
                    add(cell);
                    board[x][y] = cell;

                    board[x][y].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            currentCell = cell;
                            output.println("MOVE " + currentCell.getXPos() + currentCell.getYPos() + " " + playerNum);
                        }
                    });
                    add(board[x][y]);
                }
            }
        }
    }
    class CardPanel extends JPanel {
        public CardPanel() {
            add(new JLabel("Select a card: "));
            // Create button objects
            JRadioButton noneButton = new JRadioButton("None");
            JRadioButton doubleButton = new JRadioButton("Double");
            JRadioButton freedomButton = new JRadioButton("Freedom");
            JRadioButton replacementButton = new JRadioButton("Replacement");

            // Add the buttons to the panel
            add(noneButton);
            add(doubleButton);
            add(freedomButton);
            add(replacementButton);

            // Make the none button the first one that is selected
            noneButton.setSelected(true);

            // Group the buttons together so that only one button may be active at a time
            ButtonGroup cardButtonGroup = new ButtonGroup();
            cardButtonGroup.add(noneButton);
            cardButtonGroup.add(doubleButton);
            cardButtonGroup.add(freedomButton);
            cardButtonGroup.add(replacementButton);

            add(new JLabel("Current Turn:"));
            JTextField numCurrentTurnField = new JTextField(currentTurn + "");
            numCurrentTurnField.setEditable(false);
            numCurrentTurnField.setPreferredSize(new Dimension(20, 20));

            add(numCurrentTurnField);

        }
    }

    public void incrementCurrentTurn(){
        currentTurn++;
        if(currentTurn == 2)
            currentTurn = 0;
    }

    public void showEndScreen(char winner){
        JFrame endFrame = new JFrame("GAME FINISHED");
        endFrame.setPreferredSize(new Dimension(300, 180));
        endFrame.setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JLabel winnerText = new JLabel("Player " + winner + " has won the game!");
        endFrame.add(winnerText);
    }
}

