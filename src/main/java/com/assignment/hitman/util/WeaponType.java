package com.assignment.hitman.util;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ashutoshp
 */
public enum WeaponType {
    KNIFE("knife", 1),
    PISTOL("pistol", 2),
    REVOLVER("revolver", 3),
    RIFLE("rifle", 4),
    SPORTING_RIFLE("sporting_rifle", 5),
    SHOTGUN("shotgun", 6),
    MACHINE_GUN("machine_gun", 7);

    private String name;
    private int value;

    private static HashMap<Integer, WeaponType> weaponTypeHashMap;

    static {
        weaponTypeHashMap = new HashMap<>();
        Arrays.stream(WeaponType.values()).forEach(weaponType ->
                weaponTypeHashMap.put(weaponType.getValue(), weaponType));
    }

    WeaponType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static WeaponType getTypeFromValue(int value) {
        return weaponTypeHashMap.get(value);
    }
}
