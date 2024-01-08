package gui.game.paint;

import config.MapConfig;
import model.tour.Tour;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TourGraphics
{
    private static final String path = System.getProperty("user.dir") ;
    private static final String s = findSlash(path);
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

    private static BufferedImage[][] towerArcherIm = new BufferedImage[towerArcher.length][towerArcher[0].length];
    private static BufferedImage[][] towerCannonIm = new BufferedImage[towerCannon.length][towerCannon[0].length];
    private static BufferedImage[][] towerCatapulteIm = new BufferedImage[towerCatapulte.length][towerCatapulte[0].length];
    private static BufferedImage[][] towerSoldatIm = new BufferedImage[towerSoldat.length][towerSoldat[0].length];

    private static int width , height ;

    public TourGraphics() {
    }

    /**
     * Une fonction qui renvoie une image
     * @param imagePath
     * @return bufferedImage
     */
    public static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Gérer l'erreur de chargement de l'image
        }
    }

    public static void setImages() {
        BufferedImage[][][] lists = {towerArcherIm, towerCannonIm, towerCatapulteIm, towerSoldatIm};
        String[][][] st_lists = {towerArcher, towerCannon, towerCatapulte, towerSoldat};
        String baseFp = path + s + "src" + s + "resources" + s + "images" + s + "Tower" + s;
        String[] towerType = {"Archer", "Cannon", "Catapulte", "Soldat"};
        for (int l = 0; l < 4; l++) {
            String fp = baseFp;
            fp = baseFp + towerType[l]+ s ;
            for (int i = 0; i < lists[l].length; i++) {
                for (int j = 0; j < lists[l][0].length; j++) {
                    lists[l][i][j] = loadImage(fp + st_lists[l][i][j]);
                }
            }
        }
    }

    /**
     * Une fonction qui donne le bon type de tour
     * @param tour
     * @return
     */

    public static String towerType (Tour tour){
        switch (tour.getName()){
            case "archer" : return "Archer" ;
            case "canon" : return "Cannon" ;
            case "catapulte" : return "Catapulte" ;
            case "arme" : return "Soldat" ;
        }
        return null ;
    }

    public static boolean roadPositionRight (Tour tour, MapConfig mapConfig)
    {
        int i = tour.getCoordinates().inti();
        int j = tour.getCoordinates().intj();
        if(i + 1 < mapConfig.getGrid().length)
        {
            boolean res = false;
            if(j + 1 < mapConfig.getGrid()[i].length)
            {
                res = res || mapConfig.getGrid()[i + 1][j + 1].getType() == 1;
            }
            if(j < mapConfig.getGrid()[i].length)
            {
                res = res || mapConfig.getGrid()[i + 1][j].getType() == 1;
            }
            if(j - 1 > 0)
            {
                res = res || mapConfig.getGrid()[i + 1][j - 1].getType() == 1;
            }
            return res;
        }
        return false ;
    }

    public static boolean roadPositionLeft (Tour tour, MapConfig mapConfig)
    {
        int i = tour.getCoordinates().inti();
        int j = tour.getCoordinates().intj();
        if(i - 1 > 0)
        {
            boolean res = false;
            if(j + 1 < mapConfig.getGrid()[i].length)
            {
                res = res || mapConfig.getGrid()[i - 1][j + 1].getType() == 1;
            }
            if(j < mapConfig.getGrid()[i].length)
            {
                res = res || mapConfig.getGrid()[i - 1][j].getType() == 1;
            }
            if(j - 1 > 0)
            {
                res = res || mapConfig.getGrid()[i - 1][j - 1].getType() == 1;
            }
            return res;
        }
        return false ;
    }

    public static boolean roadPositionDown (Tour tour, MapConfig mapConfig)
    {
        int i = tour.getCoordinates().inti();
        int j = tour.getCoordinates().intj();
        if(j + 1 < mapConfig.getGrid()[i].length)
        {
            boolean res = false;
            if(i + 1 < mapConfig.getGrid().length)
            {
                res = res || mapConfig.getGrid()[i + 1][j + 1].getType() == 1;
            }
            if(i < mapConfig.getGrid().length)
            {
                res = res || mapConfig.getGrid()[i][j + 1].getType() == 1;
            }
            if(i - 1 > 0)
            {
                res = res || mapConfig.getGrid()[i - 1][j + 1].getType() == 1;
            }
            return res;
        }
        return false ;
    }

    public static boolean roadPositionUp (Tour tour, MapConfig mapConfig)
    {
        int i = tour.getCoordinates().inti();
        int j = tour.getCoordinates().intj();
        if(j - 1 > 0)
        {
            boolean res = false;
            if(i + 1 < mapConfig.getGrid().length)
            {
                res = res || mapConfig.getGrid()[i + 1][j - 1].getType() == 1;
            }
            if(i < mapConfig.getGrid().length)
            {
                res = res || mapConfig.getGrid()[i][j - 1].getType() == 1;
            }
            if(i - 1 > 0)
            {
                res = res || mapConfig.getGrid()[i - 1][j - 1].getType() == 1;
            }
            return res;
        }
        return false ;
    }

    public static int goodTowerImage (Tour tour, MapConfig mapConfig)
    {
        boolean[] tab =
                {
                    roadPositionRight(tour, mapConfig),
                    roadPositionDown(tour, mapConfig),
                    roadPositionLeft(tour, mapConfig),
                    roadPositionUp(tour, mapConfig)
                };
        for(int i = 0; i < 4; i++)
        {
            if(tab[i])
            {
                return i;
            }
        }
        return 3;
    }

    /**
     * Une fonction qui renvoie une image
     * @param tour
     * @param mapConfig
     * @return bufferedImage
     */
    public static BufferedImage getImage(Tour tour, MapConfig mapConfig) {
        int niveau = tour.getLevel() - 1;
        switch (towerType(tour)) {
            case "Archer":
                return towerArcherIm[goodTowerImage(tour, mapConfig)][niveau];
            case "Cannon":
                return towerCannonIm[goodTowerImage(tour, mapConfig)][niveau];
            case "Catapulte":
                return towerCatapulteIm[goodTowerImage(tour, mapConfig)][niveau];
            case "Soldat":
                return towerSoldatIm[goodTowerImage(tour, mapConfig)][niveau];
        }
        return towerArcherIm[goodTowerImage(tour, mapConfig)][niveau];
    }

    /**
     * Une fonction qui donne le bon slash
     * @param p
     * @return String
     */
    private static String findSlash(String p)
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
    public static void paint(Graphics2D g, Tour tour, MapConfig mapConfig)
    {
        BufferedImage image = getImage(tour, mapConfig);
        int x = (int) tour.getCoordinates().j() * width;
        int y = (int) tour.getCoordinates().i() * height;
        g.drawImage(image, x, y, width, height, null);
    }

    /* getters et setters */
    public static void setWH(int w, int h)
    {
        width = w;
        height = h;
    }
}
