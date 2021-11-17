package com.warCardGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private String[] suits = {"clubs", "diamonds", "hearts", "spades"};
    private String[] ranks = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
    private int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 , 14};
    private ArrayList<Card> deck = new ArrayList<>();
    private Random rand = new Random();

    public Deck() {
        for (int i=0; i<this.suits.length; i++) {
            for (int j=0; j<this.ranks.length; j++) {
                Image cardImg = new ImageIcon("img\\cards\\" + this.ranks[j] + " of " + this.suits[i] + ".png").getImage();
                Card card = new Card(suits[i], ranks[j], values[j], cardImg);
                this.deck.add(card);
            }
        }
    }

    public Card dealCard() {
        /*
         Deals a single card from the deck by removing the last card
         */

        return this.deck.remove(this.deck.size() - 1);
    }

    public void shuffle() {
        /*
         Shuffles the deck using the Fisher-Yates algorithm
         */

        for (int i=this.deck.size() - 1; i>0; i--) {

            // Generate a random integer from 0 to length of this.deck
            int j = rand.nextInt(i + 1);

            // Swap last card at i with randomly selected card
            Card temp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j, temp);
        }
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public int getDeckSize() {
        return this.deck.size();
    }

    public Card getCard(int index) {
        return this.deck.get(index);
    }
}
