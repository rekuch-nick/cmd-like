package com.company.gameObject;

import com.company.model.Color;
import com.company.model.Player;
import com.company.world.World;

public class Item {

    public String name, unknownName;
    public int potionId;
    public char image;
    public String color;
    public Boolean consumeOnUse, isWeapon, isArmor, isRing;

    public Item (String n, char i, String c){
        name = n;
        image = i;
        color = c;
        potionId = -1;
        unknownName = name;
        consumeOnUse = false;
        isWeapon = false;
        isArmor = false;
        isRing = false;
    }

    public String getName() {
        if(potionId != -1 && !Potion.known[potionId]){
            return unknownName;
        }
        return name;
    }

    public void use(World ww, Player pc, int x, int y){
        if(potionId != -1){
            Potion.known[potionId] = true;
            Potion.use(potionId, ww, pc, x, y);
        }

        if(consumeOnUse){
            pc.messages.add("The " + getName() + " is consumed.");
            pc.items.remove(this);
        }
    }

    public static Item potion(){
        Item i = new Item("Potion of ", '!', Color.BLUE);
        i.consumeOnUse = true;
        i.potionId = World.d(Potion.maxKindsCommon);
        i.unknownName = "Unidentified Potion";
        i.name += Potion.getName(i.potionId);
        return i;
    }
}
