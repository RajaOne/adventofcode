package com.raja.tmp.day24;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DecisionNode {

	private List<DecisionNode> nodeList = new ArrayList<>();
	private final List<List<List<Tile>>> sequence;
	private int minute;
	private Position position;
	private boolean reached = false;

	public DecisionNode(Position position, List<List<List<Tile>>> sequence, int minute) {
		this.position = position;
		this.sequence = sequence;
		this.minute = minute;
	}

	public void visit() {
		if (position.y() == sequence.get(0).get(0).size() - 1) {
			reached = true;
			return;
		}
		List<List<Tile>> blizzards = sequence.get(minute + 1);

		visitPosition(blizzards, position.up());
		visitPosition(blizzards, position.down());
		visitPosition(blizzards, position.left());
		visitPosition(blizzards, position.right());
		if (minute <= 5 || position.y() != 0) {
			visitPosition(blizzards, position);
		}

		nodeList = nodeList.stream()
				.filter(DecisionNode::reached)
				.toList();

	}

	private void visitPosition(List<List<Tile>> blizzards, Position position) {
		if (position.y() < 1) {
			return;
		}
		if (blizzards.get(position.y()).get(position.x()).isEmpty()) {
			DecisionNode decisionNode = new DecisionNode(position, sequence, minute + 1);
			nodeList.add(decisionNode);
			decisionNode.visit();
		}
	}

	public boolean reached() {
		return nodeList.stream()
				.anyMatch(DecisionNode::reached);
	}

	public int depth() {
		return Math.max(minute, nodeList.stream()
				.map(DecisionNode::getMinute)
				.reduce(0, Integer::max));
	}
}
