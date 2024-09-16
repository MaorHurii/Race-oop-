package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;


/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class RowBoat extends Racer implements NavalRacer {
    private static final String CLASS_NAME = "RowBoat";
    private static final double DEFAULT_MAX_SPEED = 75;
    private static final double DEFAULT_ACCELERATION = 10;
    private static final Color DEFAULT_COLOR = Color.RED;
    private BoatType type;
    private Team team;
    private int count = 1;
    
    
    /**
     * Constructs a new RowBoat object with default values.
     * Uses the generateDefaultName method to create the object's name.
     */
    
    public RowBoat() {
        super(generateDefaultName(), DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_COLOR);
        this.type = BoatType.SKULLING;
        this.team = Team.DOUBLE;
    }
    
    /**
     * Constructs a new RowBoat object with the given parameters.
     * @param name the name of the RowBoat object.
     * @param maxSpeed the maximum speed of the RowBoat object.
     * @param acceleration the acceleration of the RowBoat object.
     * @param color the color of the RowBoat object.
     */
    
    public RowBoat(String name, double maxSpeed, double acceleration, Color color) {
        super(name, maxSpeed, acceleration, color);
        this.type = BoatType.SKULLING;
        this.team = Team.DOUBLE;
    }

    @Override
    public Racer clone(){
        return new RowBoat(this.getName() + count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor());
    }
    /**
     * Generates a default name for a RowBoat object.
     * Uses the CLASS_NAME constant and the getLastSerialNumber method from the Racer class.
     * @return the default name for a RowBoat object.
     */
    
    private static String generateDefaultName() {
        return CLASS_NAME + " #" + Racer.getLastSerialNumber();
    }
    
    
    /**
     * Returns the type of the RowBoat object.
     * @return the type of the RowBoat object.
     */
    
    public BoatType getType() {
        return this.type;
    }
    
    /**
     * Sets the type of the RowBoat object.
     * @param type the new type of the RowBoat object.
     * @return true if the type was set successfully, false otherwise.
     */
    
    public boolean setType(BoatType type) {
        if(type != null) {
            this.type = type;
            return true;
        }
        return false;
    }
    
    /**
     * Returns the team of the RowBoat object.
     * @return the team of the RowBoat object.
     */

    
    public Team getTeam() {
        return this.team;
    }
    
    /**
     * Sets the team of the RowBoat object.
     * @param team the new team of the RowBoat object.
     * @return true if the team was set successfully, false otherwise.
     */
    
    public boolean setTeam(Team team) {
        if(team != null) {
            this.team = team;
            return true;
        }
        return false;
    }
    
    /**
     * Returns a string representation of the RowBoat object.
     * Overrides the describeSpecific method from the Racer class.
     * @return a string representation of the RowBoat object.
     */
    
    public String describeSpecific() {
    	return describeRacer() + ", Type: " + getType() + ", Team: " + getTeam();
    }
    
    /**

    Returns the name of the class, as a string.
    @return The name of the class.
    */
    
    public String className() {
    	return  CLASS_NAME;
    	
    }
    
}
