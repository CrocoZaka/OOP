package ru.nsu.ekovalenko4.blackjack;

import java.util.Random;

/**
 * Represents a deck of cards used in Blackjack.
 * Can consist of one or more 52-card decks combined.
 */
class Deck {
    private final Card[] cards;
    private int top;
    private final Random rand = new Random();

    public Deck(int decksCount) {
        cards = new Card[52 * decksCount];
        int i = 0;
        for (int d = 0; d < decksCount; d++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards[i++] = new Card(suit, rank);
                }
            }
        }
        top = cards.length - 1;
        shuffle();
    }

    public void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int j = rand.nextInt(cards.length);
            Card tmp = cards[i];
            cards[i] = cards[j];
            cards[j] = tmp;
        }
        top = cards.length - 1;
    }

    public Card dealCard() {
        if (top < 0) {
            throw new IllegalStateException("Колода пуста!");
        }
        return cards[top--];
    }
}

