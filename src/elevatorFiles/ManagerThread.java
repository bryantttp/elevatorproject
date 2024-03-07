package elevatorFiles;

/**
 * The ManagerThread class represents a thread responsible for deploying
 * elevators in the simulation.
 */
public class ManagerThread implements Runnable {
	private Manager manager;

	/**
	 * Retrieves the manager associated with this thread.
	 * 
	 * @return The manager object.
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Sets the manager associated with this thread.
	 * 
	 * @param manager The manager object to set.
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Runs the thread to deploy elevators.
	 */
	@Override
	public void run() {
		manager.deployElevators();
	}
}