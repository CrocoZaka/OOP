package ru.nsu.ekovalenko4.blackjack;

/**
 * Represents a fixed custom deck of cards for testing purposes.
 */
class FixedDeck extends Deck {
    private final Card[] fixedCards;
    private int top = 0;

    public FixedDeck(Card[] cards) {
        super(1);
        this.fixedCards = cards;
    }

    @Override
    public Card dealCard() {
        if (top >= fixedCards.length) {
            throw new IllegalStateException("Колода пуста!");
        }
        return fixedCards[top++];
    }
}
