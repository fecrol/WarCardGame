package com.warCardGame;

import javax.swing.*;;import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    private int WIDTH;
    private int HEIGHT;
    private Timer timer;
    private Image bg;

    private Deck deck;

    private Image deckImg1;
    private int deck1X;
    private int deck1Y;
    private boolean deck1Moving;

    private Image deckImg2;
    private int deck2X;
    private int deck2Y;
    private boolean deck2Moving;

    private int xVel;
    private int yVel;

    private int shuffleCount;

    public MyPanel(int WIDTH, int HEIGHT) {

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.bg = new ImageIcon("img\\background.png").getImage();

        this.deck = new Deck();

        this.deckImg1 = new ImageIcon("img\\cards\\back.png").getImage();
        this.deck1X = (this.WIDTH / 2) - (this.deckImg1.getWidth(null) / 2);
        this.deck1Y = (this.HEIGHT / 2) - (this.deckImg1.getHeight(null) / 2);
        this.deck1Moving = false;

        this.deckImg2 = new ImageIcon("img\\cards\\back.png").getImage();
        this.deck2X = this.deck1X;
        this.deck2Y = this.deck1Y;
        this.deck2Moving = true;

        this.shuffleCount = 0;

        this.xVel = 5;
        this.yVel = -1;

        this.timer = new Timer(0, this);
    }

    public void run() {

        this.deck.shuffle();
        this.timer.start();
    }

    private void shuffle() {
        // Animates the shuffling of a deck of cards

        if (this.deck2Moving) {
            this.deck2X += this.xVel;
            this.deck2Y += this.yVel;
            if (deck2X >= (deck1X + deckImg2.getWidth(null))) {
                this.deck2Moving = false;
                int tempX = this.deck2X;
                int tempY = this.deck2Y;
                this.deck2X = this.deck1X;
                this.deck2Y = this.deck1Y;
                this.deck1X = tempX;
                this.deck1Y = tempY;
                this.xVel *= -1;
                this.yVel *= -1;
                this.deck1Moving = true;
            }
        }

        if (this.deck1Moving) {
            this.deck1X += this.xVel;
            this.deck1Y += this.yVel;
            if (this.deck1X <= this.deck2X) {
                this.deck1Moving = false;
                this.xVel *= -1;
                this.yVel *= -1;
                this.deck2Moving = true;
                this.shuffleCount += 1;
            }
        }
    }

    private void deal() {

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        g2D.drawImage(this.bg, 0, 0, null);

        if (this.deck.getDeckSize() > 0) {
            g2D.drawImage(this.deckImg1, this.deck1X, this.deck1Y, null);
            g2D.drawImage(this.deckImg2, this.deck2X, this.deck2Y, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.shuffleCount < 7) {
            this.shuffle();
        }

        if (this.deck.getDeckSize() > 0) {
            this.deal();
        }
        repaint();
    }
}
