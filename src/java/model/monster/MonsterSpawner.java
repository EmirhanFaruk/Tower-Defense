package model.monster;

import config.MapConfig;
import gui.Coordinate;

import java.util.ArrayList;
import java.util.Random;

public class MonsterSpawner
{
    private double in_wave_timer;
    private final double in_wave_timer_max;
    private double between_wave_timer;
    private final double between_wave_timer_max;

    private boolean in_wave = false;
    private int wave_count;
    private int wave_count_max;
    private int monster_per_wave;
    private double monster_timer;
    private final double monster_timer_max;

    private int niveau = 0; // Entre 0-4

    private MapConfig map_config;

    public MonsterSpawner(double in_wave_timer_max, double between_wave_timer_max, int wave_count_max, int monster_per_wave, double monster_timer_max, MapConfig map_config)
    {
        this.in_wave_timer_max = in_wave_timer_max;
        in_wave_timer = in_wave_timer_max;

        this.between_wave_timer_max = between_wave_timer_max;
        between_wave_timer = between_wave_timer_max;

        this.wave_count_max = wave_count_max;
        this.wave_count = wave_count_max;

        this.monster_per_wave = monster_per_wave;
        this.monster_timer_max = monster_timer_max;

        monster_timer = monster_timer_max;

        this.map_config = map_config;
    }



    public void spawnWave(ArrayList<Monster> monsters, int level, Coordinate coordinate)
    {

    }

    /**
     * Makes random type monsters. For now.
     * @param monsters monster list to add to
     * @param coordinate start coordinate
     */
    public void spawnMonster(ArrayList<Monster> monsters, Coordinate coordinate)
    {
        Random random = new Random();
        int type = random.nextInt(3);
        int money = random.nextInt(20);
        monsters.add(new Monster("Monster of wave " + wave_count, 20 * niveau, 5, money, niveau, coordinate.copy(), type, map_config));
    }
}
