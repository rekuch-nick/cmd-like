package com.company.model;

public enum Direction {
    up, right, down, left;

    public static Direction get(int d){
        if(d == 0){ return Direction.up; }
        if(d == 1){ return Direction.right; }
        if(d == 2){ return Direction.down; }
        if(d == 3){ return Direction.left; }
        return null;
    }

    public static int get(Direction d){
        if(d == Direction.up){ return 0; }
        if(d == Direction.right){ return 1; }
        if(d == Direction.down){ return 2; }
        if(d == Direction.left){ return 3; }
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
