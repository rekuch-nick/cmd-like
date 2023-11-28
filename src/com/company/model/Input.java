package com.company.model;

public class Input {

    public String[] words;
    public Verb verb;
    public Direction direction;
    public GameObject gameObject;
    public int item;

    public Input(String[] s, Verb v, Direction d, GameObject o, int i){
        words = s;
        verb = v;
        direction = d;
        gameObject = o;
        item = i;
    }

    public static Input get(String s){
        s = s.replaceAll("^A-Za-z0-9 ", "");
        s = s.toLowerCase();
        String[] ss = s.split(" ");

        Verb v = Parse.verb(ss[0]);

        Direction dir = null;
        for(String str : ss){
            Direction d = Parse.direction(str);
            if(d != null){ dir = d; }
        }

        GameObject o = null;
        for(String str : ss){
            GameObject obj = Parse.gameObject(str);
            if(obj != null){ o = obj; }
            if(o == GameObject.door){ v = Verb.go; }
        }

        int item = -1;
        for(String str : ss){
            int itm = Parse.carriedItem(str);
            if(itm != -1){ item = itm; }
        }

        return new Input(ss, v, dir, o, item);
    }
}
