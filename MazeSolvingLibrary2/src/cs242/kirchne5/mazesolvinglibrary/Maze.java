package cs242.kirchne5.mazesolvinglibrary;

import java.awt.Point;
import java.awt.Rectangle;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
	
	private static final String ERROR_OUT_OF_MAZE = "The requested node is outside the bounds of the maze.";
	private Node[][] layout;
	private Rectangle bounds;
	private Point start;
	private Point end;
	
	public Maze() {
		layout = new Node[1][1];
		bounds = new Rectangle(0, 0, 1, 1);
		layout[0][0] = new Node(0, 0, true);
		start = new Point(0, 0);
		end = new Point(0, 0);
	}
	
	public Maze(int size) throws InvalidParameterException {
		if(size < 1) {
			throw new InvalidParameterException("Mazes must be of at least size 1.");
		}
		layout = new Node[size][size];
		bounds = new Rectangle(0, 0, size, size);
		for(int x = 0; x < size; ++x) {
			for(int y = 0; y < size; ++y) {
				layout[x][y] = new Node(x, y, true);
			}
		}
		start = new Point(0, 0);
		end = new Point(size - 1, size - 1);
	}
	
	public Maze setStart(int x, int y) {
		if(bounds.contains(x, y)) {
			start = new Point(x, y);
		} else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	public Maze setEnd(int x, int y) {
		if(bounds.contains(x, y)) {
			end = new Point(x, y);
		} else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	public boolean isStartNode(Node n) {
		return n.getLocation().equals(start);
	}
	
	public boolean isEndNode(Node n) {
		return n.getLocation().equals(end);
	}
	
	public Node getNode(int x, int y) {
		if(bounds.contains(x, y)) {
			return layout[x][y];
		}
		return null;
	}
	
	public Maze setNodeWalkable(int x, int y, boolean walkable) {
		if(bounds.contains(x, y)) {
			layout[x][y].setWalkable(walkable);
		}  else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	public Maze setNodeWalkable(Node target, boolean walkable) {
		Point location = target.getLocation();
		int x = location.x;
		int y = location.y;
		if(bounds.contains(x, y)) {
			layout[x][y].setWalkable(walkable);
		}  else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	public Maze setNodeParent(Node target, Node parent) {
		Point location = target.getLocation();
		int x = location.x;
		int y = location.y;
		if(bounds.contains(x, y)) {
			layout[x][y].setParent(parent);
		} else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	public Maze setNodeCost(Node target, double accumulated, double estimated) {
		Point location = target.getLocation();
		int x = location.x;
		int y = location.y;
		if(bounds.contains(x, y)) {
			layout[x][y].setAccumulatedCost(accumulated);
			layout[x][y].setEstimatedCost(estimated);
		} else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	public List<Node> getAdjacent(Node n) {
		Point location = n.getLocation();
		int x = location.x;
		int y = location.y;
		List<Node> result = new ArrayList<Node>();
		for(int xOffset = -1; xOffset <= 1; ++xOffset) {
			for(int yOffset = -1; yOffset <= 1; ++yOffset) {
				if(!(xOffset == 0 && yOffset == 0)) {
					Node currentNode = getNode(x + xOffset, y + yOffset);
					if(currentNode != null) {
						result.add(currentNode);
					}
				}
			}
		}
		return result;
	}
	
	public Node getStart() {
		return layout[start.x][start.y];
	}
	
	public Node getEnd() {
		return layout[end.x][end.y];
	}
}
