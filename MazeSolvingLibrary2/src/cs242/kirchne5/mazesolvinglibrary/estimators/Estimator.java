package cs242.kirchne5.mazesolvinglibrary.estimators;

import cs242.kirchne5.mazesolvinglibrary.Node;

public abstract class Estimator {
	
	/**
	 * Calculates the estimated distance between two nodes.
	 * @param from the source node
	 * @param to the destination node
	 * @return the estimated cost to move between the two nodes
	 */
	public abstract double getEstimated(final Node from, final Node to);
	
	/**
	 * Calculates the accumulated cost taking into account any adjustments required
	 * by the Estimator.
	 * @param current the current accumulated distance
	 * @return the adjusted accumulated distance
	 */
	public abstract double getAccumulated(final double current);
}
