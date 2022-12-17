package com.raja.tmp.day15;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Range {
    private int begin;
    private int end;

    private Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public static Range aRange(int begin, int end) {
        return new Range(begin, end);
    }

    public void append(int value) {
        if (begin - 1 == value) {
            begin--;
        }
        if (end + 1 == value) {
            end++;
        }
    }

    public boolean onEdge(Range range2) {
        return begin - 1 == range2.getEnd() || end + 1 == range2.getBegin();
    }

    public boolean contains(Range other) {
        return begin <= other.getBegin() && other.getEnd() <= end;
    }

    public boolean overlaps(Range other) {
        return begin <= other.getEnd() &&
               other.getBegin() <= end;
    }
}
