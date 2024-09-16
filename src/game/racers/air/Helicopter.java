package game.racers.air;

import utilities.EnumContainer.Color;

import game.racers.Racer;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Helicopter extends Racer implements AerialRacer {

    private static final String CLASS_NAME = "Helicopter";
    private static final double DEFAULT_MAX_SPEED = 400;
    private static final double DEFAULT_ACCELERATION = 50;
    private static final Color DEFAULT_color = Color.BLUE;
    private int count = 1;

    /**
     * Constructs a Helicopter racer with default parameters.
     */
    
    public Helicopter() {
        super(generateDefaultName(), DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
    }
    
    /**
     * Constructs a Helicopter racer with custom parameters.
     * 
     * @param name the name of the Helicopter racer.
     * @param maxSpeed the maximum speed of the Helicopter racer.
     * @param acceleration the acceleration of the Helicopter racer.
     * @param color the color of the Helicopter racer.
     */


    public Helicopter(String name, double maxSpeed, double acceleration, Color color) {
        super(name, maxSpeed, acceleration, color);
    }


    @Override
    public Racer clone(){
        return new Helicopter(this.getName() + count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor());
    }

    /**
     * Returns a default name for the Helicopter racer.
     * 
     * @return the default name for the Helicopter racer.
     */
    
    private static String generateDefaultName() {
        return CLASS_NAME + " #" + Racer.getLastSerialNumber();
    }
    
    /**
     * Returns a string describing the specific characteristics of the Helicopter racer.
     * 
     * @return a string describing the specific characteristics of the Helicopter racer.
     */
    
    
    public String describeSpecific() {
    	return describeRacer();
    }
    
    /**
     * Returns the name of the Helicopter racer class.
     * 
     * @return the name of the Helicopter racer class.
     */

    
    public String className() {
    	return  CLASS_NAME;
    	
    }
}
