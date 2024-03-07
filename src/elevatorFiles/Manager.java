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
     * sends command to nearest elevator
     * calls selectedElevator() to check for nearest idle elevator
     * calls waitForElevatorAvailability() synchronized lock
     */
    public void deployElevators() {
        while (!commands.isEmpty()) {
            ElevatorThread selectedElevator = findNearestIdleElevator();
            
            if (selectedElevator != null) {
            	Integer[] commandArray = commands.poll();
            	ElevatorCommand commandToSend = new ElevatorCommand(commandArray[0], commandArray[1]);
                selectedElevator.setTasks(commandToSend);
                System.out.println("Passengers call from Level " + commandToSend.getOrigin() );
                selectedElevator.getElevator().setCurrentState("Moving");
            } else {
                waitForElevatorAvailability();
            }
        }
    }
    
    /**
     * checks for idle elevators
     * then filters through them to find the nearest one to next call
     * 
     * @return elevatorThread of the nearest elevator
     */
    private ElevatorThread findNearestIdleElevator() {
        ElevatorThread selectedElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorThread elevator : elevators.keySet()) {
            if (elevator.getElevator().getCurrentState().equals("Idle")) {
                int distance = Math.abs(elevator.getElevator().getCurrentFloor() - commands.peek()[0]);
                if (distance <= minDistance) {
                    selectedElevator = elevator;
                    minDistance = distance;
                }
            }
        }

        return selectedElevator;
    }
    
    /**
     * synchronized lock to check if elevators are available for calls
     */
    private void waitForElevatorAvailability() {
        try {
            System.out.println("Waiting");
            synchronized (lock) {
                lock.wait();
            }
            System.out.println("Resume");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}