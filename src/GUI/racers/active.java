package GUI.racers;

import game.racers.Racer;

/**
 * The `active` class implements the `racerState` interface and represents an active state of a racer.
 */
public class active implements racerState {

    @Override
    public void state(Racer racer) {
        System.out.println("The " + racer.getName() + " is active");
    }
}
