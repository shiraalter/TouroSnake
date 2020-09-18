package touro.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {

    public JLabel scoreBoard;


    public SnakeFrame(GardenView gardenView, SnakeKeyListener snakeKeyListener, Snake snake) throws HeadlessException {     //ADDED SNAKE
        super();
        setSize(1000, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snake");
        setLayout(new BorderLayout());

        add(gardenView, BorderLayout.CENTER);
        addKeyListener(snakeKeyListener);

        scoreBoard = new JLabel();
        ActionListener scoreTracker = actionEvent -> {
            scoreBoard.setText("Score: " + snake.getScore());
        };

        //updates score immediately upon eating the food
        Timer timer = new Timer(0, scoreTracker);
        timer.start();
        add(scoreBoard, BorderLayout.SOUTH);


    }
}
