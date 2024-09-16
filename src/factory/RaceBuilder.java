package factory;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class RaceBuilder {
    private static RaceBuilder instance;
    private ClassLoader classLoader;
    private Class<?> classObject;
    private Constructor<?> constructor;

    /**
     * Constructs a new RaceBuilder object.
     */
    
    private RaceBuilder() {
        classLoader = ClassLoader.getSystemClassLoader();
    }
    
    /**
     * Returns the singleton instance of the RaceBuilder class.
     * @return the singleton instance of the RaceBuilder class.
     */

    public static RaceBuilder getInstance() {
        if (instance == null) {
            instance = new RaceBuilder();
        }
        return instance;
    }

    /**
	  * build Arena
	  * @param arenaType (required) brand arena.
	  * @param length (required) brand arena.
	  * @return <tt>true</tt> if the animal point x is a legal else return false.
	  */
    
    public Arena buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException,
            NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        classObject = classLoader.loadClass(arenaType);
        constructor = classObject.getConstructor(double.class, int.class);
        return (Arena) constructor.newInstance(length, maxRacers);
    }

    /**
	  * build Racer
	  * @param racerType (required)
	  * @param name (required)
	  * @param maxSpeed (required)
	  * @param color (required)
	  * @throws ClassNotFoundException if aDiameter not in given range.
	  * @throws NoSuchMethodException if No Such Method Exception.
	  * @throws SecurityException if Security problem.
	  * @throws InstantiationException if Instantiation.
	  * @throws IllegalAccessException if Illegal Access
	  * @throws IllegalArgumentException if Illegal Argument 
	  * @throws InvocationTargetException if Invocation Target
	  * @return <tt>true</tt> Racer
	  */
    
    public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, Color color)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, Color.class);
        return (Racer) constructor.newInstance(name, maxSpeed, acceleration, color);
    }

    /**
	  * build Wheeled Racer
	  * @param racerType (required)
	  * @param name (required)
	  * @param maxSpeed (required)
	  * @param color (required) 
	  * @throws ClassNotFoundException if aDiameter not in given range.
	  * @throws NoSuchMethodException if No Such Method Exception.
	  * @throws SecurityException if Security problem.
	  * @throws InstantiationException if Instantiation.
	  * @throws IllegalAccessException if Illegal Access
	  * @throws IllegalArgumentException if Illegal Argument 
	  * @throws InvocationTargetException if Invocation Target
	  * @return <tt>true</tt> Wheele dRacer
	  */
    
    public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, Color color,
            int numOfWheels) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, Color.class, int.class);
        return (Racer) constructor.newInstance(name, maxSpeed, acceleration, color, numOfWheels);
    }
}
