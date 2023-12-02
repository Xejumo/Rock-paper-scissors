package com.example.rock.paper.scissors.rps;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Player user;
    private final Player computer;
    private final Random random = new Random();
    private final int roundsToWin;

    public Game(Player user, Player computer, int roundsToWin) {
        this.user = user;
        this.computer = computer;
        this.roundsToWin = roundsToWin;
    }

    public void playGame() {
        for (int roundNumber = 1; roundNumber <= roundsToWin; roundNumber++) {
            System.out.println("Round " + roundNumber + ":");
            playRound();
        }

        endGame();
    }

    public void playRound() {
        Move userMove = getUserMove();
        Move computerMove = getComputerMove();
        GameRound round = new GameRound(userMove, computerMove);

        displayMove(round);
        int roundResult = determineRoundResult(round);
        updateScore(roundResult);
        displayRoundResult(roundResult);

        System.out.println("Current score: " + user.getScore() + " - " + computer.getScore());
    }

    public void displayGameResult() {
        if (user.getScore() > computer.getScore()) {
            System.out.println("Congratulations! " + user.getName() + " wins the game!");
        } else {
            System.out.println("Computer wins the game. Better luck next time, " + user.getName() + "!");
        }
    }

    private void endGame() {
        System.out.println("Game over!");
        displayGameResult();
        System.exit(0);
    }

    private Move getUserMove() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your move:");
        System.out.println("1 - Rock");
        System.out.println("2 - Paper");
        System.out.println("3 - Scissors");
        System.out.println("4 - Lizard");
        System.out.println("5 - Spock");

        int userChoice;

        while (true) {
            try {
                System.out.print("Enter the number corresponding to your move: ");
                userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice >= 1 && userChoice <= 5) {
                    break;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }

        return switch (userChoice) {
            case 1 -> Move.ROCK;
            case 2 -> Move.PAPER;
            case 3 -> Move.SCISSORS;
            case 4 -> Move.LIZARD;
            case 5 -> Move.SPOCK;
            default -> throw new IllegalStateException("Unexpected value: " + userChoice);
        };
    }

    private Move getComputerMove() {
        int moveIndex = random.nextInt(Move.values().length);
        return Move.values()[moveIndex];
    }

    private void displayMove(GameRound round) {
        System.out.println(user.getName() + " chose " + round.getUserMove());
        System.out.println("Computer chose: " + round.getComputerMove());
    }

    private int determineRoundResult(GameRound round) {
        Move userMove = round.getUserMove();
        Move computerMove = round.getComputerMove();

        if (userMove == computerMove) {
            return 0;
        } else if (
                (userMove == Move.ROCK && (computerMove == Move.SCISSORS || computerMove == Move.LIZARD)) ||
                        (userMove == Move.PAPER && (computerMove == Move.ROCK || computerMove == Move.SPOCK)) ||
                        (userMove == Move.SCISSORS && (computerMove == Move.PAPER || computerMove == Move.LIZARD)) ||
                        (userMove == Move.LIZARD && (computerMove == Move.PAPER || computerMove == Move.SPOCK)) ||
                        (userMove == Move.SPOCK && (computerMove == Move.ROCK || computerMove == Move.SCISSORS))
        ) {
            return 1;
        } else {
            return -1;
        }
    }

    private void updateScore(int roundResult) {
        if (roundResult == 1) {
            user.incrementScore();
        } else if (roundResult == -1) {
            computer.incrementScore();
        }
    }

    private void displayRoundResult(int roundResult) {
        if (roundResult == 0) {
            System.out.println("It's a draw!");
            System.out.println("Current score: " + user.getScore() + " - " + computer.getScore());
            playRound();
        } else if (roundResult == 1) {
            System.out.println(user.getName() + " wins this round!");
        } else {
            System.out.println("Computer wins this round!");
        }
    }

}