package ru.nsu.ekovalenko4.blackjack;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void testDeckSize() {
        Deck deck = new Deck(2);
        for (int i = 0; i < 104; i++) {
            assertNotNull(deck.dealCard());
        }
    }

    @Test
    void testDealingAllCardsExhaustsDeck() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            deck.dealCard();
        }
        assertThrows(IllegalStateException.class, () -> deck.dealCard());
    }

    @Test
    void testShuffleResetsDeck() {
        Deck deck = new Deck(1);
        deck.dealCard();
        deck.dealCard();
        deck.shuffle();
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 52; i++) {
                deck.dealCard();
            }
        });
    }
}
