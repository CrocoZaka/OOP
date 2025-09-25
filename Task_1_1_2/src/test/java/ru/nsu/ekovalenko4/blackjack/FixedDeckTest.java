package ru.nsu.ekovalenko4.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FixedDeckTest {
    @Test
    void testFixedDeck() {
        Card[] fixedCards = new Card[] {
                new Card(Card.Suit.HEARTS, Card.Rank.NINE),
                new Card(Card.Suit.CLUBS, Card.Rank.EIGHT),
                new Card(Card.Suit.DIAMONDS, Card.Rank.TEN)
        };
        FixedDeck deck = new FixedDeck(fixedCards);
        for (int i = 0; i < 3; i++) {
            deck.dealCard();
        }
        assertThrows(IllegalStateException.class, () -> deck.dealCard());
    }
}