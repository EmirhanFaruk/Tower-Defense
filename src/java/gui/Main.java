package gui;

import javax.swing.*;
import java.io.IOException;

public class Main
{
    JFrame frame;
    public Main(int width, int height) throws IOException
    {
        frame = new GameView(width, height);

    }
}
