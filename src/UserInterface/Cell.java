package UserInterface;

import Utilities.Coordinate;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    public static final int CELL_SIZE = 2;

    private Color cellColor;
    private Coordinate coord;
    private char playerNum;

    public Cell(int x, int y, char playerNum){
        this.playerNum = playerNum;
        coord = new Coordinate(0,0);
        // Set position
        coord.setxPos(x);
        coord.setyPos(y);

        // Set appearance
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        cellColor = Color.WHITE;
        setBackground(cellColor);
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    public void setCellColor(Color color) {
        this.cellColor = color;
        setBackground(cellColor);
    }

    public int getXPos(){
        return coord.getxPos();
    }

    public int getYPos(){
        return coord.getyPos();
    }
}
