package AStar;

import java.awt.Point;

public class Node {

    private int weight;
    private Point position;
    private double distance;

    public Node(Point position, int weight) {
        this.weight = weight;
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }
}