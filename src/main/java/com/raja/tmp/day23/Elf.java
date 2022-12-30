package com.raja.tmp.day23;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Elf {
    private Position position;
    private Position nextPosition;

    public Elf(Position position) {
        this.position = position;
    }

    public static Elf anElfAt(Position position) {
        return new Elf(position);
    }

    public int x() {
        return position.x();
    }

    public int y() {
        return position.y();
    }
}
