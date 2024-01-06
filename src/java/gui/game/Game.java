package gui.game;

import config.MapConfig;
import model.Character;
import model.monster.Monster;
import model.monster.MonsterSpawner;
import model.tour.Tour;

import javax.swing.*;
import java.util.ArrayList;

public class Game
{
    private MapConfig map_config;

    private MonsterSpawner monster_spawner;

    private ArrayList<Monster> monsters;
    private Character character ;

    // Les tours seront encapsulé dans les cellules, cette liste va avoir leurs references
    private ArrayList<Tour> tours;

    /**
     * Constructeur de Game. Produire map_config depuis map(path) et
     * @param map path de map
     * @param difficulty difficulté pour monster_spawner
     * @throws Exception pour map_config
     */
    public Game(String map, String difficulty, int wave_count_max, Character character)
    {
        try
        {
            map_config = MapConfig.make(map + ".txt");
            this.character = character ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        // Default values, same as NORMAL
        int in_serie_timer_max = 20,
        between_wave_timer_max = 5;
        double monster_timer_max = 1;

        switch (difficulty)
        {
            case "EASY" :
                in_serie_timer_max = 25;
                between_wave_timer_max = 7;
                monster_timer_max = 1.5;
                break;
            case "HARD" :
                in_serie_timer_max = 15;
                between_wave_timer_max = 3;
                monster_timer_max = 0.5;
                break;
        }


        monster_spawner = new MonsterSpawner(in_serie_timer_max, between_wave_timer_max, wave_count_max, monster_timer_max, map_config, character);
        monsters = new ArrayList<>();
        tours = new ArrayList<>();
        apparitionMonster();
    }

    /**
     * Mise a jour des tours et des monstres
     */
    public void updateEnts(double delta_time)
    {
        monster_spawner.update(delta_time, monsters);
        updateTours();
        updateMonsters(delta_time);
    }

    /**
     * Mettre a jour les tours
     */
    private void updateTours()
    {
        for(Tour tour : tours)
        {
            tour.attaquer(monsters);
        }
    }

    private void updateMonsters(double delta_time)
    {
        ArrayList<Monster> monstersToRemove = new ArrayList<>();

        for (Monster monster : monsters) {
            monster.moveMonster(delta_time);

            if (monster.entrerDansBase()) {
                monster.whenMonsterEnterBase();
                monstersToRemove.add(monster);
            }
        }

        // Remove monsters outside the loop to avoid concurrent modification
        monsters.removeAll(monstersToRemove);
    }

    public void apparitionMonster(){
        this.monster_spawner.startWaves();
        if (this.monster_spawner.getInWave())
        {
            this.monster_spawner.update(20000, monsters);
        }
    }

    public MonsterSpawner getMonster_spawner() {return monster_spawner;}

    public boolean gameOverCondition() {
        return this.character.getLive() <= 0 ;
    }

    public void resetGame(){

    }

    public Character getCharacter() {return character;}

    public MapConfig getMap_config() {return map_config;}
}
