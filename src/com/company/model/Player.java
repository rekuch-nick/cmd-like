package com.company.model;

import com.company.gameObject.Color;
import com.company.gameObject.Creature;
import com.company.gameObject.Tile;
import com.company.gameObject.Zone;
import com.company.world.World;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public int x, y;
    public char image;
    public String color;
    public List<String> messages;
    public int hp, hpMax, coins, keys;

    public Player(int a, int b){
        x = a; y = b;
        image = '@';
        color = Color.WHITE;
        messages = new ArrayList<>();
        hp = 100; hpMax = 100;
        coins = 0; keys = 0;
    }

    public void enterTile(World ww){

        //vision update
        ww.zones[x][y].visible = true;
        for(int d=0; d<4; d++){
            int tx = Direction.adjustX(x, d);
            int ty = Direction.adjustY(y, d);
            if(World.inBounds(tx, ty)){
                if(World.areConnected(ww.zones[x][y], ww.zones[tx][ty])) {
                    ww.zones[tx][ty].visible = true;

                    int ttx = Direction.adjustX(tx, d);
                    int tty = Direction.adjustY(ty, d);
                    if(World.inBounds(ttx, tty)) {
                        if (World.areConnected(ww.zones[tx][ty], ww.zones[ttx][tty])) {
                            ww.zones[ttx][tty].visible = true;

                        }
                    }

                }
            }
        }

        //combat
        Creature c = ww.getCreature(x, y);
        if(c != null){
            int dmg = c.hp;
            hp -= dmg;
            messages.add("You take " + dmg + " damage slaying the " + c.name);
            ww.creatures.remove(c);
        }


        //items
        int coinsCollected = 0;
        for(int a=0; a<5; a++){ for(int b=0; b<3; b++){
            if(ww.zones[x][y].tiles[a][b].isItem){
                coinsCollected += Tile.collect(this, ww.zones[x][y].tiles[a][b].image, ww.zones[x][y].tiles[a][b].color);
                ww.zones[x][y].tiles[a][b] = Tile.floor(a, b);
            }
        }}
        if(coinsCollected > 0){
            messages.add("You pick up " + coinsCollected + " coins.");
        }


        if(ww.zones[x][y].isExit){
            messages.add("There is an EXIT door here.");
        }


    }
}
