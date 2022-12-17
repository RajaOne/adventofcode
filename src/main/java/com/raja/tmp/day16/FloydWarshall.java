package com.raja.tmp.day16;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FloydWarshall {

    private final List<Node> nodes;

    private final Map<Node, Map<Node, Integer>> matrix = new HashMap<>();

    public FloydWarshall(List<Node> nodes) {
        this.nodes = nodes;
        for (Node node : nodes) {
            matrix.put(node, new HashMap<>());
            matrix.get(node).put(node, 0);
            for (String neighbour : node.getNeighbours()) {
                Node neighbourNode = nodes.stream().filter(node1 -> node1.getName().equals(neighbour)).findFirst().orElseThrow();
                matrix.get(node).put(neighbourNode, 1);
            }
        }
    }


    public void calculate() {
        for (Node nodeK : nodes) {
            for (Node nodeI : nodes) {
                for (Node nodeJ : nodes) {
//                    if (dist[i][k] + dist[k][j] < dist[i][j])
//                        dist[i][j] = dist[i][k] + dist[k][j];
                    if (getDist(nodeI, nodeK) + getDist(nodeK, nodeJ) < getDist(nodeI, nodeJ)) {
                        matrix.get(nodeI).put(nodeJ, getDist(nodeI, nodeK) + getDist(nodeK, nodeJ));
                    }
                }
            }
        }
    }

    private int getDist(Node nodeI, Node nodeK) {

        if (matrix.get(nodeI).containsKey(nodeK)) {
            return matrix.get(nodeI).get(nodeK);
        }
        return 1000;
    }


}
