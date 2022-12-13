package com.raja.tmp.day13;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Distress {

    private List<List<Item>> signalPairs;
    private static int itemIndex;

    public Distress() {
        signalPairs = new ArrayList<>();
        itemIndex = 0;
    }

    public static Distress distress(String input) {
        Distress distress = new Distress();
        String[] lines = input.split("\n");
        int lineIndex = 0;
        while (lineIndex < lines.length) {
            String line = lines[lineIndex];

            line = line.replaceAll("\\[", "\\[ ");
            line = line.replaceAll(" ?\\]", " \\]");
            String[] itemStrings = line.split("(,| )");
            itemIndex = 0;
            Item items = processItem(itemStrings);

            lineIndex++;
            String line2 = lines[lineIndex];
            line2 = line2.replaceAll("\\[", "\\[ ");
            line2 = line2.replaceAll(" ?\\]", " \\]");
            String[] itemStrings2 = line2.split("(,| )");
            itemIndex = 0;
            Item items2 = processItem(itemStrings2);

            distress.getSignalPairs().add(List.of(items, items2));

            lineIndex++;
            lineIndex++;
        }

        return distress;
    }

    private static Item processItem(String[] itemStrings) {
        if (itemStrings[itemIndex].equals("[")) {
            itemIndex++;
            return processList2(itemStrings);
        }
        int singleInt = Integer.parseInt(itemStrings[itemIndex]);
        itemIndex++;
        return new SingleItem(singleInt);
    }

    private static ListItem processList2(String[] itemStrings) {
        List<Item> items = new ArrayList<>();
        while (!itemStrings[itemIndex].equals("]")) {
            items.add(processItem(itemStrings));
        }
        itemIndex++;
        return new ListItem(items);

    }


    private int compareItem(Item item1, Item item2) {
        if (item1.isList() && item2.isList()) {
            return compareLists((ListItem) item1, (ListItem) item2);
        }
        if (item2.isList()) {
            return compareLists(new ListItem(List.of(item1)), (ListItem) item2);
        }
        if (item1.isList()) {
            return compareLists((ListItem) item1, new ListItem(List.of(item2)));
        }
        if (((SingleItem) item1).getValue() < ((SingleItem) item2).getValue()) {
            return -1;
        }
        if (((SingleItem) item1).getValue() == ((SingleItem) item2).getValue()) {
            return 0;
        }
        return 1;
    }

    private int compareLists(ListItem listItem1, ListItem listItem2) {
        int max = Math.max(listItem1.getItemList().size(), listItem2.getItemList().size());
        for (int listIndex = 0; listIndex < max; listIndex++) {
            if (listIndex == listItem1.getItemList().size() &&
                listIndex == listItem2.getItemList().size()) {
                return 0;
            }
            if (listIndex == listItem1.getItemList().size()) {
                return -1;
            }
            if (listIndex == listItem2.getItemList().size()) {
                return 1;
            }
            Item item1 = listItem1.getItemList().get(listIndex);
            Item item2 = listItem2.getItemList().get(listIndex);
            int compareItem = compareItem(item1, item2);
            if (compareItem != 0) {
                return compareItem;
            }
        }
        return 0;
    }

    public int getScore() {
        int sum = 0;
        for (int pairIndex = 0; pairIndex < signalPairs.size(); pairIndex++) {
            List<Item> signalPair = signalPairs.get(pairIndex);
            Item item1 = signalPair.get(0);
            Item item2 = signalPair.get(1);
            int i = compareItem(item1, item2);
            if (i == -1) {
                sum += (pairIndex + 1);
            }
        }

        return sum;
    }

    public int getScore2() {
        List<Item> items1 = new ArrayList<>(signalPairs.stream()
                .flatMap(Collection::stream)
                .toList());
        Item dItem1 = new ListItem(List.of(new ListItem(List.of(new SingleItem(2)))));
        Item dItem2 = new ListItem(List.of(new ListItem(List.of(new SingleItem(6)))));
        items1.add(dItem1);
        items1.add(dItem2);

        items1.sort(this::compareItem);

        int i1 = items1.indexOf(dItem1);
        int i2 = items1.indexOf(dItem2);
        return (i1 + 1) * (i2 + 1);
    }
}
