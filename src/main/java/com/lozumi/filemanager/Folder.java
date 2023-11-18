package com.lozumi.filemanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件夹类
 *
 * <p>表示文件夹的基本信息，包括名称、路径和拥有者，同时实现了 Iterable 接口以支持迭代。
 * </p>
 * <p>作者：Lozumi
 * 版本：1.0
 * 仓库：<a href="Lozumi/NAMS-GUI">https://github.com/Lozumi/FileManager</a>
 * </p>
 */
public class Folder extends FolderItem implements Iterable<FolderItem> {
    private final List<FolderItem> folderItemList;

    /**
     * 构造函数
     *
     * <p>根据提供的名称、路径和拥有者构造文件夹对象，并初始化文件夹项目列表。
     * </p>
     *
     * @param name  文件夹的名称
     * @param path  文件夹的路径
     * @param owner 文件夹的拥有者
     */
    public Folder(String name, String path, Owner owner) {
        super(name, path, owner);
        folderItemList = new ArrayList<>();
    }

    /**
     * 添加文件夹项目
     *
     * <p>将给定的文件夹项目添加到文件夹的项目列表中。
     * </p>
     *
     * @param folderItem 要添加的文件夹项目
     */
    public void addFolderItem(FolderItem folderItem) {
        folderItemList.add(folderItem);
    }

    /**
     * 获取文件夹项目
     *
     * <p>根据给定的名称查找文件夹项目。
     * </p>
     *
     * @param name 要查找的文件夹项目的名称
     * @return 如果找到匹配项，则返回文件夹项目，否则返回 null
     */
    public FolderItem getFolderItem(String name) {
        for (FolderItem item : folderItemList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null; // 如果未找到匹配项
    }

    /**
     * 获取文件夹中的文件夹项目数
     *
     * <p>递归计算文件夹中包含的文件夹和文件的总数。
     * </p>
     *
     * @return 文件夹中包含的文件夹和文件的总数
     */
    public int getNumberOfFolderItems() {
        int count = folderItemList.size();
        for (FolderItem item : folderItemList) {
            count++;
            if (item instanceof Folder) {
                // 递归调用以获取子文件夹中的项目数
                count += ((Folder) item).getNumberOfFolderItems();
            }
        }
        return count;
    }

    /**
     * 获取文件夹项目的迭代器
     *
     * @return 文件夹项目的迭代器
     */
    @Override
    public Iterator<FolderItem> iterator() {
        return folderItemList.iterator();
    }
}
