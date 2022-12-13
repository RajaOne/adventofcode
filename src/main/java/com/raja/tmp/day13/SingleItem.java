package com.raja.tmp.day13;

import lombok.Getter;

@Getter
public class SingleItem implements Item {

    private int value;

    public SingleItem(int value) {
        this.value = value;
    }

    @Override
    public boolean isList() {
        return false;
    }
}
