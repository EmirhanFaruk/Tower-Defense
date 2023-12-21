package model;

import model.monster.Monster;
public class Character {
    private final String name ;
    private double live ;
    private int degats ;
    private int money ;

    public Character (String name , double live , int degats ){
        this.name = name ;
        this.live = live ;
        this.degats = degats ;
        this.money = 0 ;
    }

    public void winMoneyWhenMonsterDead(Monster monster){
        if ( monster.isDead()) setMoney(getMoney()+monster.getMoney());
    }

    public void whenMonsterEnterBase ( Monster monster){
        if ( monster.entrerDansBase())  setLive(getLive() - monster.getLive()) ;
    }

    public String getName() {
        return name;
    }

    public double getLive() {
        return live;
    }
    public int getMoney() {
        return money;
    }
    public void setLive ( double live) {
        this.live = live;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
