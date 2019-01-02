package AStar;

import java.awt.Point;
import java.util.ArrayList;

public class NodeFactory {

    /**
     * Creates a arraylist of nodes out of a two dimensional arraylist of integers
     * @param dataset two dimensional arraylist of integers
     * @return arraylist of nodes
     */
    public static ArrayList<Node> createNodes(ArrayList<ArrayList<Integer>> dataset) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int y = 0; y < dataset.size(); y++) {
            for (int x = 0; x < dataset.get(y).size(); x++) {
                int value=Integer.MAX_VALUE;
                switch(dataset.get(y).get(x))
                {
                    case 0: value =4;break;
                    case 1:value =7; break;
                    case 2:value =2; break;
                    case 3:value =5; break;
                    case 4:value =3; break;
                    case 5:value =17; break;
                }
                Node n = new Node(new Point(x, y),value);
                nodes.add(n);
            }
        }
        return nodes;
    }

    /**
     * This function calculates and sets the distance between every node in a list and a specific point. Distance is calulated by distance^2 =x^2 + y^2 
     * @param nodes list of nodes that will get a distance
     * @param end end point / goal 
     */
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