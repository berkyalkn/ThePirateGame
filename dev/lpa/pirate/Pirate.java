package dev.lpa.pirate;

import java.util.*;

public final class Pirate extends Combatant{

    private final List<Island> islandsVisited = new LinkedList<Island>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;

    public Pirate(String name) {
        super(name, Map.of("level", 0, "islandIndex", 0));
        visitIsland();
    }

    boolean useWeapon(){

       int count = opponents.size();
       if (count > 0){
           int opponentIndex = count - 1;
           if (count > 1){
               opponentIndex = new Random().nextInt(count + 1);
           }
           Combatant combatant = opponents.get(opponentIndex);
           if (super.useWeapon(combatant)){
               opponents.remove(opponentIndex);
           }else {
               return combatant.useWeapon(this);
           }
       }
       return false;
    }

    boolean visitIsland(){

        List<Island> levelIslands = PirateGame.getIslands(value("level"));
        if (levelIslands == null){
            return true;
        }
        Island island = levelIslands.get(value("islandIndex"));
        if (island != null){
            islandsVisited.add(island);
            loot = island.loot();
            opponents = island.opponents();
            features = island.features();
            return false;
        }
        return true;
    }

    boolean hasExperiences(){
        return (features != null && features.size() > 0);
    }

    boolean hasOpponents(){
        return (opponents != null && opponents.size() > 0);
    }

    boolean findLoot(){

        if (loot.size() > 0){
            Loot item = loot.remove(0);
            System.out.println("Found " + item + "!");
            adjustValue("score", item.getWorth());
            System.out.println(name() + "'s score is now " + value("score"));
        }
        if (loot.size() == 0){
            visitNextIsland();
        }
        return false;
    }

    boolean experienceFeature(){

        if (features.size() > 0){
            Feature item = features.remove(0);
            System.out.println("Ran into " + item + "!");
            adjustHealth(item.getHealthPoints());
            System.out.println(name() + "'s health is now " + value("health"));
        }
        return (value("health") <= 0);
    }

    public String information() {

        var current = ((LinkedList<Island>) islandsVisited).getLast();
        String[] simpleNames = new String[islandsVisited.size()];
        Arrays.setAll(simpleNames, i -> islandsVisited.get(i).name());
        return "----> " + current +
                "\n" + super.information() +
                "\n\tTownsVisited= " + Arrays.toString(simpleNames);
    }

    private boolean visitNextIsland(){

        int islandIndex = value("islandIndex");
        List<Island> islands = PirateGame.getIslands(value("level"));
        if (islands == null){
            return true;
        }
        if (islandIndex >= (islands.size() - 1)){
            System.out.println("Leveling Up!!! Bonus : 500 points!");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValues("islandIndex", 0);
        }else {
            System.out.println("Sailing to next island! Bonus : 50 points!");
            adjustValue("score", 50);
            adjustValue("islandIndex", 1);
        }
        return visitIsland();
    }
}
