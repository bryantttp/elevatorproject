package elevatorFiles;

import java.util.LinkedList;
import java.util.Queue;

public class ElevatorThread implements Runnable{
	private Elevator elevator;
	Queue<Integer[]> tasks = new LinkedList<>();
	private Integer[] command;
	
	public Elevator getElevator() {
		return elevator;
	}
	public void setElevator(Elevator elevator1) {
		this.elevator = elevator1;
	}
	
	public Integer[] getCommand() {
		return command;
	}
	public void setCommand(Integer[] command1) {
		this.command = command1;
	}
	
	public void setTasks(Integer[] command1) {
		this.tasks.add(command1);
	}
		
	public ElevatorThread() {
		this.elevator = null;
		this.command = null;
	}
	
	public ElevatorThread(Elevator elevator, Integer[] command) {
		this.elevator = elevator;
		this.command = command;
	}
	
	
	
	@Override
	public synchronized void run() {
		while(true) {
			if (tasks.size() >= 1) {
				command = tasks.peek();
				elevator.move(command[0]);
				elevator.load();
				elevator.move(command[1]);
				elevator.offload();
				System.out.println(Thread.currentThread().getName() + " is available");
				tasks.poll();
			}
			else {
				elevator.setCurrentState("Idle");
			}
		}
//				elevator.setCurrentState("Idle");
//				System.out.println(Thread.currentThread().getName() + " is available");
//				tasks.poll();
			}
			
		
}		

	

