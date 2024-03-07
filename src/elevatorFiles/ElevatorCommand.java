package elevatorFiles;

public class ElevatorCommand {

	private int origin;
	private int destination;

	ElevatorCommand(int origin, int destination) {
		this.origin = origin;
		this.destination = destination;
	}

	int getOrigin() {
		return origin;
	}

	void setOrigin(int origin) {
		this.origin = origin;
	}

	int getDestination() {
		return destination;
	}

	void setDestination(int destination) {
		this.destination = destination;
	}
}