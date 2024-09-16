package factory;
import game.arenas.Arena;

/**
 * The `arena_Factory` class is responsible for creating different types of arenas.
 */
public class arena_Factory {

    private Arena arena;
    private static RaceBuilder builder = RaceBuilder.getInstance();

    /**
     * Creates a new `arena_Factory` instance.
     */
    public arena_Factory() {}

    /**
     * Creates an arena based on the specified parameters.
     *
     * @param arenaType   The type of the arena.
     * @param arenaLength The length of the arena.
     * @param maxRacers   The maximum number of racers in the arena.
     * @return The created arena.
     */
    public Arena createArena(String arenaType, int arenaLength, int maxRacers) {
        try {
            if (arenaType == null || arenaType.isEmpty())
                return null;
            switch (arenaType) {
                case "Aerial Arena":
                    arena = builder.buildArena("game.arenas.air.AerialArena", arenaLength, maxRacers);
                    break;
                case "Land Arena":
                    arena = builder.buildArena("game.arenas.land.LandArena", arenaLength, maxRacers);
                    break;
                case "Naval Arena":
                    arena = builder.buildArena("game.arenas.naval.NavalArena", arenaLength, maxRacers);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown arena " + arenaType);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return arena;
    }
}
