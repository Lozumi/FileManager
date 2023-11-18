package com.lozumi.filemanager;

import java.util.Objects;

/**
 * 文件夹项目类
 *
 * <p>表示文件或文件夹的基本信息，包括名称、路径和拥有者。
 * </p>
 * <p>作者：Lozumi
 * 版本：1.0
 * 仓库：https://github.com/Lozumi/FileManager
 * </p>
 */
public class FolderItem {
    private final String name;
    private final String path;
    private final Owner owner;

    /**
     * 构造函数
     *
     * <p>根据提供的名称、路径和拥有者构造文件夹项目对象。
     * </p>
     *
     * @param name  文件夹项目的名称
     * @param path  文件夹项目的路径
     * @param owner 文件夹项目的拥有者
     */
    public FolderItem(String name, String path, Owner owner) {
        this.name = name;
        this.path = path;
        this.owner = owner;
    }

    /**
     * 获取文件夹项目的名称
     *
     * @return 文件夹项目的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取文件夹项目的路径
     *
     * @return 文件夹项目的路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 获取文件夹项目的拥有者
     *
     * @return 文件夹项目的拥有者
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * 判断两个文件夹项目是否相等
     *
     * @param o 要比较的对象
     * @return 如果两个文件夹项目相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FolderItem that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    /**
     * 获取文件夹项目的哈希码
     *
     * @return 文件夹项目的哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
