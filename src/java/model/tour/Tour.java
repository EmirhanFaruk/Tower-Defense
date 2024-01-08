package model.tour;

import gui.Coordinate;
import model.Character;
import model.monster.Monster;

import java.util.ArrayList;

public abstract class Tour {
    private final String name ;
    private final int prix ;
    protected final int degats ;
    private int level ; // il y a que 3 niveaux
    private Coordinate coordinates ;
    private int range ; // la portée de la tour
    private final int type ; // le type de la tour
    protected long lastAttackTime;  // Temps de la dernière attaque
    protected final long cooldown;   // Temps de recharge en millisecondes
    protected Monster cible;
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
    public void target (ArrayList<Monster> monsters)
    {
        // If no target choose target
        if(cible == null)
        {
            // Get monsters in range
            ArrayList<Monster> targets_in_range = new ArrayList<>();
            for (Monster monster : monsters)
            {
                if (monsterInRange(monster))
                {
                    targets_in_range.add(monster);
                }
            }
            // If any monsters in range, get "first"
            if(!targets_in_range.isEmpty())
            {
                cible = targets_in_range.get(0);
            }
        }
        else
        {
            // If somehow target is not alive then make cible null
            if(cible.getLive() <= 0 || !monsterInRange(cible))
            {
                cible = null;
            }
        }
    }


    /**
     * Une fonction qui renvoie true si le montre est à la portée de la tour sinon non
     */

    public boolean monsterInRange (Monster monster){
        return Math.sqrt(
                Math.pow(monster.getPos().i() - this.coordinates.i(), 2)
        + Math.pow(monster.getPos().j() - this.coordinates.j(), 2)) <= this.range;
        // regarde la position de la tour et du montres est dans la portée
    }

    /* getters et setters */
    public String getName() {
        return name;
    }
    public int getPrix() {
        return this.prix;
    }
    public int getLevel() {
        return level;
    }
    public abstract void attaquer(ArrayList<Monster> monsters, String difficulty, Character character);
    public int getType() {
        return type;
    }
    public Coordinate getCoordinates() {
        return coordinates;
    }
}
