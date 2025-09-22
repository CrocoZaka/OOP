package ru.nsu.ekovalenko4.Task_1_1_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {
    @Test
    void testHandAndScore() {
        Player player = new Player();
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.FIVE));
        player.addCard(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));

        Card first = player.getCard(0);
        Card second = player.getCard(1);
        assertEquals(Card.Suit.SPADES.toString(), first.getSuit());
        assertEquals(Card.Rank.FIVE.toString(), first.getRank());
        assertEquals(Card.Suit.HEARTS.toString(), second.getSuit());
        assertEquals(Card.Rank.SEVEN.toString(), second.getRank());

        assertEquals(12, player.getScore());
    }

    @Test
    void testAceShenanigans() {
        Player player = new Player();
        player.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
        player.addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.TWO));
        assertEquals(13, player.getScore());
    }

    @Test
    void testBlackjack() {
        Player player = new Player();
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.ACE));
        assertFalse(player.isBlackjack());
        player.addCard(new Card(Card.Suit.HEARTS, Card.Rank.KING));
        assertTrue(player.isBlackjack());
    }

    @Test
    void testBust() {
        Player player = new Player();
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.KING));
        player.addCard(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));
        assertFalse(player.isBust());
        player.addCard(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        assertTrue(player.isBust());
    }

    @Test
    void testClearHand() {
        Player player = new Player();
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.QUEEN));
        player.clearHand();
        assertEquals(0, player.getScore());
    }

    @Test
    void testShowHand() {
        Player player = new Player();
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.QUEEN));
        player.addCard(new Card(Card.Suit.HEARTS, Card.Rank.THREE));
        assertEquals("[Дама Пики (10), Тройка Червы (3)] => 13", player.showHand(false));
        assertEquals("[Дама Пики (10), <закрытая карта>]", player.showHand(true));
    }
}
