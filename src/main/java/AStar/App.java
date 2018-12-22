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
                .readFromCSV("C:/Users/Lars/Documents/Software Projects/AStar/assets/dataset.csv");
        ArrayList<Node> nodes = NodeFactory.createNodes(data);
        NodeFactory.setDistance(nodes, new Point(9, 1));

        for (int i = 0; i < nodes.size(); i++) {
            System.out.printf("%4.1f ", nodes.get(i).getDistance());
            if (i + 1 < nodes.size()) {
                if (nodes.get(i).getPosition().y != nodes.get(i + 1).getPosition().y) {
                    System.out.println();
                }
            }
        }

    }
}
