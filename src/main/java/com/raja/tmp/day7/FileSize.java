package com.raja.tmp.day7;

import lombok.Getter;

import java.util.Comparator;

@Getter
public class FileSize {

    Folder rootFolder;

    public FileSize() {
        rootFolder = new Folder("/", null);
    }

    public static FileSize fileSize(String input) {
        FileSize fileSize = new FileSize();
        String[] lines = input.split("\n");

        Folder currentDir = fileSize.getRootFolder();
        int lineIndex = 1;

        while (lineIndex < lines.length) {
            String line = lines[lineIndex];
            if (line.equals("$ ls")) {
                lineIndex++;
                while (lineIndex < lines.length && !lines[lineIndex].startsWith("$")) {
                    line = lines[lineIndex];
                    String[] fileLine = line.split(" ");
                    String firstPart = fileLine[0];
                    String name = fileLine[1];
                    if (firstPart.equals("dir")) {
                        Folder folder = new Folder(name, currentDir);
                        currentDir.getNodeList().add(folder);
                    } else {
                        int size = Integer.parseInt(firstPart);
                        File file = new File(size, name);
                        currentDir.getNodeList().add(file);
                    }
                    lineIndex++;
//                    line = lines[lineIndex];
                }
                lineIndex--;
            } else if (line.equals("$ cd ..")) {
                currentDir = currentDir.getParentFolder();
            } else if (line.startsWith("$ cd")) {
                String[] cdLine = line.split(" ");
                String dirName = cdLine[2];
                Folder subFolder = currentDir.getNodeList().stream()
                        .filter(FileSystemNode::isFolder)
                        .map(fileSystemNode -> (Folder) fileSystemNode)
                        .filter(folder -> folder.getName().equals(dirName))
                        .findFirst()
                        .orElseThrow();
                currentDir = subFolder;
            }
            lineIndex++;
        }

        return fileSize;
    }

    public int getSizeOfTops() {
        return rootFolder.getFolders().stream()
                .map(FileSystemNode::getSize)
                .filter(size -> size < 100000)
                .reduce(0, Integer::sum);
    }

    public int getSmallestDeletable() {
        var totSize = 70000000;
        var needed = 30000000 - (totSize - rootFolder.getSize());
        return rootFolder.getFolders().stream()
                .filter(fileSystemNode -> fileSystemNode.getSize() > needed)
                .sorted(Comparator.comparingInt(FileSystemNode::getSize))
                .map(FileSystemNode::getSize)
                .findFirst()
                .orElseThrow();
    }

}
