package dev.lpa.pirate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public record Island(String name, String city, int level,
                     List<Loot> loot,
                     List<Feature> features,
                     List<Combatant> opponents) {

    public Island {

        loot = randomReduced(new ArrayList<>(EnumSet.allOf(Loot.class)),
               level + 2);
        features = randomReduced(new ArrayList<>(EnumSet.allOf(Feature.class)),
                level + 3);
        opponents = new ArrayList<>();
        if (level == 0){
            opponents.add(new Islander("Akin", Weapon.KNIFE));
        }else {
            opponents.add(new Islander("Arda", Weapon.MACHETE));
            opponents.add(new Soldier("Hamza", Weapon.PISTOL));
        }
    }

    public Island(String name, String city, int level){
        this(name, city, level, null, null, null);
    }

    private <T> List<T> randomReduced(List<T> list, int size){

        Collections.shuffle(list);
        return list.subList(0, size);
    }

    @Override
    public String toString() {
        return name + ", " + city;
    }

    public String information(){

        return "City: " + this + "\n\tloot= " + loot +
                "\n\tfeatures= " + features +
                "\n\topponents= " + opponents;
    }

    public List<Loot> loot(){
        return (loot == null) ? null : new ArrayList<>(loot);
    }

    public List<Combatant> opponents(){
        return (opponents == null) ? null : new ArrayList<>(opponents);
    }

    public List<Feature> features(){
        return (features == null) ? null : new ArrayList<>(features);
    }
}
