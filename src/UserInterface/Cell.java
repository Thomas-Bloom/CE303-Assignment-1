package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    public static final int CELL_SIZE = 2;
    private int xPos, yPos;

    public Cell(int x, int y){
        xPos = x;
        yPos = y;
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    public int getxPos(){return xPos;}
    public int getyPos(){return yPos;}
}
