package com.raja.tmp.day5;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

@Getter
public class MoveStack {

    List<Stack<Character>> stacks;
    List<Instruction> instructions;

    private MoveStack(int amountStacks) {
        instructions = new ArrayList<>();
        stacks = IntStream.range(0, amountStacks)
                .mapToObj(operand -> new Stack<Character>())
                .toList();
    }

    public static MoveStack moveStack(String input, int amountStacks) {
        MoveStack moveStack = new MoveStack(amountStacks);
        String[] lines = input.split("\n");
        List<List<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < amountStacks; i++) {
            stacks.add(new ArrayList<>());
        }

        int lineIndex = 0;
        while (lineIndex < lines.length) {
            String line = lines[lineIndex];
            if (line.contains(" 1   2   3 ")) {
                break;
            }
            fillInTheStacks(amountStacks, stacks, line);
            lineIndex++;
        }

        lineIndex++;
        lineIndex++;
        while (lineIndex < lines.length) {
            String line = lines[lineIndex];
            String[] instructions = line.split(" ");
            int amount = Integer.parseInt(instructions[1]);
            int from = Integer.parseInt(instructions[3]);
            int to = Integer.parseInt(instructions[5]);
            moveStack.getInstructions().add(new Instruction(amount, from, to));
            lineIndex++;
        }

        for (int i = 0; i < stacks.size(); i++) {
            List<Character> stack = stacks.get(i);
            Collections.reverse(stack);
            for (Character character : stack) {
                if (!character.equals(' ')) {
                    moveStack.getStacks().get(i).add(character);
                }
            }
        }


        return moveStack;
    }

    public String getCratesOnTop() {
        for (Instruction instruction : instructions) {
            for (int i = 0; i < instruction.amount(); i++) {
                Character character = stacks.get(instruction.from() - 1).pop();
                stacks.get(instruction.to() - 1).add(character);
            }
        }

        var characters = stacks.stream()
                .map(characters1 -> characters1.empty() ? " " : characters1.peek())
                .map(Object::toString)
                .toList();

        return String.join("", characters);
    }

    public String get9001CratesOnTop() {
        for (Instruction instruction : instructions) {
            Stack<Character> craneStack = new Stack<>();
            for (int i = 0; i < instruction.amount(); i++) {
                Character character = stacks.get(instruction.from() - 1).pop();
                craneStack.add(character);
            }
            for (int i = 0; i < instruction.amount(); i++) {
                Character character = craneStack.pop();
                stacks.get(instruction.to() - 1).add(character);
            }
        }

        var characters = stacks.stream()
                .map(characters1 -> characters1.empty() ? " " : characters1.peek())
                .map(Object::toString)
                .toList();

        return String.join("", characters);
    }

    private static void fillInTheStacks(int amountStacks, List<List<Character>> stacks, String line) {
        char[] chars = line.toCharArray();
        int index = 1;
        char firstChar = chars[index];
        stacks.get(0).add(firstChar);
        index = index + 4;
        for (int i = 1; i < amountStacks; i++) {
            char aChar = chars[index];
            stacks.get(i).add(aChar);
            index = index + 4;
        }
    }
}
