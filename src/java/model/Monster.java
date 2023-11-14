package model;

public class Monster {
    private final String name ;
    private int live ;
    private final int degats ;
    private final int speed ;
    private final int money ;
    private int x, y;


    public Monster (String name , int live , int degats , int speed , int money , int x, int y){
        this.x = x;
        this.y = y;
        this.name = name ;
        this.live = live ;
        this.degats = degats ;
        this.speed = speed ;
        this.money = money ;
    }

    public int getLive() {
        return live;
    }

    public int getDegats() { return degats; }

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
