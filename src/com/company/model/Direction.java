package com.company.model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    up(0), right(1), down(2), left(3);

    public final int value;
    public static final Map<Integer, Direction> BY_VALUE = new HashMap<>();

    static {
        for(Direction d : values()){
            BY_VALUE.put(d.value, d);
        }
    }

    Direction(int i){
        value = i;
    }

    public static Direction get(int d){
        return BY_VALUE.get(d);
    }

    public static int get(Direction d){
        for(Direction dir : values()){
            if(d == dir){ return dir.value; }
        }
        return -1;
    }

    public static String name(int d) {
        if(d == 0){ return "north"; }
        if(d == 1){ return "east"; }
        if(d == 2){ return "south"; }
        if(d == 3){ return "west"; }
        return "";
    }

    public static String name(Direction d){
        return name(get(d));
    }

    public static int adjustX(int x, int d){
        if(d == 1){ return x + 1; }
        if(d == 3){ return x - 1; }
        return x;
    }

    public static int adjustX(int x, Direction d){
        return adjustX(x, get(d));
    }

    public static int adjustY(int y, int d){
        if(d == 2){ return y + 1; }
        if(d == 0){ return y - 1; }
        return y;
    }

    public static int adjustY(int y, Direction d){
        return adjustY(y, get(d));
    }

}
