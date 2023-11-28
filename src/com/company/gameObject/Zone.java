package com.company.gameObject;

public class Zone {

    public static int W = 5;
    public static int H = 3;
    public int x, y;
    public Boolean visible;
    public Tile[][] tiles;
    public Boolean isExit, excludeFromGeneration;
    public Item item;


    public Zone(int a, int b){
        x = a; y = b;
        visible = false; // should be false
        tiles = new Tile[W][H];
        for(int x=0; x<W; x++){ for(int y=0; y<H; y++){
            tiles[x][y] = Tile.block(x, y);
        }}
        for(int x=1; x<W-1; x++){ for(int y=1; y<H-1; y++){
            tiles[x][y] = Tile.floor(x, y);
        }}

        excludeFromGeneration = false;
        isExit = false;
    }

    public void removeWallTowards(Zone zone){


        if(zone.x < x){
            tiles[0][1] = Tile.floor(0, 1);
            tiles[1][1] = Tile.floor(1, 1);
        }
        if(zone.x > x){
            tiles[3][1] = Tile.floor(3, 1);
            tiles[4][1] = Tile.floor(4, 1);
        }
        if(zone.y < y){
            tiles[1][0] = Tile.floor(1, 0);
            tiles[2][0] = Tile.floor(2, 0);
            tiles[3][0] = Tile.floor(3, 0);
        }
        if(zone.y > y){
            tiles[1][2] = Tile.floor(1, 2);
            tiles[2][2] = Tile.floor(2, 2);
            tiles[3][2] = Tile.floor(3, 2);
        }
    }



}

