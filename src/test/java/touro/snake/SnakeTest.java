package touro.snake;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.*;

public class SnakeTest {

    @Test
    public void grow() {

        //if
        SnakeHeadStateMachine state = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(state);
       // when(state.getDirection()).thenReturn(Direction.North); //The direction of the head is irrelevant

        //when
        snake.grow();

        //then
        assertTrue(snake.getGrow());

    }

    @Test
    public void move() {

        //if
        SnakeHeadStateMachine state = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(state);
        when(state.getDirection()).thenReturn(Direction.North);
        List<Square> previousSquares = new ArrayList<>(snake.getSquares());
        int prevSize = previousSquares.size();
        int prevLastIndex = previousSquares.size() - 1;

        //when
        snake.move();

        //then
        List<Square> currentSquares = snake.getSquares();
        int currSize = currentSquares.size();
        int currLastIndex = currentSquares.size() - 1;
        int firstIndex = 0;

        //assert that the snake has not changed its size
        assertEquals(prevSize, currSize);

        //assert that new head has been created in the proper position
        assertEquals(previousSquares.get(firstIndex).getY() - 1, currentSquares.get(firstIndex).getY());

        //assert that the last square has been removed
        assertEquals(previousSquares.get(prevLastIndex).getX() - 1, currentSquares.get(currLastIndex).getX());

        //assert that the rest of the squares have remained in place
        for (int i = 0; i < currLastIndex; i++) {
            Square prevSquare = previousSquares.get(i);
            Square currSquare = currentSquares.get(i + 1);
            assertEquals(prevSquare,  currSquare);
        }

    }

    @Test
    public void turnTo() {
        throw new UnsupportedOperationException("Not Implemented Yet.");
    }

    @Test
    public void contains_true() {
        throw new UnsupportedOperationException("Not Implemented Yet.");
    }

    @Test
    public void contains_false() {
        throw new UnsupportedOperationException("Not Implemented Yet.");
    }

    @Test
    public void eatsSelf_true() {
        //IF
        SnakeHeadStateMachine headStateMock = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(headStateMock);

        Square middleSquare = new Square(50,20);  //create a square on the snake with X and Y coordinates
        Square headSquare = new Square(50,20);
        List<Square> list = snake.getSquares();
        list.set(4,middleSquare);
        list.set(0,headSquare);

        //WHEN
       boolean val = snake.eatsSelf();

        //THEN
        assertTrue(val);

    }

    @Test
    public void eatsSelf_false() {

        //IF
        SnakeHeadStateMachine headStateMock = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(headStateMock);

        //WHEN
        boolean val = snake.eatsSelf();

        //THEN
        assertFalse(val);

    }

    @Test
    public void inBounds_true() {
        //given
        SnakeHeadStateMachine snakeHeadStateMachine = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(snakeHeadStateMachine);
        //when
        snake.move();
        //then
        assertTrue(snake.inBounds());
    }

    @Test
    public void inBounds_false() {
        //given
        SnakeHeadStateMachine snakeHeadStateMachine = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(snakeHeadStateMachine);
        //when
        //number of iterations to be determined
        for (int i = 0; i < 100; i++) {
            snake.move();
        }
        //then
        assertFalse(snake.inBounds());
    }

    @Test
    public void getScore(){
        //check that getScore method works which will verify that score updates every time snake grows

        //given
        SnakeHeadStateMachine snakeHeadStateMachine = mock(SnakeHeadStateMachine.class);
        Snake snake = new Snake(snakeHeadStateMachine);

        //when
        snake.grow();

        //then
        assertEquals(1, snake.getScore());
    }
}