package com.raja.tmp.day10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Integer.parseInt;

@Getter
@Setter
@AllArgsConstructor
public class CathodeRay {

    private int x;
    private int score;

    public static CathodeRay cathodeRay(String input) {
        CathodeRay cathodeRay = new CathodeRay(1, 0);

        String[] commands = input.split("\n");

        int cycle = 1;
        int cycleToRecord = 20;
        for (String command : commands) {
            if (command.equals("noop")) {
                cycleToRecord = addCycleToRecord(cathodeRay, cycle, cycleToRecord);
                printScreen(cathodeRay, cycle);
                cycle++;
                continue;
            }
            String[] addCommands = command.split(" ");
            int amount = parseInt(addCommands[1]);

            //take one cycle to process
            cycleToRecord = addCycleToRecord(cathodeRay, cycle, cycleToRecord);
            printScreen(cathodeRay, cycle);
            cycle++;
            // take another cycle to axecute
            cycleToRecord = addCycleToRecord(cathodeRay, cycle, cycleToRecord);
            printScreen(cathodeRay, cycle);
            cathodeRay.addToX(amount);
            cycle++;
        }

        return cathodeRay;
    }

    private static void printScreen(CathodeRay cathodeRay, int cycle) {
        int pixelToDraw = cycle % 40;
        if (cathodeRay.getX() <= pixelToDraw && pixelToDraw < cathodeRay.getX() + 3) {
            System.out.print("#");
        } else {
            System.out.print(" ");
        }
        if (pixelToDraw == 0) {
            System.out.println("");
        }
    }

    private static int addCycleToRecord(CathodeRay cathodeRay, int cycle, int cycleToRecord) {
        if (cycle == cycleToRecord) {
            cathodeRay.addToScore(cycle * cathodeRay.getX());
            cycleToRecord += 40;
        }
        return cycleToRecord;
    }

    public void addToX(int amount) {
        x += amount;
    }

    public void addToScore(int amount) {
        score += amount;
    }
}
