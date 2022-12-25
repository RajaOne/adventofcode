package com.raja.tmp.day19;

public record Resources (
        int ore,
        int clay,
        int obsidian,
        int geode
) {
    public Resources calcWith(Army army, int minutesToWait) {
        int ores = ore + army.oreBots() * minutesToWait;
        int clays = clay + army.clayBots() * minutesToWait;
        int obsidians = obsidian + army.obsidianBots() * minutesToWait;
        int geodes = geode + army.geodeBots() * minutesToWait;
        return new Resources(ores, clays, obsidians, geodes);
    }

    public Resources removeOres(int amount) {
        return new Resources(ore - amount, clay, obsidian, geode);
    }

    public Resources removeClays(int amount) {
        return new Resources(ore, clay - amount, obsidian, geode);
    }

    public Resources removeObsidians(int amount) {
        return new Resources(ore, clay, obsidian - amount, geode);
    }
}
