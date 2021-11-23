package com.warCardGame;

public class Player {

    private Hand hand;
    private Hand playedCard;
    private Hand wonCards;

    public Player() {

        this.hand = new Hand();
        this.playedCard = new Hand();
        this.wonCards = new Hand();
    }

    public Hand getHand() {
        return this.hand;
    }

    public Hand getPlayedCard() {
        return this.playedCard;
    }

    public Hand getWonCards() {
        return this.wonCards;
    }
}
