package utilities;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Point {

	private static final int MAX_X = 1000000;
	private static final int MIN_X = 0;
	private static final int MAX_Y = 800;
	private static final int MIN_Y = 0;
	private double x;
	private double y;
	
	
	/**
	Constructs a point with the given x and y coordinates.
	@param x The x coordinate of the point.
	@param y The y coordinate of the point.
	*/
	
	 public Point(double x, double y) {
		 this.x = x;
	     this.y = y;
	 }
	 
	 /**
	 Constructs a point at the origin (0,0).
	 */
	 
	 public Point() {
		 this(0.0,0.0);
	 }
	 
	 /**
	 copy constructs a point with the same coordinates as the given point.
	 @param other The point to copy the coordinates from.
	 */
	 
	 public Point(Point other){
		 this(other.getX(), other.getY());
	 }
	 
	 /**
	 Returns a string representation of the point.
	 The string is of the format "(x,y)".
	 @return A string representation of the point.
	 */

	 
	 public String toString() {
		 return "(" + x + "," + y + ")";
	 }
	 
	 /**
	 Returns the x coordinate of the point.
	 @return The x coordinate of the point.
	 */
	 
	 public double getX() {
		 return x;
	 }
	 
	 /**
	 Sets the x coordinate of the point.
	 If the given value is outside the valid range of x coordinates, the value is not set.
	 @param x The new value for the x coordinate.
	 @return true if the x coordinate was set, false otherwise.
	 */
	 
	 public boolean setX(double x) {
	     if (x >= MIN_X && x <= MAX_X) {
	         this.x = x;
	         return true;
	     }
	     return false;
	 }
	 
	 /**
	 Returns the y coordinate of the point.
	 @return The y coordinate of the point.
	 */
	 
	 public double getY() {
	        return y;
	 }
	 
	 /**
	 Sets the y coordinate of the point.
	 If the given value is outside the valid range of y coordinates, the value is not set.
	 @param y The new value for the y coordinate.
	 @return true if the y coordinate was set, false otherwise.
	 */
	 
	 public boolean setY(double y) {
	     if (y >= MIN_Y && y <= MAX_Y) {
	         this.y = y;
	         return true;
	     }
	     return false;
	 }

}
