package com.raja.tmp.day24;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.raja.tmp.day24.Direction.*;
import static com.raja.tmp.day24.Position.position;
import static com.raja.tmp.day24.Tile.aWall;
import static com.raja.tmp.day24.Tile.emptySpace;

@Getter
public class Blizzard {

	private List<List<Tile>> grid = new ArrayList<>();
	private final List<List<List<Tile>>> sequence = new ArrayList<>();

	public static Blizzard blizzard(String input) {
		Blizzard blizzard = new Blizzard();
		String[] lines = input.split("\n");
		for (int y = 0; y < lines.length; y++) {
			String line = lines[y];
			String[] tileStrings = line.split("");
			List<Tile> tiles = new ArrayList<>();
			for (int x = 0; x < tileStrings.length; x++) {
				String tileString = tileStrings[x];
				Tile tile = switch (tileString) {
					case "#" -> aWall(position(x, y));
					case "." -> emptySpace(position(x, y));
					case "^" -> Tile.blizzard(position(x, y), UP);
					case "v" -> Tile.blizzard(position(x, y), DOWN);
					case ">" -> Tile.blizzard(position(x, y), RIGHT);
					case "<" -> Tile.blizzard(position(x, y), LEFT);
					default -> throw new RuntimeException("what is input " + tileString + " ?");
				};
				tiles.add(tile);
			}
			blizzard.getGrid().add(tiles);
		}
		return blizzard;
	}


	private void printGrid() {
		System.out.println("==================");
		for (List<Tile> tiles : grid) {
			for (Tile tile : tiles) {
				if (tile.isWall()) {
					System.out.print("#");
				} else if (tile.isEmpty()) {
					System.out.print(".");
				} else if (tile.getDirections().size() > 1) {
					System.out.print(tile.getDirections().size());
				} else if (!tile.getDirections().isEmpty()) {
					System.out.print(tile.getDirections().get(0).getStr());
				}
			}
			System.out.println("");
		}
	}

	public int getScore() {
//		printGrid();
		List<List<Tile>> moved = grid;
		for (int i = 0; i < 10_000; i++) {
			sequence.add(moved);
			grid = moved;
			moved = moveGrid();
		}


		var startingPosition = grid.get(0).stream()
				.filter(Tile::isEmpty)
				.map(Tile::getPosition)
				.findFirst()
				.orElseThrow();

		ShortestDepthFound shortestDepthFound = new ShortestDepthFound();
//		Queue<DecisionNode> queue = new LinkedList<>();
		DecisionNode decisionNode = new DecisionNode(startingPosition, sequence, 0, shortestDepthFound);
//		queue.add(decisionNode);
//		boolean reached = false;
//		while (!queue.isEmpty() && !reached) {
//			DecisionNode node = queue.poll();
//			node.visit(queue);
//			if (node.isReached()) {
//				reached = true;
//			}
//		}
		decisionNode.visit(null);

		return decisionNode.depth();
	}

	private List<List<Tile>> moveGrid() {
		List<List<Tile>> newGrid = new ArrayList<>();
		for (int y = 0; y < grid.size(); y++) {
			ArrayList<Tile> tiles = new ArrayList<>();
			for (int x = 0; x < grid.get(0).size(); x++) {
				tiles.add(emptySpace(position(x, y)));
			}
			newGrid.add(tiles);
		}

		for (int y = 0; y < grid.size(); y++) {
			List<Tile> tiles = grid.get(y);
			for (int x = 0; x < tiles.size(); x++) {
				Tile tile = tiles.get(x);
				if (tile.isWall()) {
					newGrid.get(y).set(x, aWall(position(x, y)));
				} else if (!tile.getDirections().isEmpty()) {
					for (Direction direction : tile.getDirections()) {
						Position position = calcPositionFrom(tile.getPosition().move(direction));
						newGrid.get(position.y()).get(position.x()).getDirections().add(direction);
					}
				}
			}
		}
		return newGrid;
	}

	private Position calcPositionFrom(Position move) {
		if (move.y() == 0) {
			return position(move.x(), grid.size() - 2);
		}
		if (move.y() == grid.size() - 1) {
			return position(move.x(), 1);
		}
		if (move.x() == 0) {
			return position(grid.get(0).size() - 2, move.y());
		}
		if (move.x() == grid.get(0).size() - 1) {
			return position(1, move.y());
		}
		return move;
	}
}
