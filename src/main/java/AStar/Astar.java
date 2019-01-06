package AStar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Astar {

    public static final int STEPS_TO_INCREASE_WEIGHT = 5;
    public static final double INCREASE_WEIGHT_BY = 1.1;
    /**
     * Open list of all nodes, wich can be choosen as "next nodes".
     */
    public List<Node> open = new ArrayList<>();
    /**
     * Closed list contains all nodes, that have been examined
     */
    public ArrayList<Node> closed = new ArrayList<>();
    /**
     * Steps walked if walked through last node
     */
    public int lastSteps = 0;

    /**
     * Goes through neighbours of a node and updates their costs
     * 
     * @param neighbours neighbour nodes
     * @param through    origin point
     */
    public void checkNeighbours(ArrayList<Node> neighbours, Node through) {
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (closed.contains(n)) {
                continue;
            }
            if (n.getCost() > n.getCostThrough(through) || n.getWentThrough() == null) {
                n.setWentThrough(through);
            }
            if (!open.contains(n)) {
                open.add(n);
            }
            n.updateCombinedWeight();
        }
    }

    /**
     * Starts the algorithm
     * 
     * @param startPoint Point where the algorithm starts
     * @param endPoint   The point that the algorithm will navigate to
     * @param nodes      All existing nodes
     * @return End-Node
     */
    public Node start(Point startPoint, Point endPoint, ArrayList<Node> nodes, boolean printSteps) {
        Node start = getNode(startPoint.x, startPoint.y, nodes);
        Node end = getNode(endPoint.x, endPoint.y, nodes);
        walkThrough(start, nodes, end, printSteps);
        return end;

    }

    /**
     * Recursive algorithm, that walks through each node that was choosen
     * 
     * @param start Current node where the algorithm walks through
     * @param nodes All existing nodes
     * @param end   End node
     */
    public void walkThrough(Node start, ArrayList<Node> nodes, Node end, boolean printSteps) {
        if (start.getDistance() == 0) {
            start.markSucess();
            return;
        }
        updateWeight(nodes, start);
        ArrayList<Node> neighbours = getNeighbours(start, nodes);
        checkNeighbours(neighbours, start);
        closed.add(start);
        open.remove(start);
        if (printSteps) {
            print(nodes);
        }
        Node next = getNextNode();
        walkThrough(next, nodes, end, printSteps);
    }

    /**
     * Increases weight of all nodes that haven´t been closed after every 5 steps by
     * 10%
     * 
     * @param nodes       All nodes existing
     * @param currentNode Node where the algorithm currently walked through
     */
    public void updateWeight(ArrayList<Node> nodes, Node currentNode) {
        int currentSteps = currentNode.getStepsWalked() / STEPS_TO_INCREASE_WEIGHT;
        if (currentSteps < lastSteps) {
            for (Node n : nodes) {
                if (!closed.contains(n)) {
                    n.setWeight(n.getWeight() / Math.pow(INCREASE_WEIGHT_BY, Math.abs(currentSteps - lastSteps)));
                }
            }

        } else if (currentSteps > lastSteps) {
            for (Node n : nodes) {
                if (!closed.contains(n)) {
                    n.setWeight(n.getWeight() * Math.pow(INCREASE_WEIGHT_BY, Math.abs(currentSteps - lastSteps)));
                }
            }
        }
        lastSteps = currentSteps;
    }

    /**
     * Sorts all nodes in open list by ascending weight.
     * 
     * @return Smallest node in open-list
     */
    public Node getNextNode() {
        open.sort((Node n1, Node n2) -> n1.getCombinedWeight() > n2.getCombinedWeight() ? 1 : -1);
        return open.get(0);
    }

    /**
     * Searches for neighbours of given position
     * 
     * @param current Node whose neighbours shall be choosen
     * @param nodes   All existing nodes
     * @return All neighbours
     */
    public ArrayList<Node> getNeighbours(Node current, ArrayList<Node> nodes) {
        Point position = current.getPosition();
        ArrayList<Node> neighbours = new ArrayList<>();
        if (position.x - 1 >= 0) {
            neighbours.add(getNode(position.x - 1, position.y, nodes));
        }
        if (position.y - 1 >= 0) {
            neighbours.add(getNode(position.x, position.y - 1, nodes));
        }
        Node n = getNode(position.x, position.y + 1, nodes);
        if (n != null) {
            neighbours.add(n);
        }
        n = getNode(position.x + 1, position.y, nodes);
        if (n != null) {
            neighbours.add(n);
        }
        return neighbours;
    }

    /**
     * Returns node by position
     * 
     * @param x     X Coordinate
     * @param y     Y Coordinate
     * @param nodes All existing nodes
     * @return Node by position
     */
    public Node getNode(int x, int y, ArrayList<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getPosition().x == x && nodes.get(i).getPosition().y == y) {
                return nodes.get(i);
            }
        }
        return null;
    }

    /**
     * Prints current state of the field. "X" are considered nodes, Numbers are
     * weights of nodes, "!" are walked way
     * 
     * @param nodes All existing nodes
     */
    public void print(ArrayList<Node> nodes) {
        System.out.println();
        System.out.println();
        System.out.printf("    ");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.printf("%4d ", i);
            if (nodes.get(i).getPosition().y != nodes.get(i + 1).getPosition().y) {
                System.out.println();
                break;
            }
        }
        System.out.printf("    ");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.printf("_____", i);
            if (nodes.get(i).getPosition().y != nodes.get(i + 1).getPosition().y) {
                System.out.println();
                break;
            }
        }

        System.out.printf("%3d| ", 0);

        int countLines = 1;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).success)
                System.out.printf(" !   ");
            else if (nodes.get(i).getWentThrough() == null)
                System.out.printf("%4.1f ", nodes.get(i).getWeight());
            else
                System.out.printf(" x   ", nodes.get(i).getWeight());
            if (i + 1 < nodes.size()) {
                if (nodes.get(i).getPosition().y != nodes.get(i + 1).getPosition().y) {
                    System.out.println();
                    System.out.printf("%3d| ", countLines);
                    countLines++;
                }
            }
        }
        System.out.println();
    }

}