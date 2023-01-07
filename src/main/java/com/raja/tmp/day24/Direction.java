package com.raja.tmp.day24;

import lombok.Getter;

@Getter
public enum Direction {
	UP("^") {
		@Override
		public Position move(Position position) {
			return position.up();
		}
	},
	DOWN("v") {
		@Override
		public Position move(Position position) {
			return position.down();
		}
	},
	RIGHT(">") {
		@Override
		public Position move(Position position) {
			return position.right();
		}
	},
	LEFT("<") {
		@Override
		public Position move(Position position) {
			return position.left();
		}
	};

	private final String str;

	Direction(String str) {
		this.str = str;
	}

	public abstract Position move(Position position);
}
