package elevatorFiles;

public class Elevator {
	private int currentFloor = 1;
	private String currentState = "Idle";
//	public static Object obj = new Object();
	
	public void move(int destination) {
		if (this.currentFloor != destination) {
			
			System.out.print("Time: " + Clock.getTime() + " \t| ");
			System.out.println(Thread.currentThread().getName() + " is moving to Level " + destination);
			try {
				Thread.sleep(500);
				Clock.tick();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Time: " + Clock.getTime() + " \t| ");
			System.out.println(Thread.currentThread().getName() + " has reached Level " + destination);
			this.currentFloor = destination;
		}
		else {
			System.out.print("Time: " + Clock.getTime() + " \t| ");
			System.out.println(Thread.currentThread().getName() + " is already on the requested floor");
		}
	}
	
	public void load() {
		System.out.print("Time: " + Clock.getTime() + " \t| ");
		System.out.println("Loading passengers in " + Thread.currentThread().getName());
		try {
			Thread.sleep(2500);
			Clock.tick(5);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.print("Time: " + Clock.getTime() + " \t| ");
		System.out.println("Passengers all aboard " + Thread.currentThread().getName());
	}
	
	public void offload() {
		System.out.print("Time: " + Clock.getTime() + " \t| ");
		System.out.println("Unloading passengers from " + Thread.currentThread().getName() );
		try {
			Thread.sleep(2500);
			Clock.tick(5);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.print("Time: " + Clock.getTime() + " \t| ");
		System.out.println("All passengers unloaded from " + Thread.currentThread().getName());
//		synchronized(obj) {notifyAll();}
	}

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
