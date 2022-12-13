package com.raja.tmp.day2;

public enum Hand {
    ROCK {
        @Override
        public int scoreFrom(Hand playerTwoHand) {
            return switch (playerTwoHand) {
                case PAPER -> 6;
                case ROCK -> 3;
                case SCISSOR -> 0;
            };
        }

        @Override
        public int score() {
            return 1;
        }

        @Override
        protected Hand winsFrom() {
            return SCISSOR;
        }

        @Override
        protected Hand losesFrom() {
            return PAPER;
        }
    },
    PAPER {
        @Override
        public int scoreFrom(Hand playerTwoHand) {
            return switch (playerTwoHand) {
                case PAPER -> 3;
                case ROCK -> 0;
                case SCISSOR -> 6;
            };
        }

        @Override
        public int score() {
            return 2;
        }

        @Override
        protected Hand winsFrom() {
            return ROCK;
        }

        @Override
        protected Hand losesFrom() {
            return SCISSOR;
        }
    },
    SCISSOR {
        @Override
        public int scoreFrom(Hand playerTwoHand) {
            return switch (playerTwoHand) {
                case PAPER -> 0;
                case ROCK -> 6;
                case SCISSOR -> 3;
            };
        }

        @Override
        public int score() {
            return 3;
        }

        @Override
        protected Hand winsFrom() {
            return PAPER;
        }

        @Override
        protected Hand losesFrom() {
            return ROCK;
        }
    };

    public static Hand from(String input) {
        return switch (input) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSOR;
            default -> throw new RuntimeException("what is input " + input + " ?");
        };
    }

    public static Hand from(Hand playerOneHand, String endResult) {
        return switch (endResult) {
            case "X" -> playerOneHand.winsFrom();
            case "Y" -> playerOneHand;
            case "Z" -> playerOneHand.losesFrom();
            default -> throw new RuntimeException("what is input " + endResult + " ?");
        };
//        return switch (input) {
//            case "A", "X" -> ROCK;
//            case "B", "Y" -> PAPER;
//            case "C", "Z" -> SCISSOR;
//            default -> throw new RuntimeException("what is input " + input + " ?");
//        };
    }

    protected abstract Hand losesFrom();

    protected abstract Hand winsFrom();

    public abstract int scoreFrom(Hand playerTwoHand);

    public abstract int score();
}
