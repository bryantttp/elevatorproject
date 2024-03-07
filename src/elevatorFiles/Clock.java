package elevatorFiles;

/**
 * The Clock class represents the simulation clock. It keeps track of the
 * simulated time.
 */
public class Clock {

	// Attribute
	// Create a static time variable which increases forever as the simulation runs
	private static int time = 0;

	// Default constructor
	public Clock() {

	}

	/**
	 * Gets the current simulated time.
	 * 
	 * @return The current simulated time.
	 */
	public static int getTime() {
		return time;
	}

	/**
	 * Method to increase simulated time by one unit.
	 */
	public static void tick() {
		time++;
	}

	/**
	 * Method to increase simulated time by a specified duration.
	 * 
	 * @param duration The duration by which to increase the simulated time.
	 */
	public static void tick(int duration) {
		time += duration;
	}
}
