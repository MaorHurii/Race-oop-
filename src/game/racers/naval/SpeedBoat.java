package game.racers.naval;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Team;
import utilities.EnumContainer.Color;
import game.racers.Racer;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */


public class SpeedBoat extends Racer implements NavalRacer {


    private static final String CLASS_NAME = "SpeedBoat";
    private static final double DEFAULT_MAX_SPEED = 170;
    private static final double DEFAULT_ACCELERATION = 5;
    private static final Color DEFAULT_COLOR = Color.RED;
    private BoatType type;
    private Team team;

    private int count = 1;


    /**
     * Constructs a default {@code SpeedBoat} object with a generated name, default maximum speed, 
     * default acceleration, and default color.
     * The boat's type is set to {@code BoatType.SKULLING} and its team to {@code Team.DOUBLE}.
     */
    
    public SpeedBoat() {
        super(generateDefaultName() , DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_COLOR);
        this.type = BoatType.SKULLING;
        this.team = Team.DOUBLE;
    }
    
    /**
     * Constructs a {@code SpeedBoat} object with a given name, maximum speed, acceleration, and color.
     * The boat's type is set to {@code BoatType.SKULLING} and its team to {@code Team.DOUBLE}.
     *
     * @param name the name of the speed boat
     * @param maxSpeed the maximum speed of the speed boat
     * @param acceleration the acceleration of the speed boat
     * @param color the color of the speed boat
     */
    
    public SpeedBoat(String name, double maxSpeed, double acceleration, Color color) {
        super(name, maxSpeed, acceleration, color);
        this.type = BoatType.SKULLING;
        this.team = Team.DOUBLE;
    }
    @Override
    public Racer clone(){
        return new SpeedBoat(this.getName() + count++, this.getMaxSpeed(), this.getAcceleration(), this.getColor());
    }
    private static String generateDefaultName() {
        return CLASS_NAME + " #" + Racer.getLastSerialNumber();
    }
    
    
    /**
     * Returns the type of the boat as a {@code BoatType} object.
     * @return the boat's type
     */
    
    public BoatType getType() {
        return type;
    }
    
    /**
     * Sets the type of the boat to the given {@code BoatType} object.
     *
     * @param type the boat's type to set
     * @return true if the type is not null and was set successfully, false otherwise
     */

    public boolean setType(BoatType type) {
        if (type != null) {
            this.type = type;
            return true;
        }
        return false;
    }

    /**
     * Returns the team of the boat as a {@code Team} object.
     * @return the boat's team
     */
    
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team of the boat to the given {@code Team} object.
     * @param team the boat's team to set
     * @return true if the team is not null and was set successfully, false otherwise
     */
    
    public boolean setTeam(Team team) {
        if (team != null) {
            this.team = team;
            return true;
        }
        return false;
    }
    
    /**

    Returns a string representation of the specific characteristics of the SpeedBoat instance.
    Includes the Racer description and the boat type and team.
    @return a string representing the specific characteristics of the SpeedBoat instance.
    */
    
    public String describeSpecific() {
    	return describeRacer() + ", Type: " + getType() + ", Team: " + getTeam();
    }
    
    /**

    Returns the name of the class as a string.
    @return a string representing the name of the class.
    */
    
    public String className() {
    	return  CLASS_NAME;
    	
    }
    
}
