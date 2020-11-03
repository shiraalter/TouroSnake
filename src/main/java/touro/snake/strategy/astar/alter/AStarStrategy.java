package touro.snake.strategy.astar.alter;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AStarStrategy implements SnakeStrategy {

    private List<Square> pathList = new ArrayList<>();
    private List<Square> searchSpaceList = new ArrayList<>();

    @Override
    public void turnSnake(Snake snake, Garden garden) {

        pathList.clear();    //clear previous path
        searchSpaceList.clear();

        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();


        Node startNode = new Node(snake.getHead()); //get initial square
        Food targetNode = garden.getFood();

        if (targetNode == null) {
            return;
        }

        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = getLowestNode(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);

            searchSpaceList.add(currentNode);


            //found target
            if (currentNode.equals(targetNode)) {
                Node childNode = tracePath(currentNode, startNode);
                Direction direction = startNode.directionTo(childNode);
                snake.turnTo(direction);
                break;
            }

            searchAdjacentNodes(snake, openList, closedList, targetNode, currentNode);


        }

    }

    private void searchAdjacentNodes(Snake snake, ArrayList<Node> openList, ArrayList<Node> closedList, Food targetNode, Node currentNode) {

        Direction[] directionArray = Direction.values();
        //if target NOT found yet:
        for (Direction directions : directionArray) {
            Node adjacentNode = new Node(currentNode.moveTo(directions), currentNode, targetNode);
            if (closedList.contains(adjacentNode) || !adjacentNode.inBounds() || snake.contains(adjacentNode)) {
                continue;   //skip to next neighbor
            }

            int index = openList.indexOf(adjacentNode);
            if (index != -1) {  //check if index already exists in open list
                Node oldAdjacent = openList.get(index);
                if (adjacentNode.getCost() < oldAdjacent.getCost()) {
                    openList.remove(index);
                    openList.add(adjacentNode);

                    searchSpaceList.add(adjacentNode);
                }
            } else {
                openList.add(adjacentNode);

                searchSpaceList.add(adjacentNode);
            }
        }
    }

    @Override
    public List<Square> getPath() {
        return pathList;
    }

    @Override
    public List<Square> getSearchSpace() {
        return searchSpaceList;
    }


    private Node getLowestNode(ArrayList<Node> openList) {
        return openList.stream().min(Comparator.comparingDouble(Node::getCost)).get();
    }

    private Node tracePath(Node currentNode, Node startNode) {

        Node end = currentNode;
        while (!end.getParent().equals(startNode)) {    //loop back but stop before it reaches the head
            end = end.getParent();
            pathList.add(end);
        }

        return end;
    }
}

