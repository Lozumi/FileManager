package com.lozumi.filemanager;

import java.io.IOException;

/**
 * 主类
 *
 * <p>系统的主方法。
 *
 * <p>这个类包含系统的主方法，它调用了子系统的主方法。
 *
 * <p>作者：Lozumi
 * 版本：1.0
 * 仓库：<a href="Lozumi/NAMS-GUI">https://github.com/Lozumi/FileManager</a>
 */
public class Main {
    /**
     * 主方法，调用子系统的主方法。
     *
     * @param args 传递给主方法的参数
     * @throws IOException 当 IO 操作不受支持时抛出该异常
     */
    public static void main(String[] args) throws IOException {
        FileSystem.main(args); // 调用子系统的主方法。
    }
}
