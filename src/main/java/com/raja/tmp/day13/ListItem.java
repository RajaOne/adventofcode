package com.raja.tmp.day13;

import lombok.Getter;

import java.util.List;

@Getter
public class ListItem implements Item {

    private List<Item> itemList;

    public ListItem(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean isList() {
        return true;
    }
}
