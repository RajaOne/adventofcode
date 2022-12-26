package com.raja.tmp.day20;

public record Number(int id, int value) {

    public static Number aNumber(int id, int value) {
        return new Number(id, value);
    }
}
