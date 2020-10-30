package touro.snake.strategy;

import touro.snake.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DirectionStrategy implements SnakeStrategy {


    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Direction directions[] = Direction.values();    //list of directions

        Food food = garden.getFood();
        Square head = snake.getHead();

        //two variables for temp data
        Direction bestDirection = null;
        double shortestDistance = Double.MAX_VALUE;

        //loop through directions and get neighbor square, calc distance from neighbor to food, if that distance is better than what we have, save that direction
        for (Direction d : directions) {
            Square neighbor = head.moveTo(d);
            double distanceToFood = neighbor.distance(food);

            if (snake.contains(neighbor) || !neighbor.inBounds()) {
                continue;
            }
            if (distanceToFood < shortestDistance) {
                bestDirection = d;
                shortestDistance = distanceToFood;
            }
        }

        snake.turnTo(bestDirection);
    }
    @Override
    public List<Square> getPath() {
        return Collections.emptyList();
    }

    @Override
    public List<Square> getSearchSpace() {
        return Collections.emptyList();

    }
}
