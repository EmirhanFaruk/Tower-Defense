package gui.game;

import config.MapConfig;
import model.Character;
import model.monster.Monster;
import model.monster.MonsterSpawner;
import model.tour.*;

import java.util.ArrayList;

public class Game
{
    private MapConfig map_config;

    private MonsterSpawner monster_spawner;

    private ArrayList<Monster> monsters;
    private Character character ;

    // Les tours seront encapsulé dans les cellules, cette liste va avoir leurs references
    private ArrayList<Tour> tours;

    private String difficulty;

    /**
     * Constructeur de Game. Produire map_config depuis map(path) et
     * @param map path de map
     * @param difficulty difficulté pour monster_spawner
     * @throws Exception pour map_config
     */
    public Game(String map, String difficulty, int wave_count_max, Character character)
    {
        setMap_config(map);
        this.character = character;
        this.difficulty = difficulty;

        makeMonsterSpawner(difficulty, wave_count_max);

        monsters = new ArrayList<>();
        tours = new ArrayList<>();
        apparitionMonster();
    }

    public void setMap_config(String map)
    {
        try
        {
            map_config = MapConfig.make(map + ".txt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void makeMonsterSpawner(String difficulty, int wave_count_max)
    {
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
            tour.attaquer(monsters, difficulty);
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

    public void placeTower(int mouseX, int mouseY, String towerType) {
        int x = mouseX / GameScreen.getTile_width();
        int y = mouseY / GameScreen.getTile_height();
        System.out.println("Coordonnées: " + y + ", " + x);
        if (map_config.getGrid()[y][x].getType() == 0 ) {
            switch (towerType) {
                case "Archer": buyTower("archer" , y, x);break;
                case "Soldat": buyTower("arme" ,y, x);break;
                case "Cannon": buyTower("canon" , y, x);break;
                case "Catapulte": buyTower("catapulte" , y, x); break;
            }
            System.err.println("la tour peut etre poser.");
        } else {
            /** TODO
             *   faire un message pour dire qu'il peut pas poser la tour ici
             */
            System.err.println("La tour ne peut pas etre poser.");
        }
    }

    public void buyTower (String towerName , int i , int j){
        int towerCost = findTower(towerName , 1 ).getPrix() ;
        if ( character.getMoney() >=towerCost) {
            switch (towerName) {
                case "archer" : tours.add(new Archer(1, i, j));
                case "arme" :tours.add(new Soldat(1 , i, j)) ;
                case "catapulte" : tours.add(new Catapulte(1, i, j));
                case "canon" :tours.add(new Canon(1 , i, j)) ;
            }
            this.character.setMoney(this.character.getMoney() - towerCost );
            System.err.println("Tour acheter") ;
        } else {
            /** TODO
             *   faire un message pour dire qu'il a pas assez d'argent
             */
            System.err.println("Pas assez d'argent");
        }
    }

    public void upgradeTower(int mouseX, int mouseY){
        int x = mouseX / GameScreen.getTile_width();
        int y = mouseY / GameScreen.getTile_height() ;
        for ( Tour tour : tours ){
            if ( (int ) tour.getCoordinates().i() == x  && ( int ) tour.getCoordinates().j() == y ){
                int upgradePrice = findTower(tour.getName(),tour.getLevel()+1).getPrix() ;
                if ( this.character.getMoney() >= upgradePrice ) {
                    switch (tour.getName()){
                        case "arme" : tours.add(new Soldat(tour.getLevel(), x , y )); tours.remove(tour) ;
                        case "catapulte" : tours.add(new Catapulte(tour.getLevel(), x , y )); tours.remove(tour) ;
                        case "canon" : tours.add(new Canon(tour.getLevel(), x , y )); tours.remove(tour) ;
                        case "archer" : tours.add(new Archer(tour.getLevel(), x , y )); tours.remove(tour) ;
                    }
                    this.character.setMoney(this.character.getMoney() - upgradePrice);
                } else {
                    /** TODO
                     *   faire un message pour dire qu'il a pas assez d'argent
                     */
                    System.err.println("Pas assez d'argent pour upgrade");
                }
            } else {
                /** TODO
                 *   faire un message pour dire qu'il a pas de tour a cette place
                 */System.err.println("Pas de tour a cette endroit");

            }
        }
    }

    private ArrayList<Tour> towerList (){
        ArrayList<Tour> towerList = new ArrayList<>() ;
        towerList.add(new Archer(1 )) ;
        towerList.add(new Archer(2 )) ;
        towerList.add(new Archer(3 )) ;
        towerList.add(new Soldat(1 )) ;
        towerList.add(new Soldat(2 )) ;
        towerList.add(new Soldat(3 )) ;
        towerList.add(new Catapulte(1 )) ;
        towerList.add(new Catapulte(2 )) ;
        towerList.add(new Catapulte(3 )) ;
        towerList.add(new Canon(1 )) ;
        towerList.add(new Canon(2 )) ;
        towerList.add(new Canon(3 )) ;
        return towerList ;
    }
    // UNe fonction qui cherche si la tour que le player à demande existe
    public Tour findTower ( String str , int i ){
        for ( Tour t : towerList()){
            if (t.getName().equals(str) && i == t.getLevel()) {
                return t ;
            }
        }
        return null ;
    }

    public MonsterSpawner getMonster_spawner() {return monster_spawner;}

    public boolean gameOverCondition() {
        return this.character.getLive() <= 0 ;
    }


    public Character getCharacter() {return character;}

    public MapConfig getMap_config() {return map_config;}

    public ArrayList<Monster> getMonsters() {return monsters;}

    public ArrayList<Tour> getTours() {return tours;}
}
