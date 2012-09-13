package cs242.kirchne5.mazesolvinglibrary;

import java.util.PriorityQueue;

public class UniquePriorityQueue<E> extends PriorityQueue<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8418953906028043039L;

	@Override
	/**
	 * Adds the specified element to the Queue replacing any previous
	 * versions of the element.
	 * @return as specified by Collection.add(E)
	 */
	public boolean add(E e) {
		if(contains(e)) {
			remove(e);
		}
		return super.add(e);
	}
}
