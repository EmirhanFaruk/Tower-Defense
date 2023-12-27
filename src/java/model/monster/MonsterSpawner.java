package model.monster;

import config.MapConfig;
import gui.Coordinate;

import java.util.ArrayList;
import java.util.Random;

public class MonsterSpawner
{
    private double in_serie_timer;
    private final double in_serie_timer_max;
    private double between_wave_timer;
    private final double between_wave_timer_max;

    private boolean in_wave = false;
    private int serie_count_max = 1;
    private int serie_count = 0;
    private int wave_count;
    private final int wave_count_max; // pour refaire toutes les vagues
    private final int[] monster_per_serie_max = {5, 15};
    private int monster_per_serie;
    private double monster_timer;
    private final double monster_timer_max;

    private int niveau = 0; // Entre 0-4

    private MapConfig map_config;

    private Coordinate start;

    /**
     * Constructeur de MonsterSpawner. Initialisation des attributs.
     * @param in_serie_timer_max le temps max pour faire apparaitre et tuer les monstres
     * @param between_wave_timer_max le temps max pour attendre le prochain vague
     * @param wave_count_max maximum nombre de vagues
     * @param monster_timer_max le temps max entre l'apparition des monstres dans une serie
     * @param map_config map_config pour produire les monstre
     * @param start la coordonnée de debut
     */
    public MonsterSpawner(double in_serie_timer_max, double between_wave_timer_max, int wave_count_max, double monster_timer_max, MapConfig map_config, Coordinate start)
    {
        this.in_serie_timer_max = in_serie_timer_max;
        in_serie_timer = in_serie_timer_max;

        this.between_wave_timer_max = between_wave_timer_max;
        between_wave_timer = between_wave_timer_max;

        this.wave_count_max = wave_count_max;
        this.wave_count = 0;

        this.monster_per_serie = monster_per_serie_max[serie_count % 2];
        this.monster_timer_max = monster_timer_max;

        monster_timer = monster_timer_max;

        this.map_config = map_config;

        this.start = start;
    }

    /**
     * Mettre a jour tous.
     * @param delta_time delta time
     * @param monsters liste de monstre a ajouter
     */
    public void update(long delta_time, ArrayList<Monster> monsters)
    {
        timerHandler(delta_time);
        if(in_wave)
        {
            spawnWave(monsters);
        }
    }

    /**
     * Gerer les timers.
     * | between_wave_timer | in_serie_timer | repeat
     * @param delta_time delta time
     */
    private void timerHandler(long delta_time)
    {
        double delta_double = ((double)delta_time)/1000000000;
        if(between_wave_timer > 0)
        {
            between_wave_timer -= delta_double;
            if(between_wave_timer <= 0)
            {
                in_serie_timer = in_serie_timer_max;
            }
        }
        else
        {
            if(in_serie_timer > 0)
            {
                in_serie_timer -= delta_double;
                if (monster_per_serie > 0 && monster_timer > 0)
                {
                    monster_timer -= delta_double;
                }
            }
            else
            {
                serie_count++;
                if(serie_count == serie_count_max)
                {
                    between_wave_timer = between_wave_timer_max;
                    serie_count = 0;
                }
                else
                {
                    in_serie_timer = in_serie_timer_max;
                }
            }
        }
    }

    /**
     * Produire une vague des monstres
     * @param monsters liste de monstre a ajouter
     */
    private void spawnWave(ArrayList<Monster> monsters)
    {
        if(in_wave)
        {
            if(in_serie_timer > 0 && monster_per_serie > 0)
            {
                // If in a serie, has monsters to spawn and monster timer is up, put a monster and renew timer.
                if(monster_timer <= 0)
                {
                    spawnMonster(monsters);
                    monster_per_serie--;
                    monster_timer = monster_timer_max;
                }
            }
            if(monster_per_serie == 0 && monsters.isEmpty())
            {
                // If no more monsters, pass to the other serie in 3 seconds.
                monster_per_serie = -1;
                in_serie_timer = 3;
            }
        }
    }

    /**
     * Produire des monstres de type au hazard.
     * @param monsters liste de monstre a ajouter
     */
    private void spawnMonster(ArrayList<Monster> monsters)
    {
        Random random = new Random();
        int type = random.nextInt(3);
        int money = random.nextInt(20);
        monsters.add(new Monster("Monster of wave " + wave_count, 20 * niveau, 5, money, niveau, start.copy(), type, map_config));
    }
}
