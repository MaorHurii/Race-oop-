package GUI.racers;

import game.racers.Racer;

/**
 * The `completed` class implements the `racerState` interface and represents a completed state of a racer.
 */
public class completed implements racerState {
    private racerState currentState;

    @Override
    public void state(Racer racer) {
        System.out.println("The " + racer.getName() + " is completed, rank #" + racer.getSerialNumber());
    }
}
