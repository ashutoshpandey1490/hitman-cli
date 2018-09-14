package com.assignment.hitman.util;

import java.util.Arrays;
import java.util.HashMap;

/** @author ashutoshp */
public enum Level {
    LEVEL1(1, 150, 2),
    LEVEL2(2, 1000, 3),
    LEVEL3(2, 1000, 6);

    Integer value;
    Integer money;
    Integer defaultWeaponId;

    private static HashMap<Integer, Level> levelMap;

    static {
        levelMap = new HashMap<>();
        Arrays.stream(Level.values())
                .forEach(level -> levelMap.put(level.getValue(), level));
    }

    Level(Integer value, Integer money, Integer defaultWeaponId) {
        this.value = value;
        this.money = money;
        this.defaultWeaponId = defaultWeaponId;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getDefaultWeaponId() {
        return defaultWeaponId;
    }

    public static Level getLevelFromValue(int value) {
        return levelMap.get(value);
    }
}
