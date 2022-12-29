package com.raja.tmp.day22;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.raja.tmp.day22.Direction.*;
import static com.raja.tmp.day22.Pair.pair;
import static com.raja.tmp.day22.Position.aPosition;
import static java.lang.Integer.parseInt;

@Getter
public class MonkeyMap {

    private final List<List<Integer>> grid = new ArrayList<>();
    private final List<String> instructions = new ArrayList<>();
    private final Map<Direction, Map<Position, Pair>> cubeMap = new HashMap<>();

    public static MonkeyMap monkeyMap(String input) {
        MonkeyMap monkeyMap = new MonkeyMap();
        String[] inputs = input.split("\n\n");

        int widest = 0;
        String map = inputs[0];
        String[] maplines = map.split("\n");
        for (String mapline : maplines) {
            List<Integer> xLine = new ArrayList<>();
            String[] lineX = mapline.split("");
            widest = Math.max(widest, lineX.length);
            for (String x : lineX) {
                int value = switch (x) {
                    case " " -> 0;
                    case "." -> 1;
                    case "#" -> 2;
                    default -> throw new RuntimeException("what is input " + input + " ?");
                };
                xLine.add(value);
            }
            monkeyMap.getGrid().add(xLine);
        }

        for (List<Integer> integers : monkeyMap.grid) {
            if (integers.size() < widest) {
                for (int i = integers.size() - 1; i < widest; i++) {
                    integers.add(0);
                }
            }
        }

        String instructionLine = inputs[1].split("\n")[0];
        String[] instructions = instructionLine
                .replaceAll("L", " L ")
                .replaceAll("R", " R ")
                .split(" ");
        for (String instruction : instructions) {
            monkeyMap.getInstructions().add(instruction);
        }

        monkeyMap.getCubeMap().put(UP, new HashMap<>());
        monkeyMap.getCubeMap().put(DOWN, new HashMap<>());
        monkeyMap.getCubeMap().put(LEFT, new HashMap<>());
        monkeyMap.getCubeMap().put(RIGHT, new HashMap<>());
        monkeyMap.makeRealCubeMap();

        return monkeyMap;
    }

