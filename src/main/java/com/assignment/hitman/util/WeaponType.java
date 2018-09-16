package com.assignment.hitman.util;

import java.util.Arrays;
import java.util.HashMap;

/** @author ashutoshp */
public enum WeaponType {
    KNIFE("Knife", 1),
    PISTOL("Pistol", 2),
    REVOLVER("Revolver", 3),
    RIFLE("Rifle", 4),
    SPORTING_RIFLE("Sporting Rifle", 5),
    SHOTGUN("Shotgun", 6),
    MACHINE_GUN("Machine Gun", 7);

    private String name;
    private int value;

    private static HashMap<Integer, WeaponType> weaponTypeMap;

    static {
        weaponTypeMap = new HashMap<>();
        Arrays.stream(WeaponType.values())
                .forEach(weaponType -> weaponTypeMap.put(weaponType.getValue(), weaponType));
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
        return weaponTypeMap.get(value);
    }
}
