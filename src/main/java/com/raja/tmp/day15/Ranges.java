package com.raja.tmp.day15;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.raja.tmp.day15.Range.singleValueRange;
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

    public void addValue(int value) {
        merge(value);
        Collections.sort(rangeList, comparingInt(Range::getBegin));

//        List<Range> newList = new ArrayList<>();
        Range range1 = rangeList.get(0);
//        newList.add(range1);
        for (int i = 1; i < rangeList.size(); i++) {
            Range range2 = rangeList.get(i);
            if (range1.onEdge(range2)) {
                range1.setBegin(min(range1.getBegin(), range2.getBegin()));
                range1.setEnd(max(range1.getEnd(), range2.getEnd()));
//            } else {
//                newList.add(range2);
            }
            range1 = range2;
        }
//        rangeList = newList;
    }

    private void merge(int value) {
        for (Range range : rangeList) {
            if (range.contains(value)) {
                return;
            }
            if (range.onEdge(value)) {
                range.append(value);
                return;
            }
        }
        rangeList.add(singleValueRange(value));
    }

    public boolean contains(Range other) {
        for (Range range : rangeList) {
            if (range.contains(other)) {
                return true;
            }
        }
        return false;
    }

    public void mergeRange(Range aRange) {
        rangeList.add(aRange);
        Collections.sort(rangeList, comparingInt(Range::getBegin));

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
