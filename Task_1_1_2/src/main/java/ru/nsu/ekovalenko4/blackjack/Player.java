package ru.nsu.ekovalenko4.blackjack;

/**
 * Represents a player in Blackjack (including the dealer).
 * Stores the hand of cards, calculates the score,
 * and checks for blackjack or bust.
 * Also provides methods to display the hand as a string.
 */
public class Player {
    private static final int BLACKJACK_THRESHOLD = 21;
    private final Card[] hand = new Card[12];
    private int handSize = 0;

    public void addCard(Card card) {
        hand[handSize++] = card;
    }

    public Card getCard(int index) {
        return hand[index];
    }

    public int getScore() {
        int total = 0;
        int aces = 0;
        for (int i = 0; i < handSize; i++) {
            total += hand[i].getValue();
            if (hand[i].isAce()) {
                aces++;
            }
        }
        if (total > BLACKJACK_THRESHOLD) {
            total -= aces * 10;
        }
        return total;
    }

    public boolean isBlackjack() {
        return handSize == 2 && getScore() == BLACKJACK_THRESHOLD;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_THRESHOLD;
    }

    public String showHand(boolean hideFirst) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < handSize; i++) {
            if (i == 1 && hideFirst) {
                sb.append("<закрытая карта>");
            } else {
                sb.append(hand[i]);
            }
            if (i < handSize - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        if (!hideFirst) {
            sb.append(" => ").append(getScore());
        }
        return sb.toString();
    }

    public void clearHand() {
        handSize = 0;
    }

}
