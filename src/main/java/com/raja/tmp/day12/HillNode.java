package com.raja.tmp.day12;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HillNode {
    private String name;

    private List<HillNode> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<HillNode, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(HillNode destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public HillNode(String name) {
        this.name = name;
    }
}
