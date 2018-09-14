package com.assignment.hitman.state;

import com.assignment.hitman.util.MessageConstants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/** @author ashutoshp */
public enum ViewPlayer {
    GO_BACK(1, MessageConstants.GO_BACK);

    int key;
    String value;

    private static HashMap<Integer, ViewPlayer> optionsMap;

    static {
        optionsMap = new HashMap<>();
        Arrays.stream(ViewPlayer.values()).forEach(option -> optionsMap.put(option.getKey(), option));
    }

    ViewPlayer(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<String> getValues() {
        return Arrays.stream(ViewPlayer.values())
                .map(option -> option.getValue())
                .collect(Collectors.toList());
    }

    public static ViewPlayer getOptionByKey(Integer key) {
        return optionsMap.get(key);
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
