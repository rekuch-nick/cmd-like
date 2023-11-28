package com.company.gameObject;

import com.company.model.Color;
import com.company.world.World;

public class Creature {

    public int x, y;
    public int hp;
    public String color, name;

    public Creature(int a, int b){
        x = a; y = b;
        hp = World.d(10) + 1;
        color = Color.GREEN;
        name = "beast";
    }



    public char getDigit(int i){
        if(hp < 10){
            if(i == 10){
                return (char)(hp + 48);
            }
        } else {
            if(i == 1){
                int n = hp;
                while(n >= 10){ n -= 10; }
                return (char)(n + 48);
            } else if (i == 10){
                int n = hp;
                while(n >= 100){ n -= 100; }
                return (char)((n / 10) + 48);
            } else {
                return hp < 100 ? ' ' : (char)((hp / 100) + 48);
            }
        }
        return ' ';
    }

}
