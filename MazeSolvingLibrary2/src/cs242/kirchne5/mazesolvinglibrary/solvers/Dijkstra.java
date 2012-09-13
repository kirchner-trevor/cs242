package cs242.kirchne5.mazesolvinglibrary.solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.UniquePriorityQueue;
import cs242.kirchne5.mazesolvinglibrary.estimators.Estimator;
import cs242.kirchne5.mazesolvinglibrary.estimators.Manhattan;

public class Dijkstra implements Solver {
	
	private final Estimator estimator;
	
	private Queue<Node> open = new UniquePriorityQueue<Node>();
	
	public Dijkstra() {
		estimator = new Manhattan();
	}
	
	public Dijkstra(final Estimator est) {
		estimator = est;
	}
	
	@Override
	public List<Node> solve(Maze maze) {
		Node start = maze.getStart();
		Node end = maze.getEnd();
		
		int width = maze.getWidth();
		int height = maze.getHeight();
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				open.add(maze.getNode(x, y).setAccumulatedCost(Integer.MAX_VALUE));
			}
		}
		
		open.add(start.setAccumulatedCost(0));
		Node current;
		
		while(!open.isEmpty()) {
			current = open.poll();
			if(current.getAccumulatedCost() == Integer.MAX_VALUE) {
				break;
			}
			List<Node> adjacent = maze.getAdjacent(current);
			for(Node v : adjacent) {
				updateNode(current, v);
			}
		}
		
		return generatePath(start, end);
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
	 * Updates the current node with the best cost based on its previous node.
	 * @param previous the previous node
	 * @param current the current node
	 */
	private void updateNode(Node previous, Node current) {
		double alt = calculateAccumulatedDistance(previous, current);
		if(alt < current.getAccumulatedCost()) {
			current.setAccumulatedCost(alt);
			current.setParent(previous);
			open.add(current);//updates the entry for v in the PQ
		}
	}

	private double calculateAccumulatedDistance(Node previous, Node current) {
		if(current.isWalkable()) {
			return estimator.getEstimated(previous, current);
		}
		return Integer.MAX_VALUE;
	}

}