    private void makeRealCubeMap() {
        int cubeSize = 50;
        makeCubeMapping(
                UP, aPosition(0, cubeSize*2).up(), aPosition(cubeSize - 1, cubeSize*2).up(),
                RIGHT, aPosition(cubeSize, cubeSize), aPosition(cubeSize, cubeSize*2-1));
        makeCubeMapping(
                UP, aPosition(cubeSize, 0).up(), aPosition(cubeSize*2 - 1, 0).up(),
                RIGHT, aPosition(0, cubeSize*3), aPosition(0, cubeSize*4-1));
        makeCubeMapping(
                UP, aPosition(cubeSize*2, 0).up(), aPosition(cubeSize*3 - 1, 0).up(),
                UP, aPosition(0, cubeSize*4).up(), aPosition(cubeSize-1, cubeSize*4).up());

        makeCubeMapping(
                DOWN, aPosition(0, cubeSize*4), aPosition(cubeSize-1, cubeSize*4),
                DOWN, aPosition(cubeSize*2, 0), aPosition(cubeSize*3 - 1, 0));
        makeCubeMapping(
                DOWN, aPosition(cubeSize, cubeSize*3), aPosition(cubeSize*2-1, cubeSize*3),
                LEFT, aPosition(cubeSize-1, cubeSize*3), aPosition(cubeSize- 1, cubeSize*4-1));
        makeCubeMapping(
                DOWN, aPosition(cubeSize*2, cubeSize), aPosition(cubeSize*3-1, cubeSize),
                LEFT, aPosition(cubeSize*2-1, cubeSize), aPosition(cubeSize*2- 1, cubeSize*2-1));

        makeCubeMapping(
                RIGHT, aPosition(cubeSize*3, 0), aPosition(cubeSize*3, cubeSize-1),
                LEFT, aPosition(cubeSize*2, cubeSize*3-1).left(), aPosition(cubeSize*2, cubeSize*2).left());
        makeCubeMapping(
                RIGHT, aPosition(cubeSize*2, cubeSize), aPosition(cubeSize*2, cubeSize*2-1),
                UP, aPosition(cubeSize*2, cubeSize).up(), aPosition(cubeSize*3-1, cubeSize).up());
        makeCubeMapping(
                RIGHT, aPosition(cubeSize*2, cubeSize*2), aPosition(cubeSize*2, cubeSize*3-1),
                LEFT, aPosition(cubeSize*3, cubeSize-1).left(), aPosition(cubeSize*3, 0).left());
        makeCubeMapping(
                RIGHT, aPosition(cubeSize, cubeSize*3), aPosition(cubeSize, cubeSize*4-1),
                UP, aPosition(cubeSize, cubeSize*3).up(), aPosition(cubeSize*2-1, cubeSize*3).up());

        makeCubeMapping(
                LEFT, aPosition(cubeSize, 0).left(), aPosition(cubeSize, cubeSize-1).left(),
                RIGHT, aPosition(0, cubeSize*3-1), aPosition(0, cubeSize*2));
        makeCubeMapping(
                LEFT, aPosition(cubeSize, cubeSize).left(), aPosition(cubeSize, cubeSize*2-1).left(),
                DOWN, aPosition(0, cubeSize*2), aPosition(cubeSize-1, cubeSize*2));
        makeCubeMapping(
                LEFT, aPosition(0, cubeSize*2).left(), aPosition(0, cubeSize*3-1).left(),
                RIGHT, aPosition(cubeSize, cubeSize-1), aPosition(cubeSize, 0));
        makeCubeMapping(
                LEFT, aPosition(0, cubeSize*3).left(), aPosition(0, cubeSize*4-1).left(),
                DOWN, aPosition(cubeSize, 0), aPosition(cubeSize*2-1, 0));

    }

