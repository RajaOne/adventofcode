package com.raja.tmp.day19;

public record Army (
    int oreBots,
    int clayBots,
    int obsidianBots,
    int geodeBots
) {

    public Army copy() {
        return new Army(
                oreBots,
                clayBots,
                obsidianBots,
                geodeBots
        );
    }

    public Army withOreBot() {
        return new Army(oreBots + 1, clayBots, obsidianBots, geodeBots);
    }

    public Army withClayBot() {
        return new Army(oreBots, clayBots + 1, obsidianBots, geodeBots);
    }

    public Army withObsidianBot() {
        return new Army(oreBots, clayBots, obsidianBots + 1, geodeBots);
    }

    public Army withGeodeBot() {
        return new Army(oreBots, clayBots, obsidianBots, geodeBots + 1);
    }
}
