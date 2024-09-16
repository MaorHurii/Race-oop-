package factory;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.land.Car;
import utilities.EnumContainer;

import java.util.ArrayList;

/**
 * The `MyBuilder` class is an implementation of the `RaceDefaultBuilder` interface
 * and is responsible for building a specific configuration of a race.
 */
public class MyBuilder implements RaceDefaultBuilder {

    private Race race;

    private int N = 8;
    private String name = "Car";
    private ArrayList<Racer> racers = new ArrayList<>(N);
    private EnumContainer.Color[] colors = EnumContainer.Color.values();

    /**
     * Creates a new `MyBuilder` instance and initializes the `race`.
     */
    public MyBuilder() {
        race = new Race();
    }

    @Override
    public void buildArena() {
        arena_Factory arena_factory = new arena_Factory();
        Arena arena = arena_factory.createArena("Land Arena", 1000, N);
        race.setArena(arena);
    }

    @Override
    public void buildRacer() {
        Racer defaultRacer = new Car(name, 180, 30, EnumContainer.Color.RED, 4);

        for (int i = 0; i < N; i++) {
            racers.add(defaultRacer.clone());
            racers.get(i).upgrade(EnumContainer.Color.RED);
        }

        race.setRacer(racers);
    }

    /**
     * Returns the built race.
     *
     * @return The built race.
     */
    public Race getRace() {
        return race;
    }
}
