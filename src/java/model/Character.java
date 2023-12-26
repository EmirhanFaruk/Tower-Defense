package model;

import model.monster.Monster;
public class Character {
    private final String name ;
    private static int live ;
    private int degats ;
    private int money ;

    public Character (String name , int lives , int degats ){
        this.name = name ;
        live = lives ;
        this.degats = degats ;
        this.money = 0 ;
    }

    public void winMoneyWhenMonsterDead(Monster monster){
        if ( monster.isDead()) setMoney(getMoney()+monster.getMoney());
    }

    public void whenMonsterEnterBase ( Monster monster){
        if ( monster.entrerDansBase())  setLive(getLive() - 1) ;
    }

    public String getName() {
        return name;
    }

    public static int getLive() {
        return live;
    }
    public int getMoney() {
        return money;
    }
    public void setLive ( int live) {
        this.live = live;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
