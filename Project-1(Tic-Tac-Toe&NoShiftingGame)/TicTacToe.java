package com.game.tictactoegame;

import com.game.InterfGame;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;//random no. generation
import com.consoleFunctions.Console;

public class TicTacToe implements com.game.InterfGame {

    private static TicTacToe internalobj; // to implement singleton class

    private char[] boardArr = new char[9];

    private String player1, player2;

    private int turn = 0;

    private Scanner sc = new Scanner(System.in);

    private TicTacToe() { // constructor
        super();
    }// only be called by its member and so no external object is created.

    public static TicTacToe getTTTobj() {
        if (internalobj == null) {
            internalobj = new TicTacToe();
        } else {
            // flushing buffer (after playing once)
            internalobj.sc.nextLine();
        }
        internalobj.initializeGame();

        return internalobj;
    }

    private void initializeGame() {
        int x = 48; // 48 == (0 digit) in unicode

        // initializing the board:
        for (int i = 0; i < 9; i++) {

            boardArr[i] = (char) x++;

        }

        // sc.nextLine();

        ruleSet();

        Console.clearScr();
        System.out.println("\t\t\tTIC TAC TOE");

        System.out.println("\n\nenter player names");
        System.out.print(" player 1: x:");
        player1 = sc.nextLine();
        System.out.print(" player 2: o:");
        player2 = sc.nextLine();

        turn = toss();

    }

    private int toss() {
        Random ra = new Random();

        return ra.nextInt(2); // generate no. 0 or 1
    }

    public void play() {

        int index = 0, moves = 0;
        // move used for ending the match if no one wins the game

        while (!winComparison() && moves < 9) // not win or game not ended
        {
            Console.clearScr();

            boardDisplay();

            if (turn == 0) {
                System.out.print("\n\n\t\t" + player1 + "'s turn  X :");

                try {
                    index = sc.nextInt();
                } catch (NoSuchElementException e) // InputMismatchException (direct child)
                {
                    e.getMessage();
                    System.out.println("please enter no. only and no characters");
                    Console.pause();

                    // flushing the buffer
                    sc.nextLine();
                    continue;
                }

                if (!checkValid(index)) // will return true if out of bound or already choosen so we donot read
                {
                    boardArr[index] = 'X';
                    turn = 1;
                } else {
                    continue;
                }
            } else {

                System.out.print("\n\n\t\t" + player2 + "'s turn O: ");

                try {
                    index = sc.nextInt();
                } catch (NoSuchElementException e) {
                    e.getMessage();
                    System.out.println("  please enter no. only and no characters");
                    Console.pause();

                    // flushing the buffer
                    sc.nextLine();
                    continue;
                }

                if (!checkValid(index)) {
                    boardArr[index] = 'O';
                    turn = 0;
                } else {
                    continue;
                }
            }
            moves++;
        }

        Console.clearScr();

        boardDisplay();

        if (moves < 9) // the any player has won as all move not completed

        {
            if (turn == 1) {
                System.out.println("  player : " + player1 + " won the match");
            } else {
                System.out.println("  player : " + player1 + " won the match");
            }
        } else {
            System.out.println("\n  !!!!!!!!!!!!!TIE NO ONE WINS!!!!!!!!!!!!");
        }
        Console.pause();
    }

    public void boardDisplay() {

        int x = 0;
        System.out.println("\n\t\t  TIC TAC TOE");
        System.out.print("\t\t--------------------\n");

        for (int i = 0; i < 3; i++) {
            System.out.print("\t\t");

            for (int j = 0; j < 3; j++) {
                System.out.print("|  " + boardArr[x++] + "  ");
            }
            System.out.print("|\n");
            System.out.print("\t\t--------------------\n");
        }
    }

    public void ruleSet() {
        Console.clearScr();

        System.out.println("\n\t\t\tTIC TAC TOE");
        System.out.println("\n RULE SETS:");
        System.out.println("   1. there will be 2 players who can play the game");
        System.out.println(
                "   2. that person will win the game who will successful in creating a linear sequence of ( 'O' or 'X' )");
        System.out.println("   3. the linear sequence may be diagonal, horizontal or vertical. ");
        System.out.println("   4. In order to perform the move the user has to enter the no. displayed on the board.");

        System.out.print("\n\n");
        Console.pause();
    }

    public boolean winComparison() {

        if ((boardArr[0] == boardArr[4] && boardArr[0] == boardArr[8]) || (boardArr[0] == boardArr[1]
                && boardArr[0] == boardArr[2]) || (boardArr[0] == boardArr[3] && boardArr[0] == boardArr[6])
                || (boardArr[1] == boardArr[4]
                        && boardArr[1] == boardArr[7])
                || (boardArr[2] == boardArr[5] && boardArr[2] == boardArr[8]) || (boardArr[5] == boardArr[4]
                        && boardArr[4] == boardArr[3])
                || (boardArr[6] == boardArr[7] && boardArr[6] == boardArr[8]) || (boardArr[6] == boardArr[4]
                        && boardArr[4] == boardArr[2]))
            return true;

        return false;
    }

    private boolean checkValid(int index) {
        if (index >= 9 || index < 0 || boardArr[index] == 'X' || boardArr[index] == 'O') {
            System.out.println("out of bound or already choosen");

            Console.pause();

            return true;
        }
        return false;
    }
}
