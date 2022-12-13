package com.raja.tmp.day7;

import java.util.List;

public interface FileSystemNode {
    int getSize();
    boolean isFolder();
    List<FileSystemNode> getFolders();
}
