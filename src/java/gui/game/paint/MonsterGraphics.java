package gui.game.paint;
import gui.game.GameScreen;
import model.monster.Monster;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MonsterGraphics extends JComponent {
    private static final String path = System.getProperty("user.dir");
    private static final String s = findSlash(path);
    private static final ImageIcon[] monsterBlue = {loadImage("MonsterBlue1.gif"), loadImage("MonsterBlue2.gif"), loadImage("MonsterBlue3.gif")};
    private static final ImageIcon[] monsterRed = {loadImage("MonsterRed1.gif"), loadImage("MonsterRed2.gif"), loadImage("MonsterRed3.gif")};
    private static final ImageIcon[] monsterGreen = {loadImage("MonsterGreen1.gif"), loadImage("MonsterGreen2.gif"), loadImage("MonsterGreen3.gif")};
    private static final ImageIcon[] monsterGray = {loadImage("MonsterGray1.gif"), loadImage("MonsterGray2.gif"), loadImage("MonsterGray3.gif")};
    private int width, height;
    private Monster monster;
    private ImageIcon monsterImage;

    public MonsterGraphics(Monster monster) {
        this.monster = monster;
        this.monsterImage = chooseMonsterIcon();
        this.height = GameScreen.getTile_height();
        this.width = GameScreen.getTile_width();
    }

    private ImageIcon chooseMonsterIcon() {
        int pos = this.monster.getNiveau();
        switch (this.monster.getResistance()) {
            case "NONE":
                return monsterGray[pos];
            case "BULLET":
                return monsterBlue[pos];
            case "ARROW":
                return monsterGreen[pos];
            case "FIRE":
                return monsterRed[pos];
            default:
                return null;
        }
    }

    private static ImageIcon loadImage(String fileName) {
        try {
            String imagePath = path + s + "src" + s + "resources" + s + "images" + s + "Monster" + s + fileName;
            return new ImageIcon(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String findSlash(String p) {
        for (int i = 0; i < p.length(); i++) {
            switch (p.charAt(i)) {
                case '/':
                    return "/";
                case '\\':
                    return "\\";
            }
        }
        return "/";
    }

    public void move() {
        double distance = 100;
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

    public void paint(Graphics2D g) {
        if (monsterImage != null) {
            g.drawImage(monsterImage.getImage(), monster.getPos().intj(), monster.getPos().inti(), width, height, null);
        }
    }

    public void update(Graphics2D g) {
        move();
        repaint();
    }

    public Monster getMonster() {
        return monster;
    }
}
