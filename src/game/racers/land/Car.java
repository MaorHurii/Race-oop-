package game.racers.land;
import utilities.EnumContainer.Engine;
import utilities.EnumContainer.Color;
import game.racers.Racer;
import game.racers.Wheeled;


/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Car extends Racer implements LandRacer {

    private static final String CLASS_NAME = "Car";
    private static final int DEFAULT_WHEELS = 4;
    private static final double DEFAULT_MAX_SPEED = 400;
    private static final double DEFAULT_ACCELERATION = 20;
    private static final Color DEFAULT_COLOR = Color.RED;
    private Wheeled wheeled;
    private Engine engine;
    private int count = 1;

    /**
     * Constructor with no arguments. Initializes a car racer with default values.
     */
    
    public Car() {
        super(generateDefaultName(), DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_COLOR);
        this.wheeled = new Wheeled(DEFAULT_WHEELS);
        this.engine = Engine.FOURSTROKE;
    }

    
    /**
     * Constructor with arguments. Initializes a car racer with the given values.
     * 
     * @param name - The name of the car racer.
     * @param maxSpeed - The maximum speed of the car racer.
     * @param acceleration - The acceleration of the car racer.
     * @param color - The color of the car racer.
     * @param numOfWheels - The number of wheels of the car racer.
     */

    public Car(String name, double maxSpeed, double acceleration, Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        this.wheeled = new Wheeled(numOfWheels);
        this.engine = Engine.FOURSTROKE;
    }


    @Override
    public Racer clone(){
        return new Car(this.getName() + count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor(), this.wheeled.getNumOfWheels());
    }
    /**
     * A static method that generates a default name for a car racer, which is a concatenation of the class name and the last serial number.
     * 
     * @return The default name of the car racer.
     */
    
    private static String generateDefaultName() {
        return CLASS_NAME  + " #" + Racer.getLastSerialNumber();
    }

    /**
     * Returns the engine type of the car racer.
     * 
     * @return The engine type of the car racer.
     */
    
    public Engine getEngine() {
        return engine;
    }

    /**
     * Sets the engine type of the car racer to the given engine type.
     * 
     * @param engine - The new engine type.
     * @return true if the engine type was set successfully, false otherwise.
     */
    
    public boolean setEngine(Engine engine) {
        if (engine != null) {
            this.engine = engine;
            return true;
        } 
        else 
            return false;
     }
    
    /**
     * Returns a String that describes the car racer, including its racer details, its number of wheels, and its engine type.
     * 
     * @return A String that describes the car racer.
     */
    
    public String describeSpecific() {
    	return  describeRacer() + this.wheeled.describeSpecific() + ", Engine Type: " + getEngine();
    }
    
    /**
     * Returns the class name of the car racer.
     * 
     * @return The class name of the car racer.
     */
    
    public String className() {
    	return  CLASS_NAME;
    	
    }
    
}
