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
                        || output.contains("Дилер выиграл раунд"));
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
    void testDealerBustsOrWins() {
        setInput("0\n0\n");

        Blackjack game = new Blackjack(1);
        game.start();

        String output = getOutput();
        assertTrue(output.contains("Ход дилера"));
        assertTrue(output.contains("Вы выиграли раунд!")
                        || output.contains("Дилер выиграл раунд!")
                        || output.contains("Ничья!"));
    }
}
