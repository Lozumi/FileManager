package com.lozumi.filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class FileSystem {
    private static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter stdOut = new PrintWriter(System.out, true);
    private static final PrintWriter stdErr = new PrintWriter(System.err, true);

    private final ArrayList<FolderItem> folderItemList = new ArrayList<>();

    private FileSystem() {
        stdOut.println("Welcome to File Manager made by Lozumi (2022303251).\t请确认数据文件\"folderItem.dat\"位于resources目录对应包下。(Y/N)");
        try {
            String isConfirm = stdIn.readLine();
            if (Objects.equals(isConfirm, "Y")) {
                stdOut.println("查找到数据文件，位于：" + getClass().getClassLoader().getResource("com/lozumi/filemanager/folderItem.dat").getPath() + "\t是否进行读取？(Y/N)");
                String isRead = stdIn.readLine();
                if (Objects.equals(isRead, "Y")) {
                    // 用户确认读取，执行加载操作
                    String filePath = getClass().getClassLoader().getResource("com/lozumi/filemanager/folderItem.dat").getPath();
                    loadFolderItemFromFile(filePath);
                } else {
                    stdOut.println("用户取消读取操作，程序将退出。");
                    // 在这里可以添加其他逻辑或退出程序
                }
            } else {
                stdOut.println("用户取消确认操作，程序将退出。");
                // 在这里可以添加其他逻辑或退出程序
            }
        } catch (IOException e) {
            stdErr.println("输入错误：" + e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {
        FileSystem fileSystem = new FileSystem();
        fileSystem.displayFolderItemInfo();
    }

    public void loadFolderItemFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            stdOut.println("\n开始读取...");
            String line;
            while ((line = reader.readLine()) != null) {
                FolderItem folderItem = parseFolderItem(line);
                if (folderItem != null) {
                    folderItemList.add(folderItem);
                    // 判断是否有父文件夹，如果有，则将该FolderItem添加到父文件夹的folderItemList中
                    if (folderItem.getPath() != null && !folderItem.getPath().isEmpty()) {
                        String path = folderItem.getPath();
                        Folder parentFolder = findFolderByPath(path);
                        String type=folderItem.getClass().getSimpleName();
                        if (parentFolder != null) {
                            parentFolder.addFolderItem(folderItem);
                            stdOut.println(type+" "+folderItem.getName()+"位于"+path+"，归属文件夹"+ parentFolder.getName() +"。");
                        } else {
                            stdOut.println(type+" "+folderItem.getName()+"位于根目录。");
                        }
                    }
                }
            }
            stdOut.println("文件夹项目加载完成！\n");
        } catch (IOException e) {
            stdErr.println("加载文件夹项目时出现错误：" + e.getMessage());
        }
    }

    // 根据path查找Folder
    public Folder findFolderByPath(String path) {
        String parentFolderName = null;
        String[] pathSegments = path.split("/");
        if (pathSegments.length > 1) {
            parentFolderName = pathSegments[pathSegments.length - 1];  // 获取倒数第二个路径段
        }
        for (FolderItem item : folderItemList) {
            if (item instanceof Folder && item.getName().equals(parentFolderName)) {
                return (Folder) item;
            }
        }
        return null;
    }


    private FolderItem parseFolderItem(String line) {
        String[] data = line.split("_");
        String itemType = data[0];
        String path = data[1];
        String name = data[2];
        String ownerID = data[3];
        String ownerName = data[4];

        Owner owner = new Owner(ownerID, ownerName);

        if (itemType.equals("Folder")) {
            return new Folder(name, path, owner);
        } else if (itemType.equals("File")) {
            String extension = data[5];
            return new File(name, path, owner, extension);
        }

        return null; // 不是有效的 FolderItem 数据
    }

    public void displayFolderItemInfo() {
        stdOut.println("文件夹项目信息：");
        for (FolderItem item : folderItemList) {
            stdOut.println(itemToLine(item));
        }
    }

    private String itemToLine(FolderItem item) {
        String type = (item instanceof Folder) ? "Folder" : "File";
        String path = item.getPath();
        String ownerID = item.getOwner().getId();
        String ownerName = item.getOwner().getName();

        if (item instanceof Folder) {
            return String.format("[%s]\t %s %s %s %s", type, path, item.getName(), ownerID, ownerName);
        } else if (item instanceof File) {
            String extension = ((File) item).getExtension();
            return String.format("[%s]  \t %s %s %s %s %s", type, path, item.getName(), ownerID, ownerName, extension);
        }

        return ""; // 不是有效的 FolderItem
    }
}