    public void makeTestCubeMap() {
        cubeMap.put(UP, new HashMap<>());
        cubeMap.put(DOWN, new HashMap<>());
        cubeMap.put(LEFT, new HashMap<>());
        cubeMap.put(RIGHT, new HashMap<>());

        int cubeSize = 4;
        makeCubeMapping(
                UP, aPosition(cubeSize * 2, -1), aPosition(cubeSize * 3 - 1, -1),
                DOWN, aPosition(cubeSize - 1, cubeSize), aPosition(0, cubeSize));
        makeCubeMapping(
                UP, aPosition(0, cubeSize - 1), aPosition(cubeSize - 1, cubeSize - 1),
                DOWN, aPosition(cubeSize * 3 - 1, 0), aPosition(cubeSize * 2, 0));
        makeCubeMapping(
                UP, aPosition(cubeSize, cubeSize - 1), aPosition(cubeSize * 2 - 1, cubeSize - 1),
                RIGHT, aPosition(cubeSize * 2, 0), aPosition(cubeSize * 2, cubeSize - 1));
        makeCubeMapping(
                UP, aPosition(cubeSize * 3, cubeSize * 2 - 1), aPosition(cubeSize * 4 - 1, cubeSize * 2 - 1),
                LEFT, aPosition(cubeSize * 3 - 1, cubeSize * 2 - 1), aPosition(cubeSize * 3 - 1, cubeSize));

        makeCubeMapping(
                DOWN, aPosition(0, cubeSize * 2), aPosition(cubeSize - 1, cubeSize * 2),
                UP, aPosition(cubeSize * 3 - 1, cubeSize * 3 - 1), aPosition(cubeSize * 2, cubeSize * 3 - 1));
        makeCubeMapping(
                DOWN, aPosition(cubeSize, cubeSize * 2), aPosition(cubeSize * 2 - 1, cubeSize * 2),
                RIGHT, aPosition(cubeSize * 2, cubeSize * 3 - 1), aPosition(cubeSize * 2, 9));
        makeCubeMapping(
                DOWN, aPosition(cubeSize * 2, cubeSize * 3), aPosition(cubeSize * 3 - 1, cubeSize * 3),
                UP, aPosition(cubeSize - 1, cubeSize * 2 - 1), aPosition(0, cubeSize * 2 - 1));
        makeCubeMapping(
                DOWN, aPosition(cubeSize * 3, cubeSize * 3), aPosition(cubeSize * 4 - 1, cubeSize * 3),
                RIGHT, aPosition(0, cubeSize * 2 - 1), aPosition(0, cubeSize));

        makeCubeMapping(
                LEFT, aPosition(cubeSize * 2 - 1, 0), aPosition(cubeSize * 2 - 1, cubeSize - 1),
                DOWN, aPosition(cubeSize, cubeSize), aPosition(cubeSize * 2 - 1, cubeSize));
        makeCubeMapping(
                LEFT, aPosition(-1, cubeSize), aPosition(-1, cubeSize * 2 - 1),
                UP, aPosition(cubeSize * 4 - 1, cubeSize * 3 - 1), aPosition(cubeSize * 3, cubeSize * 3 - 1));
        makeCubeMapping(
                LEFT, aPosition(cubeSize * 2 - 1, cubeSize * 2), aPosition(cubeSize * 2 - 1, cubeSize * 3 - 1),
                UP, aPosition(cubeSize * 2 - 1, cubeSize * 2 - 1), aPosition(cubeSize, cubeSize * 2 - 1));

        makeCubeMapping(
                RIGHT, aPosition(cubeSize * 3, 0), aPosition(cubeSize * 3, cubeSize - 1),
                LEFT, aPosition(cubeSize * 4 - 1, cubeSize * 3 - 1), aPosition(cubeSize * 4 - 1, cubeSize * 2));
        makeCubeMapping(
                RIGHT, aPosition(cubeSize * 3, cubeSize), aPosition(cubeSize * 3, cubeSize * 2 - 1),
                DOWN, aPosition(cubeSize * 4 - 1, cubeSize * 2), aPosition(cubeSize * 3 - 1, cubeSize * 2));
        makeCubeMapping(
                RIGHT, aPosition(cubeSize * 4, cubeSize * 2), aPosition(cubeSize * 4, cubeSize * 3 - 1),
                LEFT, aPosition(cubeSize * 3 - 1, cubeSize - 1), aPosition(cubeSize * 3 - 1, 0));
    }

    private void makeCubeMapping(Direction startDirection, Position begin1, Position end1,
                                 Direction direction2, Position begin2, Position end2) {
        if (begin1.x() < end1.x()) {
            if (begin2.x() < end2.x()) {
                int x2 = begin2.x();
                for (int x1 = begin1.x(); x1 <= end1.x(); x1++) {
                    cubeMap.get(startDirection).put(aPosition(x1, begin1.y()), pair(aPosition(x2++, begin2.y()), direction2));
                }
            } else if (begin2.x() > end2.x()) {
                int x2 = begin2.x();
                for (int x1 = begin1.x(); x1 <= end1.x(); x1++) {
                    cubeMap.get(startDirection).put(aPosition(x1, begin1.y()), pair(aPosition(x2--, begin2.y()), direction2));
                }
            } else if (begin2.y() < end2.y()) {
                int y2 = begin2.y();
                for (int x1 = begin1.x(); x1 <= end1.x(); x1++) {
                    cubeMap.get(startDirection).put(aPosition(x1, begin1.y()), pair(aPosition(begin2.x(), y2++), direction2));
                }
            } else if (begin2.y() > end2.y()) {
                int y2 = begin2.y();
                for (int x1 = begin1.x(); x1 <= end1.x(); x1++) {
                    cubeMap.get(startDirection).put(aPosition(x1, begin1.y()), pair(aPosition(begin2.x(), y2--), direction2));
                }
            }
        } else {
            if (begin2.y() < end2.y()) {
                int y2 = begin2.y();
                for (int y1 = begin1.y(); y1 <= end1.y(); y1++) {
                    cubeMap.get(startDirection).put(aPosition(begin1.x(), y1), pair(aPosition(begin2.x(), y2++), direction2));
                }
            } else if (begin2.y() > end2.y()) {
                int y2 = begin2.y();
                for (int y1 = begin1.y(); y1 <= end1.y(); y1++) {
                    cubeMap.get(startDirection).put(aPosition(begin1.x(), y1), pair(aPosition(begin2.x(), y2--), direction2));
                }
            } else if (begin2.x() < end2.x()) {
                int x2 = begin2.x();
                for (int y1 = begin1.y(); y1 <= end1.y(); y1++) {
                    cubeMap.get(startDirection).put(aPosition(begin1.x(), y1), pair(aPosition(x2++, begin2.y()), direction2));
                }
            } else if (begin2.x() > end2.x()) {
                int x2 = begin2.x();
                for (int y1 = begin1.y(); y1 <= end1.y(); y1++) {
                    cubeMap.get(startDirection).put(aPosition(begin1.x(), y1), pair(aPosition(x2--, begin2.y()), direction2));
                }
            }
        }
    }

