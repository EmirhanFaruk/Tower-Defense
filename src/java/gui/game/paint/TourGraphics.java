package gui.game.paint;

import config.MapConfig;
import gui.game.GameScreen;
import model.tour.Tour;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TourGraphics extends JComponent {
    private static final String path = System.getProperty("user.dir") ;
    static String s = findSlash(path);
    private static final String[][] towerArcher =
            {{"TowerArcherBack1.png","TowerArcherBack2.png" ,"TowerArcherBack3.png"},
            {"TowerArcherFront1.png","TowerArcherFront2.png","TowerArcherFront3.png"},
            {"TowerArcherLeft1.png","TowerArcherLeft2.png","TowerArcherLeft3.png"},
            {"TowerArcherRight1.png","TowerArcherRight2.png","TowerArcherRight3.png"} } ;
    private static final String[][] towerCannon =
            {{"TowerCanonBack1.png","TowerCannonBack2.png","TowerCannonBack3.png"},
            {"TowerCannonFront1.png","TowerCannonFront2.png","TowerCannonFront3.png"},
            {"TowerCannonLeft1.png","TowerCannonLeft2.png","TowerCannonLeft3.png"},
            {"TowerCannonRight1.png","TowerCannonRight2.png","TowerCannonRight3.png"}} ;
    private static final String[][] towerCatapulte =
            {{"TowerCatapultBack1.png","TowerCatapultBack2.png","TowerCatapultBack3.png"},
            {"TowerCatapultFront1.png","TowerCatapultFront2.png","TowerCatapultFront3.png"},
            {"TowerCatapultLeft1.png","TowerCatapultLeft2.png","TowerCatapultLeft3.png"},
            {"TowerCatapultRight1.png","TowerCatapultRight2.png","TowerCatapultRight3.png"}};

    private static final String[][] towerSoldat =
            {{"TowerSodierBack1.png","TowerSodierBack2.png","TowerSodierBack3.png"},
            {"TowerSodierFront1.png","TowerSodierFront2.png","TowerSodierFront3.png"},
            {"TowerSodierLeft1.png","TowerSodierLeft2.png","TowerSodierLeft3.png"},
            {"TowerSodierRight1.png","TowerSodierRight2.png","TowerSodierRight3.png"}};

    private static final ImageIcon[][] towerArcherIm = new ImageIcon[towerArcher.length][towerArcher[0].length];
    private static final ImageIcon[][] towerCannonIm = new ImageIcon[towerCannon.length][towerCannon[0].length];
    private static final ImageIcon[][] towerCatapulteIm = new ImageIcon[towerCatapulte.length][towerCatapulte[0].length];
    private static final ImageIcon[][] towerSoldatIm = new ImageIcon[towerSoldat.length][towerSoldat[0].length];

    static int width , height ;

    public TourGraphics() {
        height = GameScreen.getTile_height() ;
        width = GameScreen.getTile_width() ;
    }

    public static void setWH(int w, int h)
    {
        width = w;
        height = h;
    }
    private static String chooseTowerIcon(Tour tour) {
        try {
            return path + s + "src" + s + "resources" + s + "images" + s + "Tower" + s + towerType(tour) + s + towerFile();
        } catch (Exception e){
            e.fillInStackTrace() ;
            return null ;
        }

    }
    public ImageIcon loadImage(String imagePath) {
        return new ImageIcon(imagePath);
    }

    public static void setImages()
    {
        ImageIcon[][][] lists = {towerArcherIm, towerCannonIm, towerCatapulteIm, towerSoldatIm};
        String[][][] st_lists = {towerArcher, towerCannon, towerCatapulte, towerSoldat};
        String fp = path + s + "src" + s + "resources" + s + "images" + s + "Tower" + s;
        for(int l = 0; l < 4; l++)
        {
            for(int i = 0; i < lists[l].length; i++)
            {
                for(int j = 0; j < lists[l][0].length; j++)
                {
                    lists[l][i][j] = new ImageIcon(fp + st_lists[l][i][j]);
                }
            }
        }
    }

    public static String towerType (Tour tour){
        switch (tour.getName()){
            case "archer" : return "Archer" ;
            case "canon" : return "Cannon" ;
            case "catapulte" : return "Catapulte" ;
            case "arme" : return "Soldat" ;
        }
        return null ;
    }

    public static boolean roadPositionRight (Tour tour, MapConfig mapConfig){
        for ( int i = tour.getCoordinates().inti() ; i < mapConfig.getGrid().length ; i++ ) {
           if ( mapConfig.getGrid()[ tour.getCoordinates().intj() ][ i].getType() == 1 ){
               return true ;
           }
        }
        return false ;
    }

    public static boolean roadPositionDown(Tour tour, MapConfig mapConfig){
        for ( int j = tour.getCoordinates().intj() ; j < mapConfig.getGrid().length ; j++ ) {
            if ( mapConfig.getGrid()[ j ][ tour.getCoordinates().inti()].getType() == 1 ){
                return true ;
            }
        }
        return false ;
    }

    public static int goodTowerImage (Tour tour, MapConfig mapConfig){
        if (  roadPositionRight(tour, mapConfig) ) {
            return 3 ; // personnage orienté vers la droite
        } else if ( ! roadPositionRight(tour, mapConfig)){
            return 2 ; // personnage orienté vers la gauche
        } else if ( roadPositionDown(tour, mapConfig) ) {
            return 1 ; // personnage orienté vers le bas
        } else {
            return 0 ; // personnage orienté vers le haut
        }
    }



    public static String towerFile (Tour tour, MapConfig mapConfig){
        int niveau = tour.getLevel() -1 ;
        switch (towerType(tour))
        {
            case "Archer" : return towerArcher[goodTowerImage(tour, mapConfig)][niveau];
            case "Cannon" : return towerCannon[goodTowerImage(tour, mapConfig)][niveau];
            case "Catapulte" : return towerCatapulte[goodTowerImage(tour, mapConfig)][niveau];
            case "Soldat" : return towerSoldat[goodTowerImage(tour, mapConfig)][niveau];

        }
        return towerArcher[goodTowerImage(tour, mapConfig)][niveau];
    }
    private String findSlash(String p)
    {
        for(int i = 0; i < p.length(); i++)
        {
            switch (p.charAt(i))
            {
                case '/' : return "/";
                case '\\' : return "\\";
            }
        }
        return "/";
    }
    public void paint(Graphics2D g) {
        g.drawImage(tourImage.getImage(), tour.getCoordinates().intj(), tour.getCoordinates().inti(), width, height, null);
    }
}
