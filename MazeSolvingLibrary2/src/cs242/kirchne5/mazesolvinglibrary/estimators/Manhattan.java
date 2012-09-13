package cs242.kirchne5.mazesolvinglibrary.estimators;

import cs242.kirchne5.mazesolvinglibrary.Node;

public class Manhattan extends Estimator {

	private static final double CARDINAL = 100d;

	@Override
	public double getEstimated(Node from, Node to) {
		return CARDINAL * (Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY()));
	}

	@Override
	public double getAccumulated(double current) {
		return current;
	}

}
