package com.lozumi.filemanager;

/**
 * 文件类
 *
 * <p>表示文件的基本信息，包括名称、路径、拥有者以及文件扩展名。
 * </p>
 * <p>作者：Lozumi
 * 版本：1.0
 * 仓库：https://github.com/Lozumi/FileManager
 * </p>
 */
public class File extends FolderItem {
    private final String extension;

    /**
     * 构造函数
     *
     * <p>根据提供的名称、路径、拥有者和文件扩展名构造文件对象。
     * </p>
     *
     * @param name      文件的名称
     * @param path      文件的路径
     * @param owner     文件的拥有者
     * @param extension 文件的扩展名
     */
    public File(String name, String path, Owner owner, String extension) {
        super(name, path, owner);
        this.extension = extension;
    }

    /**
     * 获取文件的扩展名
     *
     * @return 文件的扩展名
     */
    public String getExtension() {
        return extension;
    }
}
