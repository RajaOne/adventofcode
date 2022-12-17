package com.raja.tmp.day15;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.raja.tmp.day15.Range.aRange;
import static java.lang.Integer.parseInt;
import static java.lang.Math.*;

@Getter
@Setter
public class BeaconExclusion {

    private List<List<Integer>> grid;
    private int xOffset;
    private int yOffset;

    private List<Ranges> yRanges;
    private List<Ranges> xRanges;
    private int size;

    private BeaconExclusion() {
        grid = new ArrayList<>();
        xOffset = 0;
        yOffset = 0;
        yRanges = new ArrayList<>();
        xRanges = new ArrayList<>();
    }

    public static BeaconExclusion beaconExclusion(String input, int yToScan) {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        String[] lines = input.split("\n");

        int lineIndex = 0;
        int lowestX = 0;
        int lowestY = 0;
        int highestX = 0;
        int highestY = 0;

        while (lineIndex < lines.length) {
            String line = lines[lineIndex];
            String[] coordinates = line
                    .replace("Sensor at x=", "")
                    .replace(", y=", " ")
                    .replace(": closest beacon is at x=", " ")
                    .replace(", y=", "")
                    .split(" ");
            int x = parseInt(coordinates[0]);
            int y = parseInt(coordinates[1]);
            int x2 = parseInt(coordinates[2]);
            int y2 = parseInt(coordinates[3]);
            lowestX = min(x2, min(lowestX, x));
            lowestY = min(y2, min(lowestY, y));
            highestX = max(x2, max(highestX, x));
            highestY = max(y2, max(highestY, y));

            lineIndex++;
        }

        int X_RANGE = 2000000;
        beaconExclusion.setXOffset(lowestX - X_RANGE);
        beaconExclusion.setYOffset(lowestY);

        List<Integer> yList = new ArrayList<>();
        for (int x = 0; x <= (2*X_RANGE) + highestX - lowestX; x++) {
            yList.add(0);
        }
        beaconExclusion.getGrid().add(yList);

        for (String line : lines) {
            String[] coordinates = line
                    .replace("Sensor at x=", "")
                    .replace(", y=", " ")
                    .replace(": closest beacon is at x=", " ")
                    .replace(", y=", "")
                    .split(" ");
            int sensorX = parseInt(coordinates[0]);
            int sensorY = parseInt(coordinates[1]);
            int beaconX = parseInt(coordinates[2]);
            int beaconY = parseInt(coordinates[3]);

            int xRange = abs(sensorX - beaconX);
            int yRange = abs(sensorY - beaconY);
            int range = xRange + yRange;

//            if (sensorY - range > Y_TO_SCAN ||
//                sensorY + range < Y_TO_SCAN) {
//                continue;
//            }

            int y = yToScan - sensorY;
            for (int x = -range; x <= range; x++) {
                if (abs(y) + abs(x) <= range) {
                    if (beaconExclusion.getGrid()
                            .get(0)
                            .get(sensorX + x - beaconExclusion.getXOffset()) == 0) {
                        beaconExclusion.getGrid()
                                .get(0)
                                .set(sensorX + x - beaconExclusion.getXOffset(), 1);
                    }
                }
            }

            if (sensorY == yToScan) {
                beaconExclusion.getGrid()
                        .get(0)
                        .set(sensorX - beaconExclusion.getXOffset(), 2);
            }
            if (beaconY == yToScan) {
                beaconExclusion.getGrid()
                        .get(0)
                        .set(beaconX - beaconExclusion.getXOffset(), 3);
            }

//            System.out.println("(" + sensorX + ", " + sensorY + ") -> (" + beaconX + ", " + beaconY + ")");
//            beaconExclusion.printGrid();
        }

        return beaconExclusion;
    }

