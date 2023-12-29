package model;

import model.monster.Monster;

import java.util.Scanner;

public class Character {
    private final String name ;
    private int live ;
    private final int degats ;
    private int money ;
    private Player player ;

    public Character (Player player,String name , int lives , int degats ){
        this.name = name ;
        live = lives ;
        this.degats = degats ;
        this.money = 10 ;
        this.player = player ;
    }

    public static Character chooseCharacter(Player player) {
        System.out.print("Voulez choisir un personnage (commandant, artilleur, archer) ? : ");
        String userInput = player.getScanAnswer().nextLine().replaceAll("\\s", "").toLowerCase();
        switch (userInput) {
            case "commandant":
                return new Character(player, "commandant", 300, 10);
            case "artilleur":
                return new Character(player, "artilleur", 250, 7);
            case "archer":
                return new Character(player, "archer", 250, 7);
            default:
                System.out.println("Le personnage n'existe pas.");
                return chooseCharacter(player);
        }
    }


    public void winMoneyWhenMonsterDead(Monster monster){
        if ( monster.isDead()) setMoney(getMoney()+monster.getMoney());
    }

    public String getName() {
        return name;
    }
    public int getLive() {
        return live;
    }
    public int getMoney() {
        return money;
    }

    public int getDegats() {
        return degats;
    }

    public void setLive(int live) {
        this.live = live;
    }
    public void setMoney(int money) {
         this.money = money;
    }
}
