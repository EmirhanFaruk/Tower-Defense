package model;

import model.monster.Monster;

import java.util.Scanner;

public class Character {
    private final String name ;
    private int live ;
    private final int degats ;
    private int money ;

    public Character (String name , int lives , int degats ){
        this.name = name ;
        live = lives ;
        this.degats = degats ;
        this.money = 10 ;
    }

    public String getName() {
        return name;
    }
    public int getLive() {
        return live;
    }
    public int getMoney() {
        return money;
    }

    public int getDegats() {
        return degats;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void minusLive(int live) {this.live -= live;}
    public void setMoney(int money) {
         this.money = money;
    }
}
