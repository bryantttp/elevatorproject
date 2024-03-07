package elevatorFiles;

public class Clock {

	// Attribute
	// Create a static time variable which increases forever as the simulation runs
	private static int time = 0;
	

	// Add default constructor
	
	public Clock() {
		
	}
	
	public static String getTime() {
		String minutes = String.valueOf(time / 60);
		String seconds = String.format("%02d", time % 60);
		
		String displayTime = (minutes) + ":" + String.valueOf(seconds);
		return displayTime;
	}
	

	/**
	 * Method to increase simulated time
	 */
	public static void tick() {
		time++;
	}
	
	public static void tick(int duration) {
		time+=duration;
	}
	
}
