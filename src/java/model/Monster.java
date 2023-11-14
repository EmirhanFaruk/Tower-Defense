package model;

public class Monster {
    private final String name ;
    private double live ;
    private final double speed ;
    private final int money ;
    private double x, y;


    public Monster (String name , double live , double speed , int money , double x, double y){
        this.x = x;
        this.y = y;
        this.name = name ;
        this.live = live ;
        this.speed = speed ;
        this.money = money ;
    }

    public double getLive() {
        return live;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMoney() {
        return money;
    }
    public void setLive(int live) {
        this.live = live;
    }
}
