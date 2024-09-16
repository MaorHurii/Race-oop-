package game.arenas.land;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.LandRacer;
import utilities.EnumContainer.Coverage;
import utilities.EnumContainer.LandSurface;


/**
* @author Maor Huri 207871435
 * @author Matan Atias 318856937 */
public class LandArena extends Arena {
	
	private final static double DEFAULT_FRICTION = 0.5;
    private final static int DEFAULT_MAX_RACERS = 8;
    private final static int DEFAULT_LENGTH = 800;
    
    private Coverage coverage;
    private LandSurface surface;
    
    /**
    Constructs a LandArena object with default length, maximum racers, and friction.
    Sets the coverage type to DEFAULT_COVERAGE and surface type to DEFAULT_SURFACE.
    */
    
    public LandArena() {
	    super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
	    this.coverage = Coverage.GRASS;
	    this.surface = LandSurface.FLAT;
    }
    
    /**
    Constructs a LandArena object with specified length and maximum racers.
    Sets the coverage type to DEFAULT_COVERAGE and surface type to DEFAULT_SURFACE.
    @param length the length of the arena
    @param maxRacers the maximum number of racers allowed in the arena
    */
    
    public LandArena(double length, int maxRacers) {
        super(length, maxRacers, DEFAULT_FRICTION);
	    this.coverage = Coverage.GRASS;
	    this.surface = LandSurface.FLAT;
    }

    @Override
    public Arena arenaName(){
        return new LandArena(this.getLength(), this.int_MaxRacers());
    }


    /**
    Adds a new racer to the arena, if it is a LandRacer.
    Otherwise, throws a RacerTypeException.
    @param newRacer the racer to add to the arena
    @throws RacerLimitException if the arena is full
    @throws RacerTypeException if the racer is not a LandRacer
    */
    
    @Override
    public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException
  	{
  		if (newRacer instanceof LandRacer)  
  		{
  			super.addRacer(newRacer);
  		}
  		else
  			throw new RacerTypeException(newRacer.className(),"Land");
  		
  	}
    
    /**
    Sets the coverage type of the land arena.
    @param coverage the new coverage type to set
    @return true if the coverage type is set, false otherwise
    */
    
    public boolean setCoverage(Coverage coverage) {
        if (coverage != null) {
            this.coverage = coverage;
            return true;
        }
        return false;
    }
    
    /**
    Sets the surface type of the land arena.
    @param surface the new surface type to set
    @return true if the surface type is set, false otherwise
    */
    
    public boolean setSurface(LandSurface surface) {
        if (surface != null) {
            this.surface = surface;
            return true;
        }
        return false;
    }
    
    /**
    Gets the coverage type of the land arena.
    @return the coverage type of the land arena
    */
    
    public Coverage getCoverage() {
        return this.coverage;
    }
    
    /**
    Gets the surface type of the land arena.
    @return the surface type of the land arena
    */
    
    public LandSurface getSurface() {
        return this.surface;
    }



}