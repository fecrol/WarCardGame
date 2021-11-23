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
    private Player user;
    private Player computer;

    private Image deckImg;
    private int deck1X;
    private int deck1Y;
    private boolean deck1Moving;

    private int deck2X;
    private int deck2Y;
    private boolean deck2Moving;

    private int playerHand1X;
    private int playerHand1Y;
    private int playerHand2X;
    private int playerHand2Y;

    private int computerHand1X;
    private int computerHand1Y;
    private int computerHand2X;
    private int computerHand2Y;

    private int playerDiscardX;
    private int playerDiscardY;

    private int computerDiscardX;
    private int computerDiscardY;

    private boolean playerMoving;
    private boolean computerMoving;

    private int deckXVel;
    private int deckYVel;
    private int deckDealYVel;
    private int handXVel;
    private int handYVel;

    private int shuffleCount;

    private boolean clicked;

    public MyPanel(int WIDTH, int HEIGHT) {

        this.addMouseListener(this);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.timer = new Timer(0, this);
        this.bg = new ImageIcon("img\\background.png").getImage();

        this.deck = new Deck();
        this.user = new Player();
        this.computer = new Player();

        this.deckImg = new ImageIcon("img\\cards\\back.png").getImage();
        this.deck1X = (this.WIDTH / 2) - (this.deckImg.getWidth(null) / 2);
        this.deck1Y = (this.HEIGHT / 2) - (this.deckImg.getHeight(null) / 2);
        this.deck1Moving = false;

        this.deck2X = this.deck1X;
        this.deck2Y = this.deck1Y;
        this.deck2Moving = true;

        this.playerHand1X = (this.WIDTH / 2) - (this.deckImg.getWidth(null) / 2);
        this.playerHand1Y = this.HEIGHT - this.deckImg.getHeight(null) - 50;
        this.playerHand2X = this.playerHand1X;
        this.playerHand2Y = this.playerHand1Y;

        this.computerHand1X = (this.WIDTH / 2) - (this.deckImg.getWidth(null) / 2);
        this.computerHand1Y = 50;
        this.computerHand2X = this.computerHand1X;
        this.computerHand2Y = this.computerHand1Y;

        this.playerDiscardX = (this.WIDTH / 2) + 5;
        this.playerDiscardY = this.deck1Y;

        this.computerDiscardX = (this.WIDTH / 2) - (this.deckImg.getWidth(null)) - 5;
        this.computerDiscardY = this.deck1Y;

        this.playerMoving = true;
        this.computerMoving = false;

        this.deckXVel = 10;
        this.deckYVel = -1;
        this.deckDealYVel = 20;
        this.handXVel = 2;
        this.handYVel = -5;

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
        if (this.deck2Y >= this.playerHand2Y) {
            this.user.getHand().addCard(this.deck.dealCard());
            this.deck2Y = this.deck1Y;
            this.deckDealYVel *= -1;
        }

        this.deck2Y += this.deckDealYVel;
        if (this.deck2Y <= this.computerHand2Y) {
            this.computer.getHand().addCard(this.deck.dealCard());
            this.deck2Y = this.deck1Y;
            this.deckDealYVel *= -1;
        }

    }

    private void play() {

        if (this.playerMoving) {
            if (this.playerHand2X < this.playerDiscardX) {
                this.playerHand2X += this.handXVel;
            }
            if (this.playerHand2Y > this.playerDiscardY) {
                this.playerHand2Y += this.handYVel;
            }
        }

        if (this.playerHand2X >= this.playerDiscardX && playerHand2Y <= this.playerDiscardY) {
            this.playerMoving = false;
            this.playerHand2X = this.playerHand1X;
            this.playerHand2Y = this.playerHand1Y;
            Card playerCard = this.user.getHand().discardCard(this.user.getHand().getHandSize() - 1);
            this.user.getPlayedCard().addCard(playerCard);
            this.handXVel *= -1;
            this.handYVel *= -1;
            this.computerMoving = true;
        }

        if (this.computerMoving) {
            if (this.computerHand2X > this.computerDiscardX) {
                this.computerHand2X += this.handXVel;
            }
            if (this.computerHand2Y < this.computerDiscardY) {
                this.computerHand2Y += this.handYVel;
            }
        }

        if (this.computerHand2X <= this.computerDiscardX && computerHand2Y >= this.computerDiscardY) {
            this.computerMoving = false;
            this.computerHand2X = this.computerHand1X;
            this.computerHand2Y = this.computerHand1Y;
            Card computerCard = this.computer.getHand().discardCard(this.computer.getHand().getHandSize() - 1);
            this.computer.getPlayedCard().addCard(computerCard);
            this.handXVel *= -1;
            this.handYVel *= -1;
            this.playerMoving = true;
            this.clicked = false;
        }
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

        if (e.getX() >= this.playerHand2X && e.getX() <= this.playerHand2X + this.deckImg.getWidth(null) && e.getY() >= this.playerHand2Y && e.getY() <= this.playerHand2Y + this.deckImg.getHeight(null)) {
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

        if (this.user.getPlayedCard().getHandSize() > 0) {
            g2D.drawImage(this.user.getPlayedCard().getCard().getImg(), this.playerDiscardX, this.playerDiscardY, null);
        }

        if (this.computer.getPlayedCard().getHandSize() > 0) {
            g2D.drawImage(this.computer.getPlayedCard().getCard().getImg(), this.computerDiscardX, this.computerDiscardY, null);
        }

        if (this.user.getHand().getHandSize() > 0 && this.dealing()) {
            g2D.drawImage(this.deckImg, this.playerHand2X, this.playerHand2Y, null);
        }

        if (this.computer.getHand().getHandSize() > 0 && this.dealing()) {
            g2D.drawImage(this.deckImg, this.computerHand2X, this.computerHand2Y, null);
        }

        if (this.user.getHand().getHandSize() >= 2 && !this.dealing()) {
            g2D.drawImage(this.deckImg, this.playerHand1X, this.playerHand1Y, null);
            g2D.drawImage(this.deckImg, this.playerHand2X, this.playerHand2Y, null);
        }
        else if (this.user.getHand().getHandSize() < 2 && this.user.getHand().getHandSize() > 0 && !this.dealing()) {
            g2D.drawImage(this.deckImg, this.playerHand2X, this.playerHand2Y, null);
        }

        if (this.computer.getHand().getHandSize() >= 2 && !this.dealing()) {
            g2D.drawImage(this.deckImg, this.computerHand1X, this.computerHand1Y, null);
            g2D.drawImage(this.deckImg, this.computerHand2X, this.computerHand2Y, null);
        }
        else if (this.computer.getHand().getHandSize() < 2 && this.computer.getHand().getHandSize() > 0 && !this.dealing()) {
            g2D.drawImage(this.deckImg, this.computerHand2X, this.computerHand2Y, null);
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
