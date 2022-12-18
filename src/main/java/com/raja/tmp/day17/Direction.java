package com.raja.tmp.day17;

import java.util.LinkedList;
import java.util.List;

public enum Direction {
    LEFT {
        @Override
        public void move(Shape shape, List<List<Integer>> grid) {
            if (shape.canMoveLeft(grid)) {
                shape.moveLeft();
            }
        }

        @Override
        public void move(Shape shape, LinkedList<List<Integer>> gridList, long gridOffset) {
            if (shape.canMoveLeft(gridList, gridOffset)) {
                shape.moveLeft();
            }
        }
    },
    RIGHT {
        @Override
        public void move(Shape shape, List<List<Integer>> grid) {
            if (shape.canMoveRight(grid)) {
                shape.moveRight();
            }
        }

        @Override
        public void move(Shape shape, LinkedList<List<Integer>> gridList, long gridOffset) {
            if (shape.canMoveRight(gridList, gridOffset)) {
                shape.moveRight();
            }
        }
    };


    public static Direction directionFromString(String in) {
        return switch (in) {
            case ">" -> RIGHT;
            case "<" -> LEFT;
            default -> throw new RuntimeException("cannot read '" + in + "'");
        };
    }

    public abstract void move(Shape shape, LinkedList<List<Integer>> gridList, long gridOffset);

    public abstract void move(Shape shape, List<List<Integer>> grid);
}
