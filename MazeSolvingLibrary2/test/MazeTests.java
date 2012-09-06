import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.Test;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;


public class MazeTests {

	private Maze maze = new Maze(10);//Maze with nodes from (0, 0) -> (9, 9)
	
	@Test
	public void negativeSizedMaze() {
		try {
			new Maze(-1);
		} catch (InvalidParameterException e) {
			assert(true);
		}
	}
	
	@Test
	public void getNormalAdjacentNodes() {
		List<Node> nodeList = maze.getAdjacent(maze.getNode(1, 1));
		assertEquals(8, nodeList.size());
		int index = 0;
		for(int x = 0; x <= 2; ++x) {
			for(int y = 0; y <= 2; ++y) {
				if(!(x == 1 && y == 1)) {
					assertEquals(true, nodeList.get(index).getLocation().distance(x, y) == 0d);
					index++;
				}
			}
		}
	}
	
	@Test
	public void getCornerAdjacentNodes() {
		List<Node> nodeList = maze.getAdjacent(maze.getNode(0, 0));
		assertEquals(3, nodeList.size());
		int index = 0;
		for(int x = 0; x <= 1; ++x) {
			for(int y = 0; y <= 1; ++y) {
				if(!(x == 0 && y == 0)) {
					assertEquals(true, nodeList.get(index).getLocation().distance(x, y) == 0d);
					index++;
				}
			}
		}
	}
	
	@Test
	public void defaultStartNode() {
		Maze newMaze = new Maze(10);
		assertEquals(true, newMaze.isStartNode(newMaze.getNode(0, 0)));
		newMaze = new Maze();
		assertEquals(true, newMaze.isStartNode(newMaze.getNode(0, 0)));
	}
	
	@Test
	public void defaultEndNode() {
		Maze newMaze = new Maze(10);
		assertEquals(true, newMaze.isEndNode(newMaze.getNode(9, 9)));
		newMaze = new Maze();
		assertEquals(true, newMaze.isEndNode(newMaze.getNode(0, 0)));
	}
	
	@Test
	public void setStartNode() {
		Maze newMaze = new Maze(2);
		newMaze.setStart(0, 0);
		newMaze.setStart(0, 1);
		assertEquals(true, newMaze.isStartNode(newMaze.getNode(0, 1)));
		assertEquals(false, newMaze.isStartNode(newMaze.getNode(0, 0)));
	}

	@Test
	public void setEndNode() {
		Maze newMaze = new Maze(2);
		newMaze.setEnd(0, 0);
		newMaze.setEnd(0, 1);
		assertEquals(true, newMaze.isEndNode(newMaze.getNode(0, 1)));
		assertEquals(false, newMaze.isEndNode(newMaze.getNode(0, 0)));
	}

}
