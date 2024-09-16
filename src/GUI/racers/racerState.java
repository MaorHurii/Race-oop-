package GUI.racers;

import game.racers.Racer;

/**
 * The `racerState` interface defines the contract for handling the state of a racer.
 */
public interface racerState {

    /**
     * Sets the state of the racer.
     *
     * @param racer The racer to set the state for.
     */
    void state(Racer racer);
}
