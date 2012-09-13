package cs242.kirchne5.mazesolvinglibrary.solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.UniquePriorityQueue;
import cs242.kirchne5.mazesolvinglibrary.estimators.Estimator;
import cs242.kirchne5.mazesolvinglibrary.estimators.Manhattan;

public class AStar implements Solver {

	
	private static final int CARDINAL = 100;
	private static final int DIAGONAL = 141;
	
	private final Estimator estimator;
	
	private Queue<Node> open = new UniquePriorityQueue<Node>();
	private Set<Node> closed = new HashSet<Node>();
	
	public AStar() {
		estimator = new Manhattan();
	}
	
	/**
	 * Creates a new AStarSolver using the supplied Estimator.
	 * @param est the Estimator used to calculate estimated distances
	 */
	public AStar(final Estimator est) {
		estimator = est;
	}
	
	@Override
	public List<Node> solve(Maze maze) {
		Node start = maze.getStart();
		Node end = maze.getEnd();
		Node current = null;
		
		open.offer(start);
		
		while(!closed.contains(end) && !open.isEmpty()) {
			current = open.poll();
			closed.add(current);
			List<Node> adjacent = maze.getAdjacent(current);
			for(Node next : adjacent) {
				if(next.isWalkable() && !closed.contains(next)) {
					updateNode(current, next, end);
				}
			}
		}
		
		if(closed.contains(end)) {
			return generatePath(start, end);
		} else {
			return null;
		}
	}

	/**
	 * Traces a node's lineage beginning with the end node by checking each successive node's parent until
	 * the start node is found.
	 * @param start the beginning of the maze
	 * @param end the end of the maze
	 * @return a list of nodes along the shortest path from the start node (inclusive) to the end node (inclusive) 
	 * or null if no path is found
	 */
	private List<Node> generatePath(Node start, Node end) {
		List<Node> nodeList = new ArrayList<Node>();
		Node currentParent = end;
		while(currentParent != start) {
			nodeList.add(currentParent);
			currentParent = currentParent.getParent();
			if(currentParent == null) {
				return null;
			}
		}
		nodeList.add(start);
		Collections.reverse(nodeList);
		return nodeList;
	}

	/**
	 * Updates the current node's accumulated and estimated costs based on the position of the previous node
	 * and adds the current node to the open set if it isn't already in it.
	 * @param maze the maze A* is attempting to solve
	 * @param previous the previous node in the path
	 * @param current the current node in the path
	 */
	private void updateNode(Node previous, Node current, final Node end) {
		double accumulated = calculateAccumulatedDistance(previous, current);
		if(open.contains(current)) {
			if(current.getAccumulatedCost() > accumulated) {
				current.setAccumulatedCost(accumulated).setParent(previous);
			}
		} else {
			double estimated = calculateEstimatedDistance(current, end);
			current.setParent(previous).setAccumulatedCost(accumulated).setEstimatedCost(estimated);
			open.add(current);
		}
	}

	/**
	 * Calculates the total accumulated cost for the current node.
	 * @param previous the previous node in the path
	 * @param current the node that the accumulated cost is being calculated for
	 * @return the total accumulated cost after moving from the previous node to the current node
	 */
	public double calculateAccumulatedDistance(Node previous, Node current) {
		double accumulated = previous.getAccumulatedCost();
		if(previous.getX() != current.getX() && previous.getY() != current.getY()) {//Diagonal
			accumulated += DIAGONAL;
		} else {
			accumulated += CARDINAL;
		}
		return estimator.getAccumulated(accumulated);
	}

	/**
	 * Calculates the Manhattan distance from the current node to the target node.
	 * @param current the source node for the calculation
	 * @return the total cost to move from the current node to the end node via non-diagonal moves
	 * or 0.0 if there is no maze to estimate the distance to the end of
	 */
	public double calculateEstimatedDistance(final Node from, final Node to) {
		return estimator.getEstimated(from, to);
	}

}
