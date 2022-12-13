package com.raja.tmp.day1;

public record Elf (int total) {

    public static Elf elf() {
        return new Elf(0);
    }

    public Elf add(int toAdd) {
        return new Elf(total + toAdd);
    }
}
