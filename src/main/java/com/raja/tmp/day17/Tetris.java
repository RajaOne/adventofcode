package com.raja.tmp.day17;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.*;

import static com.raja.tmp.day17.Direction.directionFromString;
import static com.raja.tmp.day17.Position.position;

@Getter
@Setter
public class Tetris {

    private List<Direction> directionList = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();
    private int directionIndex = 0;
    private int shapeIndex = 0;
    private List<List<Integer>> grid = new ArrayList<>();
    private LinkedList<List<Integer>> gridList = new LinkedList<>();
    private long gridOffset = 0;
    private int lastRecordedHeight = 0;
    private static final int GRID_SIZE = 200;

    public static Tetris tetris(String input) {
        Tetris tetris = new Tetris();

        for (int i = 0; i < GRID_SIZE; i++) {
            List<Integer> x = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                x.add(0);
            }
            tetris.getGridList().add(x);
        }

        for (int i = 0; i < 5000; i++) {
            List<Integer> x = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                x.add(0);
            }
            tetris.getGrid().add(x);
        }

        String[] directionStrings = input.replace("\n", "").split("");
        for (String directionString : directionStrings) {
            Direction direction = directionFromString(directionString);
            tetris.getDirectionList().add(direction);
        }


        Shape shape1 = new Shape();
        shape1.getPositionList().add(position(0, 0));
        shape1.getPositionList().add(position(1, 0));
        shape1.getPositionList().add(position(2, 0));
        shape1.getPositionList().add(position(3, 0));
        tetris.getShapes().add(shape1);

        Shape shape2 = new Shape();
        shape2.getPositionList().add(position(1, 2));
        shape2.getPositionList().add(position(0, 1));
        shape2.getPositionList().add(position(1, 1));
        shape2.getPositionList().add(position(2, 1));
        shape2.getPositionList().add(position(1, 0));
        tetris.getShapes().add(shape2);

        Shape shape3 = new Shape();
        shape3.getPositionList().add(position(2, 2));
        shape3.getPositionList().add(position(2, 1));
        shape3.getPositionList().add(position(0, 0));
        shape3.getPositionList().add(position(1, 0));
        shape3.getPositionList().add(position(2, 0));
        tetris.getShapes().add(shape3);

        Shape shape4 = new Shape();
        shape4.getPositionList().add(position(0, 3));
        shape4.getPositionList().add(position(0, 2));
        shape4.getPositionList().add(position(0, 1));
        shape4.getPositionList().add(position(0, 0));
        tetris.getShapes().add(shape4);

        Shape shape5 = new Shape();
        shape5.getPositionList().add(position(0, 1));
        shape5.getPositionList().add(position(1, 1));
        shape5.getPositionList().add(position(0, 0));
        shape5.getPositionList().add(position(1, 0));
        tetris.getShapes().add(shape5);

        return tetris;
    }

    public long getScore2022() {
        return getScore2(2022);
    }

    public long getScoreTrillion() {
        return getScore2(1_000_000_000_000L);
    }

    public long getScore2(long rocks) {
        for (long i = 0; i < rocks; i++) {
//            if (i % 1_000_000 == 0) {
//                System.out.println(Instant.now() + ": " + i);
//            }
            Shape shape = shapes.get(shapeIndex).copy();
            int bottom = shape.bottom();
            long height = getHeight2();
            long toMoveUp = height + gridOffset + 3 - bottom;
            shape.moveUp(toMoveUp);
            if (height > GRID_SIZE - 50) {
                for (int j = 0; j < 100; j++) {
                    gridList.poll();
                    List<Integer> x = new ArrayList<>();
                    for (int k = 0; k < 7; k++) {
                        x.add(0);
                    }
                    gridList.add(x);
                    gridOffset++;
                    lastRecordedHeight--;
                }
            }

            int left = shape.leftest();
            int toMoveToRight = 2 - left;
            shape.moveRight(toMoveToRight);

            boolean isPlaced = false;
            while (!isPlaced) {
                Direction direction = directionList.get(directionIndex);
                direction.move(shape, gridList, gridOffset);

                if (shape.canMoveDown(gridList, gridOffset)) {
                    shape.moveDown();
                } else {
                    for (Position position : shape.getPositionList()) {
                        gridList.get((int) (position.y() - gridOffset)).set(position.x(), 1);
                    }
                    isPlaced = true;
                }

                directionIndex = (directionIndex + 1) % directionList.size();
            }
            shapeIndex = (shapeIndex + 1) % shapes.size();
        }

        return getHeight2() + gridOffset;
    }

//    private void printGrid(Shape shape) {
//        System.out.println("=============");
//        for (int y = 20; y >= 0; y--) {
//            List<Integer> integers = grid.get(y);
//            List<Position> positions = getYMatchingPositions(shape, y);
//            int minX = positions.stream().map(Position::x).reduce(10, Integer::min);
//            System.out.print(y + ":|");
//            if (!positions.isEmpty()) {
//                for (int i = 0; i < minX; i++) {
//                    System.out.print(".");
//                }
//                positions.forEach(p -> System.out.print("o"));
//            } else {
//                for (Integer integer : integers) {
//                    switch (integer) {
//                        case 0 -> System.out.print(".");
//                        case 1 -> System.out.print("#");
//                    }
//                }
//            }
//            System.out.println("|");
//        }
//
//    }

//    private static List<Position> getYMatchingPositions(Shape shape, int y) {
//        return shape.getPositionList().stream().filter(position -> position.y() == y).toList();
//    }

    private long getHeight2() {
        int i = lastRecordedHeight;
        List<Integer> integers = gridList.get(i);
        while (integers.contains(1)) {
            i++;
            integers = gridList.get(i);
        }
        lastRecordedHeight = i;
        return i;
    }
}
