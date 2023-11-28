package com.company.world;

import com.company.gameObject.Creature;
import com.company.gameObject.Zone;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static int W = 30;
    public static int H = 6;
    public Zone[][] zones;
    public List<Creature> creatures;
    public int level;

    public World(int l) {
        level = l;
        zones = new Zone[W][H];
        for(int x=0; x<W; x++){ for(int y=0; y<H; y++){
            zones[x][y] = new Zone(x, y);
        }}
        creatures = new ArrayList<>();
    }

    public static Boolean inBounds(int x, int y){
        return x >= 0 && y >= 0 && x < W && y < H;
    }

    public static Boolean areConnected(Zone z1, Zone z2){

        if(z1.x < z2.x){
            if(!z1.tiles[3][1].isPassable){ return false; }
            if(!z1.tiles[4][1].isPassable){ return false; }
        }
        if(z1.x > z2.x){
            if(!z1.tiles[0][1].isPassable){ return false; }
            if(!z1.tiles[1][1].isPassable){ return false; }
        }
        if(z1.y < z2.y){
            if(!z1.tiles[2][2].isPassable){ return false; }
        }
        if(z1.y > z2.y){
            if(!z1.tiles[2][0].isPassable){ return false; }
        }


        return true;
    }

    public static int d(int i) {
        return (int)(Math.random() * i);
    }

    public Creature getCreature(int x, int y){
        for(Creature c : creatures){
            if(c.x == x && c.y == y){
                return c;
            }
        }
        return null;
    }

    public List<Creature> getCreaturesAround(int x, int y){
        List<Creature> cs = new ArrayList<>();
        for(Creature c : creatures){
            int dis = World.manhattanDistance(x, y, c.x, c.y);
            if(dis <= 1){
                if(World.areConnected(zones[x][y], zones[c.x][c.y])){
                    cs.add(c);
                }
            }
        }
        return cs;
    }

    public static int manhattanDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
