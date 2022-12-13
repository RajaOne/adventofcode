package com.raja.tmp.day8;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.raja.tmp.day8.ForrestTile.forrestTile;

@Getter
public class ForrestGrid {

    private final List<List<ForrestTile>> grid;

    private ForrestGrid(int gridSize) {
        grid = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            grid.add(new ArrayList<>());
        }
    }

    public static ForrestGrid forrestGrid(String input, int gridSize) {
        ForrestGrid forrestGrid = new ForrestGrid(gridSize);

        String[] lines = input.split("\n");
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            String[] trees = line.split("");
            for (int x = 0; x < trees.length; x++) {
                int tree = Integer.parseInt(trees[x]);
                forrestGrid.getGrid().get(x).add(forrestTile(tree));
            }
        }

//        forrestGrid.printGrid();
//        forrestGrid.printVisibility();
        for (List<ForrestTile> forrestTiles : forrestGrid.getGrid()) {
            int highestValue = forrestTiles.get(0).getHeight();
            forrestTiles.get(0).makeVisible();
            for (int yIndex = 1; yIndex < gridSize; yIndex++) {
                ForrestTile forrestTile = forrestTiles.get(yIndex);
                if (forrestTile.getHeight() > highestValue) {
                    forrestTile.makeVisible();
                    highestValue = forrestTile.getHeight();
                }
            }
        }

//        forrestGrid.printVisibility();
        for (List<ForrestTile> forrestTiles : forrestGrid.getGrid()) {
            int highestValue = forrestTiles.get(gridSize - 1).getHeight();
            forrestTiles.get(gridSize - 1).makeVisible();
            for (int yIndex = gridSize - 2; yIndex > 0; yIndex--) {
                ForrestTile forrestTile = forrestTiles.get(yIndex);
                if (forrestTile.getHeight() > highestValue) {
                    forrestTile.makeVisible();
                    highestValue = forrestTile.getHeight();
                }
            }
        }

//        forrestGrid.printVisibility();
        for (int y = 0; y < gridSize; y++) {
            int highestValue = forrestGrid.getGrid().get(0).get(y).getHeight();
            forrestGrid.getGrid().get(0).get(y).makeVisible();
            for (int xIndex = 1; xIndex < gridSize; xIndex++) {
                ForrestTile forrestTile = forrestGrid.getGrid().get(xIndex).get(y);
                if (forrestTile.getHeight() > highestValue) {
                    forrestTile.makeVisible();
                    highestValue = forrestTile.getHeight();
                }
            }
        }

//        forrestGrid.printVisibility();
        for (int y = 0; y < gridSize; y++) {
            int highestValue = forrestGrid.getGrid().get(gridSize - 1).get(y).getHeight();
            forrestGrid.getGrid().get(gridSize - 1).get(y).makeVisible();
            for (int xIndex = gridSize - 2; xIndex > 0; xIndex--) {
                ForrestTile forrestTile = forrestGrid.getGrid().get(xIndex).get(y);
                if (forrestTile.getHeight() > highestValue) {
                    forrestTile.makeVisible();
                    highestValue = forrestTile.getHeight();
                }
            }
        }

//        forrestGrid.printVisibility();
        List<List<ForrestTile>> lists = forrestGrid.grid;
        for (int x = 0; x < lists.size(); x++) {
            List<ForrestTile> forrestTiles = lists.get(x);
            for (int y = 0; y < forrestTiles.size(); y++) {
                ForrestTile forrestTile = forrestTiles.get(y);

                int visibleUp = 0;
                int yScanUp = y - 1;
                while (yScanUp >= 0 && lists.get(x).get(yScanUp).getHeight() < forrestTile.getHeight()) {
                    visibleUp++;
                    yScanUp--;
                }
                if (yScanUp >= 0) {
                    visibleUp++;
                }

                int visibleDown = 0;
                int yScanDown = y + 1;
                while (yScanDown < gridSize && lists.get(x).get(yScanDown).getHeight() < forrestTile.getHeight()) {
                    visibleDown++;
                    yScanDown++;
                }
                if (yScanDown < gridSize) {
                    visibleDown++;
                }

                int visibleRight = 0;
                int xScanRight = x + 1;
                while (xScanRight < gridSize && lists.get(xScanRight).get(y).getHeight() < forrestTile.getHeight()) {
                    visibleRight++;
                    xScanRight++;
                }
                if (xScanRight < gridSize) {
                    visibleRight++;
                }

                int visibleLeft = 0;
                int xScanLeft = x - 1;
                while (xScanLeft >= 0 && lists.get(xScanLeft).get(y).getHeight() < forrestTile.getHeight()) {
                    visibleLeft++;
                    xScanLeft--;
                }
                if (xScanLeft >= 0) {
                    visibleLeft++;
                }

                int score = visibleUp * visibleDown * visibleRight * visibleLeft;
                forrestTile.setScore(score);
            }
        }
//        forrestGrid.printScore();

        return forrestGrid;
    }

    private void printGrid() {
        System.out.println("=== grid ===");
        for (int y = 0; y < grid.size(); y++) {
            String str = "";
            for (int x = 0; x < grid.size(); x++) {
                ForrestTile forrestTile = grid.get(x).get(y);
                str += Integer.toString(forrestTile.getHeight());
            }
            System.out.println(str);
        }
        System.out.println("");
    }

    private void printVisibility() {
        System.out.println("=== visi ===");
        for (int y = 0; y < grid.size(); y++) {
            String str = "";
            for (int x = 0; x < grid.size(); x++) {
                ForrestTile forrestTile = grid.get(x).get(y);
                str += forrestTile.isVisible() ? 1 : 0;
            }
            System.out.println(str);
        }
        System.out.println("");
    }

    private void printScore() {
        System.out.println("=== score ===");
        for (int y = 0; y < grid.size(); y++) {
            String str = "";
            for (int x = 0; x < grid.size(); x++) {
                ForrestTile forrestTile = grid.get(x).get(y);
                str += Integer.toString(forrestTile.getScore());
            }
            System.out.println(str);
        }
        System.out.println("");
    }

    public int getVisible() {
        return grid.stream()
                .flatMap(Collection::stream)
                .filter(ForrestTile::isVisible)
                .toList().size();
    }

    public int getHighestScore() {
        return grid.stream()
                .flatMap(Collection::stream)
                .map(forrestTile -> forrestTile.getScore())
                .reduce(0, Integer::max);
    }
}
