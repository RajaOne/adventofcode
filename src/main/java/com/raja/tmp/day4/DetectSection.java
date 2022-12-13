package com.raja.tmp.day4;

import lombok.Setter;

public class DetectSection {

    int count;

    public DetectSection() {
        count = 0;
    }

    public static DetectSection detectSection(String input) {
        DetectSection detectSection = new DetectSection();
        String[] sectionPairs = input.split("\n");
        for (String sectionPair : sectionPairs) {
            String[] sections = sectionPair.split("(-|,)");
            int section1 = Integer.parseInt(sections[0]);
            int section2 = Integer.parseInt(sections[1]);
            int section3 = Integer.parseInt(sections[2]);
            int section4 = Integer.parseInt(sections[3]);

            if (section1 <= section3 &&
                section2 >= section4) {
                detectSection.inc();
                continue;
            }
            if (section3 <= section1 &&
                section4 >= section2) {
                detectSection.inc();
            }
        }
        return detectSection;
    }

    public static DetectSection detectOverlap(String input) {
        DetectSection detectSection = new DetectSection();
        String[] sectionPairs = input.split("\n");
        for (String sectionPair : sectionPairs) {
            String[] sections = sectionPair.split("(-|,)");
            int section1 = Integer.parseInt(sections[0]);
            int section2 = Integer.parseInt(sections[1]);
            int section3 = Integer.parseInt(sections[2]);
            int section4 = Integer.parseInt(sections[3]);

            if (section1 <= section4 && section2 >= section3) {
                detectSection.inc();
//                continue;
            }
//            if (section3 <= section1 &&
//                section4 >= section2) {
//                detectSection.inc();
//            }
        }
        return detectSection;

    }

    private void inc() {
        count++;
    }


    public int count() {
        return count;
    }

}
