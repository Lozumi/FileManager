package com.lozumi.filemanager;

public class Owner {
    private final String id;
    private final String name;

    public Owner(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
