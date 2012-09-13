package cs242.kirchne5.mazesolvinglibrary;

import java.awt.Point;
import java.awt.Rectangle;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
	
	private static final String ERROR_INVALID_MAZE_SIZE = "Mazes must be of at least size 1.";
	private static final String ERROR_OUT_OF_MAZE = "The requested node is outside the bounds of the maze.";
	private Node[][] layout;
	private Rectangle bounds;
	private Node start;
	private Node end;
	private int width;
	private int height;
	
	/**
	 * Creates a new default maze of size 1, with start and end locations at 0,0.
	 */
	public Maze() {
		layout = new Node[1][1];
		bounds = new Rectangle(0, 0, 1, 1);
		layout[0][0] = new Node(0, 0, true);
		start = layout[0][0];
		end = layout[0][0];
		width = 1;
		height = 1;
	}
	
	/**
	 * Creates a square maze of with width and width "size". The start is located at (0,0) and the end is located at (size - 1, size -1).
	 * @param size the width and width of the maze
	 * @throws InvalidParameterException
	 */
	public Maze(int size) throws InvalidParameterException {
		if(size < 1) {
			throw new InvalidParameterException(ERROR_INVALID_MAZE_SIZE);
		}
		layout = new Node[size][size];
		bounds = new Rectangle(0, 0, size, size);
		for(int x = 0; x < size; ++x) {
			for(int y = 0; y < size; ++y) {
				layout[x][y] = new Node(x, y, true);
			}
		}
		start = layout[0][0];
		end = layout[size - 1][size - 1];
		width = size;
		height = size;
	}
	
	/**
	 * Creates a maze based on the given 2d array with start at (0,0) and end located at (width - 1, width - 1).
	 * @param walkable an array of the form walkable[y][x] representing walls as false and open space as true
	 * @note the maze is created such that declaring an array gives the same pictorial representation of the maze.
	 */
	public Maze(boolean[][] walkable) {
		int w, h;
		if(walkable == null || (h = walkable.length) < 1 || (w = walkable[0].length) < 1) {
			throw new InvalidParameterException(ERROR_INVALID_MAZE_SIZE);
		}
		layout = new Node[w][h];
		bounds = new Rectangle(0, 0, w, h);
		for(int x = 0; x < w; ++x) {
			for(int y = 0; y < h; ++y) {
				layout[x][y] = new Node(x, y, walkable[y][x]);
			}
		}
		start = layout[0][0];
		end = layout[w - 1][h - 1];
		width = w;
		height = h;
		
	}
	
	/**
	 * Sets the start of the maze to the location (x,y).
	 * @param x the x coordinate of the start
	 * @param y the y coordinate of the start
	 * @return the maze the start is in
	 */
	public Maze setStart(int x, int y) {
		if(bounds.contains(x, y)) {
			start = getNode(x, y);
		} else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	/**
	 * Sets the end of the maze to the location (x,y).
	 * @param x the x coordinate of the end
	 * @param y the y coordinate of the end
	 * @return the maze the end is in
	 */
	public Maze setEnd(int x, int y) {
		if(bounds.contains(x, y)) {
			end = getNode(x, y);
		} else {
			System.err.println(ERROR_OUT_OF_MAZE);
		}
		return this;
	}
	
	/**
	 * Allows for method chaining when setting up mazes.
	 * @param x the x coordinate of the target node
	 * @param y the y coordinate of the target node
	 * @param isWalkable whether or not the node should be walkable
	 * @return the maze in which the node resides
	 */
	public Maze setNodeWalkable(int x, int y, boolean isWalkable) {
		Node node = getNode(x, y);
		if(node != null) {
			node.setWalkable(isWalkable);
		}
		return this;
	}
	
	/**
	 * Get's the start of the maze.
	 * @return the node representing the start of the maze
	 */
	public Node getStart() {
		return start;
	}
	
	/**
	 * Get's the end of the maze.
	 * @return the node representing the end of the maze
	 */
	public Node getEnd() {
		return end;
	}
	
	/**
	 * Tests whether the target node is the start node.
	 * @param target the node to test
	 * @return true if the target node is the start node
	 */
	public boolean isStartNode(Node target) {
		return target.equals(start);
	}
	
	/**
	 * Tests whether the target node is the end node.
	 * @param target the node to test
	 * @return true if the target node is the end node
	 */
	public boolean isEndNode(Node target) {
		return target.equals(end);
	}
	
	/**
	 * Returns the node at the location (x,y) in the maze.
	 * @param x the x coordinate of the node
	 * @param y the y coordinate of the node
	 * @return the node at the location (x,y)
	 */
	public Node getNode(int x, int y) {
		if(bounds.contains(x, y)) {
			return layout[x][y];
		}
		return null;
	}
	
	/**
	 * Gets the width of the maze
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the maze
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns all nodes in the maze that are adjacent to the target node.
	 * @param target the target node
	 * @return a list of nodes adjacent to the target
	 */
	public List<Node> getAdjacent(Node target) {
		Point location = target.getLocation();
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
	
}
