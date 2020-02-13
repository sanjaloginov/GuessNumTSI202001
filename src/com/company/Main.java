package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        boolean answer;
        do {
            Random rand = new Random();

            System.out.println("What is your name?");
            String name = scan.next();
            System.out.println("Hello, " + name);

            int myNum = rand.nextInt(100) + 1;
            System.out.println("Cheat: " + myNum);

            for (int i = 0; i < 10; i++) {
                int userNum = askGuess();

                if (myNum < userNum) {
                    System.out.println("My number is less then yours");
                } else if (myNum > userNum) {
                    System.out.println("My number is greater then yours");
                } else {
                    System.out.println("You win!");
                    break;
                }
            }
            answer = askAnswer();
        } while (answer != false);
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
                System.out.println("This isn't a number");
            }
        }
    }
    static boolean askAnswer() {
         for (;;) {
            System.out.println("Do you want to play once else? yes/no");
            String anw = scan.next();
            if (anw.equalsIgnoreCase("yes") || anw.equalsIgnoreCase("y")) {
                return true;
            }
            else if (anw.equalsIgnoreCase("no") || anw.equalsIgnoreCase("n")) {
                return false;
            }
            else {
                System.out.println("Please enter yes or no only");
            }
         }
    }
}
