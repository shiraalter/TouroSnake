package touro.snake.strategy;

import touro.snake.Direction;
import touro.snake.Garden;
import touro.snake.Snake;
import touro.snake.Square;

import java.util.List;
import java.util.Random;


public class RandomStrategy implements SnakeStrategy {
    Random randomGen = new Random();
    @Override
    public void turnSnake(Snake snake, Garden garden) {

        Direction[] directions = Direction.values();    //array of directions
        int rand = randomGen.nextInt(4);
        Direction turnTo = directions[rand];        //get random direction from array

        snake.turnTo(turnTo);


    }

    @Override
    public List<Square> getPath() {
        return null;
    }

    @Override
    public List<Square> getSearchSpace() {
        return null;
    }
}
