package com.raja.tmp.day19;

import java.util.ArrayList;
import java.util.List;

public class DecisionNode {

    private List<DecisionNode> decisionNodes = new ArrayList<>();
    private final Blueprint blueprint;
    private final Army army;
    private final Resources resources;
    private final int minute;
    private final int maxMinutes;
    private final int geodesByTheEnd;

    public DecisionNode(Blueprint blueprint, Army army, Resources resources, int minute, int maxMinutes) {
        this.blueprint = blueprint;
        this.army = army;
        this.resources = resources;
        this.minute = minute;
        this.maxMinutes = maxMinutes;
        int minutesLeft = maxMinutes - minute;
        geodesByTheEnd = resources.geode() + Math.max(0, minutesLeft * army.geodeBots());
    }

    public static DecisionNode DecisionNode24(Blueprint blueprint, Army army, Resources resources, int minute) {
        return new DecisionNode(blueprint, army, resources, minute, 24);
    }

    public static DecisionNode DecisionNode32(Blueprint blueprint, Army army, Resources resources, int minute) {
        return new DecisionNode(blueprint, army, resources, minute, 32);
    }

    public void visit() {
        if (army.clayBots() <= 1) {
            createOreBot();
        }
        createClayBot();
        if (hasClayBots()) {
            createObsidianBot();
        }
        if (hasObsidianBots()) {
            createGeodeBot();
        }

        int maxScoreFromChildren = decisionNodes.stream()
                .map(DecisionNode::getScore)
                .reduce(0, Integer::max);
        List<DecisionNode> newDecisions = new ArrayList<>();
        for (DecisionNode decisionNode : decisionNodes) {
            if (decisionNode.getScore() >= maxScoreFromChildren) {
                newDecisions.add(decisionNode);
            }
        }
        decisionNodes = newDecisions;
    }

    private void createOreBot() {
        int oreNeeded = blueprint.getOreRobotCostOre() - resources.ore();
        int minutesToWait = Math.ceilDiv(oreNeeded, army.oreBots());
        int actualMinutesToWait = Math.max(0, minutesToWait) + 1;
        if (minute + actualMinutesToWait >= maxMinutes) {
            return;
        }
        if (army.oreBots() >= 5) {
            return;
        }

        Resources newResources = resources.calcWith(army, actualMinutesToWait)
                .removeOres(blueprint.getOreRobotCostOre());
        DecisionNode decisionNode = new DecisionNode(
                blueprint,
                army.withOreBot(),
                newResources,
                minute + actualMinutesToWait,
                maxMinutes);
        decisionNodes.add(decisionNode);
        decisionNode.visit();
    }

    private void createClayBot() {
        int oreNeeded = blueprint.getClayRobotCostOre() - resources.ore();
        int minutesToWait = Math.ceilDiv(oreNeeded, army.oreBots());
        int actualMinutesToWait = Math.max(0, minutesToWait) + 1;
        if (minute + actualMinutesToWait >= maxMinutes) {
            return;
        }
        Resources newResources = resources.calcWith(army, actualMinutesToWait)
                .removeOres(blueprint.getClayRobotCostOre());
        DecisionNode decisionNode = new DecisionNode(
                blueprint,
                army.withClayBot(),
                newResources,
                minute + actualMinutesToWait,
                maxMinutes);
        decisionNodes.add(decisionNode);
        decisionNode.visit();
    }

    private void createObsidianBot() {
        int oreNeeded = blueprint.getObsidianRobotCostOre() - resources.ore();
        int clayNeeded = blueprint.getObsidianRobotCostClay() - resources.clay();
        int minutesToWaitOre = Math.ceilDiv(oreNeeded, army.oreBots());
        int minutesToWaitClay = Math.ceilDiv(clayNeeded, army.clayBots());
        int minutesToWait = Math.max(minutesToWaitOre, minutesToWaitClay);
        int actualMinutesToWait = Math.max(0, minutesToWait) + 1;
        if (minute + actualMinutesToWait >= maxMinutes) {
            return;
        }
        Resources newResources = resources.calcWith(army, actualMinutesToWait)
                .removeOres(blueprint.getObsidianRobotCostOre())
                .removeClays(blueprint.getObsidianRobotCostClay());
        DecisionNode decisionNode = new DecisionNode(
                blueprint,
                army.withObsidianBot(),
                newResources,
                minute + actualMinutesToWait,
                maxMinutes);
        decisionNodes.add(decisionNode);
        decisionNode.visit();
    }

    private void createGeodeBot() {
        int oreNeeded = blueprint.getGeodeRobotCostOre() - resources.ore();
        int obsidianNeeded = blueprint.getGeodeRobotCostObsidian() - resources.obsidian();
        int minutesToWaitOre = Math.ceilDiv(oreNeeded, army.oreBots());
        int minutesToWaitObsidian = Math.ceilDiv(obsidianNeeded, army.obsidianBots());
        int minutesToWait = Math.max(minutesToWaitOre, minutesToWaitObsidian);
        int actualMinutesToWait = Math.max(0, minutesToWait) + 1;
        if (minute + actualMinutesToWait >= maxMinutes) {
            return;
        }
        Resources newResources = resources.calcWith(army, actualMinutesToWait)
                .removeOres(blueprint.getGeodeRobotCostOre())
                .removeObsidians(blueprint.getGeodeRobotCostObsidian());
        DecisionNode decisionNode = new DecisionNode(
                blueprint,
                army.withGeodeBot(),
                newResources,
                minute + actualMinutesToWait,
                maxMinutes);
        decisionNodes.add(decisionNode);
        decisionNode.visit();
    }

    private boolean hasClayBots() {
        return army.clayBots() > 0;
    }

    private boolean hasObsidianBots() {
        return army.obsidianBots() > 0;
    }

    public int getScore() {
        return decisionNodes.stream()
                .map(DecisionNode::getScore)
                .reduce(geodesByTheEnd, Integer::max);
    }
}
