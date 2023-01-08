package com.raja.tmp.day24;

import lombok.Getter;

import java.util.List;
import java.util.Queue;

import static com.raja.tmp.day24.Blizzard.MAX_DEPTH;

@Getter
public class DecisionNode {

//	private List<DecisionNode> nodeList = new ArrayList<>();
	private final List<List<List<Tile>>> sequence;
	private final int minute;
//	private final ShortestDepthFound shortestDepthFound;
	private final Position position;
	private boolean reached = false;
//	private static final int MAX_DEPTH = 2_000;

	public DecisionNode(Position position, List<List<List<Tile>>> sequence, int minute) {
		this.position = position;
		this.sequence = sequence;
		this.minute = minute;
//		this.shortestDepthFound = shortestDepthFound;
	}

	public void visit(Queue<DecisionNode> queue) {
//		System.out.println(minute + ": visiting " + position.x() + ", " + position.y());
//		if (minute > MAX_DEPTH) {
//			return;
//		}
		if (position.y() == sequence.get(0).size() - 1) {
			System.out.println("Reached at " + minute);
			reached = true;
			return;
		}
		List<List<Tile>> blizzards = sequence.get(minute + 1);

		if (minute <= 10 || position.y() != 0) {
			visitPosition(blizzards, position, queue);
		}
		visitPosition(blizzards, position.left(), queue);
		visitPosition(blizzards, position.up(), queue);
		visitPosition(blizzards, position.down(), queue);
		visitPosition(blizzards, position.right(), queue);
	}

	private void visitPosition(List<List<Tile>> blizzards, Position position, Queue<DecisionNode> queue) {
		if (position.y() < 0) {
			return;
		}
		if (minute+1 > MAX_DEPTH) {
			return;
		}
//		if (minute*0.5 > position.x() + position.y()) {
//			return;
//		}
		if (blizzards.get(position.y()).get(position.x()).isEmpty()) {
			DecisionNode decisionNode = new DecisionNode(position, sequence, minute + 1);
//			nodeList.add(decisionNode);
			queue.add(decisionNode);
//			decisionNode.visit(null);
		}
	}

//	public boolean reached() {
//		return reached || nodeList.stream()
//				.anyMatch(DecisionNode::reached);
//	}

//	public int depth() {
//		return Math.min(reached ? minute : Integer.MAX_VALUE, nodeList.stream()
//				.map(DecisionNode::depth)
//				.reduce(Integer.MAX_VALUE, Integer::min));
//	}
}
