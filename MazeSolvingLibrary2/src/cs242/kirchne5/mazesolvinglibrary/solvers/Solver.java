package cs242.kirchne5.mazesolvinglibrary.solvers;

import java.util.List;

import cs242.kirchne5.mazesolvinglibrary.Maze;
import cs242.kirchne5.mazesolvinglibrary.Node;

public interface Solver {
	/**
	 * Solves a given maze.
	 * @param maze the maze to be solved
	 * @return a list of nodes along the shortest path from the start to the end
	 */
	public List<Node> solve(Maze maze);
}
