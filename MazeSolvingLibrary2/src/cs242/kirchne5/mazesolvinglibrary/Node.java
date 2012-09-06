package cs242.kirchne5.mazesolvinglibrary;

import java.awt.Point;

public class Node implements Comparable<Node> {
	private final Point location;
	private boolean walkable;
	
	//Might separate this out to make an A* node subclass.
	private double accumulatedCost = 0;
	private double estimatedCost = 0;
	private double totalCost = 0;
	private Node parent = null;
	
	public Node(int x, int y, boolean isWalkable) {
		location = new Point(x, y);
		walkable = isWalkable;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getX() {
		return location.x;
	}
	
	public int getY() {
		return location.y;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public Node setWalkable(boolean isWalkable) {
		walkable = isWalkable;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node[x=" + location.x + ",y=" + location.y + "," + walkable + "]";
	}
	
	//A* node methods
	public Node setAccumulatedCost(double c) {
		accumulatedCost = c;
		totalCost = accumulatedCost + estimatedCost;
		return this;
	}
	
	public Node setEstimatedCost(double c) {
		estimatedCost = c;
		totalCost = accumulatedCost + estimatedCost;
		return this;
	}
	
	public Node setParent(Node p) {
		parent = p;
		return this;
	}
	
	public double getAccumulatedCost() {
		return accumulatedCost;
	}
	
	public double getEstimatedCost() {
		return estimatedCost;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public Node getParent() {
		return parent;
	}

	@Override
	public int compareTo(Node other) {
		if(getTotalCost() > other.getTotalCost()) {
			return 1;
		}
		else if(getTotalCost() < other.getTotalCost()) {
			return -1;
		}
		return 0;
	}
}
