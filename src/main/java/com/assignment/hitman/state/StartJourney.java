package com.assignment.hitman.state;

import com.assignment.hitman.util.MessageConstants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/** @author ashutoshp */
public enum StartJourney {
    START_GAME(1, MessageConstants.START_GAME),
    NEW_WEAPON(2, MessageConstants.NEW_WEAPON),
    VIEW_PLAYER(3, MessageConstants.VIEW_PLAYER),
    EXIT(4, MessageConstants.EXIT);

    int key;
    String value;

    private static HashMap<Integer, StartJourney> optionsMap;

    static {
        optionsMap = new HashMap<>();
        Arrays.stream(StartJourney.values()).forEach(option -> optionsMap.put(option.getKey(), option));
    }

    StartJourney(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<String> getValues() {
        return Arrays.stream(StartJourney.values())
                .map(option -> option.getValue())
                .collect(Collectors.toList());
    }

    public static StartJourney getOptionByKey(Integer key) {
        return optionsMap.get(key);
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
