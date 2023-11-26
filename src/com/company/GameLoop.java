package com.company;

import com.company.gameObject.Color;
import com.company.gameObject.GameObject;
import com.company.model.Direction;
import com.company.model.Player;
import com.company.model.Verb;
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
        }


    }
}
