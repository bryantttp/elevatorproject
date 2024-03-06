package elevatorFiles;

import java.io.File;

public class Runner {
	public static void main(String[] args) {
		Manager manager = new Manager();
		Elevator elevator1 = new Elevator();
		Elevator elevator2 = new Elevator();
//		Elevator elevator3 = new Elevator();
		ElevatorThread elevatorThread1 = new ElevatorThread();
		elevatorThread1.setElevator(elevator1);
		ElevatorThread elevatorThread2 = new ElevatorThread();
		elevatorThread2.setElevator(elevator2);
//		ElevatorThread elevatorThread3 = new ElevatorThread();
//		elevatorThread3.setElevator(elevator3);
		manager.setElevators(elevatorThread1,elevatorThread2);
		manager.getElevators().get(elevatorThread1).setName("Elevator 1");
		manager.getElevators().get(elevatorThread2).setName("Elevator 2");
//		manager.getElevators().get(elevatorThread3).setName("Elevator 3");
		// Add file to the brackets here to simulate commands
		manager.setCommands(new File("test.txt"));
//		manager.getElevators().get(elevatorThread1).start();
//		manager.getElevators().get(elevatorThread2).start();
		manager.getElevators().get(elevatorThread1).start();
		manager.getElevators().get(elevatorThread2).start();
		manager.whichElevatorToGo();
	}
}
