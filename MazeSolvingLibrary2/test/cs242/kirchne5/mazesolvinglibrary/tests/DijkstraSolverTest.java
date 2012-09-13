package cs242.kirchne5.mazesolvinglibrary.tests;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.solvers.Dijkstra;
import cs242.kirchne5.mazesolvinglibrary.solvers.Solver;


public class DijkstraSolverTest {

	@Test
	/**
	 * Solves a simple 1x1 maze with start and end nodes at 0,0.
	 */
	public void solveDefaultMaze() {
		Maze maze = new Maze();
		Solver aStar = new Dijkstra();
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(1, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}

	@Test
	/**
	 * Solves a 2x2 maze with start node at 0,0 and end node at 1,1.
	 */
	public void solve2x2Maze() {
		Maze maze = new Maze(2);
		Solver aStar = new Dijkstra();
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(3, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves all other 2x2 mazes.
	 */
	public void solveAll2x2Mazes() {
		Maze maze = new Maze(2);
		Solver aStar = new Dijkstra();
		
		maze.setEnd(0, 1);
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(2, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
		
		maze.setEnd(1, 1);
		nodeList = aStar.solve(maze);
		
		size = nodeList.size();
		assertEquals(3, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
		
		maze.setEnd(1, 0);
		nodeList = aStar.solve(maze);
		
		size = nodeList.size();
		assertEquals(2, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a 3x3 maze with 1,1 being an un-walkable node.
	 */
	public void solveSimpleMazeWithWall() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(1,  1, false)
			.setStart(0, 1)
			.setEnd(2, 1);
		Solver aStar = new Dijkstra();
		
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(5, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a 3x3 maze with a wall from 1,0 to 1,2 making it impossible.
	 */
	public void solveUnsolveableMaze() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(1, 0, false)
			.setNodeWalkable(1, 1, false)
			.setNodeWalkable(1, 2, false)
			.setStart(0, 1)
			.setEnd(2, 1);
		
		Solver aStar = new Dijkstra();
		
		List<Node> nodeList = aStar.solve(maze);

		assertNull(nodeList);
	}
	
	@Test
	/**
	 * Solves a moderately complicated maze.
	 */
	public void solveModerateMaze() {
		Maze maze = new Maze(5);
		maze.setNodeWalkable(1, 1, false)
			.setNodeWalkable(2, 1, false)
			.setNodeWalkable(3, 1, false)
			.setNodeWalkable(1, 3, false)
			.setNodeWalkable(3, 3, false)
			.setNodeWalkable(3, 4, false)
			.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new Dijkstra();
		
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(9, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
}
