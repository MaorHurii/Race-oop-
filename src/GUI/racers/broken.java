package GUI.racers;

import game.racers.Racer;

/**
 * The `broken` class implements the `racerState` interface and represents a broken state of a racer.
 */
public class broken implements racerState {
    private racerState currentState;

    @Override
    public void state(Racer racer) {
        long finishTime = racer.getArena().getFinishTime();
        System.out.println("The " + racer.getName() + " is broken after: " + finishTime + " milliseconds");
    }
}
