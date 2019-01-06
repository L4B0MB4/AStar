package AStar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.cli.*;

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

        HashMap<String, Object> arguments = handleArguments(args);
        ArrayList<ArrayList<Integer>> data = DataSetReader.readFromCSV((String) arguments.get("inputFile"));
        ArrayList<Node> nodes = NodeFactory.createNodes(data);
        Point startPoint = (Point) arguments.get("start");
        Point endPoint = (Point) arguments.get("end");
        boolean printSteps = (boolean) arguments.get("printSteps");
        NodeFactory.setDistance(nodes, endPoint);
        Astar astar = new Astar();
        Node end = astar.start(startPoint, endPoint, nodes, printSteps);
        astar.print(nodes);
        System.out.println();
        System.out.printf("Cost: %4.3f", end.getCost());
        System.out.println();
        System.out.println();
        System.out.println(
                "!!! Das Start-Planquadrat wird mitgezählt bei der Wegberechnung, deshalb kann es zu einer kleinen Abweichung der Kosten kommen, falls dies nicht so gedacht war !!!");
        System.out.println();
        System.out.println();
        /*
         * System.out.println(); System.out.println(); for (int i = 0; i < nodes.size();
         * i++) { // System.out.printf("%4.1f ", nodes.get(i).getWeight());
         * System.out.printf("%4.1f ", nodes.get(i).getWeight()); if (i + 1 <
         * nodes.size()) { if (nodes.get(i).getPosition().y != nodes.get(i +
         * 1).getPosition().y) { System.out.println(); } } }
         */
    }

    /**
     * Handles the input arguments
     * 
     * @param args input arguments from main method
     * @return Hashmap with recognized values
     */
    private static HashMap<String, Object> handleArguments(String[] args) {
        HashMap<String, Object> arguments = new HashMap<>();
        Options options = setUpOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            String startPoint = cmd.getOptionValue("start");
            String endPoint = cmd.getOptionValue("end");
            String inputFile = cmd.getOptionValue("file");
            boolean printSteps = cmd.hasOption("print");
            arguments.put("end", createPoint(endPoint));
            arguments.put("start", createPoint(startPoint));
            arguments.put("inputFile", inputFile);
            arguments.put("printSteps", printSteps);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("starting utilites", options);
            System.exit(1);
        }
        return arguments;
    }

    /**
     * Creates all available input options
     * 
     * @return
     */

    private static Options setUpOptions() {
        Options options = new Options();
        Option start = new Option("s", "start", true, "starting point e.g. 14;2");
        start.setRequired(true);
        options.addOption(start);
        Option end = new Option("e", "end", true, "end point e.g. 9;1");
        end.setRequired(true);
        options.addOption(end);
        Option inputFile = new Option("f", "file", true, "input file with coordinate system in form of a .csv");
        inputFile.setRequired(true);
        options.addOption(inputFile);
        Option printSteps = new Option("p", "print", false, " Add if you want to see every step printed out");
        printSteps.setRequired(false);
        options.addOption(printSteps);
        return options;
    }

    /**
     * Creates a point by a given string in format of "x;y"
     * 
     * @param point string in fromat "x;y"
     * @return java.awt.Point
     */
    private static Point createPoint(String point) {
        String[] koords = point.split(";");
        try {
            Point p = new Point(Integer.parseInt(koords[0]), Integer.parseInt(koords[1]));
            return p;
        } catch (Exception exception) {
            System.out.println("There was an error parsing this Point: '" + point
                    + "' make sure the syntax looks like this -> X;Y ");
            System.exit(2);
        }
        return null;
    }
}
