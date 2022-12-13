package com.raja.tmp.day12;

import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

@Getter
public class HillGraph {

    private Set<HillNode> nodes = new HashSet<>();

    public void addNode(HillNode nodeA) {
        nodes.add(nodeA);
    }

    public static HillGraph calculateShortestPathFromSource(HillGraph graph, HillNode source) {
        source.setDistance(0);

        Set<HillNode> settledNodes = new HashSet<>();
        Set<HillNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            HillNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<HillNode, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                HillNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static HillNode getLowestDistanceNode(Set<HillNode> unsettledNodes) {
        HillNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (HillNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(HillNode evaluationNode,
                                                 Integer edgeWeigh, HillNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<HillNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
