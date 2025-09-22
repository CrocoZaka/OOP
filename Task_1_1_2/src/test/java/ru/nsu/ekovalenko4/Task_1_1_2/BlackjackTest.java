package ru.nsu.ekovalenko4.Task_1_1_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BlackjackTest {

    @Test
    void testBlackjackInitialization() {
        Blackjack game = new Blackjack(1);
        assertNotNull(game);
    }

}
