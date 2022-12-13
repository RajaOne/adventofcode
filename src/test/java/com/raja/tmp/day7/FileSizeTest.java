package com.raja.tmp.day7;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day7.FileSize.fileSize;
import static org.assertj.core.api.Assertions.assertThat;

class FileSizeTest {

    @Test
    void getSizeOfTops() {
        var input = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """;

        int size = fileSize(input).getSizeOfTops();

        assertThat(size).isEqualTo(95437);
    }

    @Test
    void getSmallest() {
        var input = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """;

        int size = fileSize(input).getSmallestDeletable();

        assertThat(size).isEqualTo(24933642);
    }
}
