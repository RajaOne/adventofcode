package com.raja.tmp.day14;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.raja.tmp.day14.Position.position;
import static com.raja.tmp.day14.Position.positionFromString;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Setter
public class Regolith {

    private List<List<Integer>> grid;

    private Regolith() {
        grid = new ArrayList<>();
    }

    public static Regolith regolith(String input, int gridSize) {
        Regolith regolith = new Regolith();
        String[] lines = input.split("\n");

        fillGrid(gridSize, regolith);

        for (String line : lines) {
            String[] coords = line.split(" -> ");
            String startingCoord = coords[0];
            Position startingPosition = positionFromString(startingCoord);
            for (int i = 1; i < coords.length; i++) {
                String endCoord = coords[i];
                Position endPosition = positionFromString(endCoord);
                fillRocks(regolith, startingPosition, endPosition);
                startingPosition = endPosition;
            }
        }

        return regolith;
    }

    public static Regolith regolith2(String input, int gridSize) {
        Regolith regolith = new Regolith();
        String[] lines = input.split("\n");

        fillGrid(gridSize, regolith);

        int highestY = 0;
        for (String line : lines) {
            String[] coords = line.split(" -> ");
            String startingCoord = coords[0];
            Position startingPosition = positionFromString(startingCoord);
            highestY = max(highestY, startingPosition.y());

            for (int i = 1; i < coords.length; i++) {
                String endCoord = coords[i];
                Position endPosition = positionFromString(endCoord);
                highestY = max(highestY, endPosition.y());

                fillRocks(regolith, startingPosition, endPosition);
                startingPosition = endPosition;
            }
        }

        for (int x = 0; x < gridSize; x++) {
            regolith.getGrid().get(highestY + 2).set(x, 1);
        }
        return regolith;
    }

    private static void fillRocks(Regolith regolith, Position position1, Position position2) {
        int x1 = position1.x();
        int y1 = position1.y();
        int x2 = position2.x();
        int y2 = position2.y();
        if (x1 == x2) {
            for (int yIndex = min(y1, y2); yIndex <= max(y1, y2); yIndex++) {
                regolith.getGrid().get(yIndex).set(x1, 1);
            }
        }
        if (y1 == y2) {
            for (int xIndex = min(x1, x2); xIndex <= max(x1, x2); xIndex++) {
                regolith.getGrid().get(y1).set(xIndex, 1);
            }
        }
    }

    private static void fillGrid(int gridSize, Regolith regolith) {
        for (int y = 0; y < gridSize; y++) {
            List<Integer> xList = new ArrayList<>();
            for (int x = 0; x < gridSize; x++) {
                xList.add(0);
            }
            regolith.getGrid().add(xList);
        }
    }

    public int getScore() {
        int sands = 0;
        while (true) {
            Position sand = position(500, 0);
            sands++;
            Position next = getNextPosition(sand);
            while (!sand.equals(next)) {
                sand = next;
                next = getNextPosition(sand);
            }
            grid.get(sand.y()).set(sand.x(), 2);
//            System.out.println("===============");
//            printGrid(0, 12, 470, 510);
            if (grid.size() - 1 == sand.y()) {
                break;
            }
            if (next.equals(position(500, 0))) {
                sands++;
                break;
            }
        }
//        System.out.println("===============");
//        printGrid(0, 200, 330, 600);

        return sands - 1;
    }

    private Position getNextPosition(Position sand) {
        if (grid.size() - 1 == sand.y()) {
            return sand;
        }
        if (grid.get(sand.y() + 1).get(sand.x()) == 0) {
            return sand.down();
        }
        if (grid.get(sand.y() + 1).get(sand.x() - 1) == 0) {
            return sand.down().left();
        }
        if (grid.get(sand.y() + 1).get(sand.x() + 1) == 0) {
            return sand.down().right();
        }
        return sand;
    }

//    private void printGrid(int y1, int y2, int x1, int x2) {
//        for (int y = y1; y < y2; y++) {
//            List<Integer> yLine = grid.get(y);
//            for (int x = x1; x < x2; x++) {
//                Integer val = yLine.get(x);
//                if (val == 0) {
//                    System.out.print(" ");
//                }
//                if (val == 1) {
//                    System.out.print("#");
//                }
//                if (val == 2) {
//                    System.out.print("o");
//                }
//            }
//            System.out.println("");
//        }
//    }
}
