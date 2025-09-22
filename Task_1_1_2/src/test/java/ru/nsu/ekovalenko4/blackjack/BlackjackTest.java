package ru.nsu.ekovalenko4.blackjack;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    void testBlackjackInitialization() {
        Blackjack game = new Blackjack(1);
        assertNotNull(game);
    }

}
