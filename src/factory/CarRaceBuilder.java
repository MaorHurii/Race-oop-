package factory;

public class CarRaceBuilder {

    private RaceDefaultBuilder builder;

    public CarRaceBuilder(RaceDefaultBuilder builder){
        this.builder = builder;
    }

    public Race getRace(){
        return builder.getRace();
    }

    public void constructRace(){
        builder.buildArena();
        builder.buildRacer();
    }
}
