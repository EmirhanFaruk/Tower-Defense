package gui;

import gui.GameView;

import javax.swing.*;
import java.io.IOException;

public class Main
{
    JFrame frame;
    public Main(int width, int height) throws Exception {
        frame = new GameView(width, height);
    }
}
