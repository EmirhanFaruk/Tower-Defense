package model;

public class Monster {
    private int live ;
    private int degats ;
    private int speed ;
    private int money ;

    public Monster (int live , int degats , int speed , int money ){
        this.live = live ;
        this.degats = degats ;
        this.speed = speed ;
        this.money = money ;
    }

    public int getLive() {
        return live;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMoney() {
        return money;
    }
    public void setLive(int live) {
        this.live = live;
    }
}
