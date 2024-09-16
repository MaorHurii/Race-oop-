package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Airplane extends Racer implements AerialRacer {
	
    private static final String CLASS_NAME = "Airplane";
    private static final int DEFAULT_WHEELS = 3;
    private static final double DEFAULT_MAX_SPEED = 885;
    private static final double DEFAULT_ACCELERATION = 100;
    private static final Color DEFAULT_color = Color.BLACK;
    private Wheeled wheeled;
    private int count = 1;

    /**
     * Constructs an Airplane object with default values for name, maximum speed, acceleration, and color. 
     * The number of wheels is set to the default value of 3.
     */
    
    public Airplane() {
        super(generateDefaultName(), DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
        this.wheeled = new Wheeled(DEFAULT_WHEELS);

    }
    @Override
    public Racer clone(){
        return new Airplane(this.getName()+ count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor(), this.wheeled.getNumOfWheels());
    }

    /**
     * Constructs an Airplane object with the specified values for name, maximum speed, acceleration, color, and number of wheels.
     * @param name the name of the airplane
     * @param maxSpeed the maximum speed of the airplane
     * @param acceleration the acceleration of the airplane
     * @param color the color of the airplane
     * @param numOfWheels the number of wheels of the airplane
     */


    public Airplane(String name, double maxSpeed, double acceleration, Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        this.wheeled = new Wheeled(numOfWheels);
    }
    
    /**
     * Generates a default name for the airplane.
     * @return a String representing the default name of the airplane
     */
    
    private static String generateDefaultName() {
        return CLASS_NAME + " #" + Racer.getLastSerialNumber();
    }
    
    /**
     * Returns a String representation of the Airplane object, including the Racer's properties and the number of wheels of the airplane.
     * @return a String representing the Airplane object
     */
    
    public String describeSpecific() {
    	return describeRacer() + this.wheeled.describeSpecific();
    }
    
    /**
     * Returns the name of the class.
     * @return a String representing the class name
     */
    
    public String className() {
    	return  CLASS_NAME;
    	
    }

}
