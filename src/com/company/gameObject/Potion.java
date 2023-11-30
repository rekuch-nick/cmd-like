package com.company.gameObject;

import com.company.model.Player;
import com.company.world.World;

import java.util.ArrayList;
import java.util.List;

public class Potion {

    public static int maxKindsCommon = 2;
    public static boolean[] known = {false, false};

    public static String getName(int i){
        return switch (i){
            case 0 -> "Healing";
            case 1 -> "Fire";
            default -> null;
        };
    }

    public static void use(int kind, World ww, Player pc, int x, int y){
        if(kind == 0){
            if(pc.hp < pc.hpMax){
                int healFor = Math.min( (int)(pc.hpMax * .15), pc.hpMax - pc.hp);
                pc.hp += healFor;
                pc.messages.add("You heal for " + healFor + " points.");
            } else {
                pc.messages.add("You have no wounds to heal.");
            }
        }

        if(kind == 1){
            List<Creature> creatures = ww.getCreaturesAround(x, y);
            if(!creatures.isEmpty()){
                for(Creature c : creatures){
                    int dam = 5; //
                    c.hp -= dam;
                    if(c.hp > 0){
                        pc.messages.add("The " + c.name + " is burned for " + dam + " points.");
                    } else {
                        ww.creatures.remove(c);
                        pc.messages.add("The " + c.name + " is burned up.");
                    }
                }
            }
        }



    }

}
