package gui;

import config.Cellule;
import config.MapConfig;
import model.monster.MonsterPathFinding;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("ca marche");

        MapConfig config = new MapConfig(MapConfig.grid("Map1.txt"));

        MonsterPathFinding mpf = new MonsterPathFinding(config);

        mpf.printChemin();
    }
}
