package org.powerbot.event;

import java.util.EventListener;
import java.util.EventObject;

/**
 * An event that can by fired by this game.
 *
 * @author Timer
 */
public abstract class GameEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	private static final Object SOURCE = new Object();
	public int type = -1;

	public GameEvent() {
		super(SOURCE);
	}

	/**
	 * Dispatches this event to the provided listener.
	 *
	 * @param eventListener The listener to dispatch this event to.
	 */
	public abstract void dispatch(final EventListener eventListener);

	/**
	 * Sets the type of event this event object is.
	 *
	 * @param type The type of game event associated with this class.
	 */
	protected void setType(final int type) {
		this.type = type;
	}
}
