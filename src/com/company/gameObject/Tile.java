package com.company.gameObject;

import com.company.model.Color;
import com.company.model.Player;

public class Tile {

    public char image;
    public String color;
    public Zone zone;
    public int x, y;
    public Boolean isPassable, isItem, isTrap, isBreakable;

    public Tile(int a, int b, char i, String c, Boolean p, Boolean itm, Boolean trp, Boolean brk){
        x = a; y = b;
        image = i; color = c;
        isPassable = p; isItem = itm; isTrap = trp; isBreakable = brk;
    }

    public static Tile floor(int x, int y){
        return new Tile(x, y, ' ', Color.GREY, true, false, false, false);
    }

    public static Tile grass(int x, int y){
        return new Tile(x, y, '"', Color.GREEN, true, false, false, false);
    }

    public static Tile coin(int x, int y){
        return new Tile(x, y, '.', Color.YELLOW, true, true, false, false);
    }
    public static Tile coin2(int x, int y){
        return new Tile(x, y, ':', Color.YELLOW, true, true, false, false);
    }
    public static Tile coin5(int x, int y){
        return new Tile(x, y, '$', Color.YELLOW, true, true, false, false);
    }


    public static Tile block(int x, int y){
        return new Tile(x, y, '#', Color.GREY, false, false, false, true);
    }

    public static Tile wallV(int x, int y){
        return new Tile(x, y, '|', Color.GREY, false, false, false, false);
    }

    public static Tile wallH(int x, int y){
        return new Tile(x, y, '=', Color.GREY, false, false, false, false);
    }






    public static int collect(Player pc, char img, String col){
        int coinsCollected = 0;
        if(img == '.' && col == Color.YELLOW){ pc.coins += 1; coinsCollected += 1; }
        if(img == ':' && col == Color.YELLOW){ pc.coins += 2; coinsCollected += 1; }
        if(img == '$' && col == Color.YELLOW){ pc.coins += 5; coinsCollected += 1; }
        return coinsCollected;
    }


}
