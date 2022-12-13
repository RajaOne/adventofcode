package com.raja.tmp.day9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class RopeTile {

    private boolean empty;
    private boolean head;
    private boolean tail;
    private boolean visited;

    public static RopeTile aTile() {
        return new RopeTile(true, false, false, false);
    }

}
