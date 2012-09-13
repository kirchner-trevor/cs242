package cs242.kirchne5.mazesolvinglibrary.tests;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.estimators.Diagonal;
import cs242.kirchne5.mazesolvinglibrary.estimators.Euclidean;
import cs242.kirchne5.mazesolvinglibrary.estimators.EuclideanSquared;
import cs242.kirchne5.mazesolvinglibrary.estimators.Manhattan;
import cs242.kirchne5.mazesolvinglibrary.estimators.ManhattanTieBreaker;
import cs242.kirchne5.mazesolvinglibrary.solvers.AStar;
import cs242.kirchne5.mazesolvinglibrary.solvers.Solver;


public class EstimatorSolverTest {

	@Test
	/**
	 * Checks the distance calculation using diagonal distance.
	 */
	public void estimatedDiagonalDistances() {
		Maze maze = new Maze(2);
		AStar aStar = new AStar(new Diagonal());
		double result = aStar.calculateEstimatedDistance(maze.getStart(), maze.getEnd());
		assertEquals(new Double(141), new Double(result));
	}
	
	@Test
	/**
	 * Checks the distance calculation using Euclidean distance.
	 */
	public void estimatedEuclideanDistances() {
		Maze maze = new Maze(2);
		AStar aStar = new AStar(new Euclidean());
		double result = aStar.calculateEstimatedDistance(maze.getStart(), maze.getEnd());
		assertEquals(new Double(141.4213562373095), new Double(result));
	}
	
	@Test
	/**
	 * Checks the distance calculation using EuclideanSquared distance.
	 */
	public void estimatedEuclideanSquaredDistances() {
		Maze maze = new Maze(2);
		AStar aStar = new AStar(new EuclideanSquared());
		double result = aStar.calculateEstimatedDistance(maze.getStart(), maze.getEnd());
		assertEquals(new Double(200.0), new Double(result));
	}
	
	@Test
	/**
	 * Checks the distance calculation using Manhattan distance.
	 */
	public void estimatedManhattanDistances() {
		Maze maze = new Maze(2);
		AStar aStar = new AStar(new Manhattan());
		double result = aStar.calculateEstimatedDistance(maze.getStart(), maze.getEnd());
		assertEquals(new Double(200.0), new Double(result));
	}
	
	@Test
	/**
	 * Checks the distance calculation using ManhattanTieBreaker distance.
	 */
	public void estimatedManhattanTieBreakerDistances() {
		Maze maze = new Maze(2);
		AStar aStar = new AStar(new ManhattanTieBreaker(maze.getStart(), maze.getEnd()));
		double result = aStar.calculateEstimatedDistance(maze.getStart(), maze.getEnd());
		assertEquals(new Double(200.0), new Double(result));
	}
	
	@Test
	/**
	 * Solves a more difficult maze using Diagonal
	 */
	public void solveMazeUsingAStarDiagonal() {
		boolean[][] walkable = {{true	,true	,true	,true	,true},
								{true	,false	,false	,false	,true},
								{true	,true	,true	,true	,true},
								{true	,false	,true	,false	,true},
								{true	,true	,true	,false	,true}};
		
		Maze maze = new Maze(walkable);
		maze.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar(new Diagonal());
		
		List<Node> nodeList = aStar.solve(maze);

		int size = nodeList.size();
		assertEquals(6, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a more difficult maze using Euclidean
	 */
	public void solveMazeUsingAStarEuclidean() {
		boolean[][] walkable = {{true	,true	,true	,true	,true},
								{true	,false	,false	,false	,true},
								{true	,true	,true	,true	,true},
								{true	,false	,true	,false	,true},
								{true	,true	,true	,false	,true}};
		
		Maze maze = new Maze(walkable);
		maze.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar(new Euclidean());
		
		List<Node> nodeList = aStar.solve(maze);

		int size = nodeList.size();
		assertEquals(6, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a more difficult maze using EuclideanSquared
	 */
	public void solveMazeUsingAStarEuclideanSquared() {
		boolean[][] walkable = {{true	,true	,true	,true	,true},
								{true	,false	,false	,false	,true},
								{true	,true	,true	,true	,true},
								{true	,false	,true	,false	,true},
								{true	,true	,true	,false	,true}};
		
		Maze maze = new Maze(walkable);
		maze.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar(new EuclideanSquared());
		
		List<Node> nodeList = aStar.solve(maze);

		int size = nodeList.size();
		assertEquals(6, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a more difficult maze using Manhattan
	 */
	public void solveMazeUsingAStarManhattan() {
		boolean[][] walkable = {{true	,true	,true	,true	,true},
								{true	,false	,false	,false	,true},
								{true	,true	,true	,true	,true},
								{true	,false	,true	,false	,true},
								{true	,true	,true	,false	,true}};
		
		Maze maze = new Maze(walkable);
		maze.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar(new Manhattan());
		
		List<Node> nodeList = aStar.solve(maze);

		int size = nodeList.size();
		assertEquals(7, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	/**
	 * Solves a more difficult maze using ManhattanTieBreaker
	 */
	public void solveMazeUsingAStarManhattanTieBreaker() {
		boolean[][] walkable = {{true	,true	,true	,true	,true},
								{true	,false	,false	,false	,true},
								{true	,true	,true	,true	,true},
								{true	,false	,true	,false	,true},
								{true	,true	,true	,false	,true}};
		
		Maze maze = new Maze(walkable);
		maze.setStart(0, 4)
			.setEnd(4, 0);
		
		Solver aStar = new AStar(new ManhattanTieBreaker(maze.getStart(), maze.getEnd()));
		
		List<Node> nodeList = aStar.solve(maze);

		int size = nodeList.size();
		assertEquals(7, size);
		assertEquals(maze.getEnd(), nodeList.get(size - 1));
		assertEquals(maze.getStart(), nodeList.get(0));
	}

}
