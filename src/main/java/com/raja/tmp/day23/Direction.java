package com.raja.tmp.day23;

public enum Direction {
    UP {
        @Override
        public boolean canMoveTo(Elf elf, Grid grid) {
            Position position = elf.getPosition();
            return grid.tileAt(position.up().left()).hasNoElf() &&
                   grid.tileAt(position.up()).hasNoElf() &&
                   grid.tileAt(position.up().right()).hasNoElf();
        }

        @Override
        public Position move(Position position) {
            return position.up();
        }
    },
    RIGHT {
        @Override
        public boolean canMoveTo(Elf elf, Grid grid) {
            Position position = elf.getPosition();
            return grid.tileAt(position.right().up()).hasNoElf() &&
                   grid.tileAt(position.right()).hasNoElf() &&
                   grid.tileAt(position.right().down()).hasNoElf();
        }

        @Override
        public Position move(Position position) {
            return position.right();
        }
    },
    DOWN {
        @Override
        public boolean canMoveTo(Elf elf, Grid grid) {
            Position position = elf.getPosition();
            return grid.tileAt(position.down().left()).hasNoElf() &&
                   grid.tileAt(position.down()).hasNoElf() &&
                   grid.tileAt(position.down().right()).hasNoElf();
        }

        @Override
        public Position move(Position position) {
            return position.down();
        }
    },
    LEFT {
        @Override
        public boolean canMoveTo(Elf elf, Grid grid) {
            Position position = elf.getPosition();
            return grid.tileAt(position.left().up()).hasNoElf() &&
                   grid.tileAt(position.left()).hasNoElf() &&
                   grid.tileAt(position.left().down()).hasNoElf();
        }

        @Override
        public Position move(Position position) {
            return position.left();
        }
    };

    public abstract boolean canMoveTo(Elf elf, Grid grid);

    public abstract Position move(Position position);
}
