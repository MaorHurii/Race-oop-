package game.arenas.naval;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.EnumContainer.Body;
import utilities.EnumContainer.Water;
import utilities.EnumContainer.WaterSurface;


/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class NavalArena extends Arena {
    
    // Fields
    public static final double DEFAULT_FRICTION = 0.7;
    public static final int DEFAULT_MAX_RACERS = 5;
    public static final int DEFAULT_LENGTH = 1000;
    private Water water;
    private WaterSurface surface;
    private Body body;
  
    /**
     * Constructs a new naval arena with default length, maximum number of racers, friction coefficient,
     * water type, water surface, and body.
     */
    
    public NavalArena() {
	    super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
	    this.water = Water.SWEET;
	    this.surface = WaterSurface.FLAT;
	    this.body = Body.LAKE;
    }
    
    /**
    * Constructs a new naval arena with the given length and maximum number of racers, and default friction coefficient,
    * water type, water surface, and body.
    * 
    * @param length the length of the arena.
    * @param maxRacers the maximum number of racers allowed in the arena.
    */
    
    public NavalArena(double length, int maxRacers) {
        super(length, maxRacers, DEFAULT_FRICTION);
	    this.water = Water.SWEET;
	    this.surface = WaterSurface.FLAT;
	    this.body = Body.LAKE;
    }

    @Override
    public Arena arenaName(){
            return new NavalArena(this.getLength(), this.int_MaxRacers());
    }



    /**
     * Adds a new racer to the arena if it is a naval racer.
     * Otherwise, throws a {@code RacerTypeException}.
     * 
     * @param newRacer the racer to be added to the arena.
     * @throws RacerLimitException if the maximum number of racers in the arena has been reached.
     * @throws RacerTypeException if the racer being added is not a naval racer.
     */



    @Override
    public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException
  	{
  		if (newRacer instanceof NavalRacer)  
  		{
  			super.addRacer(newRacer);
  		}
  		else
  			throw new RacerTypeException(newRacer.className(),"Naval");
  		
  	}
    
    //Getters and Setters, all the setters boolean.
    
    public boolean setWater(Water water) {
        this.water = water;
        return true;
    }
    
    public Water getWater() {
        return water;
    }
    
    public boolean setSurface(WaterSurface surface) {
        this.surface = surface;
        return true;
    }
    
    public WaterSurface getSurface() {
        return surface;
    }
    
    public boolean setBody(Body body) {
        this.body = body;
        return true;
    }
    
    public Body getBody() {
        return body;
    }


}
  