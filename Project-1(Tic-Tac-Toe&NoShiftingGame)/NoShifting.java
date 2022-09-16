package com.game.noshiftinggame;

import java.util.Random;
import java.util.Scanner;

import com.consoleFunctions.Console;
import com.game.InterfGame;

class Coordinate {
    int x;
    int y;
}

public class NoShifting implements com.game.InterfGame {

    private static NoShifting instanceObj = null;

    private int[][] boardArr = new int[4][4]; // by default 0 value
    private int check[] = new int[16]; // used at time of initialization

    private Coordinate zeroCoord = new Coordinate();// keeps the track of empty space

    private String player;
    private Scanner sc = new Scanner(System.in);

    private NoShifting() {
        super();
    }

    public static NoShifting getObj() {
        if (instanceObj == null) {
            instanceObj = new NoShifting(); // obj is created (only once)
        }

        instanceObj.initializeGame();

        return instanceObj;
    }

    private void initializeGame() {

        // reinitializing the check[]elements
        for (int i = 0; i < 16; i++) {
            check[i] = 0;
        }

        Random r = new Random();

        int temp = 0;
        int j = 0;

        for (int i = 0; i < 4; i++) {
            j = 0;
            while (j < 4) {
                temp = r.nextInt(16); // 0 to 15
                if (check[temp] == 0) {
                    boardArr[i][j] = temp;
                    check[temp] = 1;

                    if (temp == 0) {
                        zeroCoord.x = i;
                        zeroCoord.y = j;
                    }
                    j++;
                } else {
                    continue;
                }
            }
        }

        ruleSet();
        Console.clearScr();

        System.out.println("\n\t\tNUMBER SHIFTING GAME");
        System.out.print("\n  enter the player name:");
        player = sc.nextLine();
    }

    public void play() {

        int moves = 50;
        char shift = '\0';
        int temp = 0;

        do {
            Console.clearScr();
            boardDisplay();
            System.out.print("\n\nRemaining moves: " + moves);

            System.out.print("\t\tshift :");
            shift = sc.next().charAt(0);

            // mk the move
            if (moveValid(shift)) {

                switch (shift) {
                    case 'w':
                        boardArr[zeroCoord.x][zeroCoord.y] = boardArr[zeroCoord.x + 1][zeroCoord.y];
                        boardArr[++zeroCoord.x][zeroCoord.y] = 0;
                        break;

                    case 's':
                        boardArr[zeroCoord.x][zeroCoord.y] = boardArr[zeroCoord.x - 1][zeroCoord.y];

                        boardArr[--zeroCoord.x][zeroCoord.y] = 0;
                        break;
                    case 'd':
                        boardArr[zeroCoord.x][zeroCoord.y] = boardArr[zeroCoord.x][zeroCoord.y - 1];

                        boardArr[zeroCoord.x][--zeroCoord.y] = 0;
                        break;

                    case 'a':
                        boardArr[zeroCoord.x][zeroCoord.y] = boardArr[zeroCoord.x][zeroCoord.y + 1];

                        boardArr[zeroCoord.x][++zeroCoord.y] = 0;
                        break;

                    default:
                        System.out.println("invalid char");
                        Console.pause();
                        break;

                }
                moves--;

                // flushing buffer
                sc.nextLine();

            } else {
                System.out.println("invalid move");
                Console.pause();
            }

        } while (moves > 0 && !winComparison());

        // final display of table
        Console.clearScr();
        boardDisplay();
        System.out.println("\n\nRemaining moves: " + moves);

        if (moves > 0) {
            System.out.println("player :" + player + "won the game");
        } else {
            System.out.println("player :" + player + " \n   BETTER LUCK NEXT TIME");
        }

        Console.pause();
    }

    private boolean moveValid(char shift) { // for extreme cases (edges of board)
        if ((shift == 's' && zeroCoord.x == 0) || (shift == 'w' && zeroCoord.x == 3) || (shift == 'a'
                && zeroCoord.y == 3) || (shift == 'd' && zeroCoord.y == 0))
            return false;

        return true;
    }

    public void boardDisplay() {

        System.out.println("\n\t\tNUMBER SHIFTING GAME");

        System.out.println("\t\t-------------------------");
        for (int arr[] : boardArr) {
            System.out.print("\t\t");

            for (int a : arr) {
                if (a == 0) { // space
                    System.out.print("|  " + " " + "  ");
                } else {
                    System.out.print("|  " + a + "  ");
                }
            }
            System.out.print("|\n");
            System.out.println("\t\t-------------------------");

        }
    }

    public void ruleSet() {
        System.out.println("\n\t\t\tNUMBER SHIFTING GAME");
        System.out.println("\n RULE SET");
        System.out.println("   1.no. of player are: 1");
        System.out.println("   2.the player has to arrange the no. in the board as given below in the given moves.");
        System.out.println("   3.to shift no.s we have following moves ");
        System.out.println("     i.  's' then enter : moves the above no. down ");
        System.out.println("     ii. 'w' then enter : moves the below no. up ");
        System.out.println("     iii.'d' then enter : moves the left no. right ");
        System.out.println("     iv. 'a' then enter : moves the right no. left ");

        // table :
        int x = 1;
        System.out.println("\t\t-------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.print("\t\t");
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    System.out.print("|  " + "   ");
                } else {
                    if (x > 9)
                        System.out.print("|  " + (x++) + " ");
                    else
                        System.out.print("|  " + (x++) + "  ");
                }
            }
            System.out.print("|\n");
            System.out.println("\t\t-------------------------");
        }

        Console.pause();
    }

    public boolean winComparison() {
        int x = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (!(i == 3 && j == 3)) {
                    if (x == boardArr[i][j]) {
                        x++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
