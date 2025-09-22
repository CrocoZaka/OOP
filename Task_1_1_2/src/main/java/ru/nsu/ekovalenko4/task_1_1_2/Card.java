package ru.nsu.ekovalenko4.task_1_1_2;

/**
 * Represents a single playing card with a suit and a rank.
 * Used by players and the dealer to calculate scores in Blackjack.
 */
public class Card {

    /**
     * Enumeration of card suits.
     */
    public enum Suit {
        SPADES("Пики"),
        HEARTS("Червы"),
        DIAMONDS("Бубны"),
        CLUBS("Трефы");

        private final String name;

        Suit(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    /**
     * Enumeration of card ranks with associated values.
     */
    public enum Rank {
        TWO("Двойка", 2),
        THREE("Тройка", 3),
        FOUR("Четверка", 4),
        FIVE("Пятерка", 5),
        SIX("Шестерка", 6),
        SEVEN("Семерка", 7),
        EIGHT("Восьмерка", 8),
        NINE("Девятка", 9),
        TEN("Десятка", 10),
        JACK("Валет", 10),
        QUEEN("Дама", 10),
        KING("Король", 10),
        ACE("Туз", 11);

        private final String name;
        private final int value;

        Rank(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String toString() {
            return name;
        }
    }

    private final Suit suit;
    private final Rank rank;

    /**
     * Creates a new card with a given suit and rank.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit.toString();
    }

    public String getRank() {
        return rank.toString();
    }

    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public String toString() {
        return rank + " " + suit + " (" + rank.getValue() + ")";
    }
}
