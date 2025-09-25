package ru.nsu.ekovalenko4.blackjack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Blackjack console game.
 */
public class BlackjackTest {

    private ByteArrayOutputStream outContent;

    /**
     * Sets up a stream to capture console output before each test.
     */
    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    private void setInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return outContent.toString();
    }

    @Test
    void testPlayerExitsImmediately() {
        setInput("0\n");
        Blackjack game = new Blackjack(1);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Раунд 1"));
        assertTrue(output.contains("Хотите сыграть еще раунд?"));
    }

    @Test
    void testPlayerHitsThenStands() {
        setInput("1\n0\n0\n");

        Blackjack game = new Blackjack(1);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Ваш ход"));
        assertTrue(output.contains("Вы открыли карту"));
        assertTrue(output.contains("Ход дилера")
                        || output.contains("Вы выиграли раунд")
                        || output.contains("Вы проиграли раунд")
                        || output.contains("Ничья"));
    }

    @Test
    void testPlayerBusts() {
        setInput("1\n1\n1\n0\n0\n");

        Blackjack game = new Blackjack(1);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Перебор!")
                || output.contains("У вас Блэкджек!")
                || output.contains("Вы открыли карту"));
    }

    @Test
    void testFixedDeck_PlayerWins() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.SPADES, Card.Rank.EIGHT),
            new Card(Card.Suit.CLUBS, Card.Rank.KING),
            new Card(Card.Suit.HEARTS, Card.Rank.NINE),
            new Card(Card.Suit.DIAMONDS, Card.Rank.TEN),
            new Card(Card.Suit.SPADES, Card.Rank.TWO)
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("1\n0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Вы выиграли раунд!"));
    }

    @Test
    void testFixedDeck_PlayerBlackjack() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.SPADES, Card.Rank.ACE),
            new Card(Card.Suit.CLUBS, Card.Rank.KING),
            new Card(Card.Suit.HEARTS, Card.Rank.NINE),
            new Card(Card.Suit.DIAMONDS, Card.Rank.SEVEN)
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("0\n0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("У вас Блэкджек!"));
    }

    @Test
    void testFixedDeck_PlayerBusts() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.HEARTS, Card.Rank.KING),
            new Card(Card.Suit.CLUBS, Card.Rank.QUEEN),
            new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE),
            new Card(Card.Suit.SPADES, Card.Rank.TWO),
            new Card(Card.Suit.HEARTS, Card.Rank.THREE)
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("1\n0\n0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Перебор! Вы проиграли раунд."));
    }

    @Test
    void testFixedDeck_DealerWins() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.SPADES, Card.Rank.NINE),
            new Card(Card.Suit.CLUBS, Card.Rank.KING),
            new Card(Card.Suit.HEARTS, Card.Rank.SIX),
            new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN),
            new Card(Card.Suit.SPADES, Card.Rank.FOUR)
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("0\n0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Дилер выиграл раунд!"));
    }

    @Test
    void testFixedDeck_DealerBlackjack() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.HEARTS, Card.Rank.NINE),
            new Card(Card.Suit.CLUBS, Card.Rank.EIGHT),
            new Card(Card.Suit.DIAMONDS, Card.Rank.TEN),
            new Card(Card.Suit.SPADES, Card.Rank.ACE)
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("У дилера Блэкджек!"));
    }

    @Test
    void testFixedDeck_DealerBusts() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.HEARTS, Card.Rank.NINE),
            new Card(Card.Suit.CLUBS, Card.Rank.EIGHT),
            new Card(Card.Suit.DIAMONDS, Card.Rank.SIX),
            new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
            new Card(Card.Suit.HEARTS, Card.Rank.KING)
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("0\n0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("У дилера перебор!"));
    }

    @Test
    void testFixedDeck_Tie() {
        Card[] fixedCards = new Card[] {
            new Card(Card.Suit.HEARTS, Card.Rank.NINE),
            new Card(Card.Suit.CLUBS, Card.Rank.EIGHT),
            new Card(Card.Suit.DIAMONDS, Card.Rank.TEN),
            new Card(Card.Suit.SPADES, Card.Rank.SEVEN),
        };
        FixedDeck deck = new FixedDeck(fixedCards);

        setInput("0\n0\n");
        Blackjack game = new Blackjack(deck);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Ничья!"));
    }

    @Test
    void testManyDecks() {
        setInput("0\n");
        Blackjack game = new Blackjack(100000);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Раунд 1"));
        assertTrue(output.contains("Хотите сыграть еще раунд?"));
    }
}
