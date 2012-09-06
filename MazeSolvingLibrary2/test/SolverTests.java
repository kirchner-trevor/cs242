import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.AStarSolver;
import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;
import cs242.kirchne5.mazesolvinglibrary.Solver;


public class SolverTests {

	@Test
	public void solveDefaultMazeUsingAStar() {
		Maze maze = new Maze();
		Solver aStar = new AStarSolver();
		List<Node> nodeList = aStar.solve(maze);
		assertEquals(1, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(0));
	}
	
	@Test
	public void solveDefault2x2MazeUsingAStar() {
		Maze maze = new Maze(2);
		Solver aStar = new AStarSolver();
		List<Node> nodeList = aStar.solve(maze);
		assertEquals(2, nodeList.size());
		assertEquals(maze.getEnd(), nodeList.get(0));
		assertEquals(maze.getStart(), nodeList.get(1));
	}
	
	@Test
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
	public void solveSimpleMazeWillWallUsingAStar() {
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
}
