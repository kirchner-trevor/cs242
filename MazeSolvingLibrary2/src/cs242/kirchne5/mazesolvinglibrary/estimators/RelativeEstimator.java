package cs242.kirchne5.mazesolvinglibrary.estimators;

import cs242.kirchne5.mazesolvinglibrary.Node;

public abstract class RelativeEstimator extends Estimator {
	
	private Node start;
	private Node end;
	
	public RelativeEstimator(final Node startNode, final Node endNode) {
		start = startNode;
		end = endNode;
	}
	
	public Node getStart() {
		return start;
	}
	
	public Node getEnd() {
		return end;
	}
}
