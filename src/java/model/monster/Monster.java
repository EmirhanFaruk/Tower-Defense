package model.monster;

import config.MapConfig;
import gui.Coordinate;
import model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster {
    private final String name ;
    private  double live ;
    private double max_live;
    private final double speed ;
    private final int money ;
    private final int niveau;
    private  Coordinate pos;
    private final int type ; // 0 est lent ; 1 est normal ; 2 est rapide
    private final ArrayList<Coordinate> path;

    private Character character ;
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

    private final static String[] res_list = {"NONE", "BULLET", "ARROW", "EXPLOSION"};

    private String resistance = "";


    /**
     * Constructeur de Monster.
     * @param name
     * @param live
     * @param speed
     * @param money
     * @param niveau
     * @param pos
     * @param type
     * @param character
     * @param mapConfig
     */
    public Monster(String name, double live, double speed, int money, int niveau, Coordinate pos, int type, Character character , MapConfig mapConfig)
    {
        this.pos = pos;
        this.name = name;
        this.live = live * mulp[type][niveau][0];
        this.max_live = this.live;
        this.speed = speed * mulp[type][niveau][1];
        if(niveau > 2)
        {
            this.niveau = 2;
        }
        else
        {
            this.niveau = niveau; // niveau est entre 0-2
        }
        this.money = money;
        this.type  = type;
        choixResistance();
        this.mapConfig = mapConfig;
        path = MonsterPathFinding.makeMonster_path(mapConfig);
        this.character = character ;
    }

    /**
     * Constructeur de Monster. La position n'est pas donné donc on le defini avec le premier cible de path
     * @param name
     * @param live
     * @param speed
     * @param money
     * @param niveau
     * @param type
     * @param character
     * @param mapConfig
     */
    public Monster(String name, double live, double speed, int money, int niveau, int type, Character character ,MapConfig mapConfig)
    {
        this.name = name;
        this.live = live * mulp[type][niveau][0];
        this.max_live = this.live;
        this.speed = speed * mulp[type][niveau][1];
        if(niveau > 2)
        {
            this.niveau = 2;
        }
        else
        {
            this.niveau = niveau; // niveau est entre 0-2
        }
        this.money = money;
        this.type  = type;
        choixResistance();
        this.mapConfig = mapConfig;
        path = MonsterPathFinding.makeMonster_path(mapConfig);
        Coordinate temp = path.get(0);
        temp.add(0, -1);
        this.pos = temp.copy();
        this.character = character ;
    }


    /**
     * Choisir le type de resistance en fonction de niveau
     */
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



    /**
     * Faire degats a la monstre en prenant compte de type, resistance et difficulté
     * @param degats de tour
     * @param type_degats type de tour
     * @param difficulty difficulté du jeu
     */
    public void monsterHurt(double degats, String type_degats, String difficulty)
    {
        if(type_degats.equals(resistance))
        {
            degats = degats - degats * resistance_pourcentage[niveau];
        }
        switch (difficulty)
        {
            case "EASY" : live -= degats * 1.5; break;
            case "NORMAL" : live -= degats * 1; break;
            case "HARD" : live -= degats * 0.5; break;
        }
    }

    /**
     * Une fonction qui diminue la vie quand les monstres sont dans la base
     */
    public void whenMonsterEnterBase()
    {
        if (character != null) {
            character.minusLive((int) getLive());
        }

        setLive(0);
    }

    /**
     * Une fonction qui donne l'argent au character
     */
    public void winMoneyWhenMonsterDead(){
        character.setMoney(character.getMoney()+getMoney());
    }

    /**
     * Une fonction qui fait bouger le monstre
     * @param delta
     */
    public void moveMonster(double delta) {MonsterPathFinding.moveMonster(this, delta);}

    /**
     *  Une fonction qui retourne un boolean si le montre est rentré dans la base
     * @return bool
     */
    public boolean entrerDansBase(){
        // regarde si les coordonnés du monstre est celui où est la base
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

    /* getters et setters */
    public double getLive() {return live;}
    public double getMax_live() {return max_live;}
    public double getSpeed(double delta) {return speed * delta;}
    public int getMoney() {return money;}
    public void setLive(double lives) { live = lives ; }
    public boolean isDead () {return this.live <=0;}
    public int getNiveau() {
        return niveau;
    }
    public String getResistance() {
        return resistance;
    }
    public ArrayList<Coordinate> getPath() {return path;}
    public void popPath() { if(!path.isEmpty()) path.remove(0);}
    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}
    public Coordinate getPos() {return pos;}
    public void setPos(double a, double b) {pos.set(a, b);}
    public void addPos(double a, double b) {pos.add(a, b);}
}
