package touro.snake.strategy.astar.alter;

import touro.snake.Direction;
import touro.snake.Food;
import touro.snake.Garden;
import touro.snake.Snake;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.ArrayList;


public class AStarStrategy implements SnakeStrategy {

    @Override
    public void turnSnake(Snake snake, Garden garden) {
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        Direction direction = null;     //had to initialize
        Direction directionArray[] = Direction.values();

        Node currentNode;   //temp node
        Node startNode = new Node(snake.getHead()); //get initial square
        Food targetNode = garden.getFood();

        if (targetNode == null) {
            return;
        }

        openList.add(startNode);
        currentNode = openList.get(0);

        while (!openList.isEmpty()) {

            currentNode = getCurrentNode(openList, currentNode);
            openList.remove(currentNode);
            closedList.add(currentNode);

            if (currentNode.getX() == targetNode.getX() && currentNode.getY() == targetNode.getY()) {
                 //found path!
                //create array list to hold the path. Loop through path and get direction
                ArrayList<Node> path = tracePath(currentNode,startNode);
                for (Node node : path) {
                    direction = node.directionTo(startNode);
                    snake.turnTo(direction);
                }
                break;
            }
        }


        //if target NOT found:
        for (Direction directions : directionArray) {
            Node adjacentNode = new Node(currentNode.moveTo(directions));
            if (closedList.contains(adjacentNode) || !adjacentNode.inBounds() || snake.contains(adjacentNode)) {
                continue;   //skip to next neighbor
            }

            //include if new path to neighbor is shorter..?
            if (!openList.contains(adjacentNode)) {
                Node newNode = new Node(adjacentNode, currentNode, targetNode);
                openList.add(newNode);
            }
        }
    }

    private Node getCurrentNode(ArrayList<Node> openList, Node currentNode) {
        //search open list and let currentNode equal node with least f val
        for (Node node : openList) {
            if (node.getCost() < currentNode.getCost()) {
                currentNode = node;
            }
        }
        return currentNode;
    }

    private ArrayList<Node> tracePath (Node currentNode, Node startNode) {
        ArrayList<Node> path = new ArrayList<>();
        while (currentNode.getParent() != startNode) {    //loop back but stop before it reaches the head
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        return path;
       // return startNode.directionTo(currentNode);
    }
}

