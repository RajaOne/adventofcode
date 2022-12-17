package com.raja.tmp.day16;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Volcanium {


    private List<Node> nodes = new ArrayList<>();

    public static Volcanium volcanium(String input) {
        Volcanium volcanium = new Volcanium();
        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] inputValues = line
                    .replace("Valve ", "")
                    .replace("has flow rate=", "")
                    .replaceAll("; tunnels? leads? to valves?", "")
                    .replaceAll(",", "")
                    .split(" ");


            String node = inputValues[0];
            int valve = Integer.parseInt(inputValues[1]);
            List<String> neighbours = new ArrayList<>();
            for (int i = 2; i < inputValues.length; i++) {
                neighbours.add(inputValues[i]);
            }
            volcanium.getNodes().add(new Node(node, valve, neighbours));

        }

        return volcanium;
    }

    public int getScore() {
        FloydWarshall floydWarshall = new FloydWarshall(nodes);
        floydWarshall.calculate();

        Node start = nodes.stream().filter(node -> node.getName().equals("AA")).findFirst().orElseThrow();

        DecisionNode decisionNode = new DecisionNode(start);
        decisionNode.traverse(floydWarshall, new HashMap<>(), 0);

        return decisionNode.getHighest();
    }

    public int getScore2() {
        FloydWarshall floydWarshall = new FloydWarshall(nodes);
        floydWarshall.calculate();

        Node start = nodes.stream().filter(node -> node.getName().equals("AA")).findFirst().orElseThrow();

        DecisionNode decisionNode = new DecisionNode(start);
        decisionNode.traverse2(floydWarshall, new HashMap<>(), 0, start);

        return decisionNode.getHighest();
    }

}
