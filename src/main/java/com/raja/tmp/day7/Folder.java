package com.raja.tmp.day7;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Folder implements FileSystemNode {

    List<FileSystemNode> nodeList;
    private String name;
    private Folder parentFolder;

    public Folder(String name, Folder parentFolder) {
        this.name = name;
        this.parentFolder = parentFolder;
        this.nodeList = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return nodeList.stream()
                .map(fileSystemNode -> fileSystemNode.getSize())
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public List<FileSystemNode> getFolders() {
        List<FileSystemNode> folders = new ArrayList<>();
        folders.add(this);
        List<FileSystemNode> subFolders = nodeList.stream()
                .filter(FileSystemNode::isFolder)
                .map(fileSystemNode -> fileSystemNode.getFolders())
                .flatMap(fileSystemNodes -> fileSystemNodes.stream())
                .toList();
        folders.addAll(subFolders);
        return folders;
    }
}
