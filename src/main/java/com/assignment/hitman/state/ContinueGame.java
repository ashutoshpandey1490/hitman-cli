package com.assignment.hitman.state;

import com.assignment.hitman.util.MessageConstants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/** @author ashutoshp */
public enum ContinueGame {
    CONTINUE(1, MessageConstants.CONTINUE),
    EXIT(2, MessageConstants.EXIT);

    int key;
    String value;

    private static HashMap<Integer, ContinueGame> optionsMap;

    static {
        optionsMap = new HashMap<>();
        Arrays.stream(ContinueGame.values()).forEach(option -> optionsMap.put(option.getKey(), option));
    }

    ContinueGame(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<String> getValues() {
        return Arrays.stream(ContinueGame.values())
                .map(option -> option.getValue())
                .collect(Collectors.toList());
    }

    public static ContinueGame getOptionByKey(Integer key) {
        return optionsMap.get(key);
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
