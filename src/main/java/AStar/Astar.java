package AStar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Astar {

    public List<Node> open = new ArrayList<>();
    public ArrayList<Node> closed = new ArrayList<>();
    public int lastSteps = 0;

    /**
     * 
     * @param neighbours
     * @param through
     */
    public void checkNeighbours(ArrayList<Node> neighbours, Node through) {
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (closed.contains(n)) {
                continue;
            }
            // System.out.println(n.getCost());
            // System.out.println(n.getCostThrough(through));
            if (n.getCost() > n.getCostThrough(through) || n.getWentThrough() == null) {
                n.setWentThrough(through);
                ;
            }
            if (!open.contains(n)) {
                open.add(n);
            }
            n.updateCombinedWeight();
        }
    }

    public void start(Point startPoint, Point endPoint, ArrayList<Node> nodes) {
        Node start = getNode(startPoint.x, startPoint.y, nodes);
        Node end = getNode(endPoint.x, endPoint.y, nodes);
        walkThrough(start, nodes, end);
        System.out.println(end.getCost());
        end.printPathWeight();

    }

    public void walkThrough(Node start, ArrayList<Node> nodes, Node end) {
        if (start.getDistance() == 0) {
            start.markSucess();
            return;
        }
        updateWeight(nodes, start);
        ArrayList<Node> neighbours = new ArrayList<>();
        Point startPosition = start.getPosition();
        getNeighbours(neighbours, startPosition, nodes);
        checkNeighbours(neighbours, start);
        closed.add(start);
        open.remove(start);
        print(nodes);
        Node next = getNextNode();
        walkThrough(next, nodes, end);
    }

    public void updateWeight(ArrayList<Node> nodes, Node currentNode) {
        int currentSteps = currentNode.getStepsWalked() / 5;
        if (currentSteps < lastSteps) {
            for (Node n : nodes) {
                if (!closed.contains(n)) {
                    n.setWeight(n.getWeight() / Math.pow(1.1, Math.abs(currentSteps - lastSteps)));
                }
            }

        } else if (currentSteps > lastSteps) {
            for (Node n : nodes) {
                if (!closed.contains(n)) {
                    n.setWeight(n.getWeight() * Math.pow(1.1, Math.abs(currentSteps - lastSteps)));
                }
            }
        }
        lastSteps = currentSteps;
    }

    public Node getNextNode() {
        open.sort((Node n1, Node n2) -> n1.getCombinedWeight() > n2.getCombinedWeight() ? 1 : -1);
        return open.get(0);
    }

    public void getNeighbours(ArrayList<Node> neighbours, Point startPosition, ArrayList<Node> nodes) {

        if (startPosition.x - 1 >= 0) {
            neighbours.add(getNode(startPosition.x - 1, startPosition.y, nodes));
        }
        if (startPosition.y - 1 >= 0) {
            neighbours.add(getNode(startPosition.x, startPosition.y - 1, nodes));
        }
        Node n = getNode(startPosition.x, startPosition.y + 1, nodes);
        if (n != null) {
            neighbours.add(n);
        }
        n = getNode(startPosition.x + 1, startPosition.y, nodes);
        if (n != null) {
            neighbours.add(n);
        }
    }

    public Node getNode(int x, int y, ArrayList<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getPosition().x == x && nodes.get(i).getPosition().y == y) {
                return nodes.get(i);
            }
        }
        return null;
    }

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