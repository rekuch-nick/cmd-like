package com.company.gameObject;

import com.company.model.Direction;
import com.company.model.Verb;

public class Parse {

    public static Verb verb(String s){
        return switch(s){
            case "go", "walk", "w", "a", "s", "d" -> Verb.go;
            case "rest", "wait", "sleep", "sit" -> Verb.rest;
            case "enter", "open" -> Verb.enter;
            default -> null;
        };
    }

    public static GameObject gameObject(String s){
        return switch(s){
            case "in", "door", "entrance", "portal", "hole", "inside", "into" -> GameObject.door;
            default -> null;
        };
    }


    public static Direction direction(String s){
        return switch (s){
            case "up", "north", "w" -> Direction.up;
            case "right", "east", "d" -> Direction.right;
            case "down", "south", "s" -> Direction.down;
            case "left", "west", "a" -> Direction.left;
            default -> null;
        };
    }


}
