package com.warCardGame;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private int WIDTH;
    private int HEIGHT;
    private String TITLE;

    public MyFrame() {
        this.WIDTH = 500;
        this.HEIGHT = 500;
        this.TITLE = "WAR (Java Edition)";

        this.setSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setTitle(this.TITLE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getWIDTH() {
        return this.WIDTH;
    }

    public int getHEIGHT() {
        return this.HEIGHT;
    }
}
