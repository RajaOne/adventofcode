package com.raja.tmp.day12;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.raja.tmp.day12.HillGraph.calculateShortestPathFromSource;

@Getter
@Setter
public class HillClimb {

    private HillGraph graph;
    private List<List<HillNode>> hill;

    public HillClimb() {
        graph = new HillGraph();
        hill = new ArrayList<>();
    }

    public static HillClimb hillClimbAnyA(String input) {
        return getHillClimb(input, true);
    }

    public static HillClimb hillClimb(String input) {
        return getHillClimb(input, false);
    }

    private static HillClimb getHillClimb(String input, boolean anyA) {
        HillClimb hillClimb = new HillClimb();
        String[] lines = input.split("\n");
        List<List<HillNode>> hill = hillClimb.getHill();
        int lineIndex = 0;
        while (lineIndex < lines.length) {
            String line = lines[lineIndex];
            List<HillNode> xHills = new ArrayList<>();
            String[] xHillStrings = line.split("");
            for (String xHillString : xHillStrings) {
                xHills.add(new HillNode(xHillString));
            }
            hill.add(xHills);
            lineIndex++;
        }

        for (List<HillNode> yHills : hill) {
            for (HillNode node : yHills) {
                hillClimb.getGraph().addNode(node);
            }
        }

        for (int y = 0; y < hill.size(); y++) {
            List<HillNode> yHills = hill.get(y);
            for (int x = 0; x < yHills.size(); x++) {
                HillNode node = yHills.get(x);
                int value = getValue(node.getName());

                extracted(hill, y, yHills, x, node, value, anyA);
            }
        }

        return hillClimb;
    }

    private static void extracted(List<List<HillNode>> hill, int y, List<HillNode> yHills, int x, HillNode node, int value, boolean anyA) {
        if (x > 0) {
            addDestination(node, value, hill.get(y).get(x - 1), anyA);
        }
        if (x < yHills.size() - 1) {
            addDestination(node, value, hill.get(y).get(x + 1), anyA);
        }
        if (y > 0) {
            addDestination(node, value, hill.get(y - 1).get(x), anyA);
        }
        if (y < hill.size() - 1) {
            addDestination(node, value, hill.get(y + 1).get(x), anyA);
        }
    }

    private static void addDestination(HillNode node, int value, HillNode adjacent, boolean anyA) {
        int adjacentValue = getValue(adjacent.getName());
        int distance = adjacentValue - value;
        if (!node.getName().equals("z") && adjacent.getName().equals("E")) {
            return;
        }
        if (node.getName().equals("S") || (node.getName().equals("z") && adjacent.getName().equals("E"))) {
            distance = 1;
        }
        if (distance <= 1) {
            if (anyA) {
                if ((node.getName().equals("S") || node.getName().equals("a")) && adjacent.getName().equals("a")) {
                    node.addDestination(adjacent, 0);
                } else {
                    node.addDestination(adjacent, 1);
                }
            } else {
                node.addDestination(adjacent, 1);
            }
        }
    }

    private static char getValue(String xHill) {
        return xHill.toCharArray()[0];
    }

    public int getScore() {
        HillNode source = graph.getNodes().stream()
                .filter(hillNode -> hillNode.getName().equals("S"))
                .findFirst()
                .orElseThrow();

        HillGraph hillGraph = calculateShortestPathFromSource(graph, source);

        HillNode e = hillGraph.getNodes().stream()
                .filter(hillNode -> hillNode.getName().equals("E"))
                .findFirst()
                .orElseThrow();

        return e.getDistance();
    }
}
