package com.lozumi.filemanager;

/**
 * 拥有者类
 *
 * <p>表示文件或文件夹的拥有者，包括拥有者的ID和名称。
 * </p>
 * <p>作者：Lozumi
 * 版本：1.0
 * 仓库：<a href="Lozumi/NAMS-GUI">https://github.com/Lozumi/FileManager</a>
 * </p>
 */
public class Owner {
    private final String id;
    private final String name;

    /**
     * 构造函数
     *
     * <p>根据提供的ID和名称构造拥有者对象。
     * </p>
     *
     * @param id   拥有者的ID
     * @param name 拥有者的名称
     */
    public Owner(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 获取拥有者的ID
     *
     * @return 拥有者的ID
     */
    public String getId() {
        return id;
    }

    /**
     * 获取拥有者的名称
     *
     * @return 拥有者的名称
     */
    public String getName() {
        return name;
    }
}
