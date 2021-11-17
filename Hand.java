package com.warCardGame;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() {

        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void discardCard(Card card) {
        this.hand.remove(card);
    }

    public int getHandSize() {
        return this.hand.size();
    }

    public Card getCard(int index) {
        return this.hand.get(index);
    }
}
