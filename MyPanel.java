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
    private Hand playerHand;
    private Hand computerHand;

    private Image deckImg1;
    private int deck1X;
    private int deck1Y;
    private boolean deck1Moving;

    private Image deckImg2;
    private int deck2X;
    private int deck2Y;
    private boolean deck2Moving;

    private Image playerHandImg;
    private int playerHandX;
    private int playerHandY;

    private Image computerHandImg;
    private int computerHandX;
    private int computerHandY;

    private int deckXVel;
    private int deckYVel;
    private int deckDealYVel;

    private int shuffleCount;

    public MyPanel(int WIDTH, int HEIGHT) {

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.timer = new Timer(0, this);
        this.bg = new ImageIcon("img\\background.png").getImage();

        this.deck = new Deck();
        this.playerHand = new Hand();
        this.computerHand = new Hand();

        this.deckImg1 = new ImageIcon("img\\cards\\back.png").getImage();
        this.deck1X = (this.WIDTH / 2) - (this.deckImg1.getWidth(null) / 2);
        this.deck1Y = (this.HEIGHT / 2) - (this.deckImg1.getHeight(null) / 2);
        this.deck1Moving = false;

        this.deckImg2 = new ImageIcon("img\\cards\\back.png").getImage();
        this.deck2X = this.deck1X;
        this.deck2Y = this.deck1Y;
        this.deck2Moving = true;

        this.playerHandImg = new ImageIcon("img\\cards\\back.png").getImage();
        this.playerHandX = (this.WIDTH / 2) - (this.deckImg1.getWidth(null) / 2);
        this.playerHandY = this.HEIGHT - this.playerHandImg.getHeight(null) - 50;

        this.computerHandImg = new ImageIcon("img\\cards\\back.png").getImage();
        this.computerHandX = (this.WIDTH / 2) - (this.deckImg1.getWidth(null) / 2);
        this.computerHandY = 50;

        this.shuffleCount = 0;

        this.deckXVel = 5;
        this.deckYVel = -1;
        this.deckDealYVel = 9;
    }

    private void shuffle() {
        // Animates the shuffling of a deck of cards

        if (this.deck2Moving) {
            this.deck2X += this.deckXVel;
            this.deck2Y += this.deckYVel;
            if (deck2X >= (deck1X + deckImg2.getWidth(null))) {
                this.deck2Moving = false;
                int tempX = this.deck2X;
                int tempY = this.deck2Y;
                this.deck2X = this.deck1X;
                this.deck2Y = this.deck1Y;
                this.deck1X = tempX;
                this.deck1Y = tempY;
                this.deckXVel *= -1;
                this.deckYVel *= -1;
                this.deck1Moving = true;
            }
        }

        if (this.deck1Moving) {
            this.deck1X += this.deckXVel;
            this.deck1Y += this.deckYVel;
            if (this.deck1X <= this.deck2X) {
                this.deck1Moving = false;
                this.deckXVel *= -1;
                this.deckYVel *= -1;
                this.deck2Moving = true;
                this.shuffleCount += 1;
            }
        }
    }

    private void deal() {
        // Animates the deal of cards from the deck and adds cards to player and computer hands

        this.deck2Y += this.deckDealYVel;
        if (this.deck2Y >= this.playerHandY) {
            this.playerHand.addCard(this.deck.dealCard());
            this.deck2Y = this.deck1Y;
            this.deckDealYVel *= -1;
        }

        this.deck2Y += this.deckDealYVel;
        if (this.deck2Y <= this.computerHandY) {
            this.computerHand.addCard(this.deck.dealCard());
            this.deck2Y = this.deck1Y;
            this.deckDealYVel *= -1;
        }

    }

    private boolean shuffling() {
        return this.shuffleCount < 7;
    }

    private boolean dealing() {
        return this.deck.getDeckSize() > 0 && this.shuffleCount >= 7;
    }

    public void run() {
        this.deck.shuffle();
        this.timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        g2D.drawImage(this.bg, 0, 0, null);

        if (this.playerHand.getHandSize() > 0 && this.dealing()) {
            g2D.drawImage(this.playerHandImg, this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.computerHandImg, this.computerHandX, this.computerHandY, null);
        }

        if (this.playerHand.getHandSize() >= 2 && !this.dealing()) {
            g2D.drawImage(this.playerHand.getCard(this.playerHand.getHandSize() - 1).getImg(), this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.playerHand.getCard(this.playerHand.getHandSize() - 2).getImg(), this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.computerHandImg, this.computerHandX, this.computerHandY, null);
            g2D.drawImage(this.computerHandImg, this.computerHandX, this.computerHandY, null);
        }

        if (this.deck.getDeckSize() > 0) {
            g2D.drawImage(this.deckImg1, this.deck1X, this.deck1Y, null);
            g2D.drawImage(this.deckImg2, this.deck2X, this.deck2Y, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.shuffling()) {
            this.shuffle();
        }

        if (this.dealing()) {
            this.deal();
        }
        repaint();
    }
}
