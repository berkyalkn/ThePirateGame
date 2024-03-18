package dev.lpa.pirate;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Weapon {

    KNIFE(0, 30),
    AXE(0,30),
    MACHETE(1,40),
    PISTOL(1,40);

    private final int minLevel;
    private final int hitPoints;

    Weapon(int minLevel, int hitPoints) {
        this.minLevel = minLevel;
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public static Weapon getWeaponByChar(char firstInitial){

        for (Weapon w : values()){
            if (w.name().charAt(0) == firstInitial){
                return w;
            }
        }
        return values()[0];
    }

    public static List<Weapon> getWeaponsByLevel(int levelOfPlay){

        List<Weapon> weapons = new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(w -> (w.minLevel > levelOfPlay));
        return weapons;
    }
}
