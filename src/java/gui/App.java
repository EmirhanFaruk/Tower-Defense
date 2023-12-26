package gui;

import config.MapConfig;
import model.monster.MonsterPathFinding;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("ca marche");

        MapConfig config = new MapConfig(MapConfig.grid("Map1.txt"));

        System.out.println("Printing array...");
        MonsterPathFinding.printCellArray(config.getGrid());

        System.out.println("Making MonsterPathFinding object...");
        MonsterPathFinding mpf = new MonsterPathFinding(config);

        System.out.println("Printing its chemin...");
        mpf.printChemin();

        System.out.println("Done!");
    }
}
