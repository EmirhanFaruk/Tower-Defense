package com.gui;

import com.gui.GameView;

import javax.swing.*;
import java.io.IOException;

public class Main
{
    JFrame frame;
    public Main() throws Exception {
        frame = new GameView(800, 500);
    }
}
