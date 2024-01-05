package gui.game.paint;

import gui.game.GameScreen;
import model.monster.Monster ;

import javax.swing.*;
import java.awt.*;

public class MonsterGraphics extends JComponent {
    private final String path = System.getProperty("user.dir") ;
    String s = findSlash(path);
    private final String[] monsterBlue = { "MonsterBlue1.gif" , "MonsterBlue2.gif" , "MonsterBlue3.gif" } ;
    private final String[] monsterRed = {"MonsterRed1.gif" , "MonsterRed2.gif" , "MonsterRed3.gif"} ;
    private final String[] monsterGreen = {"MonsterGreen1.gif" , "MonsterGreen2.gif" , "MonsterGreen3.gif"} ;
    private final String [] monsterGray = {"MonsterGray1.gif" , "MonsterGray2.gif" , "MonsterGray3.gif"} ;
    int width, height;
    private Monster monster;
    private ImageIcon monsterImage;

    public MonsterGraphics(Monster monster) {
        this.monster = monster;
        this.monsterImage = loadImage(chooseMonsterIcon());
        this.height = GameScreen.getTile_height() ;
        this.width = GameScreen.getTile_width() ;
    }

    /**
     * Retourne le path de l'iamge du monstre
     * @return le path
     */
    private String chooseMonsterIcon() {
        try {
            return path + s + "src" + s + "resources" + s + "images" + s + "Monster" + s + monsterFile();
        } catch (Exception e){
            e.fillInStackTrace() ;
            return null ;
        }

    }

    /**
     * Retourne une Imageicon en fonction d'imagePath
     * @param imagePath
     * @return une imageIcon
     */
    public ImageIcon loadImage(String imagePath) {
        return new ImageIcon(imagePath);
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

    /**
     *  Donne l'image du monstre en fonction de sa resistance et de son niveau
     * @return l'image du monstre correspondant
     */
    public String monsterFile (){
        int pos = this.monster.getNiveau() -1 ;
        switch (this.monster.getResistance()){
            case " NONE" : return monsterGray[pos] ;
            case " BULLET" : return monsterBlue[pos] ;
            case  "ARROW" : return monsterGreen[pos] ;
            case " FIRE" : return monsterRed[pos] ;
        }
        return null ;
    }

    /**
     * Déplace le monstre en fonction de sa direction
     */
    public void move() {
        double distance = 100 ;
        switch (monster.getDirection()) {
            case "UP":
                monster.getPos().add(0, -distance);
                break;
            case "DOWN":
                monster.getPos().add(0, distance);
                break;
            case "LEFT":
                monster.getPos().add(-distance, 0);
                break;
            case "RIGHT":
                monster.getPos().add(distance, 0);
                break;
            default:
                // Aucune direction définie, ne rien faire
        }
    }

    /**
     * Permet d'update l'image en fonction de la position
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(monsterImage.getImage(), monster.getPos().intj(), monster.getPos().inti(),width, height, null);
    }

    /**
     * Une fonction renouvelle endroit où est l'image
     */
    public void update(){
        move();
        repaint();
    }

    /* getteurs et setteur */
    public Monster getMonster() {
        return monster;
    }

}
