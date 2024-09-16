package factory;

import game.arenas.Arena;
import game.racers.Racer;

import java.util.ArrayList;

/**
 * The `RacePlan` interface defines the contract for building a race configuration.
 */
public interface RacePlan {

    /**
     * Sets the arena for the race.
     *
     * @param arena The arena to set.
     */
    void setArena(Arena arena);

    /**
     * Sets the racers for the race.
     *
     * @param racer The list of racers to set.
     */
    void setRacer(ArrayList<Racer> racer);

}
