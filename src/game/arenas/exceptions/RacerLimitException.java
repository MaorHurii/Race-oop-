package game.arenas.exceptions;
/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */
public class RacerLimitException extends Exception{

	/**
	 * Constructs a new RacerLimitException with the specified maximum number of racers and serial number of the racer that was not added.
	 * 
	 * @param maxRacer the maximum number of racers allowed in the arena.
	 * @param serialNumber the serial number of the racer that was not added.
	 */
	
	public RacerLimitException(double maxRacer, int serialNumber) {
		
		super("Arena is full! (" + maxRacer + " active racers exist). racer #" + serialNumber + " was not added");
		
	}

}
