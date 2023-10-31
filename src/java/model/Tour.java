package model;

public class Tour {
    private final String name ;
    private final int prix ;
    private final int degats ;
    private int level ;
    public Tour (String name , int prix , int degats , int level ){
        this.name = name ;
        this.prix = prix ;
        this.degats = degats ;
        this.level = level ;
    }

    public String getName() {
        return name;
    }

    public int getPrix() {
        return prix;
    }

    public int getDegats() {
        return degats;
    }

    public int getLevel() {
        return level;
    }
}
