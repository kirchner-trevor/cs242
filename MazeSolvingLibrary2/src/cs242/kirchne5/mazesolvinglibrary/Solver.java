package cs242.kirchne5.mazesolvinglibrary;

import java.util.List;

public interface Solver {
	/**
	 * Must solve a supplied Maze and return a list of nodes along the path 
	 * from the start to the end.
	 * @param m
	 * @return
	 */
	public List<Node> solve(Maze m);
}
