package com.raja.tmp.day21;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public class Monkey {
    private Supplier<Long> execution;

}
