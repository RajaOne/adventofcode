package com.raja.tmp.day9;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.raja.tmp.day9.Direction.UP;
import static com.raja.tmp.day9.Direction.directionFrom;
import static com.raja.tmp.day9.Position.position;
import static com.raja.tmp.day9.RopeTile.aTile;
import static java.lang.Integer.parseInt;

@Getter
@Setter
public class RopeBridge {

    List<List<RopeTile>> grid;
    Position headPosition;
    Position talePosition;
    Direction currentDirection;
    List<Position> tails;

    private RopeBridge(int size) {
        tails = new ArrayList<>();
        headPosition = position(size / 2, size / 2);
        talePosition = position(size / 2, size / 2);
        currentDirection = UP;
        grid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<RopeTile> tiles = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                tiles.add(aTile());
            }
            grid.add(tiles);
        }
        grid.get(talePosition.x()).get(talePosition.y()).setVisited(true);
    }

    public static RopeBridge ropeBridge(String input) {
        RopeBridge ropeBridge = new RopeBridge(500);
        String[] commands = input.split("\n");
        for (String command : commands) {
            String[] dirAndAmount = command.split(" ");
            Direction direction = directionFrom(dirAndAmount[0]);
            int amount = parseInt(dirAndAmount[1]);
            ropeBridge.setCurrentDirection(direction);
            ropeBridge.move(amount);
        }

        return ropeBridge;
    }

    public static RopeBridge ropeBridge9(String input) {
        RopeBridge ropeBridge = new RopeBridge(500);
        for (int i = 0; i < 9; i++) {
            ropeBridge.getTails().add(position(500/2, 500/2));
        }
        String[] commands = input.split("\n");
        for (String command : commands) {
            String[] dirAndAmount = command.split(" ");
            Direction direction = directionFrom(dirAndAmount[0]);
            int amount = parseInt(dirAndAmount[1]);
            ropeBridge.setCurrentDirection(direction);
            ropeBridge.moveTails(amount);
        }

        return ropeBridge;
    }

    private void moveTails(int amount) {
        for (int i = 0; i < amount; i++) {
            headPosition = currentDirection.move(headPosition);
            Position head = headPosition;
            List<Position> newTails = new ArrayList<>();
            for (Position tail : tails) {
                if (tail.distanceTo(head) > 1) {
                    tail = tail.moveTo(head);
                }
                newTails.add(tail);
                head = tail;
            }
            tails = newTails;
            grid.get(tails.get(8).x()).get(tails.get(8).y()).setVisited(true);
        }
    }

    private void move(int amount) {
        for (int i = 0; i < amount; i++) {
            headPosition = currentDirection.move(headPosition);
            if (talePosition.distanceTo(headPosition) > 1) {
                talePosition = talePosition.moveTo(headPosition);
            }
            grid.get(talePosition.x()).get(talePosition.y()).setVisited(true);
        }
    }

    public int getCount() {
        return grid.stream()
                .flatMap(Collection::stream)
                .filter(RopeTile::isVisited)
                .toList().size();
    }
}
