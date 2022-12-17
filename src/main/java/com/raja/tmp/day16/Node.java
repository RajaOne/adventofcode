package com.raja.tmp.day16;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Node {

    private String name;
    private int valve;
    private List<String> neighbours;

    public Node(String name, int valve, List<String> neighbours) {
        this.name = name;
        this.valve = valve;
        this.neighbours = neighbours;
    }

}
