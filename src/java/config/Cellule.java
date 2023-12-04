package config;

public class Cellule {
    // road est le chemin des monstres
    private final boolean road ;
    private final int type ;
    public Cellule ( boolean road , int type) {
        this.road = road ;
        this.type = type ;
    }
    // 0 pour dire que on peut placer les tours
    // 1 pour dire que c'est le chemin des monstres
    // 2 pour dire que c'est l'eau
    // 3 pour dire que c'est un arbre
    // 4 pour dire que c'est la base

    public static Cellule Cell (int i ){
        if ( i == 0 ) return new Cellule(false , 0 ) ;
        if ( i == 1 ) return new Cellule(true, 1 ) ;
        if ( i == 2 ) return new Cellule(false, 2 ) ;
        if ( i == 3 ) return new Cellule(false, 3 ) ;
        if (  i== 4 ) return new Cellule(true , 4) ;
        return null ;
    }
    public boolean isRoad() {
        return road;
    }

    public int getType() {
        return type;
    }
}
