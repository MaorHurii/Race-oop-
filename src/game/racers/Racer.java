package game.racers;

import GUI.racers.broken;
import GUI.racers.disabled;
import GUI.racers.racerState;
import game.arenas.Arena;
import utilities.EnumContainer.Color;
import utilities.Fate;
import utilities.Mishap;
import utilities.MyObservable;
import utilities.Point;

import java.util.Random;


/**
* @author Maor Huri 207871435
* @author Matan Atias 318856937 */

public abstract class Racer extends MyObservable implements Runnable, Cloneable{
    private static int lastSerialNumber = 1;

    private int serialNumber;
    private String name;
    private Point currentLocation;
    private Point finish;
    private Arena arena;
    private double maxSpeed;
    private double acceleration;
    private double currentSpeed;
    private double failureProbability;
    private Color color;
    private Mishap mishap;
    private racerState currentState;
    private int brokenSpot;

    
    public Racer(String name, double maxSpeed, double acceleration, Color color) {
    	
        this.setSerialNumber(getLastSerialNumber());
        this.lastSerialNumber++;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.currentSpeed = 0;
        this.failureProbability = 0;
        this.color = color;
    }
    public Racer(){};


    /**
     * Executes the racer's movement in a separate thread until the racer reaches the end of the arena.
     * The racer continuously moves based on the arena's friction and sleeps for a specified interval between movements.
     * Once the racer reaches the end of the arena, it is added to the completed racers and removed from the active racers in the arena.
     * The method also marks the racer as changed, notifying its observers.
     */
    @Override
    public void run() {

        while (this.currentLocation.getX() < this.getArena().getLength()) {
            if (!arena.allFinished()) {
                    if (this.getCurrentLocation().getX() >= this.getBrokenSpot() && this.getMishap().getTurnsToFix()<= 0) {
                        this.setState(new broken());
                        Random random = new Random();
                        int turns = random.nextInt(100, 200);
                        this.getMishap().setTurnsToFix(turns);
                        break;

                    }
                    try {
                        if (this.getState() instanceof disabled)
                            break;
                        this.move(this.getArena().getFriction());

                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();

                }
            }
        }


        setChanged();
        this.notifyObservers();

    }

    public abstract Racer clone();
    public void upgrade(Color newColor) {
        this.color = newColor;
    }

    public void setBrokenSpot(int brokenSpot){
        this.brokenSpot = brokenSpot;
    }

    public int getBrokenSpot(){
        return this.brokenSpot;
    }
    public racerState getState(){
        return currentState;
    }
    public void setState(racerState state){
        this.currentState = state;
    }
    public void initRace(Arena arena, Point start, Point finish) {
       
        this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
	
    }

    /**
    Moves the racer across the arena based on a friction value. If the racer has a mishap,
    its movement is affected by the mishap's reduction factor.
    @param friction the friction value for the racer
    @return the racer's new location
    */
    
    public void move(double friction)
	{

        double reductionFactor = 1;
        if (!(this.hasMishap()) && Fate.breakDown()) {
            this.mishap = Fate.generateMishap();
        }

        if (this.hasMishap()) {
            currentState = new broken();

            reductionFactor = mishap.getReductionFactor();
            this.mishap.nextTurn();

            if (!this.mishap.getFixable())
                currentState = new broken();


        }
        if (this.currentSpeed < this.maxSpeed) {
            this.currentSpeed += this.acceleration * friction * reductionFactor;
        }
        if (this.currentSpeed > this.maxSpeed) {
            this.currentSpeed = this.maxSpeed;
        }
        double newX = (this.currentLocation.getX() + (this.currentSpeed));
        if (newX>this.finish.getX())
            newX = this.finish.getX();
        Point newLocation = new Point(newX, this.currentLocation.getY());
        this.currentLocation = newLocation;
    }


    /**
    Moves the racer across the arena based on a friction value and a mishap's reduction factor.
    @param friction the friction value for the racer
    @param m the reduction factor due to a mishap
    @return the racer's new location
    */
    
	private Point move(double friction,double m)
	{
		if (this.currentSpeed < this.maxSpeed)
		{
			this.setCurrentSpeed(this.currentSpeed + this.acceleration * friction*m);
		}
		
		if (this.currentSpeed > this.maxSpeed) 
		{
			this.setCurrentSpeed(this.maxSpeed);
		}
		
		Point newLocation = new Point((this.currentLocation.getX() + (1 * this.currentSpeed)),this.currentLocation.getY());
        if(newLocation.getX() > this.getArena().getLength()){
            newLocation.setX(this.getArena().getLength());

        }
		this.setCurrentLocation(newLocation);
		return newLocation;
	}
    
	/**
	Abstract method that describes the specific racer type.
	@return a String describing the specific racer type
	*/
    	
    public abstract String describeSpecific();

    /**
    Returns a String describing the racer's attributes including name, serial number,
    max speed, acceleration, and color.
    @return a String describing the racer's attributes
    */
    
    public String describeRacer() {
    	return " name: " + this.getName() + ", SerialNumber: " + getSerialNumber() +
   			 ", maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + this.getColor();
    }
    
    /**
    Prints a message to the console introducing the racer and its specific attributes.
    */
    
    public void introduce() {
    	 System.out.println("[" + className() + "]" + describeSpecific());
    }
    
    /**
    Returns the name of the racer's class.
    @return the name of the racer's class
    */
    
    public abstract String className();
    
    /**
    Returns whether or not the racer has a mishap.
    @return true if the racer has a mishap, false otherwise
    */
    
    public boolean hasMishap() {
    	if(mishap != null) {
    		return true;
    	}
    	return false;
    }
    
    //Getters
    
    public int getSerialNumber() {
        return this.serialNumber;
    }
    
    
    public static int getLastSerialNumber() {
        return lastSerialNumber;
    }

    public String getName() {
        return name;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public Point getFinish() {
        return finish;
    }

    public Arena getArena() {
        return arena;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getFailureProbability() {
        return failureProbability;
    }

    public Color getColor() {
        return color;
    }

    public Mishap getMishap() {
        return mishap;
    }

    //Setters, all boolean. 
    
    public boolean setSerialNumber(int serialNumber) {
        if (serialNumber >= 0) {
            this.serialNumber = serialNumber;
            return true;
        }
        return false;
    }

    public boolean setName(String name) {
        if (name != null) {
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean  setCurrentLocation(Point currentLocation) {
        if (currentLocation != null) {
            this.currentLocation = currentLocation;
            return true;
        }
        return false;
    }

    public boolean setFinish(Point finish) {
        if (finish != null) {
            this.finish = finish;
            return true;
        }
        return false;
    }

    public boolean setArena(Arena arena) {
        if (arena != null) {
            this.arena = arena;
            return true;
        }
        return false;
    }

    public boolean setMaxSpeed(double maxSpeed) {
        if (maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
            return true;
        }
        return false;
    }

    public boolean setAcceleration(double acceleration) {
        if (acceleration > 0) {
            this.acceleration = acceleration;
            return true;
        }
        return false;
    }

    public boolean setCurrentSpeed(double currentSpeed) {
        if (currentSpeed >= 0) {
            this.currentSpeed = currentSpeed;
            return true;
        }
        return false;
    }

    public boolean setFailureProbability(double failureProbability) {
        if (failureProbability >= 0 && failureProbability <= 1) {
            this.failureProbability = failureProbability;
            return true;
        }
        return false;
    }

    public boolean setColor(Color color) {
        if (color != null) {
            this.color = color;
            return true;
        }
        return false;
    }
   
}