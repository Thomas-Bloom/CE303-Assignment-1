package UserInterface;

import javax.swing.JFrame;

public class Window extends JFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = (int)(WIDTH * 0.6);

    public static LevelGrid levelGrid;

    public Window(String title, int rows, int columns){
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        levelGrid = new LevelGrid(rows, columns);
        add(levelGrid);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
