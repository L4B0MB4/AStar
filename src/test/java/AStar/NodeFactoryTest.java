package AStar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class NodeFactoryTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void canCreateNode() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> aList = new ArrayList<>();
        aList.add(3);
        list.add(aList);
        ArrayList<Node> nodes = NodeFactory.createNodes(list);
        assertEquals(nodes.size(), 1);
    }

    @Test
    public void emptyNodeList() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Node> nodes = NodeFactory.createNodes(list);
        assertEquals(nodes.size(), 0);
    }

    @Test
    public void setDistance() {
        Node n = new Node(new Point(1, 1), 1);
        ArrayList<Node> list = new ArrayList<>();
        list.add(n);
        NodeFactory.setDistance(list, new Point(5, 1));
        assertEquals(n.getDistance() == 4.0, true);
    }
}
