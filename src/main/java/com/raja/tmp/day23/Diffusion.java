package com.raja.tmp.day23;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.raja.tmp.day23.Direction.*;
import static com.raja.tmp.day23.Elf.anElfAt;
import static com.raja.tmp.day23.Position.position;

@Getter
public class Diffusion {

    private final Grid grid = new Grid();
    private final List<Elf> elves = new ArrayList<>();
    private final List<Direction> directions = new LinkedList<>();
    private int directionIndex = 0;

    public static Diffusion diffusion(String input) {
        Diffusion diffusion = new Diffusion();
        String[] lines = input.split("\n");
        for (int yIndex = 0; yIndex < lines.length; yIndex++) {
            String line = lines[yIndex];
            String[] tiles = line.split("");
            for (int xIndex = 0; xIndex < tiles.length; xIndex++) {
                String tile = tiles[xIndex];
                if (tile.equals("#")) {
                    Elf elf = anElfAt(position(xIndex, yIndex));
                    diffusion.getGrid().addElf(elf);
                    diffusion.getElves().add(elf);
                }
            }
        }

        diffusion.getDirections().add(UP);
        diffusion.getDirections().add(DOWN);
        diffusion.getDirections().add(LEFT);
        diffusion.getDirections().add(RIGHT);

        return diffusion;
    }


    public int getScore() {
        for (int round = 0; round < 10; round++) {
            roundOne();

            for (Elf elf : elves) {
                Position position = elf.getPosition();
                Position nextPosition = elf.getNextPosition();
                if (grid.hasElvesWithNextDestination(nextPosition) == 1) {
                    grid.tileAt(position).removeElf();
                    grid.tileAt(nextPosition).setElf(elf);
                    elf.setPosition(nextPosition);
                }
            }
            directionIndex = (directionIndex + 1) % directions.size();
        }

        int firstXIndex = grid.firstXIndex();
        int lastXIndex = grid.lastXIndex() + 1;
        int firstYIndex = grid.firstYIndex();
        int lastYIndex = grid.lastYIndex() + 1;

        return ((lastXIndex - firstXIndex) * (lastYIndex - firstYIndex)) - elves.size();
    }

    public int getScore2() {

        boolean moved = true;
        int roundCounter = 0;
        while(moved) {
            moved = false;
            roundOne();

            for (Elf elf : elves) {
                Position position = elf.getPosition();
                Position nextPosition = elf.getNextPosition();
                if (grid.hasElvesWithNextDestination(nextPosition) == 1) {
                    grid.tileAt(position).removeElf();
                    grid.tileAt(nextPosition).setElf(elf);
                    elf.setPosition(nextPosition);
                    moved = true;
                }
            }
            directionIndex = (directionIndex + 1) % directions.size();
            roundCounter++;
        }

        return roundCounter;
    }

    private void roundOne() {
        for (Elf elf : elves) {
            elf.setNextPosition(elf.getPosition());
            if (grid.noElvesAround(elf)) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int elfDirectionIndex1 = (directionIndex + i) % directions.size();
                Direction direction = directions.get(elfDirectionIndex1);
                if (grid.canMoveIn(direction, elf)) {
                    elf.setNextPosition(elf.getPosition().move(direction));
                    break;
                }
            }
        }
    }

//    private void printGrid(int size) {
//        System.out.println("================");
//
//        List<List<Tile>> gridMatrix = grid.getMatrix();
//        for (int yIndex = grid.getOffset() - size; yIndex < grid.getOffset() + size; yIndex++) {
//            List<Tile> matrix = gridMatrix.get(yIndex);
//            for (int xIndex = grid.getOffset() - size; xIndex < grid.getOffset() + size; xIndex++) {
//                Tile tile = matrix.get(xIndex);
//                if (tile.hasElf()) {
//                    System.out.print("#");
//                } else {
//                    System.out.print(".");
//                }
//            }
//            System.out.println("");
//        }
//    }
}
