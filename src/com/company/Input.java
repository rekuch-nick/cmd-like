package com.company;

import com.company.gameObject.GameObject;
import com.company.gameObject.Parse;
import com.company.model.Direction;
import com.company.model.Verb;

import java.util.Locale;

public class Input {

    public String[] words;
    public Verb verb;
    public Direction direction;
    public GameObject gameObject;

    public Input(String[] s, Verb v, Direction d, GameObject o){
        words = s;
        verb = v;
        direction = d;
        gameObject = o;
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

        return new Input(ss, v, dir, o);
    }
}
