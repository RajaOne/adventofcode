package com.raja.tmp.day14;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.raja.tmp.day14.Position.position;
import static java.lang.Integer.parseInt;

@Getter
@Setter
public class Regolith {

    private List<List<Integer>> grid;

    public Regolith() {

        grid = new ArrayList<>();
    }

    public static Regolith regolith(String input, int gridSize) {
        Regolith regolith = new Regolith();

        String[] lines = input.split("\n");

        for (int y = 0; y < gridSize; y++) {
            List<Integer> xList = new ArrayList<>();
            for (int x = 0; x < gridSize; x++) {
                xList.add(0);
            }
            regolith.getGrid().add(xList);
        }

        int lineIndex = 0;
        while (lineIndex < lines.length) {
            String line = lines[lineIndex];
            String[] coords = line.split(" -> ");
            String startingCoord = coords[0];
            String[] xyCoord = startingCoord.split(",");
            int x1 = parseInt(xyCoord[0]);
            int y1 = parseInt(xyCoord[1]);
            for (int i = 1; i < coords.length; i++) {
                String endCoord = coords[i];
                String[] xyCoord2 = endCoord.split(",");
                int x2 = parseInt(xyCoord2[0]);
                int y2 = parseInt(xyCoord2[1]);

                if (x1 == x2) {
                    if (y1 < y2) {
                        for (int yIndex = y1; yIndex <= y2; yIndex++) {
                            regolith.getGrid().get(yIndex).set(x1, 1);
                        }
                    } else {
                        for (int yIndex = y2; yIndex <= y1; yIndex++) {
                            regolith.getGrid().get(yIndex).set(x1, 1);
                        }
                    }
                }
                if (y1 == y2) {
                    if (x1 < x2) {
                        for (int xIndex = x1; xIndex <= x2; xIndex++) {
                            regolith.getGrid().get(y1).set(xIndex, 1);
                        }
                    } else {
                        for (int xIndex = x2; xIndex <= x1; xIndex++) {
                            regolith.getGrid().get(y1).set(xIndex, 1);
                        }
                    }
                }

                x1 = x2;
                y1 = y2;
            }


            lineIndex++;
        }

        return regolith;
    }

    public int getScore() {
//        printGrid(0, 10, 450, 510);

        boolean hasReachedEnd = false;
        int sands = 0;
        while (!hasReachedEnd) {
            Position sand = position(500, 0);
            sands++;
            Position next = getNextPosition(sand);
            while (!sand.equals(next)) {
                sand = next;
                next = getNextPosition(sand);
            }
            grid.get(sand.y()).set(sand.x(), 2);
//            System.out.println("=========================");
//            printGrid(0, 10, 450, 510);
            if (grid.size() - 1 == sand.y()) {
                hasReachedEnd = true;
            }
        }

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

    private void printGrid(int y1, int y2, int x1, int x2) {
        for (int y = y1; y < y2; y++) {
            List<Integer> yLine = grid.get(y);
            for (int x = x1; x < x2; x++) {
                Integer val = yLine.get(x);
                if (val == 0) {
                    System.out.print(" ");
                }
                if (val == 1) {
                    System.out.print("#");
                }
                if (val == 2) {
                    System.out.print("o");
                }
            }
            System.out.println("");
        }
    }

    public int getScore2() {
        return 0;
    }

}
