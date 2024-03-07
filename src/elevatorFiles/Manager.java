package elevatorFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * The Manager class manages the elevators and commands in the simulation.
 */
public class Manager {	
    private Map<ElevatorThread,Thread> elevators = new HashMap<ElevatorThread,Thread>();
    private Queue<Integer[]> commands = new LinkedList<Integer[]>();
    static Object lock = new Object();
    
    /**
     * Retrieves the elevators.
     * @return A map containing the elevators.
     */
    public Map<ElevatorThread,Thread> getElevators(){
        return this.elevators;
    }
    
    /**
     * Sets the elevators.
     * @param elevators1 The elevators to set.
     */
    public void setElevators(ElevatorThread ...elevators1 ) {
        for (ElevatorThread e : elevators1) {
            Thread t = new Thread(e);
            this.elevators.put(e,t);
        }
    }
    
    /**
     * Retrieves the commands.
     * @return A queue containing the commands.
     */
    public Queue<Integer[]> getCommands(){
        return commands;
    }
    
    /**
     * Sets the commands from a file.
     * @param file The file containing the commands.
     */
    public void setCommands(File file) {
        System.out.println("Reading commands");
        try (BufferedReader bR = new BufferedReader(new FileReader(file))){
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
    
    /**
     * Determines which elevator to assign a command to.
     */
    public void deployElevators() {
        while (this.commands.size() != 0) {
            boolean flagCondition = false;
            ElevatorThread elevator = new ElevatorThread();
            int distance = Integer.MAX_VALUE;
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
                elevator.setTasks(commands.poll());
                elevator.getElevator().setCurrentState("Moving");
            }
            else {
                try {
                	System.out.println("Waiting");
                    synchronized(lock) {
                        lock.wait();
                    }
                    System.out.println("Resume");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            
        }
            
    }
}