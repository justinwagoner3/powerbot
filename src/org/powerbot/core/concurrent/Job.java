package org.powerbot.core.concurrent;

/**
 * A {@link Job} is generally deployed in an asynchronous or concurrent environment.
 * Submitted to a {@link Container}.
 *
 * @author Timer
 */
public interface Job {
	/**
	 * Executes the job or task of this worker.
	 */
	public void work();

	/**
	 * Makes an attempt to wait for this thread to complete by pausing (suspending) the calling thread.
	 */
	public void join();

	/**
	 * Makes an attempt to wait for this thread to complete by pausing (suspending) the calling thread within a certain time limit.
	 *
	 * @param timeout The maximum amount of time to allot for this worker to join with the calling thread.
	 * @return <tt>true</tt> when successfully joined; otherwise <tt>false</tt>.
	 */
	public boolean join(int timeout);

	/**
	 * @return Whether or not this worker is alive.
	 */
	public boolean isAlive();

	/**
	 * Interrupts the working, bringing it to a halt (if alive).
	 */
	public void interrupt();

	/**
	 * @return Whether or not this worker has been interrupted.
	 */
	public boolean isInterrupted();

	/**
	 * Sets the {@link Container} of which this {@link Job} was submitted to.
	 * Internal use only.
	 *
	 * @param container The {@link Container} in which this {@link Job} was submitted to.
	 */
	public void setContainer(final Container container);

	/**
	 * @return The {@link Container} in which this {@link Job} was submitted to.
	 */
	public Container getContainer();
}
