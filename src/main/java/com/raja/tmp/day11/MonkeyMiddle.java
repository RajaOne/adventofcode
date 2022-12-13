package com.raja.tmp.day11;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

import static com.raja.tmp.day11.Monkey.monkey;
import static java.lang.Integer.parseInt;
import static java.util.Collections.reverseOrder;

@Getter
@Setter
@AllArgsConstructor
public class MonkeyMiddle {

    private List<Monkey> monkeys;

    public static MonkeyMiddle monkeyMiddle(String input, int monkeys) {
        MonkeyMiddle monkeyMiddle = new MonkeyMiddle(new ArrayList<>());
        String[] lines = input.split("\n");
        int lineIndex = 0;
        while (lineIndex < lines.length) {
            Monkey monkey = monkey();
            monkeyMiddle.getMonkeys().add(monkey);

            lineIndex++;
            String itemsString = lines[lineIndex];
            String[] items = itemsString.split(",? ");
            for (int i = 4; i < items.length; i++) {
                monkey.getItems().add(new MyNumber(parseInt(items[i]), monkeys));
            }

            lineIndex++;
            // operation
            String operationsString = lines[lineIndex];
            String[] operations = operationsString.split(" ");
            String in1 = operations[5];
            String operation = operations[6];
            String in2 = operations[7];

            Function<MyNumber, MyNumber> firstArg;
            if (in1.equals("old")) {
                firstArg = integer -> integer;
            } else {
                firstArg = integer -> new MyNumber(parseInt(in1), monkeys);
            }

            Function<MyNumber, MyNumber> secondArg;
            if (in2.equals("old")) {
                secondArg = integer -> integer;
            } else {
                secondArg = integer -> new MyNumber(parseInt(in2), monkeys);
            }

            if (operation.equals("*")) {
                monkey.setOperation((old, mods) -> firstArg.apply(old).multiply(secondArg.apply(old), mods));
            } else {
                monkey.setOperation((old, mods) -> firstArg.apply(old).add(secondArg.apply(old), mods));
            }

            lineIndex++;
            // test
            String testString = lines[lineIndex];
            String[] tests = testString.split(" ");
            String divide = tests[5];
            monkey.setMod(parseInt(divide));
            monkey.setTest((index, integer) -> integer.isDivisibleBy(index));
            monkey.setTestSimple((index, integer) -> integer.isDivisibleBySimple(parseInt(divide)));
            lineIndex++;
            //true
            String truesString = lines[lineIndex];
            String[] trues = truesString.split(" ");
            String ifTrue = trues[9];
            monkey.setIfTrue((item, monkeyMiddle1) -> monkeyMiddle1.getMonkeys().get(parseInt(ifTrue)).getItems().add(item));

            lineIndex++;
            //false
            String falsesString = lines[lineIndex];
            String[] falses = falsesString.split(" ");
            String ifFalse = falses[9];
            monkey.setIfFalse((item, monkeyMiddle1) -> monkeyMiddle1.getMonkeys().get(parseInt(ifFalse)).getItems().add(item));
            lineIndex++;
            lineIndex++;
        }

        List<Integer> mods = monkeyMiddle.getMonkeys().stream()
                .map(Monkey::getMod)
                .toList();

        monkeyMiddle.getMonkeys()
                        .forEach(monkey -> monkey.setMods(mods));

        return monkeyMiddle;
    }

    public int getScore() {
        for (int i = 0; i < 20; i++) {
            for (int monkeyIndex = 0; monkeyIndex < monkeys.size(); monkeyIndex++) {
                Monkey monkey = monkeys.get(monkeyIndex);
                Queue<MyNumber> items = monkey.getItems();
                while (!items.isEmpty()) {
                    MyNumber item = items.poll();
                    MyNumber operated = monkey.getOperation().apply(item, monkey.getMods());
                    operated.divideBy(3);
                    if (monkey.getTestSimple().test(monkeyIndex, operated)) {
                        monkey.getIfTrue().accept(operated, this);
                    } else {
                        monkey.getIfFalse().accept(operated, this);
                    }
                    monkey.inc();
                }
            }
        }

        return monkeys.stream()
                .sorted(Comparator.comparingInt(Monkey::getOperated))
                .map(Monkey::getOperated)
                .sorted(reverseOrder())
                .limit(2)
                .reduce(1, (integer, integer2) -> integer* integer2);
    }

    public long getScore2() {
        for (int i = 0; i < 10000; i++) {
            for (int monkeyIndex = 0; monkeyIndex < monkeys.size(); monkeyIndex++) {
                Monkey monkey = monkeys.get(monkeyIndex);
                Queue<MyNumber> items = monkey.getItems();
                while (!items.isEmpty()) {
                    MyNumber item = items.poll();
                    MyNumber operated = monkey.getOperation().apply(item, monkey.getMods());
                    if (monkey.getTest().test(monkeyIndex, operated)) {
                        monkey.getIfTrue().accept(operated, this);
                    } else {
                        monkey.getIfFalse().accept(operated, this);
                    }
                    monkey.inc();
                }
            }
        }

        return monkeys.stream()
                .sorted(Comparator.comparingInt(Monkey::getOperated))
                .map(Monkey::getOperated)
                .sorted(reverseOrder())
                .limit(2)
                .map(Long::valueOf)
                .reduce(1L, (long1, long2) -> long1* long2);
    }
}
