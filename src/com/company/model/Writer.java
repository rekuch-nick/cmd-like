package com.company.model;

import com.company.gameObject.Item;
import com.company.model.Color;
import com.company.gameObject.Creature;
import com.company.gameObject.Tile;
import com.company.gameObject.Zone;
import com.company.model.Player;
import com.company.world.World;

import java.util.ArrayList;

public class Writer {

    public static void draw(World ww, Player pc){

        System.out.println();
        System.out.println();
        System.out.println();

        for(String s : pc.messages){
            System.out.println(s);
        }
        pc.messages = new ArrayList<>();

        for(int y=0; y<World.H; y++){
            for(int b = 0; b< Zone.H; b++){


                for(int x=0; x<World.W; x++){


                    for(int a=0; a<Zone.W; a++){

                        Tile t = ww.zones[x][y].tiles[a][b];
                        char image = t.image;
                        String color = t.color;

                        Item i = ww.zones[x][y].item;
                        if(i != null && a == 2 && b == 1){
                            color = ww.zones[x][y].item.color;
                            image = ww.zones[x][y].item.image;
                        }

                        Creature c = ww.getCreature(x, y);
                        if(c != null && b == 1){
                            char img = ' ';

                            if(a == 1){ img = (char)c.getDigit(100); }
                            if(a == 2){ img = (char)c.getDigit(10); }
                            if(a == 3){ img = (char)c.getDigit(1); }

                            if(img != ' '){ color = c.color; image = img; }
                        }

                        if(!ww.zones[x][y].visible){
                            color = Color.GREY;
                            image = '.';
                        }

                        if(pc.x == x && pc.y == y && a == 2 && b == 1){
                            color = pc.color;
                            image = pc.image;
                        }


                        System.out.print(color);
                        System.out.print(image);
                    }
                }


                System.out.println();

            }
        }

        System.out.print(Color.WHITE);
        System.out.print("HP: ");
        System.out.print(Color.WHITE);
        System.out.print(pc.hp);
        System.out.print(Color.WHITE);
        System.out.print(" / " + pc.hpMax + "   $ ");
        System.out.print(Color.YELLOW);
        System.out.print(pc.coins);
        System.out.print(Color.WHITE);
        System.out.print("    Keys: ");
        System.out.print(Color.YELLOW);
        System.out.print(pc.keys);
        System.out.print(Color.WHITE);
        System.out.print("    DEEP: " + ww.level);
        System.out.println();



        System.out.print(Color.WHITE);
    }
}
