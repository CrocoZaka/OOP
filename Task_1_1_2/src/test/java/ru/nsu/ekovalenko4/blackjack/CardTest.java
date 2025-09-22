package ru.nsu.ekovalenko4.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    public void testCreateCard() {
        Card card = new Card(Card.Suit.SPADES, Card.Rank.QUEEN);
        assertNotNull(card);
        assertEquals(Card.Suit.SPADES.toString(), card.getSuit());
        assertEquals(Card.Rank.QUEEN.getValue(), card.getValue());
        assertEquals(Card.Rank.QUEEN.toString(), card.getRank());
    }

    @Test
    public void testDisplay() {
        Card card = new Card(Card.Suit.HEARTS, Card.Rank.KING);
        assertEquals("Король Червы (10)", card.toString());
    }

    @Test
    public void testIsAce() {
        Card card = new Card(Card.Suit.DIAMONDS, Card.Rank.ACE);
        assertTrue(card.isAce());
        Card card2 = new Card(Card.Suit.CLUBS, Card.Rank.JACK);
        assertFalse(card2.isAce());
    }
}