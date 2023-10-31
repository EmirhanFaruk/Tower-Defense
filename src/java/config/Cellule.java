package config;

public class Cellule {
    // road est le chemin des monstres
    boolean road ;
    public Cellule ( boolean road){
        this.road = road ;
    }
    // 0 pour dire que on peut placer les tours
    // 1 pour dire que c le chemin des monstres
    public static Cellule Cell (int i ){
        if (i==0) return new Cellule(false) ;
        if (i==1) return new Cellule(true) ;
        return null ;
    }
}
