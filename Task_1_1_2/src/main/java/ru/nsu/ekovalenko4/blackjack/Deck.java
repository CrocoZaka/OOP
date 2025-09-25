package ru.nsu.ekovalenko4.blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a deck of cards used in Blackjack.
 * Can consist of one or more 52-card decks combined.
 */
public class Deck {
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
        Collections.shuffle(Arrays.asList(cards));
        top = cards.length - 1;
    }

    public Card dealCard() {
        if (top < 0) {
            throw new IllegalStateException("Колода пуста!");
        }
        return cards[top--];
    }
}

/**
 * Represents a fixed custom deck of cards for testing purposes.
 */
class FixedDeck extends Deck {
    private final Card[] fixedCards;
    private int top = 0;

    public FixedDeck(Card[] cards) {
        super(1);
        this.fixedCards = cards;
    }

    @Override
    public Card dealCard() {
        return fixedCards[top++];
    }
}