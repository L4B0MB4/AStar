package AStar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AStarTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void testCheckNeighbours() {
        Astar astar = new Astar();
        ArrayList<Node> nodes = createSimpleNodes();
        ArrayList<Node> neighbours = new ArrayList<>();
        astar.getNeighbours(neighbours, nodes.get(0).getPosition(), nodes);
        astar.checkNeighbours(neighbours, nodes.get(0));
    }

    public ArrayList<Node> createSimpleNodes()
    {
        Node s = new Node(new Point(0,0),5);
        Node a = new Node(new Point(1,0), 1);
        Node b = new Node(new Point(0,1),5);
        Node end = new Node(new Point(1,1),1);
        ArrayList<Node> nodes= new ArrayList<>();
        nodes.add(s);
        nodes.add(a);
        nodes.add(b);
        nodes.add(end);
        NodeFactory.setDistance(nodes, end.getPosition());
        return nodes;
    }
}
