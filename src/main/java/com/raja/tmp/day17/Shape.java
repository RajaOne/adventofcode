package com.raja.tmp.day17;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Shape {

    private List<Position> positionList = new ArrayList<>();


    public Shape copy() {
        Shape copy = new Shape();
        for (Position position : positionList) {
            copy.getPositionList().add(position.copy());
        }
        return copy;
    }

    public int bottom() {
        return 0;
//        positionList.stream()
//                .map(Position::y)
//                .reduce(5000L, Long::min);
    }

    public int leftest() {
        return 0;
//        positionList.stream()
//                .map(Position::x)
//                .reduce(5000, Integer::min);
    }

    public void moveRight(int toMoveToRight) {
        positionList = positionList.stream()
                .map(position -> position.moveRight(toMoveToRight))
                .toList();
    }

    public void moveUp(long toMoveUp) {
        positionList = positionList.stream()
                .map(position -> position.moveUp(toMoveUp))
                .toList();
    }

    public void moveLeft() {
        positionList = positionList.stream()
                .map(Position::left)
                .toList();
    }

    public void moveRight() {
        positionList = positionList.stream()
                .map(Position::right)
                .toList();
    }

    public void moveDown() {
        positionList = positionList.stream()
                .map(Position::down)
                .toList();
    }

    public boolean canMoveDown(List<List<Integer>> grid) {
        for (Position position : positionList) {
            Position down = position.down();
            if (down.y() < 0) {
                return false;
            }
            if (grid.get((int) down.y()).get(down.x()) == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveDown(LinkedList<List<Integer>> gridList, long gridOffset) {
        for (Position position : positionList) {
            Position down = position.down();
            if (down.y() < 0) {
                return false;
            }
            if (gridList.get((int) (down.y() - gridOffset)).get(down.x()) == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveLeft(List<List<Integer>> grid) {
        for (Position position : positionList) {
            Position left = position.left();
            if (left.x() < 0) {
                return false;
            }
            if (grid.get((int) left.y()).get(left.x()) == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveLeft(LinkedList<List<Integer>> queue, long gridOffset) {
        for (Position position : positionList) {
            Position left = position.left();
            if (left.x() < 0) {
                return false;
            }
            if (queue.get((int) (left.y() - gridOffset)).get(left.x()) == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveRight(List<List<Integer>> grid) {
        for (Position position : positionList) {
            Position right = position.right();
            if (right.x() > grid.get(0).size() - 1) {
                return false;
            }
            if (grid.get((int) right.y()).get(right.x()) == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveRight(LinkedList<List<Integer>> gridList, long gridOffset) {
        for (Position position : positionList) {
            Position right = position.right();
            if (right.x() > gridList.peekFirst().size() - 1) {
                return false;
            }
            if (gridList.get((int) (right.y() - gridOffset)).get(right.x()) == 1) {
                return false;
            }
        }
        return true;
    }
}
