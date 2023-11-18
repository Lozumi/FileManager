package com.lozumi.filemanager;

import java.io.IOException;

/**
 * 主类
 *
 * <p>系统的主方法。
 *
 * @author Lozumi
 * @version 1.0
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
