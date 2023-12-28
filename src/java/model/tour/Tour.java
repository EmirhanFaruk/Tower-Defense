package model.tour;

import gui.Coordinate;
import model.Character;
import model.monster.Monster;

import java.util.ArrayList;

public class Tour {
    private final String name ;
    private final int prix ;
    private final int degats ;
    private int level ; // il y a que 3 niveaux
    private Coordinate coordinates ;
    private int range ; // la portée de la tour
    private long lastAttackTime;  // Temps de la dernière attaque
    private final long cooldown;   // Temps de recharge en millisecondes
    private final static int[][] mulp= { { 1 } , { 2 } , { 3 } };
    public Tour (String name , int prix , int degats , int level , int x , int y , int range , long time ){
        this.name = name ;
        this.prix = prix ;
        this.degats = degats ;
        this.level = level ;
        this.coordinates = new Coordinate( x , y ) ;
        this.range =  range ;
        this.cooldown = time ;
        this.lastAttackTime = System.currentTimeMillis();
    }

    // une fonction qui l'améliore la tour au niveau supérieur
    public void upgradeTower(){
        if (Character.getMoney()>=this.prix*mulp[level+1][0]){ // regarde si le Character a assez d'argent pour pouvoir l'upgrade
            this.level++ ; // upgrade de niveau
            Character.setMoney(Character.getMoney()-this.prix*mulp[level][0]); // retire l'argent au Character
        }
    }

    // une fonction qui attaque le monstre
    public void target (ArrayList<Monster> monsters){
        Monster m = monsters.get(0) ;
        if (monsterInRange(m)) { // vérifie que le monstre est à la portée
            m.setLive(m.getLive() - this.degats); //fait perdre de la vie au monstre
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

    public boolean canBuyTower(){
        return Character.getMoney() >= this.prix ;
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

    public Coordinate getCoordinates() {
        return coordinates;
    }
}
