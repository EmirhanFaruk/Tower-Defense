package model;

import java.util.Random;

public class MonsN extends Monster
{

    private static double[][][] mulp =
            {
                    {{1, 1}, {1.5, 1.1}, {1.8, 1.3}, {2, 1.5}},
                    {{1, 0.9}, {1.5, 0.9}, {1.8, 0.85}, {2, 0.8}},
                    {{1, 1.6}, {1.5, 1.45}, {1.8, 1.3}, {2, 1.1}}
            }; // live, speed. Exemples a chane

    private static int[][] res_persentage_avoir =
            {
                    {100, 0, 0, 0},
                    {70, 10, 10, 10},
                    {40, 20, 20, 20},
                    {10, 30, 30, 30}
            }; // NONE, BULLET, ARROW, FIRE. Pourcentage pour avoir une resistance.

    private static double[] resistance_pourcentage =
            { 0 , 0.1 ,0.3 ,0.5 } ;
    private int type ; // 0 est lent ; 1 est normal ; 2 est rapide
    private static String[] res_list = {"NONE", "BULLET", "ARROW", "FIRE"};

    private String resistance = "";

    public MonsN(String name , double live , double speed , int money , double x, double y, int niveau,int type)
    {
        super(name, live * mulp[type][niveau][0], speed * mulp[type][niveau][1], money, niveau, x, y);
        choixResistance();
        this.type = type ;
    }

    private void choixResistance()
    {
        Random random = new Random();
        int choix_resistance = random.nextInt(100);
        resistance = res_list[3]; // Si les cas dessous ne marchent pas ça veut dire res est le dernier
        for (int i = 0; i < res_list.length-1; i++) { // Parcourir les pourcentages des resistances
            int res_pour = 0; // Pourcentage de resistance
            for (int j = 0; j <= i; j++) {
                res_pour += res_persentage_avoir[niveau][j];
            }

            if (choix_resistance < res_pour) {
                resistance = res_list[i]; // assigner la resistance
                i = res_list.length; // Pour sortir du boucle
            }
        }
    }
}
