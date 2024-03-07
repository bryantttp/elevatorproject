package elevatorFiles;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The ElevatorThread class represents a thread that controls the movement of an
 * elevator.
 */
public class ElevatorThread implements Runnable {

	private Elevator elevator;
	private Queue<ElevatorCommand> tasks = new LinkedList<>();
	private ElevatorCommand command;

	/**
	 * Get the elevator associated with this thread.
	 * 
	 * @return The elevator associated with this thread.
	 */
	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * Set the elevator associated with this thread.
	 * 
	 * @param elevator The elevator to be associated with this thread.
	 */
	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}

	/**
	 * Get the command associated with this thread.
	 * 
	 * @return The command associated with this thread.
	 */
	public ElevatorCommand getCommand() {
		return command;
	}

	/**
	 * Set the command associated with this thread.
	 * 
	 * @param command The command to be associated with this thread.
	 */
	public void setCommand(ElevatorCommand command) {
		this.command = command;
	}

	/**
	 * Add a task to the queue of tasks for this elevator thread.
	 * 
	 * @param command The command to be added to the task queue.
	 */
	public void setTasks(ElevatorCommand command) {
		this.tasks.add(command);
		
	}

	/**
	 * Default constructor for ElevatorThread. Initializes elevator and command to
	 * null.
	 */
	public ElevatorThread() {
		this.elevator = null;
		this.command = null;
	}

	/**
	 * Constructor for ElevatorThread with specified elevator and command.
	 * 
	 * @param elevator The elevator associated with this thread.
	 * @param command  The initial command for this thread.
	 */
	public ElevatorThread(Elevator elevator, ElevatorCommand command) {
		this.elevator = elevator;
		this.command = command;
	}

	/**
	 * Run method for ElevatorThread. This method controls the movement of the
	 * elevator based on the commands in the task queue.
	 */
	@Override
	public synchronized void run() {

		while (true) {
			if (!tasks.isEmpty()) {
				command = tasks.poll(); // Retrieve and remove the next command from the task queue
				System.out.println(elevator.getElevatorThreadName() + " is accepting call from passengers at Level " + command.getOrigin());
				elevator.move(command.getOrigin());
				elevator.load();
				elevator.move(command.getDestination());
				elevator.offload();
				System.out.println(Thread.currentThread().getName() + " is available at Level " + elevator.getCurrentFloor());
				synchronized (Manager.lock) {
					Manager.lock.notifyAll(); // Notify other threads waiting on Manager.lock
				}
			} else {
				elevator.setCurrentState("Idle");
				}
			}

		}
}

