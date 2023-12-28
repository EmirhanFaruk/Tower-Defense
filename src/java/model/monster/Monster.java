package model.monster;

import config.MapConfig;
import gui.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster {
    private final String name ;
    private  double live ;
    private final double speed ;
    private final int money ;
    private final int niveau;
    private  Coordinate pos;
    private final int type ; // 0 est lent ; 1 est normal ; 2 est rapide
    private final ArrayList<Coordinate> path;
    private String direction = "NONE";

    private final MapConfig mapConfig ;
    private final static double[][][] mulp =
            {
                    {{1, 1}, {1.5, 1.1}, {1.8, 1.3}, {2, 1.5}},
                    {{1, 0.9}, {1.5, 0.9}, {1.8, 0.85}, {2, 0.8}},
                    {{1, 1.6}, {1.5, 1.45}, {1.8, 1.3}, {2, 1.1}}
            }; // live, speed. Exemples a changer

    private final static int[][] resistance_pourcentage_avoir =
            {
                    {100, 0, 0, 0},
                    {70, 10, 10, 10},
                    {40, 20, 20, 20},
                    {10, 30, 30, 30}
            }; // NONE, BULLET, ARROW, FIRE. Pourcentage pour avoir une resistance.

    private final static double[] resistance_pourcentage =
            { 0 , 0.1 ,0.3 ,0.5 } ;

    private final static String[] res_list = {"NONE", "BULLET", "ARROW", "FIRE"};

    private String resistance = "";


    public Monster(String name, double live, double speed, int money, int niveau, Coordinate pos, int type, MapConfig mapConfig)
    {
        this.pos = pos;
        this.name = name;
        this.live = live * mulp[type][niveau][0];
        //this.speed = speed * mulp[type][niveau][1];
        this.niveau = niveau; // niveau est entre 0-3
        this.money = money;
        this.type  = type;
        choixResistance();
        this.mapConfig = mapConfig;
        path = MonsterPathFinding.makeMonster_path(mapConfig);
        this.speed = 0.5;
    }

    public Monster(String name, double live, double speed, int money, int niveau, int type, MapConfig mapConfig)
    {
        this.name = name;
        this.live = live * mulp[type][niveau][0];
        //this.speed = speed * mulp[type][niveau][1];
        this.niveau = niveau; // niveau est entre 0-3
        this.money = money;
        this.type  = type;
        choixResistance();
        this.mapConfig = mapConfig;
        path = MonsterPathFinding.makeMonster_path(mapConfig);
        this.pos = path.get(0);
        this.speed = speed;
    }


    private void choixResistance()
    {
        Random random = new Random();
        int choix_resistance = random.nextInt(100);
        resistance = res_list[3]; // Si les cas dessous ne marchent pas ça veut dire res est le dernier
        for (int i = 0; i < res_list.length-1; i++) { // Parcourir les pourcentages des resistances
            int res_pour = 0; // Pourcentage de resistance
            for (int j = 0; j <= i; j++) {
                res_pour += resistance_pourcentage_avoir[niveau][j];
            }

            if (choix_resistance < res_pour) {
                resistance = res_list[i]; // assigner la resistance
                i = res_list.length; // Pour sortir du boucle
            }
        }
    }

    public void monsterHurt(double degats, String type_degats)
    {
        if(type_degats.equals(resistance))
        {
            degats = degats - degats * resistance_pourcentage[niveau];
        }
        live -= degats;
    }

    public void moveMonster() {MonsterPathFinding.moveMonster(this);}

    // une fonction qui retourne un boolean si le montre est rentrer dans la base
    public boolean entrerDansBase(){
        // regarde si les coordonnes du monstres est celui où la base
        return mapConfig.getGrid()[(int) pos.i()][(int) pos.j()].getType() == 4; // renvoie true
    }

    public String toString()
    {
        String s = "\n\n>>>>>>>>>>\n\n" + name + "\nlive = " + live + "\nspeed = " + speed + "\nmoney = " + money + "\n(i, j) = " + pos + "\nnext = ";
        if(path.isEmpty())
        {
            s = s + "none";
        }
        else
        {
            s = s + path.get(0);
        }
        s = s + "\ndirection = " + direction + "\nniveau = " + niveau + "\ntype = " + type + "\nresistance = " + resistance;
        return s;
    }

    public double getLive() {return live;}
    public double getSpeed() {return speed;}
    public int getMoney() {return money;}
    public void setLive(double lives) { live = lives ; }
    public boolean isDead () {return this.live <=0;}

    public ArrayList<Coordinate> getPath() {return path;}
    public void popPath() { if(!path.isEmpty()) path.remove(0);}

    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}

    public Coordinate getPos() {return pos;}
    public void setPos(double a, double b) {pos.set(a, b);}
    public void addPos(double a, double b) {pos.add(a, b);}
}
