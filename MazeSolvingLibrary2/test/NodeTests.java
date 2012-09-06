import static org.junit.Assert.*;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;


public class NodeTests {

	private Maze maze = new Maze(10);//Maze with nodes from (0, 0) -> (9, 9)
	
	@Test
	/**
	 * Tests whether the default walk-ability of nodes is assured.
	 */
	public void isPositionTraversable() {
		assertEquals(true, maze.getNode(0, 0).isWalkable());
	}
	
	@Test
	/**
	 * Tests to make sure a nodes walk-ability can be changed.
	 */
	public void isPositionNotTraversable() {
		maze.setNodeWalkable(maze.getNode(1, 1), false);
		assertEquals(false, maze.getNode(1, 1).isWalkable());
	}
	
	@Test
	/**
	 * Tests nodes for similarity.
	 */
	public void sameNodeEquality() {
		Node aNode = new Node(0, 0, true);
		Node bNode = new Node(0, 0, true);
		assertEquals(aNode, bNode);
	}
	
	@Test
	/** 
	 * Tests nodes for uniqueness.
	 */
	public void differentNodeEquality() {
		Node aNode = new Node(0, 0, true);
		Node bNode = new Node(1, 1, true);
		assertNotSame(aNode, bNode);
	}
	
	@Test
	/**
	 * Tests and out of lower bounds input.
	 */
	public void accessOutOfMinBoundsPosition() {
		assertNull(maze.getNode(-1, -1));
	}
	
	@Test
	/**
	 * Tests and out of upper bounds input.
	 */
	public void accessOutOfMaxBoundsPosition() {
		assertNull(maze.getNode(10, 10));
	}

}
