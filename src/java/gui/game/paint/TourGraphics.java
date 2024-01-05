package gui.game.paint;

import model.tour.Tour;

import javax.swing.*;
import java.awt.*;

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
    private ImageIcon tourImage;

    public TourGraphics( Tour tour) {
        this.tour = tour ;
        this.tourImage = loadImage(chooseTowerIcon());
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

    public String towerFile (){
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


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tourImage.getImage(), this.tour.getCoordinates().intj(), this.tour.getCoordinates().inti(), width , height , null); // Dessiner l'image à la position (0, 0) pour cet exemple
    }
}
