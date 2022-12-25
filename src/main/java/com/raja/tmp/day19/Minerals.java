package com.raja.tmp.day19;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.raja.tmp.day19.DecisionNode.DecisionNode24;
import static com.raja.tmp.day19.DecisionNode.DecisionNode32;
import static java.lang.Integer.parseInt;

@Getter
@Setter
public class Minerals {

    List<Blueprint> blueprints = new ArrayList<>();

    public static Minerals minerals(String input) {
        Minerals minerals = new Minerals();

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] numbers = line
                    .replace("Blueprint ", "")
                    .replace(": Each ore robot costs", "")
                    .replace("ore. Each clay robot costs ", "")
                    .replace("ore. Each obsidian robot costs ", "")
                    .replace("ore and ", "")
                    .replace("clay. Each geode robot costs ", "")
                    .replace("ore and ", "")
                    .replace(" obsidian.", "")
                    .split(" ");

            int blueprintNumber = parseInt(numbers[0]);
            int oreRobotCostOre = parseInt(numbers[1]);
            int clayRobotCostOre = parseInt(numbers[2]);
            int obsidianRobotCostOre = parseInt(numbers[3]);
            int obsidianRobotCostClay = parseInt(numbers[4]);
            int geodeRobotCostOre = parseInt(numbers[5]);
            int geodeRobotCostObsidian = parseInt(numbers[6]);

            minerals.getBlueprints().add(new Blueprint(
                    blueprintNumber,
                    oreRobotCostOre,
                    clayRobotCostOre,
                    obsidianRobotCostOre,
                    obsidianRobotCostClay,
                    geodeRobotCostOre,
                    geodeRobotCostObsidian
            ));
        }

        return minerals;
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < blueprints.size(); i++) {
            System.out.println("checking blueprint " + i);
            Blueprint blueprint = blueprints.get(i);
            Army army = new Army(1, 0, 0, 0);
            Resources resources = new Resources(0, 0, 0, 0);
            DecisionNode decisionNode = DecisionNode24(blueprint, army, resources, 0);
            decisionNode.visit();
            int score1 = decisionNode.getScore();
            score = score + (i+1)*score1;
        }

        return score;
    }

    public int getScore2() {
        int score = 1;
        int i = 0;
        while (i < blueprints.size() && i < 3) {
            System.out.println("checking blueprint " + i);
            Blueprint blueprint = blueprints.get(i);
            Army army = new Army(1, 0, 0, 0);
            Resources resources = new Resources(0, 0, 0, 0);
            DecisionNode decisionNode = DecisionNode32(blueprint, army, resources, 0);
            decisionNode.visit();
            int score1 = decisionNode.getScore();
            score = score * score1;
            i++;
        }

        return score;
    }
}
