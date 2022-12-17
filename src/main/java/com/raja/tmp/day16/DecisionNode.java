package com.raja.tmp.day16;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DecisionNode {

    private List<DecisionNode> decisionNodes = new ArrayList<>();
    private Node node;
    private Integer pressureByEndOfTime = 0;

    public DecisionNode(Node node) {
        this.node = node;
    }

    public void traverse(FloydWarshall floydWarshall, Map<Node, Integer> parentWithMinutes, int minutes) {
        for (Map.Entry<Node, Integer> entry : parentWithMinutes.entrySet()) {
            if (entry.getKey().getName().equals("AA")) {
                continue;
            }
            int valve = entry.getKey().getValve();
            int atMinute = entry.getValue();
            pressureByEndOfTime += (30 - atMinute - 1) * valve;
        }
        pressureByEndOfTime += ((30 - minutes - 1) * node.getValve());

        for (Map.Entry<Node, Integer> neighbours : floydWarshall.getMatrix().get(node).entrySet()) {
            if (neighbours.getKey().getValve() == 0) {
                continue;
            }
            if (!parentWithMinutes.containsKey(neighbours.getKey()) && !neighbours.getKey().equals(node)) {
                if (minutes + 1 + neighbours.getValue() < 30) {
                    HashMap<Node, Integer> newParents = new HashMap<>(parentWithMinutes);
                    newParents.put(node, minutes);
                    DecisionNode decisionNode = new DecisionNode(neighbours.getKey());
                    decisionNodes.add(decisionNode);
                    if (node.getName().equals("AA")) {
                        decisionNode.traverse(floydWarshall, newParents, minutes + neighbours.getValue());
                    } else {
                        decisionNode.traverse(floydWarshall, newParents, minutes + 1 + neighbours.getValue());
                    }
                }
            }
        }
    }

    public void traverse2(FloydWarshall floydWarshall, Map<Node, Integer> parentWithMinutes, int minutes, Node root) {
        for (Map.Entry<Node, Integer> entry : parentWithMinutes.entrySet()) {
            if (entry.getKey().getName().equals("AA")) {
                continue;
            }
            int valve = entry.getKey().getValve();
            int atMinute = entry.getValue();
            pressureByEndOfTime += (26 - (atMinute%26) - 1) * valve;
        }
        pressureByEndOfTime += ((26 - (minutes%26) - 1) * node.getValve());

        Set<Map.Entry<Node, Integer>> allNeighbours = floydWarshall.getMatrix().get(node).entrySet();
        if (minutes < 26) {
            beginAtStartAgain(floydWarshall, parentWithMinutes, minutes, root);
        }
        for (Map.Entry<Node, Integer> neighbours : allNeighbours) {
            if (neighbours.getKey().getValve() == 0) {
                continue;
            }
            if (!parentWithMinutes.containsKey(neighbours.getKey()) && !neighbours.getKey().equals(node)) {
                if (minutes < 26 && minutes + 1 + neighbours.getValue() > 26 && decisionNodes.stream().noneMatch(decisionNode -> decisionNode.getNode() == root)) {
                    beginAtStartAgain(floydWarshall, parentWithMinutes, minutes, root);
                } else if (minutes + 1 + neighbours.getValue() < 52) {
                    HashMap<Node, Integer> newParents = new HashMap<>(parentWithMinutes);
                    newParents.put(node, minutes);
                    DecisionNode decisionNode = new DecisionNode(neighbours.getKey());
                    decisionNodes.add(decisionNode);
                    if (node.getName().equals("AA")) {
                        decisionNode.traverse2(floydWarshall, newParents, minutes + neighbours.getValue(), root);
                    } else {
                        decisionNode.traverse2(floydWarshall, newParents, minutes + 1 + neighbours.getValue(), root);
                    }
                }
            }
        }
    }

    private void beginAtStartAgain(FloydWarshall floydWarshall, Map<Node, Integer> parentWithMinutes, int minutes, Node root) {
        HashMap<Node, Integer> newParents = new HashMap<>(parentWithMinutes);
        newParents.put(node, minutes);
        DecisionNode decisionNode = new DecisionNode(root);
        decisionNodes.add(decisionNode);
        decisionNode.traverse2(floydWarshall, newParents, 26, root);
    }

    public int getHighest() {
        return Math.max(pressureByEndOfTime, decisionNodes.stream()
                .map(DecisionNode::getHighest)
                .reduce(0, Integer::max));
    }
}
