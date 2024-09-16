package factory;

import game.arenas.Arena;
import game.arenas.land.LandArena;
import game.racers.Racer;

import java.util.ArrayList;

/**
 * The `Race` class implements the `RacePlan` interface and represents a race configuration.
 */
public class Race implements RacePlan {

    private Arena arena;
    private int N = 8;
    private ArrayList<Racer> racers = new ArrayList<>();

    @Override
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void setRacer(ArrayList<Racer> dRacer) {
        this.racers.addAll(dRacer);
    }

    /**
     * Returns the name of the arena.
     *
     * @return The name of the arena, or `null` if the arena is not an instance of `LandArena`.
     */
    public String getNameArena() {
        if (arena instanceof LandArena)
            return "Land Arena";
        else
            return null;
    }

    /**
     * Returns the list of racers in the race.
     *
     * @return The list of racers.
     */
    public ArrayList<Racer> getRacers() {
        return racers;
    }

    /**
     * Returns the arena of the race.
     *
     * @return The arena.
     */
    public Arena getArena() {
        return arena;
    }
}
