package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import utilities.EnumContainer.Height;
import utilities.EnumContainer.Vision;
import utilities.EnumContainer.Weather;
import utilities.EnumContainer.Wind;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */
public class AerialArena extends Arena {
  
  // Fields
  public static final double DEFAULT_FRICTION = 0.4;
  public static final int DEFAULT_MAX_RACERS = 6;
  public static final int DEFAULT_LENGTH = 1500;
  
  private Vision vision;
  private Weather weather;
  private Height height;
  private Wind wind;
  
  /**
  Creates a new aerial arena with default values.
  Sets the vision to sunny, the weather to dry, the height to high and the wind to high.
  */
  
  public AerialArena() {
	    super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
	    this.vision = Vision.SUNNY;
	    this.weather = Weather.DRY;
	    this.height = Height.HIGH;
	    this.wind = Wind.HIGH;
  }
  
  
  /**
  Creates a new aerial arena with the specified length and maximum number of racers.
  Sets the vision to sunny, the weather to dry, the height to high and the wind to high.
  @param length The length of the aerial arena.
  @param maxRacers The maximum number of racers in the aerial arena.
  */
  
  public AerialArena(double length, int maxRacers) {
      super(length, maxRacers, DEFAULT_FRICTION);
	  this.vision = Vision.SUNNY;
	  this.weather = Weather.DRY;
	  this.height = Height.HIGH;
	  this.wind = Wind.HIGH;
  }

    @Override
    public Arena arenaName(){
        return new AerialArena(this.getLength(), this.int_MaxRacers());
    }



    /**
  Adds a new racer to the aerial arena.
  The racer must be an instance of AerialRacer.
  @param newRacer The new racer to add.
  @throws RacerLimitException If the maximum number of racers has been reached.
  @throws RacerTypeException If the new racer is not an instance of AerialRacer.
  */

  
  @Override
  public void addRacer(Racer newRacer) throws RacerLimitException,RacerTypeException
	{
		if (newRacer instanceof AerialRacer)  
		{
			super.addRacer(newRacer);
		}
		else
			throw new RacerTypeException(newRacer.className(),"Aerial");
		
	}

    /*
   
   Getters and Setters, all the setters boolean.
   
  */
  
  public boolean setWind(Wind wind) {
      this.wind = wind;
      return true;
  }
  
  public Wind getWind() {
      return wind;
  }
  
  public boolean setVision(Vision vision) {
      this.vision = vision;
      return true;
  }
  
  public Vision getVision() {
      return vision;
  }
  
  public boolean setHeight(Height height) {
      this.height = height;
      return true;
  }
  
  public Height getHeight() {
      return height;
  }
  
  public boolean setWeather(Weather weather) {
      this.weather = weather;
      return true;
  }
  
  public Weather getWeather() {
      return weather;
  }

}