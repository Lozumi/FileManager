package com.lozumi.filemanager;

public class File extends FolderItem {
    private final String extension;

    public File(String name, String path, Owner owner, String extension) {
        super(name, path, owner);
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
