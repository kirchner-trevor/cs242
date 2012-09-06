package cs242.kirchne5.mazesolvinglibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSolver implements Solver {

	private PriorityQueue<Node> open = new PriorityQueue<Node>();
	private PriorityQueue<Node> closed = new PriorityQueue<Node>();
	
	private static final int CARDINAL = 100;
	private static final int DIAGONAL = 141;
	
	@Override
	public List<Node> solve(Maze maze) {
		Node start = maze.getStart();
		Node end = maze.getEnd();
		
		open.offer(start);
		
		Node current;
		while(!closed.contains(end) && !open.isEmpty()) {
			current = open.poll();
			
			if(closed.contains(current)) {
				closed.remove(current);
			}
			closed.add(maze.getNode(current.getX(), current.getY()));
			
			List<Node> adjacent = maze.getAdjacent(current);
			for(Node n : adjacent) {
				if(n.isWalkable() && !closed.contains(n)) {
					
					double accumulated = current.getAccumulatedCost();
					if(current.getX() != n.getX() && current.getY() != n.getY()) {//Diagonal
						accumulated += DIAGONAL;
					} else {
						accumulated += CARDINAL;
					}
					
					if(open.contains(n)) {
						if(n.getAccumulatedCost() > accumulated) {
							maze.setNodeCost(n, accumulated, n.getEstimatedCost());
							maze.setNodeParent(n, current);
						}
					} else {
						maze.setNodeParent(n, current);//Sets the parent in the base structure
						double estimated = calculateManhattanDistance(n, end);
						maze.setNodeCost(n, accumulated, estimated);//Sets the cost in the base structure
						
						if(open.contains(n)) {
							open.remove(n);
						}
						open.add(maze.getNode(n.getX(), n.getY()));
					}
				}
			}
		}
		
		if(closed.contains(end)) {
			List<Node> nodeList = new ArrayList<Node>();
			start = maze.getStart();
			end = maze.getEnd();
			
			Node currentParent = end;
			while(currentParent != start) {
				nodeList.add(currentParent);
				currentParent = currentParent.getParent();
			}
			nodeList.add(start);
			
			return nodeList;
		} else {
			return null;
		}
	}

	public double calculateManhattanDistance(Node n, Node end) {
		return (Math.abs(end.getX() - n.getX()) + Math.abs(end.getY() - n.getY())) * CARDINAL;
	}

}
