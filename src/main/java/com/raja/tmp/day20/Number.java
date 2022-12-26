package com.raja.tmp.day20;

public record Number(int id, long value) {

    public static Number aNumber(int id, long value) {
        return new Number(id, value);
    }
}
