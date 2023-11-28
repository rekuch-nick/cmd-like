package com.company;

import com.company.gameObject.Item;
import com.company.gameObject.Potion;
import com.company.model.*;
import com.company.world.World;
import com.company.world.WorldBuilder;

import java.util.Scanner;

public class GameLoop {

    World ww;
    Player pc;
    Scanner input;

    public void run() {

        ww = WorldBuilder.create(1, World.W/2, World.H/2);
        pc = new Player(World.W/2, World.H/2);
        pc.enterTile(ww);
        input = new Scanner(System.in);

        while(true){

            if(pc.hp < 1){ pc.messages.add("You have died!"); pc.color = Color.RED; }

            Writer.draw(ww, pc);
            if(pc.hp < 1){ System.exit(0); }
            Input in = Input.get(input.nextLine());

            //move
            if(in.verb == Verb.go && in.direction != null){
                int tx = Direction.adjustX(pc.x, in.direction);
                int ty = Direction.adjustY(pc.y, in.direction);

                if(World.inBounds(tx, ty) && World.areConnected(ww.zones[pc.x][pc.y], ww.zones[tx][ty])){
                    pc.messages.add("You move " + Direction.name(in.direction) + ".");
                    pc.x = tx; pc.y = ty;
                    pc.enterTile(ww);
                } else {
                    pc.messages.add("Can't move " + Direction.name(in.direction) + ".");
                }
            }
            if(ww.zones[pc.x][pc.y].isExit && in.verb == Verb.go && in.gameObject == GameObject.door){
                pc.messages.add("You walk through the exit and down the stairs into a new area...");
                ww = WorldBuilder.create(ww.level + 1, pc.x, pc.y);
                pc.enterTile(ww);
            }

            // look
            if(in.verb == Verb.look){
                if(in.gameObject == GameObject.bag){
                    if(pc.items.size() == 0){
                        pc.messages.add("You aren't carrying any items.");
                    } else {
                        pc.messages.add("You are carrying:");
                        int num = 0;
                        for (Item i : pc.items){
                            pc.messages.add(num + "] " + i.getName());
                            num ++;
                        }
                    }
                }
            }


            // use
            if(in.verb == Verb.use){
                if(in.item != -1){
                    try {
                        Item i = pc.items.get(in.item);
                        i.use(ww, pc, pc.x, pc.y);
                    } catch (Exception e){
                        pc.messages.add("No item in slot " + in.item);
                    }
                }
            }

        }


    }
}
