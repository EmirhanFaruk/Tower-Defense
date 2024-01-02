package model.tour;

import gui.Coordinate;
import model.monster.Monster;

import java.util.ArrayList;

public class Tour {
    private final String name ;
    private final int prix ;
    private final int degats ;
    private int level ; // il y a que 3 niveaux
    private Coordinate coordinates ;
    private int range ; // la portée de la tour
    private final int type ; // le type de la tour
    private long lastAttackTime;  // Temps de la dernière attaque
    private final long cooldown;   // Temps de recharge en millisecondes
    private final static int[][] mulp= { { 1 } , { 2 } , { 3 } };

    public Tour ( String name , int prix , int degats , int level , int x , int y , int range , int type ,  long time ){
        this.name = name ;
        this.level = level ;
        this.prix = prix * mulp[getLevel()-1][0] ;
        this.degats = degats *  mulp[getLevel()-1][0] ;
        this.coordinates = new Coordinate( x , y ) ;
        this.range =  range ;
        this.type = type ;
        this.cooldown = time ;
        this.lastAttackTime = System.currentTimeMillis();
    }

    public Tour ( String name , int prix , int degats , int level , int range , int type , long time  ){
        this.name = name ;
        this.level = level ;
        this.prix = prix * mulp[getLevel()-1][0] ;
        this.degats = degats *  mulp[getLevel()-1][0] ;
        this.range =  range ;
        this.type = type ;
        this.cooldown = time ;
        this.lastAttackTime = System.currentTimeMillis();
    }

    // une fonction qui attaque le monstre
    public void target (ArrayList<Monster> monsters){
        if (!monsters.isEmpty()) { // Vérifie que la liste n'est pas vide
            Monster m = monsters.get(0); // attaque le premier monstre
            if (monsterInRange(m)) { // vérifie s'il est à sa portée si oui
                m.setLive(m.getLive() - this.degats); // perd de la vie
                if (m.isDead()) { // vérifie si le monstre est mort
                    m.winMoneyWhenMonsterDead(); // donne l'argent au character
                    monsters.remove(0); // enlève le monstre de la liste
                }
            }
        }
    }

    // une fonction qui attaque les monstres après le cooldown
    public void attaquer(ArrayList<Monster> monsters) {
        long currentTime = System.currentTimeMillis();
        // Vérifier si le cooldown est écoulé
        if (currentTime - lastAttackTime >= cooldown) {
            target(monsters); //attaque
            lastAttackTime = currentTime;  // Mettre à jour le temps de la dernière attaque
        }
    }

    // une fonction qui renvoie true si le montre est à la portée de la tour sinon non
    public boolean monsterInRange (Monster monster){
        return Math.abs(monster.getPos().i() - this.coordinates.i() ) <= this.range
                && Math.abs(monster.getPos().j() - this.coordinates.j() ) <= this.range ;
        // regarde la position de la tour et du montres est dans la portée
    }

    public String getName() {
        return name;
    }

    public int getPrix() {
        return this.prix;
    }

    public int getDegats() {
        return degats;
    }

    public int getLevel() {
        return level;
    }

    public int getType() {
        return type;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }
}
