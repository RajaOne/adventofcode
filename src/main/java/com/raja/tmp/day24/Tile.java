package com.raja.tmp.day24;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tile {

	private Position position;
	private List<Direction> directions = new ArrayList<>();
	private boolean wall = false;

	public Tile(Position position) {
		this.position = position;
	}

	public static Tile aWall(Position position) {
		Tile tile = new Tile(position);
		tile.setWall(true);
		return tile;
	}

	public static Tile emptySpace(Position position) {
		return new Tile(position);
	}

	public static Tile blizzard(Position position, Direction direction) {
		Tile tile = new Tile(position);
		tile.getDirections().add(direction);
		return tile;
	}

	public boolean isEmpty() {
		return (!wall) && directions.isEmpty();
	}
}
