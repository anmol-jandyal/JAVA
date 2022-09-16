import java.io.IOError;
import java.util.Scanner;
import com.consoleFunctions.Console;
import com.game.InterfGame;
import com.game.noshiftinggame.NoShifting;
import com.game.tictactoegame.TicTacToe;

import java.io.IOException;

public class Start {
    public static void main(String[] arg) {

        InterfGame playable = null; // can points to objects of its implemented class

        char choice = '\0';

        Scanner sc = new Scanner(System.in);

        // MENU
        do {
            Console.clearScr(); // clears the (console screen)

            System.out.println("\n\n\n  welcome to the GAME MENU");
            System.out.println("\n\n 1.TIC TAC TOE.");
            System.out.println(" 2. NUMBER SHIFTING GAME.");
            System.out.println(" e/E. for exit");

            System.out.print("\n enter your choice:");

            choice = sc.next().charAt(0); // it will read whole string (word) and returns the 0th index element

            switch (choice) {
                case '1':
                    // related to tic tac toe.
                    playable = TicTacToe.getTTTobj();
                    playable.play();
                    break;

                case '2':
                    // no. shifting game.
                    playable = NoShifting.getObj();
                    playable.play();
                    break;

                // exit case
                case 'e':
                case 'E':
                    break;

                default:
                    System.out.println("\n\nPLEASE ENTER CORRECT CHOICE");

                    Console.pause();
                    break;

            }
        } while ((choice != 'e') && (choice != 'E'));

        sc.close();
    }
}
