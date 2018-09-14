package com.assignment.hitman.state;

import com.assignment.hitman.util.MessageConstants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/** @author ashutoshp */
public enum ResumeGame {
    START(1, MessageConstants.START),
    RESUME(2, MessageConstants.RESUME),
    EXIT(3, MessageConstants.EXIT);

    int key;
    String value;

    private static HashMap<Integer, ResumeGame> optionsMap;

    static {
        optionsMap = new HashMap<>();
        Arrays.stream(ResumeGame.values()).forEach(option -> optionsMap.put(option.getKey(), option));
    }

    ResumeGame(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<String> getValues() {
        return Arrays.stream(ResumeGame.values())
                .map(option -> option.getValue())
                .collect(Collectors.toList());
    }

    public static ResumeGame getOptionByKey(Integer key) {
        return optionsMap.get(key);
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
