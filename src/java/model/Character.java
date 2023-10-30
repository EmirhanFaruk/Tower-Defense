package model;

public class Character {
    private int live ;
    private int degats ;
    private int money ;

    public Character ( int live , int degats ){
        this.live = live ;
        this.degats = degats ;
        this.money = 0 ;
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
