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

    public Card discardCard(int index) {
        return this.hand.remove(index);
    }

    public int getHandSize() {
        return this.hand.size();
    }

    public Card getCard(int index) {
        return this.hand.get(index);
    }
}
