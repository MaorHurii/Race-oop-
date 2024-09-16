package game.racers;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Wheeled {
	
    private int numOfWheels;

    /**
     * Constructs a Wheeled racer with a specified number of wheels.
     * @param numOfWheels the number of wheels of the racer.
     */
    
    public Wheeled(int numOfWheels) {
        this.numOfWheels = numOfWheels;
    }
    
    /**
     * Constructs a Wheeled racer with zero wheels.
     */

    public Wheeled() {
        this(0);
    }

    /**
     * Describes the specific attributes of the Wheeled racer.
     * @return a String representing the number of wheels of the racer.
     */

    
    public String describeSpecific() {
    	return ", Number of Wheels: " + getNumOfWheels();
    }
    
    /**
     * Gets the number of wheels of the racer.
     * @return the number of wheels of the racer.
     */

    public int getNumOfWheels() {
        return numOfWheels;
    }
    
    /**
     * Sets the number of wheels of the racer.
     * @param numOfWheels the number of wheels of the racer.
     * @return true if the number of wheels is greater than or equal to zero, false otherwise.
     */

    public boolean setNumOfWheels(int numOfWheels) {
        if(numOfWheels >= 0) {
            this.numOfWheels = numOfWheels;
            return true;
        }
        else {
            return false;
        }
    }
}
