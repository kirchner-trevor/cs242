package cs242.kirchne5.mazesolvinglibrary.estimators;

import cs242.kirchne5.mazesolvinglibrary.Node;

public class EuclideanSquared extends Estimator {

	private static final double CARDINAL = 100d;

	@Override
	public double getEstimated(Node from, Node to) {
		return CARDINAL * (Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
	}

	@Override
	public double getAccumulated(double current) {
		return Math.pow(current, 2);
	}

}
