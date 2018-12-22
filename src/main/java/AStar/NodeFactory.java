package AStar;

import java.awt.Point;
import java.util.ArrayList;

public class NodeFactory {

    public static ArrayList<Node> createNodes(ArrayList<ArrayList<Integer>> dataset) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int y = 0; y < dataset.size(); y++) {
            for (int x = 0; x < dataset.get(y).size(); x++) {
                Node n = new Node(new Point(x, y), dataset.get(x).get(y));
                nodes.add(n);
            }
        }

        return nodes;
    }

    public static void setDistance(ArrayList<Node> nodes, Point end) {

        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            int xdist = end.x - n.getPosition().x;
            int ydist = end.y - n.getPosition().y;
            double distance = Math.sqrt(Math.pow(xdist, 2) + (Math.pow(ydist, 2)));
            nodes.get(i).setDistance(distance);
        }
    }
}