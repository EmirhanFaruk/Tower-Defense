package model.tour;

import gui.Coordinate;
import model.Character;
import model.monster.Monster;

import static java.lang.Thread.sleep;
public class Tour {
    private final String name ;
    private final int prix ;
    private final int degats ;
    private int level ; // il y a que 3 niveau
    private Coordinate coordinates ;
    private int range ;
    private int cooldown ;
    private final static int[][] mulp= { { 1 } , { 2 } , { 3 } };
    public Tour (String name , int prix , int degats , int level , int x , int y , int range , int time ){
        this.name = name ;
        this.prix = prix ;
        this.degats = degats ;
        this.level = level ;
        this.coordinates = new Coordinate( x , y ) ;
        this.range =  range ;
        this.cooldown = time ;
    }

    // une fonction qui l'améliore la tour au niveau supérieur
    public void upgradeTower(){
        if (Character.getMoney()>=this.prix*mulp[level+1][0]){ // regarde si le Character a assez d'argent pour pouvoir l'upgrade
            this.level++ ; // upgrade de niveau
            Character.setMoney(Character.getMoney()-this.prix*mulp[level][0]); // retire l'argent au Character
        }
    }
    public void target (){
        if ( monsterInRange()){
            Monster.setLive(Monster.getLive() - this.degats );
        }
    }

    // la fonction ne fonctionne pas je changerai plus tard ( il faut faire un lambda )
    public void cooldown() throws InterruptedException {
        sleep(cooldown * 1000L) ;
    }

    public boolean monsterInRange (){
        return true;
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
}
