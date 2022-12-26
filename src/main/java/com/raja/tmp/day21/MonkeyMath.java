package com.raja.tmp.day21;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MonkeyMath {

    private final Map<String, Monkey> monkeys = new HashMap<>();

    public static MonkeyMath monkeyMath(String input) {
        MonkeyMath monkeyMath = new MonkeyMath();
        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] values = line.replace(":", "")
                    .split(" ");

            String id = values[0];
            if (values.length > 2) {
                String id1 = values[1];
                String op = values[2];
                String id2 = values[3];
                Monkey monkey = new Monkey(() -> {
                    Long integer1 = monkeyMath.getMonkeys().get(id1).getExecution().get();
                    Long integer2 = monkeyMath.getMonkeys().get(id2).getExecution().get();

//                    if (id.equals("root")) {
//                        System.out.println("root checking \n" + integer1 + " == \n" + integer2);
//                        if (integer1 < integer2) {
//                            System.out.println("go lower");
//                        } else if (integer1.equals(integer2)) {
//                            System.out.println("PERFECT!");
//                        } else {
//                            System.out.println("too low");
//                        }
//                    }
                    return switch (op) {
                        case "*" -> integer1 * integer2;
                        case "+" -> integer1 + integer2;
                        case "-" -> integer1 - integer2;
                        case "/" -> integer1 / integer2;
                        default -> throw new IllegalStateException("Unexpected value: " + op);
                    };
                });
                monkeyMath.getMonkeys().put(id, monkey);
            } else {
                long val = Long.parseLong(values[1]);
                Monkey monkey = new Monkey(() -> val);
                monkeyMath.getMonkeys().put(id, monkey);
            }
        }

        return monkeyMath;
    }

    public long getScore() {
        return monkeys.get("root").getExecution().get();
    }
}
