package game.racers.land;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Breed;
import game.racers.Racer;


/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Horse extends Racer implements LandRacer {

    
    private static final String CLASS_NAME = "Horse";
    private static final double DEFAULT_MAX_SPEED = 50;
    private static final double DEFAULT_ACCELERATION = 3;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private Breed breed;
    private int count = 1;

    /**
     * Default constructor.
     * Initializes a new {@link Horse} racer with default values:
     * a generated name, a maximum speed of 50, an acceleration of 3, and a black color.
     * The breed is set to {@link Breed#THOROUGHBRED} by default.
     */
    
    public Horse() {
    	super(generateDefaultName(), DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_COLOR);
    	this.breed = Breed.THOROUGHBRED;
    }
    
    /**
     * Parameterized constructor.
     * Initializes a new {@link Horse} racer with the specified name, maximum speed, acceleration, and color.
     * The breed is set to {@link Breed#THOROUGHBRED} by default.
     * @param name the name of the horse racer.
     * @param maxSpeed the maximum speed of the horse racer.
     * @param acceleration the acceleration of the horse racer.
     * @param color the color of the horse racer.
     */
    
    public Horse(String name, double maxSpeed, double acceleration, Color color) {
        super(name, maxSpeed, acceleration, color);
        this.breed = Breed.THOROUGHBRED;
    }


    @Override
    public Racer clone(){
        return new Horse(this.getName() + count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor());
    }

    /**
     * Generates a default name for a {@link Horse} racer, consisting of the class name and the last serial number.
     * @return a default name for a {@link Horse} racer.
    */
    
    private static String generateDefaultName() {
        return CLASS_NAME + " #" + Racer.getLastSerialNumber();
    }
    
    /**
     * Returns the breed of the horse racer.
     * @return the breed of the horse racer.
     */
    
    public Breed getBreed() {
        return breed;
    }

    /**
     * Sets the breed of the horse racer.
     * @param breed the breed to set.
     * @return {@code true} if the breed is not {@code null}, {@code false} otherwise.
     */
    
    public boolean setBreed(Breed breed) {
        if (breed != null) {
            this.breed = breed;
            return true;
        }
        return false;
    }
    
    /**
     * Returns a {@code String} describing the specific attributes of the horse racer, including its breed.
     * @return a {@code String} describing the specific attributes of the horse racer, including its breed.
     */
    
    public String describeSpecific() {
    	return describeRacer() + ", Breed: " + getBreed();
    }
    
    /**
     * Returns the name of the class as a {@code String}.
     * @return the name of the class as a {@code String}.
     */
    
    public String className() {
    	return CLASS_NAME;
    	
    }

}
