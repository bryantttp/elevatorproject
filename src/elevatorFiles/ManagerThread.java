package elevatorFiles;

public class ManagerThread implements Runnable {
	private Manager manager;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public void run() {
		manager.whichElevatorToGo();		
	}
	
}
