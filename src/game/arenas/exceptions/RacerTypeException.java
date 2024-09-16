package game.arenas.exceptions;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */
public class RacerTypeException extends Exception{
	
	/**
	 * Constructs a RacerTypeException with a message indicating the invalid racer type.
	 * @param className The class name of the invalid racer type.
	 * @param arena The type of arena for which the racer is being added.
	 */

	public RacerTypeException(String className, String arena) {
		super("Invalid Racer of type '" + className +  "' for " + arena + " arena.");
	}

}
