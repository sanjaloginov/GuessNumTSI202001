package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<GameResult> leaders = new ArrayList<>();

        do {
            System.out.println("What is your name?");
            String name = scan.next();
            System.out.println("Hello, " + name);

            long t1 = System.currentTimeMillis();

            int myNum = rand.nextInt(100) + 1;
            System.out.println("Cheat: " + myNum);

            for (int i = 0; i < 10; i++) {
                int userNum = askGuess();

                if (userNum == myNum) {
                    long t2 = System.currentTimeMillis();
                    long t = t2 - t1;
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i + 1;
                    r.time = t;
                    leaders.add(r);
                    System.out.println("You win!");
                    break;
                }
                if (i == 9) {
                    System.out.println("You lost! My number was " +myNum);
                    break;
                }
                if (myNum < userNum) {
                    System.out.println("My number is less then yours");
                } else {
                    System.out.println("My number is greater then yours");
                }
            }
        } while (askAnotherGame());
        for (GameResult r: leaders) {
            System.out.printf("user: %-10s tries: %2d time: %5.2fsec %n", r.name, r.triesCount, r.time/1000.0);
        }
        System.out.println("Good bue!");
    }
    static int askGuess() {
        for (; ; ) {
            try {
                System.out.println("Enter your guess");
                int num = scan.nextInt();
                if (num >= 1 && num <= 100) {
                    return num;
                } else {
                    System.out.println("Please enter a number from 1 to 100");
                }
            } catch (InputMismatchException ex) {
                String str = scan.next();
                System.out.println(str + " isn't a number");
            }
        }
    }
    static boolean askAnotherGame() {
         for (;;) {
            System.out.println("Do you want to play again? y/n");
            String answer = scan.next();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                return true;
            }
            else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                return false;
            }
            else {
                System.out.println("Please enter yes or no (y/n) only");
            }
         }
    }
}
