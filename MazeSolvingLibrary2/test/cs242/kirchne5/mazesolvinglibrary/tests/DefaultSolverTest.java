package cs242.kirchne5.mazesolvinglibrary.tests;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.solvers.AStar;
import cs242.kirchne5.mazesolvinglibrary.solvers.Solver;

/**
 * Tests the default AStar solver using the Manhattan heuristic.
 * @author Trevor
 */
public class DefaultSolverTest {

	@Test
	/**
	 * Checks the distance calculation using manhattan distance.
	 */
	public void calculateEstimatedDistances() {
		Maze maze = new Maze(2);
		AStar aStar = new AStar();
		assertEquals(new Double(200), new Double(aStar.calculateEstimatedDistance(maze.getStart(), maze.getEnd())));
	}
	
	@Test
	/**
	 * Solves a simple 1x1 maze with start and end nodes at 0,0.
	 */
	public void solveDefaultMazeUsingAStar() {
		Maze maze = new Maze();
		Solver aStar = new AStar();
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
	public void solveDefault2x2MazeUsingAStar() {
		Maze maze = new Maze(2);
		Solver aStar = new AStar();
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(2, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves all other 2x2 mazes.
	 */
	public void solveOther2x2MazesUsingAStar() {
		Maze maze = new Maze(2);
		Solver aStar = new AStar();
		
		maze.setEnd(0, 1);
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(2, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
		
		maze.setEnd(1, 1);
		nodeList = aStar.solve(maze);
		
		size = nodeList.size();
		assertEquals(2, size);
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
	public void solveSimpleMazeWithWallUsingAStar() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(1,  1, false)
			.setStart(0, 1)
			.setEnd(2, 1);
		Solver aStar = new AStar();
		
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(3, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a 3x3 maze with a wall from 1,0 to 1,2 making it impossible.
	 */
	public void solveUnsolveableMazeUsingAStar() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(1, 0, false)
			.setNodeWalkable(1, 1, false)
			.setNodeWalkable(1, 2, false)
			.setStart(0, 1)
			.setEnd(2, 1);
		
		Solver aStar = new AStar();
		
		List<Node> nodeList = aStar.solve(maze);
		
		assertNull(nodeList);
	}
	
	@Test
	/**
	 * Solves a maze that requires more checks.
	 */
	public void solveSimpleMazeWithLongWallUsingAStar() {
		Maze maze = new Maze(3);
		maze.setNodeWalkable(1, 2, false)
			.setNodeWalkable(1, 1, false)
			.setStart(0, 2)
			.setEnd(2, 2);
		
		Solver aStar = new AStar();
		
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(5, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a moderately complicated maze.
	 */
	public void solveModerateMazeUsingAStar() {
		Maze maze = new Maze(5);
		maze.setNodeWalkable(1, 1, false)
			.setNodeWalkable(2, 1, false)
			.setNodeWalkable(3, 1, false)
			.setNodeWalkable(1, 3, false)
			.setNodeWalkable(3, 3, false)
			.setNodeWalkable(3, 4, false)
			.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar();
		
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(7, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	public void solveModerateArrayMazeUsingAStar() {
		boolean[][] walkable = {{true	,true	,true	,true},
								{true	,false	,false	,false},
								{true	,true	,true	,true},
								{true	,false	,true	,false},
								{true	,true	,true	,false}};
		
		Maze maze = new Maze(walkable);
		maze.setStart(0, 4)
			.setEnd(3, 0);
		
		Solver aStar = new AStar();
		
		List<Node> nodeList = aStar.solve(maze);
		
		int size = nodeList.size();
		assertEquals(7, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Tests to see that it is calculating accumulated cardinal distances correctly.
	 */
	public void testCardinalDistanceAccumulation() {
		Maze maze = new Maze(2);
		maze.setEnd(0, 1);
		Solver aStar = new AStar();
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(new Double(100), new Double(nodeList.get(size - 1).getAccumulatedCost()));
	}
	
	@Test
	/**
	 * Tests to see that it is calculating accumulated diagonal distances correctly.
	 */
	public void testDiagonalDistanceAccumulation() {
		Maze maze = new Maze(2);
		Solver aStar = new AStar();
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(new Double(141), new Double(nodeList.get(size - 1).getAccumulatedCost()));
	}
	
	@Test
	/**
	 * Tests the distance calculation on a more complex map.
	 */
	public void testComplexDistanceAccumulation() {
		Maze maze = new Maze(5);
		maze.setNodeWalkable(1, 1, false)
			.setNodeWalkable(2, 1, false)
			.setNodeWalkable(3, 1, false)
			.setNodeWalkable(1, 3, false)
			.setNodeWalkable(3, 3, false)
			.setNodeWalkable(3, 4, false)
			.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar();
		
		List<Node> nodeList = aStar.solve(maze);
		int size = nodeList.size();
		assertEquals(new Double(682), new Double(nodeList.get(size - 1).getAccumulatedCost()));
	}
}
