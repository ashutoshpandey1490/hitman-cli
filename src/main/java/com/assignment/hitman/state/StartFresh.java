package com.assignment.hitman.state;

import com.assignment.hitman.util.MessageConstants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ashutoshp
 */
public enum StartFresh {
    START(1, MessageConstants.START),
    EXIT(2, MessageConstants.EXIT);

    int key;
    String value;

    private static HashMap<Integer, StartFresh> optionsMap;

    static {
        optionsMap = new HashMap<>();
        Arrays.stream(StartFresh.values())
                .forEach(option -> optionsMap.put(option.getKey(), option));
    }

    StartFresh(int key, String value){
        this.key = key;
        this.value = value;
    }

    public static List<String> getValues() {
        return Arrays.stream(StartFresh.values()).map(option -> option.getValue()).collect(Collectors.toList());
    }

    public static StartFresh getOptionByKey(Integer key) {
        return optionsMap.get(key);
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
