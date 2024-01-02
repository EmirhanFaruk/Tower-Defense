package gui;

import gui.GameView;

import javax.swing.*;
import java.io.IOException;

public class Main
{
    JFrame frame;
    public Main(int width, int height)
    {
        frame = new GameView(width, height);
    }
}
