package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = (int)(WIDTH * 0.6);

    public LevelGrid levelGrid;

    public Window(String title, int rows, int columns){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        levelGrid = new LevelGrid(rows, columns);
        add(levelGrid, BorderLayout.CENTER);

        add(createBottomPanel(), BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Select a card"));
        JRadioButton noneButton = new JRadioButton("None");
        noneButton.setSelected(true);
        JRadioButton doubleButton = new JRadioButton("Double");
        JRadioButton freedomButton = new JRadioButton("Freedom");
        JRadioButton replacementButton = new JRadioButton("Replacement");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(noneButton);
        radioButtonGroup.add(doubleButton);
        radioButtonGroup.add(freedomButton);
        radioButtonGroup.add(replacementButton);

        bottomPanel.add(noneButton);
        bottomPanel.add(doubleButton);
        bottomPanel.add(freedomButton);
        bottomPanel.add(replacementButton);

        return bottomPanel;
    }
}
