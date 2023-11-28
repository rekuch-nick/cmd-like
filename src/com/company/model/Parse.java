package com.company.model;

public class Parse {

    public static Verb verb(String s){
        return switch(s){
            case "go", "walk", "w", "a", "s", "d" -> Verb.go;
            case "rest", "wait", "sleep", "sit" -> Verb.rest;
            case "enter", "open" -> Verb.enter;
            case "look", "inspect", "check", "i" -> Verb.look;
            case "use", "drink", "wear", "equip", "wield" -> Verb.use;
            default -> null;
        };
    }

    public static GameObject gameObject(String s){
        return switch(s){
            case "in", "door", "entrance", "portal", "hole", "inside", "into" -> GameObject.door;
            case "player", "self", "me", "character", "hero" -> GameObject.self;
            case "bag", "stuff", "items", "equipment", "treasure", "i" -> GameObject.bag;
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

    public static int carriedItem(String s){
        try{
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

}
