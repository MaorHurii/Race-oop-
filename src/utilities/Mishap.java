package utilities;
import java.text.DecimalFormat;


/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class Mishap {
    private boolean fixable;
    private double reductionFactor;
    private int turnsToFix;
    
    
    /**
    Constructs a new Mishap object with the given parameters.
    @param fixable a boolean indicating whether the mishap can be fixed or not.
    @param turnsToFix an integer representing the number of turns it takes to fix the mishap.
    @param reductionFactor a double representing the reduction factor in speed due to the mishap.
    */
    
    public Mishap(boolean fixable, int turnsToFix, double reductionFactor) {
        this.fixable = fixable;
        this.turnsToFix = turnsToFix;
        this.reductionFactor = reductionFactor;
    }
    
    /**
    Decreases the number of turns left to fix the mishap by 1, if the mishap is fixable and there are turns left to fix it.
    */
    
    public void nextTurn() {
        if (fixable && turnsToFix > 0) {
            turnsToFix--;
        }
    }
    
    // Getters
    public boolean getFixable() {
        return fixable;
    }

    public double getReductionFactor() {
        return reductionFactor;
    }

    public int getTurnsToFix() {
        return turnsToFix;
    }

    // boolean Setters
    public boolean setFixable(boolean fixable) {
        this.fixable = fixable;
        return true;
    }

    public boolean setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
        return true;
    }

    public boolean setTurnsToFix(int turnsToFix) {
        this.turnsToFix = turnsToFix;
        return true;
    }

    /**
    Returns a string representation of the Mishap object.
    @return a string representation of the Mishap object.
    */
    
    public String toString() {
		return "(" + getFixable() + "," + getTurnsToFix() + "," + new DecimalFormat("0.00").format(getReductionFactor())+ ")";
	}
}