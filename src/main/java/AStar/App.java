package AStar;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> data = DataSetReader
                .readFromCSV(System.getProperty("user.dir") + "\\assets\\dataset.csv");
        ArrayList<Node> nodes = NodeFactory.createNodes(data);
        NodeFactory.setDistance(nodes, new Point(9, 1));

        for (int i = 0; i < nodes.size(); i++) {
            // System.out.printf("%4.1f ", nodes.get(i).getWeight());
            System.out.printf("%4.1f ", nodes.get(i).getDistance());
            if (i + 1 < nodes.size()) {
                if (nodes.get(i).getPosition().y != nodes.get(i + 1).getPosition().y) {
                    System.out.println();
                }
            }
        }
        System.out.println();
        Astar astar = new Astar();
        astar.start(nodes);
        astar.print(nodes);
        System.out.println();

        System.out.println();
        for (int i = 0; i < nodes.size(); i++) {
            // System.out.printf("%4.1f ", nodes.get(i).getWeight());
            System.out.printf("%4.1f ", nodes.get(i).getWeight());
            if (i + 1 < nodes.size()) {
                if (nodes.get(i).getPosition().y != nodes.get(i + 1).getPosition().y) {
                    System.out.println();
                }
            }
        }
    }
}
