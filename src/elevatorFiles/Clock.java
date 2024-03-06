package elevatorFiles;

public class Clock {

	// Attribute
	// Create a static time variable which increases forever as the simulation runs
	private static int time = 0;
	

	// Add default constructor
	
	public Clock() {
		
	}
	
	public static int getTime() {
		return Clock.time;
	}
	
	public static void tick() {
		time++;
	}
	
	public static void tick(int duration) {
		time+=duration;
	}
	
}
