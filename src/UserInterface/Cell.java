package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    public static final int CELL_SIZE = 2;

    private Color color;
    public int cellValue;
    private int xPos, yPos;

    public Cell(int x, int y){
        cellValue = 0;
        xPos = x;
        yPos = y;
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(color);
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    public int getxPos(){return xPos;}
    public int getyPos(){return yPos;}

    public void setxPos(int x){xPos = x;}
    public void setyPos(int y){yPos = y;}

    public void setColor(Color color){setBackground(color);}
}
