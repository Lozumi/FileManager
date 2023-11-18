package com.lozumi.filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 文件系统类
 *
 * <p>该类表示文件系统的主要功能，包括加载文件夹项目、显示文件夹项目信息等。
 * </p>
 *
 * <p>作者：Lozumi
 * 版本：1.0
 * 仓库：https://github.com/Lozumi/FileManager
 * </p>
 */
public class FileSystem {
    private static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter stdOut = new PrintWriter(System.out, true);
    private static final PrintWriter stdErr = new PrintWriter(System.err, true);

    private final ArrayList<FolderItem> folderItemList = new ArrayList<>();

    /**
     * 构造函数
     *
     * <p>欢迎消息包含作者和学号信息，用户需要确认数据文件的位置和是否读取。
     * 如果确认读取，则执行加载文件夹项目的操作，并根据路径找到父文件夹，将项目添加到父文件夹的 folderItemList 中。
     * </p>
     *
     * @throws IOException 当 IO 操作不受支持时抛出该异常
     */
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

    /**
     * 主方法
     *
     * <p>创建 FileSystem 对象，执行文件夹项目加载和显示信息操作。
     * 同时，遍历所有文件夹，打印名称和 getNumberOfFolderItems。
     * </p>
     *
     * @param args 传递给主方法的参数
     * @throws IOException 当 IO 操作不受支持时抛出该异常
     */
    public static void main(String[] args) throws IOException {
        FileSystem fileSystem = new FileSystem();
        fileSystem.displayFolderItemInfo();

        // 遍历所有Folder，打印名称和 getNumberOfFolderItems
        stdOut.println("\n文件夹深度查询：");
        for (FolderItem item : fileSystem.folderItemList) {
            if (item instanceof Folder) {
                Folder folder = (Folder) item;
                System.out.println(folder.getName() + "包含的FolderItem数量: " + folder.getNumberOfFolderItems());
            }
        }
    }

    /**
     * 从文件加载文件夹项目
     *
     * <p>根据文件名读取文件，解析文件内容，构造 FolderItem 对象并添加到 folderItemList 中。
     * 对于每个文件夹项目，判断是否有父文件夹，如果有，则将该 FolderItem 添加到父文件夹的 folderItemList 中。
     * </p>
     *
     * @param fileName 要加载的文件的名称
     */
    public void loadFolderItemFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            stdOut.println("\n开始读取...");
            String line;
            while ((line = reader.readLine()) != null) {
                FolderItem folderItem = parseFolderItem(line);
                if (folderItem != null) {
                    folderItemList.add(folderItem);
                    // 判断是否有父文件夹，如果有，则将该 FolderItem 添加到父文件夹的 folderItemList 中
                    if (folderItem.getPath() != null && !folderItem.getPath().isEmpty()) {
                        String path = folderItem.getPath();
                        Folder parentFolder = findFolderByPath(path);
                        String type = folderItem.getClass().getSimpleName();
                        if (parentFolder != null) {
                            parentFolder.addFolderItem(folderItem);
                            stdOut.println(type + " " + folderItem.getName() + "位于" + path + "，归属文件夹" + parentFolder.getName() + "。");
                        } else {
                            stdOut.println(type + " " + folderItem.getName() + "位于根目录。");
                        }
                    }
                }
            }
            stdOut.println("文件夹项目加载完成！\n");
        } catch (IOException e) {
            stdErr.println("加载文件夹项目时出现错误：" + e.getMessage());
        }
    }

    /**
     * 根据路径查找文件夹
     *
     * <p>根据路径查找文件夹，用于确定父文件夹。
     * </p>
     *
     * @param path 要查找的文件夹的路径
     * @return 找到的文件夹，如果找不到则返回 null
     */
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

    /**
     * 解析文件夹项目
     *
     * <p>解析从文件中读取的一行数据，构造相应的 FolderItem 对象。
     * </p>
     *
     * @param line 从文件中读取的一行数据
     * @return 构造的 FolderItem 对象，如果不是有效的 FolderItem 数据则返回 null
     */
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

    /**
     * 显示文件夹项目信息
     *
     * <p>显示 folderItemList 中所有文件夹项目的信息。
     * </p>
     */
    public void displayFolderItemInfo() {
        stdOut.println("文件夹项目信息：");
        for (FolderItem item : folderItemList) {
            stdOut.println(itemToLine(item));
        }
    }

    /**
     * 将文件夹项目转换为字符串
     *
     * <p>根据文件夹项目的类型，转换为字符串表示。
     * </p>
     *
     * @param item 要转换的文件夹项目
     * @return 转换后的字符串
     */
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
