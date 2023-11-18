package com.lozumi.filemanager;

import java.util.Objects;

public class FolderItem {
    private final String name;
    private final String path;
    private final Owner owner;

    public FolderItem(String name, String path, Owner owner) {
        this.name = name;
        this.path = path;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FolderItem that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