    public static BeaconExclusion beaconExclusion2(String input, int size) {
        BeaconExclusion beaconExclusion = new BeaconExclusion();
        beaconExclusion.setSize(size);
        String[] lines = input.split("\n");

        for (int i = 0; i <= size; i++) {
            beaconExclusion.getYRanges().add(new Ranges());
            beaconExclusion.getXRanges().add(new Ranges());
        }

        for (String line : lines) {
            String[] coordinates = line
                    .replace("Sensor at x=", "")
                    .replace(", y=", " ")
                    .replace(": closest beacon is at x=", " ")
                    .replace(", y=", "")
                    .split(" ");
            int sensorX = parseInt(coordinates[0]);
            int sensorY = parseInt(coordinates[1]);
            int beaconX = parseInt(coordinates[2]);
            int beaconY = parseInt(coordinates[3]);

            int xRange = abs(sensorX - beaconX);
            int yRange = abs(sensorY - beaconY);
            int range = xRange + yRange;

//            if (!aRange(sensorX - range, sensorX + range).contains(aRange(0, size))) {
//                continue;
//            }

//            if (!aRange(sensorY - range, sensorY + range).contains(aRange(0, size))) {
//                continue;
//            }


            for (int y = 0; y < beaconExclusion.getYRanges().size(); y++) {

                int beginX = sensorX - (range - abs(y - sensorY));
                int endX = sensorX + (range - abs(y - sensorY));
                if (beginX > endX) {
                    continue;
                }
                beaconExclusion.getYRanges().get(y).mergeRange(aRange(beginX, endX));

//                if (beaconExclusion.getCompleted().get(y)) {
//                    continue;
//                }
//                for (int x = 0; x < beaconExclusion.getXRanges().size(); x++) {
//                    if (abs(y - sensorY) + abs(x - sensorX) <= range) {
//                        beaconExclusion.getYRanges().get(y).addValue(x);
//                        beaconExclusion.getXRanges().get(x).addValue(y);
//                    }
//                }
//                if (beaconExclusion.getYRanges().get(y).contains(aRange(0, size))) {
//                    beaconExclusion.getCompleted().set(y, true);
//                }
            }
//            for (int x = 0; x < beaconExclusion.getXRanges().size(); x++) {
//
//                int beginY = sensorY - (range - abs(x - sensorX));
//                int endY = sensorY + (range - abs(x - sensorX));
//                if (beginY > endY) {
//                    continue;
//                }
//                beaconExclusion.getXRanges().get(x).mergeRange(aRange(beginY, endY));
//            }
        }

        return beaconExclusion;
    }

    private void printGrid() {
        System.out.println("======================");
        System.out.println("  000000000011111111112222222");
        System.out.println("  012345678901234567890123456");
        for (int j = 0; j < grid.size(); j++) {
            List<Integer> yGrid = grid.get(j);
            for (int i = 0; i < yGrid.size(); i++) {
                if (i == 0) {
                    System.out.print(j + " ");
                }
                Integer value = yGrid.get(i);
                switch (value) {
                    case 0 -> System.out.print(".");
                    case 1 -> System.out.print("#");
                    case 2 -> System.out.print("S");
                    case 3 -> System.out.print("B");
                }
            }
            System.out.println("");
        }
    }

    public int getScore() {
        long count = grid.get(0).stream()
                .filter(integer -> integer == 1)
                .count();
        return (int) count;
    }

    public long getScore2() {
        Integer y = getMissingY();
        Integer x = getMissingYRange().getRangeList().get(0).getEnd() + 1;
        return (x * 1L * 4_000_000) + y;
    }

    private Integer getMissingX() {
        for (int x = 0; x < xRanges.size(); x++) {
            Ranges range = xRanges.get(x);
            if (!range.contains(aRange(0, size))) {
                return x;
            }
        }
        return null;
    }

    private Integer getMissingY() {
        for (int y = 0; y < yRanges.size(); y++) {
            Ranges range = yRanges.get(y);
            if (!range.contains(aRange(0, size))) {
                return y;
            }
        }
        return null;
    }

    private Ranges getMissingYRange() {
        for (int y = 0; y < yRanges.size(); y++) {
            Ranges range = yRanges.get(y);
            if (!range.contains(aRange(0, size))) {
                return range;
            }
        }
        return null;
    }
}
