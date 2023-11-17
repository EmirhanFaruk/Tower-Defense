package model.tour;

import gui.Coordinate;
import static java.lang.Thread.sleep;
public class Tour {
    private final String name ;
    private final int prix ;
    private final int degats ;
    private int level ;
    private Coordinate coordinates ;
    private int range ;
    private int cooldown ;
    public Tour (String name , int prix , int degats , int level , int x , int y , int range , int time ){
        this.name = name ;
        this.prix = prix ;
        this.degats = degats ;
        this.level = level ;
        this.coordinates = new Coordinate( x , y ) ;
        this.range =  range ;
        this.cooldown = time ;
    }

    public String getName() {
        return name;
    }

    public int getPrix() {
        return prix;
    }

    public int getDegats() {
        return degats;
    }

    public int getLevel() {
        return level;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void upgrade (){
    }

    public void target (){
    }

    public void cooldown() throws InterruptedException {
        sleep(cooldown * 1000L) ;
    }

    public boolean monsterInRange (){
        return true;
    }

    public boolean targetInRange(){
        return true ;
    }

}
