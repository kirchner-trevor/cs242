package cs242.kirchne5.mazesolvinglibrary.estimators;

import cs242.kirchne5.mazesolvinglibrary.Node;

public class ManhattanTieBreaker extends RelativeEstimator {
	
	private static final double SCALING_FACTOR = 0.001;
	
	public ManhattanTieBreaker(final Node startNode, final Node endNode) {
		super(startNode, endNode);
	}

	@Override
	public double getEstimated(Node from, Node to) {
		final Node end = getEnd();
		final Node start = getStart();
		
		//Calculates the cross product of from->to and start->end
		final double cross = Math.abs(((from.getX() - to.getX()) * (start.getY() - end.getY())) - ((start.getX() - end.getX()) * (from.getY() - to.getY())));
		final double estimated = (new Manhattan()).getEstimated(from, to);
		return estimated + (cross * SCALING_FACTOR);
	}

	@Override
	public double getAccumulated(double current) {
		return current;
	}

}
