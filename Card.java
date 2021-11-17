package com.warCardGame;

import java.awt.*;

public class Card {

    private String suit;
    private String rank;
    private int value;
    private Image img;

    public Card(String suit, String rank, int value, Image img) {

        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.img = img;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getRank() {
        return this.rank;
    }

    public int getValue() {
        return this.value;
    }

    public Image getImg() {
        return this.img;
    }
}
