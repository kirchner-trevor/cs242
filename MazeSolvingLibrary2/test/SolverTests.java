import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.AStarSolver;
import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.Solver;


public class SolverTests {

	@Test
	/**
	 * Checks the distance calculation using manhattan distance.
	 */
	public void calculateManhattanDistances() {
		Maze maze = new Maze(2);
		AStarSolver aStar = new AStarSolver();
		assertEquals(true, 100.0 == aStar.calculateManhattanDistance(maze.getNode(0, 0), maze.getNode(0, 1)));
		assertEquals(true, 200.0 == aStar.calculateManhattanDistance(maze.getNode(0, 0), maze.getNode(1, 1)));
	}
	
	@Test
	/**
	 * Solves a simple 1x1 maze with start and end nodes at 0,0.
	 */
	public void solveDefaultMazeUsingAStar() {
		Maze maze = new Maze();
		Solver aStar = new AStarSolver();
		List<Node> nodeList = aStar.solve(maze);
		assertEquals(1, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a 2x2 maze with start node at 0,0 and end node at 1,1.
	 */
	public void solveDefault2x2MazeUsingAStar() {
		Maze maze = new Maze(2);
		Solver aStar = new AStarSolver();
		List<Node> nodeList = aStar.solve(maze);
		assertEquals(2, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(1));
	}
	
	@Test
	/**
	 * Solves all other 2x2 mazes.
	 */
	public void solveOther2x2MazesUsingAStar() {
		Maze maze = new Maze(2);
		Solver aStar = new AStarSolver();
		
		maze.setEnd(0, 1);
		List<Node> nodeList = aStar.solve(maze);
		
		assertEquals(2, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(1));
		
		maze.setEnd(1, 1);
		nodeList = aStar.solve(maze);
		
		assertEquals(2, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(1));
		
		maze.setEnd(1, 0);
		nodeList = aStar.solve(maze);
		
		assertEquals(2, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(1));
	}

	@Test
	/**
	 * Solves a 3x3 maze with 1,1 being an un-walkable node.
	 */
	public void solveSimpleMazeWithWallUsingAStar() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(maze.getNode(1,  1), false);
		maze.setStart(0, 1);
		maze.setEnd(2, 1);
		Solver aStar = new AStarSolver();
		
		List<Node> nodeList = aStar.solve(maze);
		
		assertEquals(3, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(nodeList.size() - 1));
	}
	
	@Test
	/**
	 * Solves a 3x3 maze with a wall from 1,0 to 1,2 making it impossible.
	 */
	public void solveUnsolveableMazeUsingAStar() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(maze.getNode(1, 0), false);
		maze.setNodeWalkable(maze.getNode(1, 1), false);
		maze.setNodeWalkable(maze.getNode(1, 2), false);
		maze.setStart(0, 1);
		maze.setEnd(2, 1);
		
		Solver aStar = new AStarSolver();
		
		List<Node> nodeList = aStar.solve(maze);
		
		assertNull(nodeList);
	}
	
	@Test
	/**
	 * Solves a maze that requires more checks.
	 */
	public void solveSimpleMazeWithLongWallUsingAStar() {
		Maze maze = new Maze(3);
		maze.setStart(0, 2);
		maze.setEnd(2, 2);
		maze.setNodeWalkable(1, 2, false);
		maze.setNodeWalkable(1, 1, false);
		
		Solver aStar = new AStarSolver();
		
		List<Node> nodeList = aStar.solve(maze);
		
		assertEquals(5, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(nodeList.size() - 1));
	}
	
	@Test
	/**
	 * Solves a moderately complicated maze.
	 */
	public void solveModerateMazeUsingAStar() {
		Maze maze = new Maze(5);
		maze.setStart(0, 4).setEnd(4, 0);
		maze.setNodeWalkable(1, 1, false).setNodeWalkable(2, 1, false).setNodeWalkable(3, 1, false);
		maze.setNodeWalkable(1, 3, false);
		maze.setNodeWalkable(3, 3, false).setNodeWalkable(3, 4, false);
		
		Solver aStar = new AStarSolver();
		
		List<Node> nodeList = aStar.solve(maze);
		
		assertEquals(7, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(nodeList.size() - 1));
	}
	
	@Test
	/**
	 * Tests to see that it is calculating accumulated cardinal distances correctly.
	 */
	public void testCardinalDistanceAccumulation() {
		Maze maze = new Maze(2);
		maze.setEnd(0, 1);
		Solver aStar = new AStarSolver();
		List<Node> nodeList = aStar.solve(maze);
		assertEquals(true, 100.0 == nodeList.get(0).getAccumulatedCost());
	}
	
	@Test
	/**
	 * Tests to see that it is calculating accumulated diagonal distances correctly.
	 */
	public void testDiagonalDistanceAccumulation() {
		Maze maze = new Maze(2);
		Solver aStar = new AStarSolver();
		List<Node> nodeList = aStar.solve(maze);
		assertEquals(true, 141.0 == nodeList.get(0).getAccumulatedCost());
	}
	
	@Test
	/**
	 * Tests the distance calculation on a more complex map.
	 */
	public void testComplexDistanceAccumulation() {
		Maze maze = new Maze(5);
		maze.setStart(0, 4).setEnd(4, 0);
		maze.setNodeWalkable(1, 1, false).setNodeWalkable(2, 1, false).setNodeWalkable(3, 1, false);
		maze.setNodeWalkable(1, 3, false);
		maze.setNodeWalkable(3, 3, false).setNodeWalkable(3, 4, false);
		
		Solver aStar = new AStarSolver();
		
		List<Node> nodeList = aStar.solve(maze);
		
		assertEquals(true, 682.0 == nodeList.get(0).getAccumulatedCost());
	}
}
