package AStar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AStarTest {
    @Test
    /**
     * Tests if checkNeighbours works correctly by ensuring if the wentThrough
     * Property is set when its null or the way costs are less through the 'cheat'
     * node
     */
    public void testCheckNeighbours() {
        Astar astar = new Astar();
        ArrayList<Node> nodes = createSimpleNodes();
        ArrayList<Node> neighbours = astar.getNeighbours(nodes.get(0), nodes);
        astar.checkNeighbours(neighbours, nodes.get(0));
        for (Node n : neighbours) {
            assertTrue(n.getWentThrough() != null);
        }
        Node cheat = new Node(new Point(0, 2), -10);
        neighbours = astar.getNeighbours(cheat, nodes);
        astar.checkNeighbours(neighbours, cheat);
        for (Node n : neighbours) {
            assertTrue(n.getWentThrough() == cheat);
        }
    }

    @Test
    /**
     * Makes sure, that always the right amount of neighbours are choosen.
     */
    public void testGetNeighbours() {
        Astar astar = new Astar();
        ArrayList<Node> nodes = createSimpleNodes();
        ArrayList<Node> neighbours = astar.getNeighbours(nodes.get(0), nodes);
        assertSame(neighbours.size(), 2);
        Node cheat = new Node(new Point(0, 2), -10);
        neighbours = astar.getNeighbours(cheat, nodes);
        assertSame(neighbours.size(), 1);
    }

    /**
     * Creates the first 4 nodes (0,0;0,1;1,0;1,1) from the csv - hard coded
     * 
     * @return
     */

    public ArrayList<Node> createSimpleNodes() {
        Node s = new Node(new Point(0, 0), 5);
        Node a = new Node(new Point(1, 0), 1);
        Node b = new Node(new Point(0, 1), 5);
        Node end = new Node(new Point(1, 1), 1);
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(s);
        nodes.add(a);
        nodes.add(b);
        nodes.add(end);
        NodeFactory.setDistance(nodes, end.getPosition());
        return nodes;
    }

    @Test
    /**
     * Makes sure, that the way is updated properly after 5 steps
     */
    public void testUpdateWeight() {
        Astar astar = new Astar();
        Node a1 = new Node(new Point(0, 2), -10);
        Node a2 = new Node(new Point(0, 2), -10);
        Node a3 = new Node(new Point(0, 2), -10);
        Node a4 = new Node(new Point(0, 2), -10);
        Node a5 = new Node(new Point(0, 2), -10);
        Node a6 = new Node(new Point(0, 2), -10);
        Node a7 = new Node(new Point(0, 2), -10);
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(a7);
        nodes.add(a6);
        nodes.add(a5);
        nodes.add(a4);
        nodes.add(a3);
        nodes.add(a2);
        nodes.add(a1);
        a6.setWentThrough(a5);
        a5.setWentThrough(a4);
        a4.setWentThrough(a3);
        a3.setWentThrough(a2);
        a2.setWentThrough(a1);
        astar.updateWeight(nodes, a6);
        assertTrue(a7.getWeight() == -11);
        a6.setWentThrough(a2);
        astar.updateWeight(nodes, a6);
        assertTrue(a7.getWeight() == -10);
    }

}
