package com.raja.tmp.day22;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.raja.tmp.day22.Direction.RIGHT;
import static com.raja.tmp.day22.Position.aPosition;
import static java.lang.Integer.parseInt;

@Getter
public class MonkeyMap {

    private final List<List<Integer>> grid = new ArrayList<>();
    private final List<String> instructions = new ArrayList<>();

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

        return monkeyMap;
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
//            printGrid(currentPosition, currentDirection);
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

        if (valueAtPosition(newPosition) == 2) {
            return position;
        }

        return newPosition;
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

    private Integer valueAtPosition(Position position) {
        return grid.get(position.y()).get(position.x());
    }

}
