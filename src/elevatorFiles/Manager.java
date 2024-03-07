package elevatorFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Manager {
	private Map<ElevatorThread,Thread> elevators = new HashMap<ElevatorThread,Thread>();
	private Queue<Integer[]> commands = new LinkedList<Integer[]>();
	
	public Map<ElevatorThread,Thread> getElevators(){
		return this.elevators;
	}
	
	public void setElevators(ElevatorThread ...elevators1 ) {
		for (ElevatorThread e : elevators1) {
			Thread t = new Thread(e);
			this.elevators.put(e,t);
		}
	}
	
	public Queue<Integer[]> getCommands(){
		return commands;
	}
	
	public void setCommands(File file) {
		System.out.println("Reading commands");
		try {
			BufferedReader bR = new BufferedReader(new FileReader(file));
			String line;
			while ((line = bR.readLine()) != null) {
				String[] input = line.split(" ");
				for (int i = 0; i < input.length ; i += 2) {
					int origin = Integer.parseInt(input[i]);
					int destination = Integer.parseInt(input[i+1]);
					Integer[] tempCommand = new Integer[2];
					tempCommand[0] = origin;
					tempCommand[1] = destination;
					this.commands.add(tempCommand);
				}
			}
			bR.close();
		} catch (IOException e) {
			e.printStackTrace();
			}
		System.out.println("Commands received");
	}
	
	public void whichElevatorToGo() {
		while (this.commands.size() != 0) {
			boolean flagCondition = false;
			ElevatorThread elevator = new ElevatorThread();
			double distance = 10;
			for (ElevatorThread e : this.elevators.keySet()) {
				// Checks if elevator is idle
				if (e.getElevator().getCurrentState() == "Idle") {
					// Input to get elevator
					if (Math.abs(e.getElevator().getCurrentFloor()- this.commands.peek()[0]) <= distance ) {
						elevator = e;
						distance = Math.abs(e.getElevator().getCurrentFloor()- this.commands.peek()[0]);
						flagCondition = true;
					}
				}
			}
			if (flagCondition == true) {
				elevator.setTasks(commands.peek());
				elevator.getElevator().setCurrentState("Moving");
//				Thread thread = new Thread(elevator);
//				thread.setName(elevators.get(elevator).getName());
//				thread.start();
//				try {
//					this.elevators.get(elevator).join();
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
				commands.poll();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
			}
			else {
//				System.out.println("Command paused");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
//				try {
//					synchronized(Elevator.obj) {wait();}
//				} catch (InterruptedException e1) {
//					System.out.println("Command resumed");
//				}
//				finally {
//					System.out.println("Command resumed");
//				}
			}
			
		}
			
	}
}