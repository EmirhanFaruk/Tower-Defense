package config;

public class Cellule {
    // road est le chemin des monstres
    private final boolean road ;
    private final int type ;
    public Cellule ( boolean road , int type) {
        this.road = road ;
        this.type = type ;
    }
    // 0 pour dire qu'on peut placer les tours
    // 1 pour dire que c'est le chemin des monstres
    // 2 pour dire que c'est l'eau
    // 3 pour dire que c'est un arbre
    // 4 pour dire que c'est la base
    // 5 pour dire que c'est une tour archer
    // 6 pour dire que c'est une tour arme
    // 7 pour dire que c'est une tour artillerie
    // 8 pour dire que c'est une tour canon

    public static Cellule Cell (int i ){
        if ( i == 0 ) return new Cellule(false , 0 ) ;
        if ( i == 1 ) return new Cellule(true, 1 ) ;
        if ( i == 2 ) return new Cellule(false, 2 ) ;
        if ( i == 3 ) return new Cellule(false, 3 ) ;
        if ( i == 4 ) return new Cellule(true , 4) ;
        if ( i == 5 ) return new Cellule ( false , 5) ;
        if ( i == 6 ) return new Cellule ( false , 6) ;
        if ( i == 7 ) return new Cellule ( false , 7) ;
        if ( i == 8 ) return new Cellule ( false , 8) ;
        return null ;
    }
    public boolean isRoad() {
        return road;
    }

    public int getType() {
        return type;
    }

    public String toString()
    {
        return "Type: " + type + ", isRoad: " + road;
    }
}
