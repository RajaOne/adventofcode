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
    private Map<Node, Integer> parents = new HashMap<>();

    public DecisionNode(Node node) {
        this.node = node;
    }

    public void traverse30(FloydWarshall floydWarshall, Map<Node, Integer> parentWithMinutes, int minutes) {
        traverse(floydWarshall, parentWithMinutes, minutes, 30);
    }

    public void traverse26(FloydWarshall floydWarshall, Map<Node, Integer> parentWithMinutes, int minutes) {
        traverse(floydWarshall, parentWithMinutes, minutes, 26);
    }

    public void traverse(FloydWarshall floydWarshall, Map<Node, Integer> parentWithMinutes, int minutes, int maxMinutes) {
        parents = new HashMap<>(parentWithMinutes);
        parents.put(node, minutes);
        for (Map.Entry<Node, Integer> entry : parentWithMinutes.entrySet()) {
            if (entry.getKey().getName().equals("AA")) {
                continue;
            }
            int valve = entry.getKey().getValve();
            int atMinute = entry.getValue();
            pressureByEndOfTime += (maxMinutes - atMinute - 1) * valve;
        }
        pressureByEndOfTime += ((maxMinutes - minutes - 1) * node.getValve());

        for (Map.Entry<Node, Integer> neighbours : floydWarshall.getMatrix().get(node).entrySet()) {
            if (neighbours.getKey().getValve() == 0) {
                continue;
            }
            if (!parentWithMinutes.containsKey(neighbours.getKey()) && !neighbours.getKey().equals(node)) {
                if (minutes + 1 + neighbours.getValue() < maxMinutes) {
                    DecisionNode decisionNode = new DecisionNode(neighbours.getKey());
                    decisionNodes.add(decisionNode);
                    if (node.getName().equals("AA")) {
                        decisionNode.traverse(floydWarshall, parents, minutes + neighbours.getValue(), maxMinutes);
                    } else {
                        decisionNode.traverse(floydWarshall, parents, minutes + 1 + neighbours.getValue(), maxMinutes);
                    }
                }
            }
        }
    }

    public List<DecisionNode> allDecisions() {
        List<DecisionNode> decisionNodeList = new ArrayList<>();
        decisionNodeList.add(this);
        for (DecisionNode decisionNode : decisionNodes) {
            decisionNodeList.addAll(decisionNode.allDecisions());
        }
        return decisionNodeList;
    }

    public int getHighest() {
        return Math.max(pressureByEndOfTime, decisionNodes.stream()
                .map(DecisionNode::getHighest)
                .reduce(0, Integer::max));
    }
}
