package model;

import model.monster.Monster;

import java.util.Scanner;

public class Character {
    private final String name ;
    private static int live ;
    private final int degats ;
    private int money ;

    public Character (String name , int lives , int degats ){
        this.name = name ;
        live = lives ;
        this.degats = degats ;
        this.money = 0 ;
    }

    public static Character chooseCharacter(){
        System.out.print("Voulez choisir un personnage ( commandant , artilleur , archer ) ? : "); // le player choisit son character
        Scanner scanner = new Scanner(System.in); // ouverture d'un scanner
        if ( scanner.nextLine().replaceAll("\\s", "").equalsIgnoreCase("commandant")) {
            return  new Character("commandant",300 , 10 ) ; // création du character commandant si le player a écrit commandant
        } else if (scanner.nextLine().replaceAll("\\s", "").equalsIgnoreCase("artilleur")){
            return new Character("artilleur",250 , 7 ) ; // création du character artilleur si le player a écrit artilleur
        } else if (scanner.nextLine().replaceAll("\\s", "").equalsIgnoreCase("archer")){
            return new Character("archer",250 , 7 ) ; // création du character archer si le player a écrit archer
        } else {
            System.out.println("Le character n'existe pas. ");
            return chooseCharacter() ;
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
    public int getMoney() {
        return money;
    }

    public int getDegats() {
        return degats;
    }

    public void setLive (int live) {
        this.live = live;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
