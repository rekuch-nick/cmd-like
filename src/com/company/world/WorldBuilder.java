package com.company.world;

import com.company.gameObject.Color;
import com.company.gameObject.Creature;
import com.company.gameObject.Tile;
import com.company.gameObject.Zone;
import com.company.model.Direction;

public class WorldBuilder {

    static int px = 0;
    static int py = 0;

    public static World create(int level, int x, int y){
        px = x; py = y;
        World ww = new World(level);
        excludeColumns(ww, Math.max(7 - (level / 2), 0));
        connectAllZones(ww);
        for(int i=0; i<40 - level * 2; i++) {
            connectRandomZone(ww);
        }
        sealEdges(ww);

        for(int i=0; i<20; i++) {
            addMonster(ww);
        }

        addExit(ww);

        addTreasure(ww);


        return ww;
    }

    private static void excludeColumns(World ww, int w){
        for(int i=0; i<w; i++){
            int x2 = World.W - (i + 1);
            for(int b=0; b<World.H; b++){
                ww.zones[i][b].excludeFromGeneration = true;
                brickRoom(ww, i, b);
                ww.zones[x2][b].excludeFromGeneration = true;
                brickRoom(ww, x2, b);
            }
        }
    }


    private static void connectAllZones(World ww) {
        Boolean[][] visited = new Boolean[World.W][World.H];
        int unvisited = 0;
        for(int x=0; x<World.W; x++){ for(int y=0; y<World.H; y++){
            if(ww.zones[x][y].excludeFromGeneration){ continue; }
            visited[x][y] = false;
            unvisited ++;
        }}

        visited[px][py] = true; unvisited --;

        while (unvisited > 0){
            //choose a random unvisited zone
            int x = -1; int y = -1;
            Zone zone = null;
            while(zone == null) {
                x = World.d(World.W); y = World.d(World.H);
                if (!ww.zones[x][y].excludeFromGeneration && !visited[x][y]) {
                    zone = ww.zones[x][y];
                }
            }

            //choose a direction & check for visited zone
            int d = World.d(4);
            int tx = Direction.adjustX(zone.x, d);
            int ty = Direction.adjustY(zone.y, d);
            if(World.inBounds(tx, ty)){
                if(!ww.zones[tx][ty].excludeFromGeneration && visited[tx][ty]){
                    ww.zones[tx][ty].removeWallTowards(zone);
                    zone.removeWallTowards(ww.zones[tx][ty]);
                    visited[x][y] = true;
                    unvisited --;
                }
            }
        }
    }

    private static void connectRandomZone(World ww){
        int tries = 0;
        while(tries < 1000){
            tries ++;
            int x = World.d(World.W);
            int y = World.d(World.H);
            if(ww.zones[x][y].excludeFromGeneration){ continue; }
            int d = World.d(4);
            int tx = Direction.adjustX(x, d);
            int ty = Direction.adjustY(y, d);
            if(World.inBounds(tx, ty)){
                if(ww.zones[tx][ty].excludeFromGeneration){ continue; }
                if(!World.areConnected(ww.zones[x][y], ww.zones[tx][ty]) ){
                    ww.zones[x][y].removeWallTowards(ww.zones[tx][ty]);
                    ww.zones[tx][ty].removeWallTowards(ww.zones[x][y]);
                    return;
                }
            }
        }
    }

    private static void sealEdges(World ww){
        /*for(int x=0; x<World.W; x++){
            for(int a=0; a<Zone.W; a++){
                ww.zones[x][0].tiles[a][0] = Tile.wallH(a, 0);
                ww.zones[x][World.H - 1].tiles[a][Zone.H - 1] = Tile.wallH(a, Zone.H - 1);
            }
        }*/

        for(int y=0; y<World.H; y++){
            for(int b=0; b<Zone.H; b++){
                ww.zones[0][y].tiles[0][b] = Tile.wallV(0, b);
                ww.zones[World.W - 1][y].tiles[Zone.W - 1][b] = Tile.wallV(Zone.W - 1, b);
            }
        }
    }

    private static void addExit(World ww){
        int minDistance = World.W / 2;
        while(true){
            int x = World.d(World.W);
            int y = World.d(World.H);
            if(ww.zones[x][y].excludeFromGeneration){ continue; }
            int d = World.manhattanDistance(x, y, px, py);
            if( d >= minDistance ){
                Creature c = ww.getCreature(x, y);
                if(c != null){ ww.creatures.remove(c); }
                ww.zones[x][y].tiles[1][1] = new Tile(1, 1, '[', Color.PURPLE, true, false, false, false);
                ww.zones[x][y].tiles[2][1] = new Tile(2, 1, '_', Color.PURPLE, true, false, false, false);
                ww.zones[x][y].tiles[3][1] = new Tile(3, 1, ']', Color.PURPLE, true, false, false, false);
                ww.zones[x][y].isExit = true;
                return;
            }
            if(World.d(100) == 0){ minDistance --; }
        }
    }


    private static void addMonster(World ww){
        int tries = 0;
        while(tries < 1000){
            tries ++;
            int x = World.d(World.W);
            int y = World.d(World.H);
            if(!ww.zones[x][y].tiles[2][1].isPassable){ continue; }
            if( (x != px || y != py) && ww.getCreature(x, y) == null){
                ww.creatures.add(new Creature(x, y));
                return;
            }
        }
    }


    private static void addTreasure(World ww){
        for(int x=0; x<World.W; x++){ for(int y=0; y<World.H; y++){
            if(!ww.zones[x][y].tiles[2][1].isPassable){ continue; }
            if(x == px && y == py){ continue; }
            if(World.d(100) < 30){
                for(int a=1; a<4; a++){ for(int b=0; b<3; b++){
                    if(World.d(100) < 80 && ww.zones[x][y].tiles[a][b].image == ' '){
                        ww.zones[x][y].tiles[a][b] = Tile.coin(a, b);
                        if(World.d(100) < ww.level + 30 ){
                            ww.zones[x][y].tiles[a][b] = Tile.coin2(a, b);
                        }
                    }
                }}
            }
        }}
    }

    private static void brickRoom(World ww, int x, int y){
        for(int a=0; a<Zone.W; a++){ for(int b=0; b<Zone.H; b++) {
            ww.zones[x][y].tiles[a][b] = Tile.block(a, b);
        }}
    }
}
