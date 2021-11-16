package com.warCardGame;

public class Main {

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();

        int WIDTH = myFrame.getWIDTH();
        int HEIGHT = myFrame.getHEIGHT();

        MyPanel myPanel = new MyPanel(WIDTH, HEIGHT);
        myPanel.run();

        myFrame.add(myPanel);

        myFrame.setVisible(true);
    }
}
