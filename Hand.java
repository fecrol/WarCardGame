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

    public Card discardCard() {
        return this.hand.remove(0);
    }

    public Card discardCard(int index) {
        return this.hand.remove(index);
    }

    public int getHandSize() {
        return this.hand.size();
    }

    public Card getCard() {
        /*
        Returns the last card from hand
         */

        return this.hand.get(this.hand.size() - 1);
    }

    public Card getCard(int index) {
        return this.hand.get(index);
    }
}
