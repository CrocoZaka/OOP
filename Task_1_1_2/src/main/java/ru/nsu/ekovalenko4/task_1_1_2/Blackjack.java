package ru.nsu.ekovalenko4.task_1_1_2;

import java.util.Scanner;

/**
 * Represents the console-based Blackjack game.
 * Manages game rounds, handles user interaction via console,
 * deals cards to the player and the dealer, and determines the winner.
 */
public class Blackjack {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    private int playerScore = 0;
    private int dealerScore = 0;
    private int round = 1;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new Blackjack game with a given number of decks.
     */
    public Blackjack(int decksCount) {
        deck = new Deck(decksCount);
        player = new Player();
        dealer = new Player();
    }

    /**
     * Starts the game loop.
     * Repeats rounds until the player chooses to stop.
     */
    public void start() {
        while (true) {
            playRound();
            System.out.println("Хотите сыграть еще раунд? (1 - да, 0 - нет)");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            round++;
            deck.shuffle();
        }
    }

    private void playRound() {
        player.clearHand();
        dealer.clearHand();

        System.out.println("Раунд " + round);
        System.out.println("Дилер раздал карты");

        player.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());

        System.out.println("\tВаши карты: " + player.showHand(false));
        System.out.println("\tКарты дилера: " + dealer.showHand(true));
        System.out.println();

        if (player.isBlackjack()) {
            System.out.println("У вас Блэкджек! Вы выиграли раунд.");
            playerScore++;
            showScore();
            return;
        }
        if (dealer.isBlackjack()) {
            System.out.println("У дилера Блэкджек! Вы проиграли раунд.");
            dealerScore++;
            showScore();
            return;
        }

        System.out.println("Ваш ход\n-------");
        while (true) {
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
            int choice = scanner.nextInt();
            if (choice == 1) {
                Card card = deck.dealCard();
                System.out.println("Вы открыли карту " + card);
                player.addCard(card);
                System.out.println("\tВаши карты: "  + player.showHand(false));
                System.out.println("\tКарты дилера: " + dealer.showHand(true));
                System.out.println();

                if (player.isBust()) {
                    System.out.print("Перебор! Вы проиграли раунд. ");
                    dealerScore++;
                    showScore();
                    return;
                }
            } else {
                break;
            }
        }

        System.out.println("Ход дилера\n-------");
        System.out.println("Дилер открывает закрытую карту " + dealer.getCard(1));
        System.out.println("\tВаши карты: " + player.showHand(false));
        System.out.println("\tКарты дилера: " + dealer.showHand(false));
        System.out.println();

        while (dealer.getScore() < 17) {
            Card card = deck.dealCard();
            System.out.println("Дилер открывает карту " + card);
            dealer.addCard(card);
            System.out.println("\tВаши карты: " + player.showHand(false));
            System.out.println("\tКарты дилера: " + dealer.showHand(false));
            System.out.println();
        }

        if (dealer.isBust()) {
            System.out.print("У дилера перебор! Вы выиграли раунд. ");
            playerScore++;
        } else {
            if (player.getScore() > dealer.getScore()) {
                System.out.print("Вы выиграли раунд! ");
                playerScore++;
            } else if (player.getScore() < dealer.getScore()) {
                System.out.print("Дилер выиграл раунд! ");
                dealerScore++;
            } else {
                System.out.print("Ничья! ");
            }
        }
        showScore();
    }

    /**
     * Displays the current score of the match.
     */
    private void showScore() {
        System.out.print("Счет " + playerScore + ":" + dealerScore);
        if (playerScore > dealerScore) {
            System.out.print(" в вашу пользу.");
        }
        if (playerScore < dealerScore) {
            System.out.print(" в пользу дилера.");
        }
        System.out.println("\n");
    }

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Блэкджек! Введите количество колод...");
        int n = Integer.parseInt(System.console().readLine());
        Blackjack game = new Blackjack(n);
        game.start();
    }
}
