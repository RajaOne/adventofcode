package com.raja.tmp.day15;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Comparator.comparingInt;

@Getter
@Setter
public class Ranges {

    private List<Range> rangeList;

    public Ranges() {
        rangeList = new ArrayList<>();
    }

    public boolean doesNotContain(Range other) {
        for (Range range : rangeList) {
            if (range.contains(other)) {
                return false;
            }
        }
        return true;
    }

    public void mergeRange(Range aRange) {
        rangeList.add(aRange);
        rangeList.sort(comparingInt(Range::getBegin));

        List<Range> newList = new ArrayList<>();
        for (Range range : rangeList) {
            mergeToList(newList, range);
        }
        rangeList = newList;
    }

    private void mergeToList(List<Range> newList, Range range) {
        if (newList.isEmpty()) {
            newList.add(range);
            return;
        }

        Range range1 = newList.get(newList.size() - 1);
        if (range1.contains(range)) {
            return;
        }
        if (range.contains(range1)) {
            range1.setBegin(range.getBegin());
            range1.setEnd(range.getEnd());
            return;
        }
        if (range1.overlaps(range)) {
            range1.setBegin(min(range.getBegin(), range1.getBegin()));
            range1.setEnd(max(range.getEnd(), range1.getEnd()));
            return;
        }
        if (range1.onEdge(range)) {
            range1.setBegin(min(range.getBegin(), range1.getBegin()));
            range1.setEnd(max(range.getEnd(), range1.getEnd()));
            return;
        }
        newList.add(range);
    }
}
