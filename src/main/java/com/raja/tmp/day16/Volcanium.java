package com.raja.tmp.day16;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        decisionNode.traverse30(floydWarshall, new HashMap<>(), 0);

        return decisionNode.getHighest();
    }

    public int getScore2() {
        FloydWarshall floydWarshall = new FloydWarshall(nodes);
        floydWarshall.calculate();

        Node start = nodes.stream().filter(node -> node.getName().equals("AA")).findFirst().orElseThrow();

        DecisionNode decisionNode = new DecisionNode(start);
        decisionNode.traverse26(floydWarshall, new HashMap<>(), 0);

        int maxScore = 0;
        List<DecisionNode> decisionNodeList = decisionNode.allDecisions();
        List<DecisionNode> consideredDecisionNodeList = decisionNodeList.stream()
                .filter(decisionNode1 -> decisionNode1.getParents().size() > 5)
                .toList();
        System.out.println("considering " + consideredDecisionNodeList.size());
        for (int i = 0; i < consideredDecisionNodeList.size(); i++) {
            DecisionNode node1 = consideredDecisionNodeList.get(i);
            for (int j = i + 1; j < consideredDecisionNodeList.size(); j++) {
                DecisionNode node2 = consideredDecisionNodeList.get(j);
                if (node1 == node2) {
                    continue;
                }
                if (overlaps(node1.getParents(), node2.getParents())) {
                    continue;
                }
                if (maxScore < node1.getPressureByEndOfTime() + node2.getPressureByEndOfTime()) {
                    maxScore = node1.getPressureByEndOfTime() + node2.getPressureByEndOfTime();
                }
            }
        }

        return maxScore;
    }

    private boolean overlaps(Map<Node, Integer> parents1, Map<Node, Integer> parents2) {
        for (Node node : parents1.keySet()) {
            if (node.getName().equals("AA")) {
                continue;
            }
            if (parents2.containsKey(node)) {
                return true;
            }
        }
        return false;
    }

}
