package com.raja.tmp.day8;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForrestTile {
    private int height;
    private boolean visible;
    private int score;

    private ForrestTile(int height, boolean visible, int score) {
        this.height = height;
        this.visible = visible;
    }

    public static ForrestTile forrestTile(int height) {
        return new ForrestTile(height, false, 0);
    }

    public void makeVisible() {
        visible = true;
    }
}
