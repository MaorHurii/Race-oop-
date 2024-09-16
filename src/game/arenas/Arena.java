package game.arenas;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.MyObservable;
import utilities.MyObserver;
import utilities.Point;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @author Maor Huri 207871435
* @author Matan Atias 318856937  */

public abstract class Arena implements MyObserver {

	private ArrayList<Racer> activeRacers;
	private ArrayList<Racer> completedRacers;

	private ArrayList<Racer> brokens = new ArrayList<>();
	private ArrayList<Racer> disableds = new ArrayList<>();
	private final double FRICTION;
	private final double MAX_RACERS;
	private final static int MIN_Y_GAP = 10;
	private double length;

	private long startTime;


	/**
	 * Constructor for the Arena class.
	 *
	 * @param length    The length of the arena.
	 * @param maxRacers The maximum number of racers allowed in the arena.
	 * @param friction  The friction coefficient of the arena.
	 */

	public Arena(double length, int maxRacers, double friction) {
		this.length = length;
		this.MAX_RACERS = maxRacers;
		this.FRICTION = friction;
		this.activeRacers = new ArrayList<Racer>();
		this.completedRacers = new ArrayList<Racer>();
	}

	/**
	 * Adds a racer to the arena.
	 *
	 * @param newRacer The racer to be added to the arena.
	 * @throws RacerTypeException  When attempting to add a racer of an unsupported type.
	 * @throws RacerLimitException When the maximum number of racers allowed in the arena has been reached.
	 */

	public void addRacer(Racer newRacer) throws RacerTypeException, RacerLimitException {

		if (this.activeRacers.size() >= this.MAX_RACERS) {
			throw new RacerLimitException(getMaxRacers(), newRacer.getSerialNumber());
		}
		this.activeRacers.add(newRacer);

	}

	public ArrayList<Racer> getBrokens() {
		return brokens;
	}

	public Boolean setBrokens(Racer racer) {
		if (this.brokens != null) {
			this.brokens.add(racer);
			return true;
		} else return false;
	}

	public ArrayList<Racer> getDisableds() {
		return disableds;
	}

	public Boolean setDisabled(Racer racer) {
		if (this.disableds != null) {
			this.disableds.add(racer);
			return true;
		} else return false;
	}

	public abstract Arena arenaName();

	public int int_MaxRacers() {
		return (int) MAX_RACERS;
	}


	/**
	 * Starts the race by initializing the race, creating an executor service, and executing the active racers.
	 *
	 * @throws InterruptedException If the thread is interrupted while waiting for the executor service to terminate.
	 */


	public void startRace() throws InterruptedException {
		initRace();
		ExecutorService e = Executors.newFixedThreadPool(this.activeRacers.size());
		for (Racer racer : activeRacers) {
			e.execute(racer);
		}
		e.shutdown();

	}

	/**
	 * Initializes the race, setting the initial y-axis location for each racer.
	 */

	public void initRace() {
		setStartTime();
		double currentY = 0;
		Random random = new Random();
		int brokenSpot = random.nextInt(50, (int) length);
		for (Racer racer : this.activeRacers) {
			Point p = new Point(0, this.MIN_Y_GAP * this.FRICTION);
			racer.setCurrentLocation(p);
			racer.setBrokenSpot(brokenSpot);
			currentY++;
		}
	}

	public void setStartTime() {
		Date start_Time = new Date();
		startTime = start_Time.getTime();
	}

	public long getFinishTime() {
		Date finishTime = new Date();
		return finishTime.getTime() - startTime;
	}

	/**
	 * Checks if there are any active racers in the arena.
	 *
	 * @return true if there are active racers in the arena, false otherwise.
	 */

	public boolean hasActiveRacers() {

		return !activeRacers.isEmpty();
	}

	public boolean allFinished() {

		Boolean flag = false;
		if (activeRacers.size() > 0) {
			for (int i = 0; i < activeRacers.size(); i++) {
				if (activeRacers.get(i) != null) {
					if (activeRacers.get(i).getCurrentLocation().getX() != length)
						flag = true;
				}
			}



			if (!flag)
				return true;

			else return false;
		}
		return false;
	}

	public void checkFinished(){

		for (int i = activeRacers.size() - 1; i >= 0; i--) {
			Racer racer = activeRacers.get(i);
			if (racer.getCurrentLocation().getX() == length) {
				completedRacers.add(racer);
				activeRacers.remove(i);
			}
		}

	}

	/**
	 * Plays a single turn of the race for all active racers in the arena.
	 */

	public void playTurn() {
		for (Racer racer : activeRacers) {
			racer.move(FRICTION);

		}

		activeRacers.removeAll(completedRacers);
	}

	/**
	 * Adds a racer to the completed racers list and removes it from the active racers list when it crosses the finish line.
	 *
	 * @param racer The racer that crossed the finish line.
	 */

	public void crossFinishLine(Racer racer) {
		completedRacers.add(racer);
		activeRacers.remove(racer);
	}

	/**
	 * Displays the results of the race.
	 */

	public void showResults() {
		synchronized (activeRacers) {
			for (int i = 0; i < completedRacers.size(); i++) {
				Racer racer = completedRacers.get(i);
				System.out.println("#" + (i) + " -> " + racer.describeSpecific());
			}
		}
	}

	//Getters and Setters

	public ArrayList<Racer> getActiveRacers() {
		return activeRacers;
	}

	public boolean setActiveRacers(ArrayList<Racer> activeRacers) {
		if (activeRacers.size() <= MAX_RACERS) {
			this.activeRacers = activeRacers;
			return true;
		}
		return false;
	}

	public ArrayList<Racer> getCompletedRacers() {
		return completedRacers;
	}

	public boolean setCompletedRacers(ArrayList<Racer> completedRacers) {
		this.completedRacers = completedRacers;
		return true;
	}

	public double getFriction() {
		return FRICTION;
	}

	public double getMaxRacers() {
		return MAX_RACERS;
	}


	public static int getMinYGAP() {
		return MIN_Y_GAP;
	}

	public double getLength() {
		return length;
	}

	public boolean setLength(double length) {
		if (length >= 0) {
			this.length = length;
			return true;
		}
		return false;
	}

	/**
	 * This method is called when the observed object notifies the observer of a change.
	 * It updates the state of the racer by removing it from the active racers and adding it to the completed racers.
	 *
	 * @param o   The observed object (racer).
	 * @param arg The argument passed by the observed object (not used in this implementation).
	 */


	@Override
	public void update(MyObservable o, Object arg) {
		this.completedRacers.add((Racer) o);
		this.activeRacers.remove((Racer) o);
	}



}

