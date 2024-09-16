package GUI.racers;

import game.racers.Racer;

/**
 * The `disabled` class implements the `racerState` interface and represents a disabled state of a racer.
 */
public class disabled implements racerState {
    private racerState currentState;

    @Override
    public void state(Racer racer) {
        System.out.println("The " + racer.getName() + " is disabled");
    }
}
