package com.company;

import java.util.Random;

public class Main {
    static LeaderBoard LeaderBoard = new LeaderBoard();

    public static void main(String[] args) {
        LeaderBoard.load();
        Random rand = new Random();

        do {
            String name = Asker.askUserName();
            System.out.println("Hello, " + name);

            int myNum = rand.nextInt(100) + 1;
            System.out.println("Cheat: " + myNum);

            long t1 = System.currentTimeMillis();

            for (int i = 0; i < 10; i++) {
                int userNum = Asker.askGuess();

                if (userNum == myNum) {
                    long t2 = System.currentTimeMillis();
                    GameResult r = new GameResult();
                    r.setGameStartTime(t1);
                    r.setName(name);
                    r.setTriesCount(i + 1);
                    r.setTime(t2 - t1);
                    LeaderBoard.addLeader(r);
                    System.out.println("You win!");
                    break;
                }
                if (i == 9) {
                    System.out.println("You lost! My number was " + myNum);
                    break;
                }
                if (myNum < userNum) {
                    System.out.println("My number is less then yours");
                } else {
                    System.out.println("My number is greater then yours");
                }
            }
        } while (Asker.askAnotherGame());
        LeaderBoard.printResults();
        LeaderBoard.save();
        System.out.println("Good bue!");
    }
}
