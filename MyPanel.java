package com.warCardGame;

import javax.swing.*;;import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel implements ActionListener, MouseListener {

    private int WIDTH;
    private int HEIGHT;
    private Timer timer;
    private Image bg;

    private Deck deck;
    private Hand playerHand;
    private Hand computerHand;

    private Image deckImg;
    private int deck1X;
    private int deck1Y;
    private boolean deck1Moving;

    private int deck2X;
    private int deck2Y;
    private boolean deck2Moving;

    private int playerHandX;
    private int playerHandY;

    private int computerHandX;
    private int computerHandY;

    private int deckXVel;
    private int deckYVel;
    private int deckDealYVel;

    private int shuffleCount;

    private boolean clicked;

    public MyPanel(int WIDTH, int HEIGHT) {

        this.addMouseListener(this);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.timer = new Timer(0, this);
        this.bg = new ImageIcon("img\\background.png").getImage();

        this.deck = new Deck();
        this.playerHand = new Hand();
        this.computerHand = new Hand();

        this.deckImg = new ImageIcon("img\\cards\\back.png").getImage();
        this.deck1X = (this.WIDTH / 2) - (this.deckImg.getWidth(null) / 2);
        this.deck1Y = (this.HEIGHT / 2) - (this.deckImg.getHeight(null) / 2);
        this.deck1Moving = false;

        this.deck2X = this.deck1X;
        this.deck2Y = this.deck1Y;
        this.deck2Moving = true;

        this.playerHandX = (this.WIDTH / 2) - (this.deckImg.getWidth(null) / 2);
        this.playerHandY = this.HEIGHT - this.deckImg.getHeight(null) - 50;

        this.computerHandX = (this.WIDTH / 2) - (this.deckImg.getWidth(null) / 2);
        this.computerHandY = 50;

        this.deckXVel = 10;
        this.deckYVel = -1;
        this.deckDealYVel = 15;

        this.shuffleCount = 0;

        this.clicked = false;
    }

    private void shuffle() {
        // Animates the shuffling of a deck of cards

        if (this.deck2Moving) {
            this.deck2X += this.deckXVel;
            this.deck2Y += this.deckYVel;
            if (deck2X >= (deck1X + deckImg.getWidth(null))) {
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

    private void play() {

        Card playerCard = this.playerHand.discardCard(this.playerHand.getHandSize() - 1);
        System.out.println("removed");
        this.clicked = false;
    }

    private boolean shuffling() {
        return this.shuffleCount < 10;
    }

    private boolean dealing() {
        return this.deck.getDeckSize() > 0 && !this.shuffling();
    }

    private boolean playing() {
        return !this.shuffling() && !this.dealing() && this.clicked;
    }

    private boolean mouseOverCard(MouseEvent e) {

        if (e.getX() >= this.playerHandX && e.getX() <= this.playerHandX + this.deckImg.getWidth(null) && e.getY() >= this.playerHandY && e.getY() <= this.playerHandY + this.deckImg.getHeight(null)) {
            return true;
        }
        return false;
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
            g2D.drawImage(this.deckImg, this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.deckImg, this.computerHandX, this.computerHandY, null);
        }

        if (this.playerHand.getHandSize() >= 2 && !this.dealing()) {
            g2D.drawImage(this.deckImg, this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.deckImg, this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.deckImg, this.computerHandX, this.computerHandY, null);
            g2D.drawImage(this.deckImg, this.computerHandX, this.computerHandY, null);
        }
        else if (this.playerHand.getHandSize() < 2 && this.playerHand.getHandSize() > 0 && !this.dealing()) {
            g2D.drawImage(this.deckImg, this.playerHandX, this.playerHandY, null);
            g2D.drawImage(this.deckImg, this.computerHandX, this.computerHandY, null);
        }

        if (this.deck.getDeckSize() > 0) {
            g2D.drawImage(this.deckImg, this.deck1X, this.deck1Y, null);
            g2D.drawImage(this.deckImg, this.deck2X, this.deck2Y, null);
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

        if (this.playing()) {
            this.play();
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!this.shuffling() && !this.dealing() && !this.playing()) {
                if (this.mouseOverCard(e)) {
                    this.clicked = true;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
