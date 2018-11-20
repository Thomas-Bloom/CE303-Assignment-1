package UserInterface;
import Server.Server;
import assignment.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LevelGrid extends JPanel{

    public int currentPlayerNum;

    public String message = "";

    public LevelGrid(int rows, int columns){
        setPreferredSize(new Dimension(300, 300));
        setLayout(new GridLayout(rows, columns));

        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++){
                final Cell cell = new Cell(x, y);

                add(cell);

                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cell.setColor(Server.gameState.currentColor);
                        cell.cellValue = 1;
                        GameState.nextPlayer();
                        System.out.println("current cell: " + cell.getxPos() + ", " + cell.getyPos());
                        message = "MOVE " + cell.getxPos() + cell.getyPos();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
    }
}
