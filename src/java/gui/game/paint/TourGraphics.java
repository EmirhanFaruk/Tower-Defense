package gui.game.paint;

import config.MapConfig;
import gui.game.GameScreen;
import model.tour.Tour;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TourGraphics extends JComponent {
    private final String path = System.getProperty("user.dir") ;
    String s = findSlash(path);
    private final String[][] towerArcher =
            {{"TowerArcherBack1.png","TowerArcherBack2.png" ,"TowerArcherBack3.png"},
            {"TowerArcherFront1.png","TowerArcherFront2.png","TowerArcherFront3.png"},
            {"TowerArcherLeft1.png","TowerArcherLeft2.png","TowerArcherLeft3.png"},
            {"TowerArcherRight1.png","TowerArcherRight2.png","TowerArcherRight3.png"} } ;
    private final String[][] towerCannon =
            {{"TowerCanonBack1.png","TowerCannonBack2.png","TowerCannonBack3.png"},
            {"TowerCannonFront1.png","TowerCannonFront2.png","TowerCannonFront3.png"},
            {"TowerCannonLeft1.png","TowerCannonLeft2.png","TowerCannonLeft3.png"},
            {"TowerCannonRight1.png","TowerCannonRight2.png","TowerCannonRight3.png"}} ;
    private final String[][] towerCatapulte =
            {{"TowerCatapultBack1.png","TowerCatapultBack2.png","TowerCatapultBack3.png"},
            {"TowerCatapultFront1.png","TowerCatapultFront2.png","TowerCatapultFront3.png"},
            {"TowerCatapultLeft1.png","TowerCatapultLeft2.png","TowerCatapultLeft3.png"},
            {"TowerCatapultRight1.png","TowerCatapultRight2.png","TowerCatapultRight3.png"}};

    private final String[][] towerSoldat =
            {{"TowerSodierBack1.png","TowerSodierBack2.png","TowerSodierBack3.png"},
            {"TowerSodierFront1.png","TowerSodierFront2.png","TowerSodierFront3.png"},
            {"TowerSodierLeft1.png","TowerSodierLeft2.png","TowerSodierLeft3.png"},
            {"TowerSodierRight1.png","TowerSodierRight2.png","TowerSodierRight3.png"}};


    int width , height ;
    private Tour tour ;
    private MapConfig mapConfig ;
    private ImageIcon tourImage;

    public TourGraphics(Tour tour , MapConfig mapConfig) {
        this.tour = tour ;
        this.tourImage = loadImage(chooseTowerIcon());
        this.mapConfig = mapConfig ;
        this.height = GameScreen.getTile_height() ;
        this.width = GameScreen.getTile_width() ;
    }
    private String chooseTowerIcon() {
        try {
            return path + s + "src" + s + "resources" + s + "images" + s + "Tower" + s + towerType() + s + towerFile();
        } catch (Exception e){
            e.fillInStackTrace() ;
            return null ;
        }

    }
    public ImageIcon loadImage(String imagePath) {
        return new ImageIcon(imagePath);
    }

    public String towerType (){
        switch (this.tour.getName()){
            case "archer" : return "Archer" ;
            case "canon" : return "Cannon" ;
            case "catapulte" : return "Catapulte" ;
            case "arme" : return "Soldat" ;
        }
        return null ;
    }

    public boolean roadPositionRight (){
        for ( int i = this.tour.getCoordinates().inti() ; i < mapConfig.getGrid().length ; i++ ) {
           if ( mapConfig.getGrid()[ this.tour.getCoordinates().intj() ][ i].getType() == 1 ){
               return true ;
           }
        }
        return false ;
    }

    public boolean roadPositionDown (){
        for ( int j = this.tour.getCoordinates().intj() ; j < mapConfig.getGrid().length ; j++ ) {
            if ( mapConfig.getGrid()[ j ][ this.tour.getCoordinates().inti()].getType() == 1 ){
                return true ;
            }
        }
        return false ;
    }

    public int goodTowerImage (){
        if (  roadPositionRight() ) {
            return 3 ; // personnage orienté vers la droite
        } else if ( ! roadPositionRight()){
            return 2 ; // personnage orienté vers la gauche
        } else if ( roadPositionDown() ) {
            return 1 ; // personnage orienté vers le bas
        } else {
            return 0 ; // personnage orienté vers le haut
        }
    }



    public String towerFile (){
        int niveau = this.tour.getLevel() -1 ;
        if ( towerType().equals("Archer" )){
            return towerArcher[goodTowerImage()][niveau] ;
        } else if ( towerType().equals("Cannon" )){
            return towerCannon[goodTowerImage()][niveau] ;
        } else if ( towerType().equals("Catapulte" )){
            return towerCatapulte[goodTowerImage()][niveau] ;
        } else if ( towerType().equals("Soldiat" )){
            return towerSoldat[goodTowerImage()][niveau] ;
        }
        return null ;
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
