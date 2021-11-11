package com.warCardGame;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    final int WIDTH;
    final int HEIGHT;
    final String TITLE;

    public MyFrame() {
        this.WIDTH = 800;
        this.HEIGHT = 600;
        this.TITLE = "WAR (Java Edition)";

        this.setSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setTitle(this.TITLE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getWIDTH() {

        return WIDTH;
    }

    public int getHEIGHT() {

        return HEIGHT;
    }
}
