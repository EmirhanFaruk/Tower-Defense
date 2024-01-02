package gui.mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.io.File;
import java.util.Objects;

public class Play extends JPanel
{

    private String difficulty = "EASY";

    private JLabel level_name_tag;
    private JLabel difficulty_tag;

    public Play(int width, int height)
    {
        makePlay();
    }

    @Override
    public void setSize(int width, int height)
    {
        makePlay();
    }

    private void makePlay()
    {
        setLayout(new BorderLayout());

        add(makeLevelPanel());
        add(makeConfigPanel(), BorderLayout.EAST);

    }

    /*
     * START OF LEVEL LIST FUNCTIONS
     */

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

    private int getMapCount()
    {
        String path = System.getProperty("user.dir");
        String s = findSlash(path);

        File directory = new File(path + s + "src" + s + "resources" + s + "maps");
        int map_count = 0;
        if(directory.list() != null)
        {
            map_count = directory.list().length;
        }
        return map_count;
    }

    private String[] getMapNames()
    {
        String path = System.getProperty("user.dir");
        String s = findSlash(path);

        File directory = new File(path + s + "src" + s + "resources" + s + "maps");
        if(directory.list() != null)
        {
            String[] res = directory.list();
            for(int i = 0; i < res.length; i++)
            {
                res[i] = res[i].substring(0, res[i].length()-4);
            }
            return res;
        }
        return new String[]{};
    }

    private JButton makeSingleLevelPanel(String level_name)
    {
        JButton res = new JButton(level_name);
        res.setBackground(Color.BLACK);
        res.setForeground(Color.GRAY);
        res.addActionListener(
                new ActionListener()
            {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                level_name_tag.setText(level_name);
            }
        });
        return res;
    }

    private JPanel makeLevelPanel()
    {
        JPanel res = new JPanel();
        int map_count = getMapCount();
        if(map_count > 0)
        {
            res.setLayout(new GridLayout(map_count, 1));
            for (String level_name : getMapNames())
            {
                res.add(makeSingleLevelPanel(level_name));
            }
        }
        return res;
    }


    /*
     * END OF LEVEL LIST FUNCTIONS
     */



    /*
     * START OF DIFFICULTY PANEL FUNCTIONS
     */

    private JPanel makeDifficultyPanel()
    {
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(5, 1));
        String[] diff_list = {"EASY", "NORMAL", "HARD"};
        for(String diff : diff_list)
        {
            JButton button = new JButton(diff);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.GRAY);
            button.addActionListener(
                    new ActionListener()
                {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    difficulty = diff;
                    difficulty_tag.setText(diff);
                }
            });
            res.add(button);
        }

        JPanel lnt_capsule = new JPanel();
        lnt_capsule.setBackground(Color.BLACK);
        lnt_capsule.setForeground(Color.GRAY);

        level_name_tag = new JLabel("");
        level_name_tag.setHorizontalTextPosition(SwingConstants.CENTER);
        level_name_tag.setVerticalTextPosition(SwingConstants.CENTER);
        level_name_tag.setBackground(Color.BLACK);
        level_name_tag.setForeground(Color.GRAY);

        lnt_capsule.add(level_name_tag);
        res.add(lnt_capsule);


        JPanel dt_capsule = new JPanel();
        dt_capsule.setBackground(Color.BLACK);
        dt_capsule.setForeground(Color.GRAY);

        difficulty_tag = new JLabel(difficulty);
        difficulty_tag.setHorizontalTextPosition(SwingConstants.CENTER);
        difficulty_tag.setVerticalTextPosition(SwingConstants.CENTER);
        difficulty_tag.setBackground(Color.BLACK);
        difficulty_tag.setForeground(Color.GRAY);

        dt_capsule.add(difficulty_tag);
        res.add(dt_capsule);

        return res;
    }

    private JPanel makePlayButton()
    {
        JPanel res = new JPanel();
        res.setBackground(Color.BLACK);
        res.setForeground(Color.GRAY);

        JButton play_button = new JButton("GO!");
        play_button.setBackground(Color.BLACK);
        play_button.setForeground(Color.GRAY);

        play_button.addActionListener(
                new ActionListener()
            {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        res.add(play_button);

        return res;
    }

    private JPanel makeConfigPanel()
    {
        JPanel res = new JPanel();
        res.setLayout(new BorderLayout());
        res.add(makeDifficultyPanel(), BorderLayout.CENTER);

        res.add(makePlayButton(), BorderLayout.SOUTH);

        return res;
    }

}
