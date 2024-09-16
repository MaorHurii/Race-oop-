package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.BicycleType;
import utilities.EnumContainer.Color;


/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Bicycle extends Racer implements LandRacer {
	
    private static final String CLASS_NAME = "Bicycle";
    private static final int DEFAULT_WHEELS = 2;
    private static final double DEFAULT_MAX_SPEED = 270;
    private static final double DEFAULT_ACCELERATION = 10;
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private Wheeled wheeled;
    private BicycleType type;
    private int count = 1;

    
    /**
     * Constructs a default Bicycle racer with default parameters.
     */

    public Bicycle() {
        super(generateDefaultName(), DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_COLOR);
        this.wheeled = new Wheeled(DEFAULT_WHEELS);
    }


    @Override
    public Racer clone(){
        return new Bicycle(this.getName() + count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor(), this.wheeled.getNumOfWheels());
    }
    
    /**
     * Constructs a Bicycle racer with the given parameters.
     * 
     * @param name         the name of the racer
     * @param maxSpeed     the maximum speed of the racer
     * @param acceleration the acceleration of the racer
     * @param color        the color of the racer
     * @param numOfWheels  the number of wheels of the racer
     */
    
    public Bicycle(String name, double maxSpeed, double acceleration, Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        this.wheeled = new Wheeled(numOfWheels);
        this.type = BicycleType.MOUNTAIN;
    }
    
    /**
     * Generates a default name for the racer.
     * 
     * @return the default name
     */

    
    private static String generateDefaultName() {
        return CLASS_NAME + " #" + Racer.getLastSerialNumber();
    }
    
    /**
     * Gets the type of the Bicycle racer.
     * 
     * @return the type of the Bicycle racer
     */
    
    public BicycleType getType() {
        return type;
    }
    
    /**
     * Sets the type of the Bicycle racer.
     * 
     * @param type the type of the Bicycle racer
     * @return true if the type is set successfully, false otherwise
     */
    
    public boolean setType(BicycleType type) {
        if (type != null) {
            this.type = type;
            return true;
        }
        return false;
    }
    
    /**
     * Describes the specific attributes of the Bicycle racer.
     * 
     * @return a String describing the specific attributes of the Bicycle racer
     */
    
    public String describeSpecific() {
    	return describeRacer() + this.wheeled.describeSpecific() + ", Bicycle Type: " + getType();
    }
    
    /**
     * Gets the name of the Bicycle class.
     * 
     * @return the name of the Bicycle class
     */

    
    public String className() {
    	return  CLASS_NAME;
    	
    }
}
