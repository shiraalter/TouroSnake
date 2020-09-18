package touro.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {

    public JLabel scoreBoard;

    public SnakeFrame(GardenView gardenView, SnakeKeyListener snakeKeyListener, Snake snake) throws HeadlessException {     //ADDED SNAKE
        super();
        setSize(1000,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snake");
        setLayout(new BorderLayout());

        add(gardenView, BorderLayout.CENTER);
        addKeyListener(snakeKeyListener);

        scoreBoard = new JLabel("Score");
        add(scoreBoard, BorderLayout.SOUTH);



    }
}
