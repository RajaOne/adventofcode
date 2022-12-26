package com.raja.tmp.day20;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

import static com.raja.tmp.day20.Number.aNumber;

@Getter
@Setter
public class Encryption {

    private LinkedList<Number> initialNumbers = new LinkedList<>();

    public static Encryption encryption(String input) {
        Encryption encryption = new Encryption();
        String[] numbers = input.split("\n");
        for (int i = 0; i < numbers.length; i++) {
            String number = numbers[i];
            int numberInt = Integer.parseInt(number);
            encryption.getInitialNumbers().add(aNumber(i, numberInt));
        }
        return encryption;
    }

    public int getScore() {
        LinkedList<Number> arrangedNumbers = new LinkedList<>();
        arrangedNumbers.addAll(initialNumbers);

        for (Number initialNumber : initialNumbers) {
            int index = arrangedNumbers.indexOf(initialNumber);
            arrangedNumbers.remove(index);
            int indexToMove = initialNumber.value();
            int newIndex = (index + indexToMove) % arrangedNumbers.size();
            if (newIndex <= 0) {
                newIndex += arrangedNumbers.size();
            }

            arrangedNumbers.add(newIndex, initialNumber);
        }

        int indexOf0 = 0;
        for (int i = 0; i < arrangedNumbers.size(); i++) {
            Number arrangedNumber = arrangedNumbers.get(i);
            if (arrangedNumber.value() == 0) {
                indexOf0 = i;
            }
        }

        int index1000 = (indexOf0 + 1000) % initialNumbers.size();
        int index2000 = (indexOf0 + 2000) % initialNumbers.size();
        int index3000 = (indexOf0 + 3000) % initialNumbers.size();

        int value1000 = arrangedNumbers.get(index1000).value();
        int value2000 = arrangedNumbers.get(index2000).value();
        int value3000 = arrangedNumbers.get(index3000).value();

        return value1000 + value2000 + value3000;
    }

    public int getScore2() {
        initialNumbers = new LinkedList<>(initialNumbers.stream()
                .map(number -> aNumber(number.id(), number.value() * 811589153))
                .toList());
        LinkedList<Number> arrangedNumbers = new LinkedList<>();
        for (Number initialNumber : initialNumbers) {
            arrangedNumbers.add(initialNumber);
        }

        for (Number initialNumber : initialNumbers) {
            int index = arrangedNumbers.indexOf(initialNumber);
            arrangedNumbers.remove(index);
            int indexToMove = initialNumber.value();
            int newIndex = (index + indexToMove) % arrangedNumbers.size();
            if (newIndex <= 0) {
                newIndex += arrangedNumbers.size();
            }

            arrangedNumbers.add(newIndex, initialNumber);
        }

        int indexOf0 = 0;
        for (int i = 0; i < arrangedNumbers.size(); i++) {
            Number arrangedNumber = arrangedNumbers.get(i);
            if (arrangedNumber.value() == 0) {
                indexOf0 = i;
            }
        }

        int index1000 = (indexOf0 + 1000) % initialNumbers.size();
        int index2000 = (indexOf0 + 2000) % initialNumbers.size();
        int index3000 = (indexOf0 + 3000) % initialNumbers.size();

        int value1000 = arrangedNumbers.get(index1000).value();
        int value2000 = arrangedNumbers.get(index2000).value();
        int value3000 = arrangedNumbers.get(index3000).value();

        return value1000 + value2000 + value3000;
    }

    private static void print(LinkedList<Number> arrangedNumbers) {
        String toPrint = String.join(", ", arrangedNumbers.stream()
                .map(Number::value)
                .map(integer -> Integer.toString(integer))
                .toList()
        );
        System.out.println(toPrint);
    }
}
