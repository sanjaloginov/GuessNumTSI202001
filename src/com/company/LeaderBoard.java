package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class LeaderBoard {
    private ArrayList<GameResult> leaders = new ArrayList<>();

    public void addLeader (GameResult gr) {
        leaders.add(gr);
    }
    public void printResults() {
        int maxLen = leaders.stream().mapToInt(r -> r.getName().length()).max().orElse(0);
        /*for (GameResult r: leaders) {
            maxLen = Math.max(maxLen, r.getName().length());
        }*/

        //leaders.sort(Comparator.comparing(GameResult::getTriesCount).thenComparing(GameResult::getTime)); //menjaet porjadok v spiske

        /*int count = 0;
        for (GameResult r: leaders) {
            System.out.printf("user: %-" + maxLen + "s tries: %2d time: %3.2fsec %n", r.getName(), r.getTriesCount(), r.getTime() / 1000.0);
            count++;
            if (count == 5) {
                break;
            }
        }*/
        /*for (int i = 0; i < Math.min(leaders.size(), 5); i++) {
            GameResult r =leaders.get(i);
            System.out.printf("user: %-" + maxLen + "s tries: %2d time: %3.2fsec %n", r.getName(), r.getTriesCount(), r.getTime() / 1000.0);
        }*/
        leaders.stream()
                .sorted(Comparator.comparing(GameResult::getTriesCount).thenComparing(GameResult::getTime))
                .limit(5)
                .forEach(r -> System.out.printf("%-" + maxLen + "s %2d %3.2fsec %n", r.getName(), r.getTriesCount(), r.getTime() / 1000.0));
    }

    public void load() {
        File leaderDataFile = new File("GameResults.txt");
        try (Scanner in = new Scanner(leaderDataFile)) {
            while (in.hasNext()) {
                long gameStartTime = in.nextLong();
                String name = in.next();
                int tries = in.nextInt();
                long time = in.nextLong();

                GameResult r = new GameResult();
                r.setGameStartTime(gameStartTime);
                r.setName(name);
                r.setTriesCount(tries);
                r.setTime(time);
                addLeader(r);
            }
        } catch (IOException e) {
            System.out.println("System can't read from file");
        }
    }

    public void save() {
        File leaderDataFile = new File("GameResults.txt");
        try (PrintWriter out = new PrintWriter(leaderDataFile)) {
            for (GameResult r: leaders) {
                out.printf("%d %s %d %d%n", r.getGameStartTime(), r.getName(), r.getTriesCount(), r.getTime());
            }
        } catch (IOException e) {
            System.out.println("System can't write data to file");
        }
    }
}
