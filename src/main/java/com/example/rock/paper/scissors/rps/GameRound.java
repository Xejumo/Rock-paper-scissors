package com.example.rock.paper.scissors.rps;

public class GameRound {
    private final Move userMove;
    private final Move computerMove;

    public GameRound(Move userMove, Move computerMove) {
        this.userMove = userMove;
        this.computerMove = computerMove;
    }

    public Move getUserMove() {
        return userMove;
    }

    public Move getComputerMove() {
        return computerMove;
    }
}
