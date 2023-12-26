package model.monster;

import config.MapConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster {
    protected final String name ;
    protected double live ;
    protected final double speed ;
    protected final int money ;
    protected final int niveau;
    protected final int degats ;
    protected double i, j;
    private final int type ; // 0 est lent ; 1 est normal ; 2 est rapide
    private final List<ArrayList<Integer>> path;
    private String direction = "EAST";

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

    public Monster(String name, double live, double speed, int degats, int money, int niveau, double i, double j, int type, MapConfig mapConfig)
    {
        this.i = i;
        this.j = j;
        this.name = name;
        this.live = live * mulp[type][niveau][0];
        this.speed = speed * mulp[type][niveau][1];
        this.niveau = niveau; // niveau est entre 0-3
        this.money = money;
        this.degats = degats;
        this.type  = type;
        choixResistance();
        this.mapConfig = mapConfig;
        path = MonsterPathFinding.makeMonster_path(mapConfig);
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

    // une fonction qui retourne un boolean si le montre est rentrer dans la base
    public boolean entrerDansBase(){
        // regarde si les coordonnes du monstres est celui où la base
        return mapConfig.getGrid()[(int) i][(int) j].getType() == 4; // renvoie true
    }

    public String toString()
    {
        return "\n\n>>>>>>>>>>\n\n" + name + "\nlive = " + live + "\nspeed = " + speed + "\nmoney = " + money + "\n(i, j) = (" + i + ", " + j + ")\nniveau = " + niveau + "\ntype = " + type + "\nresistance = " + resistance;
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
    public void setLive(double live) {
        this.live = live;
    }
    public boolean isDead (){
        return this.live <=0 ;
    }


}
