package com.raja.tmp.day23;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.raja.tmp.day23.Tile.aTile;

@Getter
public class Grid {
    private final List<List<Tile>> matrix = new ArrayList<>();
    private final int SIZE = 1000;
    private final int offset = SIZE / 2;

    public Grid() {
        for (int y = 0; y < SIZE; y++) {
            List<Tile> yTiles = new ArrayList<>();
            for (int x = 0; x < SIZE; x++) {
                yTiles.add(aTile());
            }
            matrix.add(yTiles);
        }
    }

    public void addElf(Elf elf) {
        matrix.get(elf.y() + offset).get(elf.x() + offset).setElf(elf);
    }

    public boolean noElvesAround(Elf elf) {
        Position position = elf.getPosition();
        return elvesAround(position).isEmpty();
    }

    public List<Elf> elvesAround(Position position) {
        return Stream.of(
                        tileAt(position.up().left()),
                        tileAt(position.up()),
                        tileAt(position.up().right()),
                        tileAt(position.right()),
                        tileAt(position.down().right()),
                        tileAt(position.down()),
                        tileAt(position.down().left()),
                        tileAt(position.left()))
                .filter(Tile::hasElf)
                .map(Tile::getElf)
                .toList();
    }

    public Tile tileAt(Position position) {
        return matrix.get(position.y() + offset).get(position.x() + offset);
    }

    public boolean canMoveIn(Direction direction, Elf elf) {
        return direction.canMoveTo(elf, this);
    }

    public int hasElvesWithNextDestination(Position position) {
        return (int) elvesAround(position).stream()
                .filter(elf -> elf.getNextPosition().equals(position))
                .count();
    }

    public int firstXIndex() {
        for (int xIndex = 0; xIndex < matrix.get(0).size(); xIndex++) {
            for (List<Tile> tiles : matrix) {
                if (tiles.get(xIndex).hasElf()) {
                    return xIndex;
                }
            }
        }
        return 0;
    }

    public int lastXIndex() {
        for (int xIndex = matrix.get(0).size() - 1; xIndex >= 0; xIndex--) {
            for (List<Tile> tiles : matrix) {
                if (tiles.get(xIndex).hasElf()) {
                    return xIndex;
                }
            }
        }
        return 0;
    }

    public int firstYIndex() {
        for (int yIndex = 0; yIndex < matrix.size(); yIndex++) {
            List<Tile> tiles = matrix.get(yIndex);
            for (Tile tile : tiles) {
                if (tile.hasElf()) {
                    return yIndex;
                }
            }
        }
        return 0;
    }

    public int lastYIndex() {
        for (int yIndex = matrix.size() - 1; yIndex >= 0; yIndex--) {
            List<Tile> tiles = matrix.get(yIndex);
            for (Tile tile : tiles) {
                if (tile.hasElf()) {
                    return yIndex;
                }
            }
        }
        return 0;
    }
}
