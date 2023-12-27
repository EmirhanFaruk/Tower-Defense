package model;

import model.monster.Monster;

import java.util.Scanner;

public class Character {
    private final String name ;
    private static int live ;
    private final int degats ;
    private static int money ;
    private Player player ;

    public Character (Player player,String name , int lives , int degats ){
        this.name = name ;
        live = lives ;
        this.degats = degats ;
        this.money = 0 ;
        this.player = player ;
    }

    public static Character chooseCharacter(Player player) {
        System.out.print("Voulez choisir un personnage (commandant, artilleur, archer) ? : ");
        String userInput = player.getScanAnswer().nextLine().replaceAll("\\s", "").toLowerCase();
        if (userInput.equals("commandant")) {
            return new Character(player, "commandant", 300, 10);
        } else if (userInput.equals("artilleur")) {
            return new Character(player, "artilleur", 250, 7);
        } else if (userInput.equals("archer")) {
            return new Character(player, "archer", 250, 7);
        } else {
            System.out.println("Le personnage n'existe pas.");
            return chooseCharacter(player);
        }
    }


    public void winMoneyWhenMonsterDead(Monster monster){
        if ( monster.isDead()) setMoney(getMoney()+monster.getMoney());
    }

    public void whenMonsterEnterBase ( Monster monster){
        if ( monster.entrerDansBase())  setLive(getLive() - (int)monster.getLive()) ;
    }

    public String getName() {
        return name;
    }
    public static int getLive() {
        return live;
    }
    public static  int getMoney() {
        return money;
    }

    public int getDegats() {
        return degats;
    }

    public void setLive (int lives) {
        live = lives;
    }
    public static void setMoney(int moneys) {
         money = moneys;
    }
}
