package com.lozumi.filemanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Folder extends FolderItem implements Iterable<FolderItem> {
    private final List<FolderItem> folderItemList;

    public Folder(String name, String path, Owner owner) {
        super(name, path, owner);
        folderItemList = new ArrayList<>();
    }

    public void addFolderItem(FolderItem folderItem) {
        folderItemList.add(folderItem);
    }

    public FolderItem getFolderItem(String name) {
        for (FolderItem item : folderItemList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null; // 如果未找到匹配项
    }

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

    @Override
    public Iterator<FolderItem> iterator() {
        return folderItemList.iterator();
    }
}
