package com.raja.tmp.day7;

import java.util.List;

public class File implements FileSystemNode {
    private int size;
    private String name;

    public File(int size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public List<FileSystemNode> getFolders() {
        throw new RuntimeException("not a folder");
    }
}
