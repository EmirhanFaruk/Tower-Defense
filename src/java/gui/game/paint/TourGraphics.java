package gui.game.paint;

import model.tour.Tour;

import javax.swing.*;
import java.awt.*;

public class TourGraphics extends JComponent {
    private final String path = System.getProperty("user.dir") ;
    String s = findSlash(path);

    private Tour tour ;
    private ImageIcon tourImage;

    public TourGraphics( Tour tour) {
        this.tour = tour ;
        this.tourImage = loadImage(chooseTowerIcon());
    }
    private String chooseTowerIcon() {
        try {
            return path + s + "src" + s + "resources" + s + "images" + s + "Monster" + s + towerFile();
        } catch (Exception e){
            e.fillInStackTrace() ;
            return null ;
        }

    }
    public ImageIcon loadImage(String imagePath) {
        return new ImageIcon(imagePath);
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

    public void update() {
        repaint(); // Demande une nouvelle peinture
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tourImage.getImage(), 0, 0, null); // Dessiner l'image à la position (0, 0) pour cet exemple
    }
}
