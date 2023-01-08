package com.raja.tmp.day24;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Getter
public class DecisionNode {

	private List<DecisionNode> nodeList = new ArrayList<>();
	private final List<List<List<Tile>>> sequence;
	private final int minute;
	private final ShortestDepthFound shortestDepthFound;
	private final Position position;
	private boolean reached = false;
	private static final int MAX_DEPTH = 2_000;

	public DecisionNode(Position position, List<List<List<Tile>>> sequence, int minute, ShortestDepthFound shortestDepthFound) {
		this.position = position;
		this.sequence = sequence;
		this.minute = minute;
		this.shortestDepthFound = shortestDepthFound;
	}

	public void visit(Queue<DecisionNode> queue) {
//		System.out.println(minute + ": visiting " + position.x() + ", " + position.y());
		if (minute >= shortestDepthFound.getShortestDepth() || minute > MAX_DEPTH) {
			return;
		}
//		if (minute > 100 && position.x() + position.y() < 40) {
//			return;
//		}
		if (position.y() == sequence.get(0).size() - 1) {
			System.out.println("Reached at " + minute);
			reached = true;
			if (shortestDepthFound.getShortestDepth() > minute) {
				shortestDepthFound.setShortestDepth(minute);
			}
			return;
		}
		List<List<Tile>> blizzards = sequence.get(minute + 1);

		visitPosition(blizzards, position.down());
		visitPosition(blizzards, position.right());
		visitPosition(blizzards, position.left());
		visitPosition(blizzards, position.up());
		if (minute <= 10 || position.y() != 0) {
			visitPosition(blizzards, position);
		}

		nodeList = nodeList.stream()
				.filter(DecisionNode::reached)
				.toList();

		int minDepth = depth();
		nodeList = nodeList.stream()
				.filter(decisionNode -> decisionNode.depth() == minDepth)
				.toList();

	}

	private void visitPosition(List<List<Tile>> blizzards, Position position) {
		if (position.y() < 0) {
			return;
		}
		if (minute+1 >= shortestDepthFound.getShortestDepth() || minute+1 > MAX_DEPTH) {
			return;
		}
		if (blizzards.get(position.y()).get(position.x()).isEmpty()) {
			DecisionNode decisionNode = new DecisionNode(position, sequence, minute + 1, shortestDepthFound);
			nodeList.add(decisionNode);
			decisionNode.visit(null);
		}
	}

	public boolean reached() {
		return reached || nodeList.stream()
				.anyMatch(DecisionNode::reached);
	}

	public int depth() {
		return Math.min(reached ? minute : Integer.MAX_VALUE, nodeList.stream()
				.map(DecisionNode::depth)
				.reduce(Integer.MAX_VALUE, Integer::min));
	}
}
