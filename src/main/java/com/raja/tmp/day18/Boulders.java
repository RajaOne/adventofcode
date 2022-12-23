package com.raja.tmp.day18;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

@Getter
@Setter
public class Boulders {

    private List<List<List<Integer>>> grid;
    private List<Point> points = new ArrayList<>();
    private int size = 100;

    private Boulders() {
        this.grid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<List<Integer>> y = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                List<Integer> x = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    x.add(0);
                }
                y.add(x);
            }
            grid.add(y);
        }
    }

    public static Boulders boulders(String input) {
        Boulders boulders = new Boulders();

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] coordinates = line.split(",");
            int x = parseInt(coordinates[0]);
            int y = parseInt(coordinates[1]);
            int z = parseInt(coordinates[2]);

            boulders.getGrid().get(z).get(y).set(x, 1);
            boulders.getPoints().add(new Point(x,y,z));
        }

        return boulders;
    }


    public int getScore() {
        int exposedSides = 0;

        for (Point point : points) {

            exposedSides += 6;
            if (point.z() > 0 && grid.get(point.z()-1).get(point.y()).get(point.x()) == 1) {
                exposedSides--;
            }
            if (grid.get(point.z()+1).get(point.y()).get(point.x()) == 1) {
                exposedSides--;
            }

            if (point.y() > 0 && grid.get(point.z()).get(point.y()-1).get(point.x()) == 1) {
                exposedSides--;
            }
            if (grid.get(point.z()).get(point.y()+1).get(point.x()) == 1) {
                exposedSides--;
            }

            if (point.x() > 0 && grid.get(point.z()).get(point.y()).get(point.x()-1) == 1) {
                exposedSides--;
            }
            if (grid.get(point.z()).get(point.y()).get(point.x()+1) == 1) {
                exposedSides--;
            }
        }

        return exposedSides;
    }

    public int getScore2() {
        int reachableSides = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (isVisited(point)) {
                continue;
            }

            visited(point);

            if (point.z() > 0 && isBlock(point.down())) {
                reachableSides++;
            }
            if (point.z() > 0 && isEmpty(point.down())) {
                queue.add(point.down());
            }
            if (point.z() < size - 1 && isBlock(point.up())) {
                reachableSides++;
            }
            if (point.z() < size - 1 && isEmpty(point.up())) {
                queue.add(point.up());
            }

            if (point.y() > 0 && isBlock(point.south())) {
                reachableSides++;
            }
            if (point.y() > 0 && isEmpty(point.south())) {
                queue.add(point.south());
            }
            if (point.y() < size - 1 && isBlock(point.north())) {
                reachableSides++;
            }
            if (point.y() < size - 1 && isEmpty(point.north())) {
                queue.add(point.north());
            }

            if (point.x() > 0 && isBlock(point.left())) {
                reachableSides++;
            }
            if (point.x() > 0 && isEmpty(point.left())) {
                queue.add(point.left());
            }
            if (point.x() < size - 1 && isBlock(point.right())) {
                reachableSides++;
            }
            if (point.x() < size - 1 && isEmpty(point.right())) {
                queue.add(point.right());
            }
        }

        return reachableSides;
    }

    public boolean isBlock(Point point) {
        return grid.get(point.z()).get(point.y()).get(point.x()) == 1;
    }

    public boolean isEmpty(Point point) {
        return grid.get(point.z()).get(point.y()).get(point.x()) == 0;
    }

    public boolean isVisited(Point point) {
        return grid.get(point.z()).get(point.y()).get(point.x()) == 2;
    }

    public void visited(Point point) {
        grid.get(point.z()).get(point.y()).set(point.x(), 2);
    }
}
