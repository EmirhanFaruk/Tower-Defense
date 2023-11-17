package model;

public class Monster {
    protected final String name ;
    protected double live ;
    protected final double speed ;
    protected final int money ;
    protected final int niveau;

    protected double x, y;


    public Monster (String name , double live , double speed , int money , int niveau , double x, double y){
        this.x = x;
        this.y = y;
        this.name = name ;
        this.live = live ;
        this.speed = speed ;
        this.niveau = niveau;
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

    public boolean isDead (){
        return this.live <=0 ;
    }


}