    public int getScore() {
        Position currentPosition = aPosition(firstXPosition(0), 0);
        Direction currentDirection = RIGHT;
        for (String instruction : instructions) {
            switch (instruction) {
                case "L" -> currentDirection = currentDirection.turnLeft();
                case "R" -> currentDirection = currentDirection.turnRight();
                default -> {
                    int steps = parseInt(instruction);
                    int stepCounter = 0;
                    Position newPosition = getNewPosition(currentPosition, currentDirection);
                    while (stepCounter < steps && !newPosition.equals(currentPosition)) {
                        currentPosition = newPosition;
                        newPosition = getNewPosition(currentPosition, currentDirection);
                        stepCounter++;
                    }
                }
            }
        }

        return ((currentPosition.y()+1) * 1000) +
               ((currentPosition.x()+1) * 4) +
               (currentDirection.value);
    }

    public int getScore2() {
        Position currentPosition = aPosition(firstXPosition(0), 0);
        Direction currentDirection = RIGHT;
        for (String instruction : instructions) {
            switch (instruction) {
                case "L" -> currentDirection = currentDirection.turnLeft();
                case "R" -> currentDirection = currentDirection.turnRight();
                default -> {
                    int steps = parseInt(instruction);
                    int stepCounter = 0;
                    Pair newPair = getNewPosition2(currentPosition, currentDirection);
                    while (stepCounter < steps && !newPair.position().equals(currentPosition)) {
                        currentPosition = newPair.position();
                        currentDirection = newPair.direction();
                        newPair = getNewPosition2(currentPosition, currentDirection);
                        stepCounter++;
                    }
                }
            }
        }

        return ((currentPosition.y()+1) * 1000) +
               ((currentPosition.x()+1) * 4) +
               (currentDirection.value);
    }
//    private void printGrid(Position currentPosition, Direction currentDirection) {
//        System.out.println("=================");
//        for (int y = 0; y < grid.size(); y++) {
//            List<Integer> integers = grid.get(y);
//            for (int x = 0; x < integers.size(); x++) {
//                Integer integer = integers.get(x);
//                if (currentPosition.equals(aPosition(x, y))) {
//                    String player = switch (currentDirection) {
//                        case UP -> "^";
//                        case RIGHT -> ">";
//                        case DOWN -> "v";
//                        case LEFT -> "<";
//                    };
//                    System.out.print(player);
//                    continue;
//                }
//                String tile = switch (integer) {
//                    case 0 -> " ";
//                    case 1 -> ".";
//                    case 2 -> "#";
//                    default -> throw new RuntimeException("what is input " + integer + " ?");
//                };
//                System.out.print(tile);
//            }
//            System.out.println("");
//        }
//    }

