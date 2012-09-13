package cs242.kirchne5.mazesolvinglibrary.estimators;

import cs242.kirchne5.mazesolvinglibrary.Node;

public class Diagonal extends Estimator {

	private static final double CARDINAL = 100d;
	private static final double DIAGONAL = 141d;

	@Override
	public double getEstimated(Node from, Node to) {
		final double diagonal = Math.min(Math.abs(from.getX() - to.getX()), Math.abs(from.getY() - to.getY()));
		final double cardinal = (Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY()));
		return (DIAGONAL * diagonal) + (CARDINAL * (cardinal - (2 * diagonal)));
	}

	@Override
	public double getAccumulated(double current) {
		return current;
	}

}
