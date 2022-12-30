package com.raja.tmp.day23;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Tile {
    private Elf elf;

    public static Tile aTile() {
        return new Tile();
    }

    public boolean hasElf() {
        return Objects.nonNull(elf);
    }

    public boolean hasNoElf() {
        return !hasElf();
    }

    public void removeElf() {
        elf = null;
    }
}
