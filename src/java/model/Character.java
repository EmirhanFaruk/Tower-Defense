package model;

public class Character {
    private final String name ;
    private int live ;
    private int degats ;
    private int money ;

    public Character (String name , int live , int degats ){
        this.name = name ;
        this.live = live ;
        this.degats = degats ;
        this.money = 0 ;
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
    public void setLive(int live) {
        this.live = live;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
