package AStar;

import java.awt.Point;

public class Node {

    private double weight;
    private Point position;
    private double distance;
    private double currentWayWeight = Double.MAX_VALUE;
    public Node wentTrough = null;
    public boolean success = false;

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

    public double getWeight() {
        return weight;
    }

    public double getCurrentWayWeight() {
        return currentWayWeight;
    }

    public void setCurrentWayWeight(double weight) {
        currentWayWeight = weight;
    }

    public double getCompleteWeight() {
        if (wentTrough != null) {
            return weight + wentTrough.getCompleteWeight();
        } else {
            return weight;
        }
    }

    public void markSucess() {
        this.success = true;
        if (this.wentTrough != null) {
            this.wentTrough.markSucess();
        }
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStepsWalked() {
        if (this.wentTrough != null) {
            return 1 + this.wentTrough.getStepsWalked();
        } else {
            return 1;
        }
    }
}