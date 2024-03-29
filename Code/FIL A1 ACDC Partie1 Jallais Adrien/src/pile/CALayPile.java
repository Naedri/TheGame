/**
 * 
 */
package pile;

import java.util.Deque;
import java.util.LinkedList;

import card.ICard;
import card.Number;
import direction.Direction;
import services.ServiceRules;

/**
 * @author Adrien Jallais
 *
 */
public abstract class CALayPile implements ILayPile {

	private Deque<ICard> deque;
	private final Direction direction;

	public CALayPile(Direction direction) {
		this.direction = direction;
		this.deque = new LinkedList<ICard>();
		this.addFirstCard();
	}

	// add the first card to a laying pile (100 or 1)
	// we can not use the add method as the deque is empty
	private void addFirstCard() {
		assert (isEmpty());
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			this.deque.add(new Number(ServiceRules.getLayDescendingPileRange()[0]));
			break;
		}
		case UP: {
			// Ascending Pile
			this.deque.add(new Number(ServiceRules.getLayAscendingPileRange()[0]));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	// shortcut
	// beware : Deque.getFirst() == Queue.getFirst();
	private ICard get() {
		return this.deque.getLast();
	}

	// AC Method

	/**
	 * to assert for the add of the first card
	 * 
	 * @return if the dequeue is empty or not
	 */
	private boolean isEmpty() {
		return this.deque.size() == 0;
	}

	/**
	 * adding card to the dequeue
	 * 
	 * @param card
	 * @return
	 */
	private boolean add(ICard card) {
		assert (card != null);
		return this.isFull() ? false : this.deque.add(card);
	}

	/**
	 * should return new in order to avoid access
	 */
	@Override
	public ICard read() {
		// return new Number(this.get().getValue());
		return new Number(this.get()); // defensive copy
	}

	@Override
	public boolean lay(ICard card) {
		return isLayable(card) ? this.add(card) : false;
	}

	@Override
	public boolean isBackwardsAllowed() {
		int val = this.get().getValue();
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			// card <= 90
			return val <= (ServiceRules.getDrawPileRange()[1] - Direction.SUPER_UP.getDRow());
		}
		case UP: {
			// Ascending Pile
			// card >= 11
			return val >= (ServiceRules.getDrawPileRange()[0] - Direction.SUPER_DOWN.getDRow());
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	// CA Method
	@Override
	public ICard readBackwardsAllowed() throws IllegalArgumentException {
		assert (isBackwardsAllowed());
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			return new Number(this.get().getSuperUp());
		}
		case UP: {
			// Ascending Pile
			return new Number(this.get().getSuperDown());
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	@Override
	public int getSize() {
		return this.deque.size();
	}

	@Override
	public boolean isFull() {
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			return this.get().getValue() <= this.readThresholdMax().getValue();
		}
		case UP: {
			// Ascending Pile
			return this.get().getValue() >= this.readThresholdMax().getValue();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	@Override
	public boolean isLayable(ICard card) {
		assert (card != null);
		if (!this.isFull()) {
			// Backwards laying
			if (this.isBackwardsAllowed() && this.readBackwardsAllowed().equals(card)) {
				return true;
			}
			// usual laying
			switch (this.direction) {
			case DOWN: {
				// Descending Pile
				// current - card > 0
				return this.get().compareTo(card) > 0;
			}
			case UP: {
				// Ascending Pile
				// current - card < 0
				return this.get().compareTo(card) < 0;
			}
			default:
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int getRemainCards() {
		if (isFull()) {
			return 0;
		}
		return (1 + this.getDirection().getDRow()
				* (this.readThresholdMax().getValue() - this.readThresholdMin().getValue()));
	}

	@Override
	public ICard readThresholdMin() {
		if (this.isFull()) {
			return new Number(this.get().getValue());
		} else {
			return new Number(this.get().getValue() + this.direction.getDRow());
		}
	}

	// @Override
	public ICard readThresholdMax() throws IllegalArgumentException {
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			return (new Number(ServiceRules.getLayDescendingPileRange()[1]));
		}
		case UP: {
			// Ascending Pile
			return (new Number(ServiceRules.getLayAscendingPileRange()[1]));
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	@Override
	public boolean reset() {
		this.deque.clear();
		this.addFirstCard();
		return this.deque.size() == 1;
	}

	/**
	 * we can return this.direction and avoid modification, as we use final
	 */
	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		String s;
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			s = "Descending Pile ( ";
			break;
		}
		case UP: {
			// Ascending Pile
			s = "Ascending Pile ( ";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
		s += this.read().toString() + " )";
		return s;
	}
}
