package AStar;

import java.awt.Point;

public class Node {

    /**
     * The weight of the node
     */
    private double weight;
    /**
     * Two dimensional position of the node
     */
    private Point position;
    /**
     * Distance from the node to the point you want to go
     */
    private double distance;
    /**
     * The weight from the way to this node + the nodes weight + it´s distance
     */
    private double combinedWeight = Double.MAX_VALUE;
    /**
     * The node you went through, to get to this node.
     */
    private Node wentTrough = null;
    /**
     * Is this node on the way to the goal?
     */
    public boolean success = false;

    /**
     * 
     * @param position Two dimensional position of the node
     * @param weight   Weight of the node
     */
    public Node(Point position, int weight) {
        this.weight = weight;
        this.position = position;
    }

    /**
     * Returns the cost (summed up weight) it needed to get to this node
     * 
     * @return the costs of the way without the distance calculated in.
     */
    public double getCost() {
        if (wentTrough != null) {
            return weight + wentTrough.getCost();
        } else {
            return weight;
        }
    }

    /**
     * Marks this node and its predecessors as a node that is on the easiest way to
     * the goal.
     */
    public void markSucess() {
        this.success = true;
        if (this.wentTrough != null) {
            this.wentTrough.markSucess();
        }
    }

    /**
     * Gets the steps that you have walked until this node
     * 
     * @return steps walked until here
     */
    public int getStepsWalked() {
        if (this.wentTrough != null) {
            return 1 + this.wentTrough.getStepsWalked();
        } else {
            return 0;
        }
    }

    double getCostThrough(Node through) {
        return through.getCost() + this.getWeight();
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

    public double getCombinedWeight() {
        return combinedWeight;
    }

    public void updateCombinedWeight() {
        combinedWeight = this.getCost() + this.getDistance();
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node getWentThrough() {
        return wentTrough;
    }

    public void setWentThrough(Node n) {
        this.wentTrough = n;
    }

    public void printPathWeight() {
        System.out.print(getWeight() + " - ");
        if (wentTrough != null) {
            wentTrough.printPathWeight();
        }
    }
}