package com.warCardGame;

public class Main {

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        MyPanel myPanel = new MyPanel();

        myFrame.add(myPanel);

        myFrame.setVisible(true);
    }
}
