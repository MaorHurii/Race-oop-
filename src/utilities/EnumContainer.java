package utilities;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937
* 
 * Contains all Enums for the game.
 * 
 * To set a field type: EnumContainer.Vision vision; To set a value: this.vision
 * = EnumContainer.Vision.Sunny
 * 
 *
 *
 */
public class EnumContainer {
	
	//Aerial Arena
	
	public static enum Vision {
		CLOUDS, SUNNY, FOG
	}
	
	public static enum Weather {
		DRY,RAIN,SNOW
	}
	
	public static enum Height {
		LOW,MEDIUM,HIGH
	}
	
	public static enum Wind {
		LOW,MEDIUM,HIGH
	}
	
	//NavalArena
	
	public static enum Water {
		SALTED,SWEET
	}
	
	public static enum WaterSurface {
		FLAT,WAVY
	}
	
	public static enum Body {
		SEA,LAKE,RIVER,OCEAN
	}
	
	//Land Arena
	
	public static enum Coverage {
		SAND,GRASS,MUD
	}
	
	public static enum LandSurface {
		FLAT,MOUNTAIN
	}
	
	//Racer
	
	public static enum Color {
		RED,GREEN,BLUE,BLACK,YELLOW
	}
	
	//Car
	
	public static enum Engine {
		FOURSTROKE, VTYPE, STRAIGHT, BOXER, ROTARY
	}
	
	//Bicycle
	
	public static enum BicycleType {
		MOUNTAIN,HYBRID,CRUISER,ROAD
	}
	
	//Horse
	
	public static enum Breed {
		THOROUGHBRED,STANDARDBRED,MORGAN,FRIESIAN
	}
	
	//Boat
	
	public static enum BoatType {
		SKULLING,SWEEP
	}
	
	public static enum Team {
		SINGLE,DOUBLE,QUAD,EIGHT
	}
	

}
