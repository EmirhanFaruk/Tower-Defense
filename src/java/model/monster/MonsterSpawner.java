package model.monster;

import config.MapConfig;
import model.Character;

import java.util.ArrayList;
import java.util.Random;

public class MonsterSpawner
{
    private double in_serie_timer;
    private final double in_serie_timer_max;
    private double between_wave_timer;
    private final double between_wave_timer_max;

    private boolean in_wave = false;
    private int serie_count_max = 2;
    private int serie_count = 0;
    private int wave_count;
    private final int wave_count_max; // pour refaire toutes les vagues
    private final int[] monster_per_serie_max = {5, 15};
    private int monster_per_serie;
    private double monster_timer;
    private final double monster_timer_max;

    private int niveau = 1; // Entre 1-4

    private MapConfig map_config;

    private Character character ;

    /**
     * Constructeur de MonsterSpawner. Initialisation des attributs.
     * @param in_serie_timer_max le temps max pour faire apparaitre et tuer les monstres
     * @param between_wave_timer_max le temps max pour attendre le prochain vague
     * @param wave_count_max maximum nombre de vagues
     * @param monster_timer_max le temps max entre l'apparition des monstres dans une serie
     * @param map_config map_config pour produire les monstre
     */
    public MonsterSpawner(double in_serie_timer_max, double between_wave_timer_max, int wave_count_max, double monster_timer_max, MapConfig map_config , Character character)
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
        this.character = character ;
    }

    public void startWaves() {in_wave = true;}

    /**
     * Mettre a jour tous.
     * @param delta_time delta time
     * @param monsters liste de monstre a ajouter
     */
    public void update(long delta_time, ArrayList<Monster> monsters)
    {
        if (in_wave)
        {
            spawnWave(monsters);
            timerHandler(delta_time);
        }
    }


    /**
     * Gerer les timers.
     * | between_wave_timer | in_serie_timer && serie count | repeat
     * @param delta_time delta time
     */
    private void timerHandler(long delta_time)
    {
        double delta_double = (double)delta_time/1000;
        if(between_wave_timer > 0)
        {
            // Not in serie
            between_wave_timer -= delta_double;
            if(between_wave_timer <= 0)
            {
                between_wave_timer = 0;
                in_serie_timer = in_serie_timer_max;
                serie_count = 0;
            }
        }
        else
        {
            // In serie
            System.out.println("In serie");
            in_serie_timer -= delta_double;
            monster_timer -= delta_double;
            if(in_serie_timer <= 0)
            {
                // serie out
                in_serie_timer = 0;
                monster_per_serie = monster_per_serie_max[serie_count%2];
                serie_count++;
                if(serie_count == serie_count_max)
                {
                    // wave out
                    between_wave_timer = between_wave_timer_max;
                    serie_count = 0;
                    wave_count++;
                }
            }
        }
        if(wave_count == wave_count_max)
        {
            in_wave = false;
            System.out.println("Wave done.");
        }
    }

    /**
     * Produire une vague des monstres
     * in wave -> if between_wave_timer <= 0 then
     * if monster per serie > 0 -> spawn
     * else if monsters.isEmpty() && monster per serie == 0 ->
     * in serie timer = 3 (3 seconds wait for next serie); monster per serie = -1
     * @param monsters liste de monstre a ajouter
     */
    private void spawnWave(ArrayList<Monster> monsters)
    {
        if(in_wave)
        {
            if(in_serie_timer > 0)
            {
                // If in a serie, has monsters to spawn and monster timer is up, put a monster and renew timer.
                if(monster_timer <= 0 && monster_per_serie > 0)
                {
                    spawnMonster(monsters);
                    monster_per_serie--;
                    monster_timer = monster_timer_max;
                }
                if(monster_per_serie == 0 && monsters.isEmpty())
                {
                    // If no more monsters, pass to the other serie in 3 seconds.
                    monster_per_serie = -1;
                    in_serie_timer = 3;
                }
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
        monsters.add(new Monster("Monster of wave " + wave_count, 20 * niveau, 5, money, niveau, type , character , map_config));
    }

    public boolean getInWave(){
        return this.in_wave ;
    }
}
