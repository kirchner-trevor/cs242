import static org.junit.Assert.*;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;


public class NodeTests {

	private Maze maze = new Maze(10);//Maze with nodes from (0, 0) -> (9, 9)
	
	@Test
	public void isPositionTraversable() {
		assertEquals(true, maze.getNode(0, 0).isWalkable());
	}
	
	@Test
	public void isPositionNotTraversable() {
		maze.setNodeWalkable(maze.getNode(1, 1), false);
		assertEquals(false, maze.getNode(1, 1).isWalkable());
	}
	
	@Test
	public void sameNodeEquality() {
		Node aNode = new Node(0, 0, true);
		Node bNode = new Node(0, 0, true);
		assertEquals(aNode, bNode);
	}
	
	@Test
	public void differentNodeEquality() {
		Node aNode = new Node(0, 0, true);
		Node bNode = new Node(1, 1, true);
		assertNotSame(aNode, bNode);
	}
	
	@Test
	public void accessOutOfMinBoundsPosition() {
		assertNull(maze.getNode(-1, -1));
	}
	
	@Test
	public void accessOutOfMaxBoundsPosition() {
		assertNull(maze.getNode(10, 10));
	}

}
