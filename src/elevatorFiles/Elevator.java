package elevatorFiles;

/**
 * The Elevator class represents an elevator in the simulation. It handles
 * movement between floors, loading passengers, and unloading passengers.
 */
public class Elevator {

	private final int MOVE_TIME = 500;
	private final int LOAD_UNLOAD_TIME = 2500;
	private final int FACTOR = 5;

	private int currentFloor = 1;
	private String currentState = "Idle";

	/**
	 * Moves the elevator to the specified destination floor.
	 * 
	 * @param destination The destination floor to which the elevator will move.
	 */
	public void move(int destination) {
		if (this.currentFloor != destination) {
			// Print message indicating elevator movement
			System.out.println("Time: " + Clock.getTime() + " \t| " + Thread.currentThread().getName() + " is moving to Level " + destination);
			try {
				// Simulate elevator movement
				Thread.sleep(MOVE_TIME);
				// Increase simulation time
				Clock.tick();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Print message indicating arrival at destination floor
			System.out.println("Time: " + Clock.getTime() + " \t| " + Thread.currentThread().getName() + " has reached Level " + destination);
			this.currentFloor = destination;
		} else {
			// Print message indicating elevator is already at the requested floor
			System.out.println("Time: " + Clock.getTime() + " \t| " + Thread.currentThread().getName() + " is already on the requested floor");
		}
	}

	/**
	 * Loads passengers into the elevator.
	 */
	public void load() {
		// Print message indicating loading passengers
		System.out.println("Time: " + Clock.getTime() + " \t| " + "Loading passengers in " + Thread.currentThread().getName());
		System.out.println("Loading passengers in " + Thread.currentThread().getName());
		try {
			// Simulate loading passengers
			Thread.sleep(LOAD_UNLOAD_TIME);
			// Increase simulation time
			Clock.tick(FACTOR);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Print message indicating all passengers are aboard
		System.out.print("Time: " + Clock.getTime() + " \t| " + "Passengers all aboard " + Thread.currentThread().getName());
		System.out.println("Passengers all aboard " + Thread.currentThread().getName());
	}

	/**
	 * Unloads passengers from the elevator.
	 */
	public void offload() {
		// Print message indicating unloading passengers
		System.out.println("Time: " + Clock.getTime() + " \t| " + "Unloading passengers from " + Thread.currentThread().getName());
		try {
			// Simulate unloading passengers
			Thread.sleep(LOAD_UNLOAD_TIME);
			// Increase simulation time
			Clock.tick(FACTOR);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Print message indicating all passengers are unloaded
		System.out.println("Time: " + Clock.getTime() + " \t| " + "All passengers unloaded from " + Thread.currentThread().getName());
	}

	// Getters and setters for currentFloor and currentState
	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
}