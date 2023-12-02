package com.example.rock.paper.scissors;

import com.example.rock.paper.scissors.rps.Game;
import com.example.rock.paper.scissors.rps.Player;

import java.util.Scanner;

public class RockPaperScissorsApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rock-Paper-Scissors-Lizard-Spock Game!");

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter the number of rounds to win: ");
        int roundsToWin = scanner.nextInt();

        Player user = new Player(playerName);
        Player computer = new Player("Computer");

        Game game = new Game(user, computer, roundsToWin);

        System.out.println("Let the game begin!");

        game.playGame();

        game.displayGameResult();
    }
}