    private int firstXPosition(int yPosition) {
        List<Integer> get = grid.get(yPosition);
        for (int i = 0; i < get.size(); i++) {
            Integer integer = get.get(i);
            if (integer == 2) {
                return lastXPosition(yPosition);
            }
            if (integer == 1) {
                return i;
            }
        }
        return 0;
    }

    private int lastXPosition(int yPosition) {
        List<Integer> get = grid.get(yPosition);
        for (int i = get.size() - 1; i >= 0; i--) {
            Integer integer = get.get(i);
            if (integer == 2) {
                return firstXPosition(yPosition);
            }
            if (integer == 1) {
                return i;
            }
        }
        return 0;
    }

    private int firstYPosition(int xPosition) {
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> integers = grid.get(i);
            if (integers.get(xPosition) == 2) {
                return lastYPosition(xPosition);
            }
            if (integers.get(xPosition) == 1) {
                return i;
            }
        }
        return 0;
    }

    private int lastYPosition(int xPosition) {
        for (int i = grid.size() - 1; i >= 0; i--) {
            List<Integer> integers = grid.get(i);
            if (integers.get(xPosition) == 2) {
                return firstYPosition(xPosition);
            }
            if (integers.get(xPosition) == 1) {
                return i;
            }
        }
        return 0;
    }

    private Position getNewPosition(Position position, Direction direction) {
        Position newPosition = position.move(direction);

        if (isOverLeftEdge(position, newPosition)) {
            return aPosition(lastXPosition(newPosition.y()), newPosition.y());
        }
        if (isOverRightEdge(position, newPosition)) {
            return aPosition(firstXPosition(newPosition.y()), newPosition.y());
        }

        if (isOverTopEdge(position, newPosition)) {
            return aPosition(newPosition.x(), lastYPosition(newPosition.x()));
        }
        if (isOverDownEdge(position, newPosition)) {
            return aPosition(newPosition.x(), firstYPosition(newPosition.x()));
        }

        if (isaRock(newPosition)) {
            return position;
        }

        return newPosition;
    }

    private Pair getNewPosition2(Position position, Direction direction) {
        Position newPosition = position.move(direction);

        if (cubeMap.get(direction).containsKey(newPosition)) {
            Pair pair = cubeMap.get(direction).get(newPosition);
            if (isaRock(pair.position())) {
                return pair(position, direction);
            }
            return pair;
        }

        if (isaRock(newPosition)) {
            return pair(position, direction);
        }

        return pair(newPosition, direction);
    }

    private boolean isOverDownEdge(Position position, Position newPosition) {
        return position.down().equals(newPosition) &&
               isEmptySpace(position) &&
               (newPosition.y() >= grid.size() || isVoidSpace(newPosition));
    }

    private boolean isOverTopEdge(Position position, Position newPosition) {
        return position.up().equals(newPosition) &&
               isEmptySpace(position) &&
               (newPosition.y() < 0 || isVoidSpace(newPosition));
    }

    private boolean isOverRightEdge(Position position, Position newPosition) {
        return position.right().equals(newPosition) &&
               isEmptySpace(position) &&
               (newPosition.x() >= grid.get(0).size() || isVoidSpace(newPosition));
    }

    private boolean isOverLeftEdge(Position position, Position newPosition) {
        return position.left().equals(newPosition) &&
               isEmptySpace(position) &&
               (newPosition.x() < 0 || isVoidSpace(newPosition));
    }

    private boolean isVoidSpace(Position newPosition) {
        return valueAtPosition(newPosition) == 0;
    }

    private boolean isEmptySpace(Position position) {
        return valueAtPosition(position) == 1;
    }

    private boolean isaRock(Position position) {
        return valueAtPosition(position) == 2;
    }

    private Integer valueAtPosition(Position position) {
        return grid.get(position.y()).get(position.x());
    }
}
