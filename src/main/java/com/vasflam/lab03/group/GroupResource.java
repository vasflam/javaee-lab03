package com.vasflam.lab03.group;

public class GroupResource {
    public Long id;
    public String name;

    public GroupResource(Group ge) {
        this.id = ge.getId();
        this.name = ge.getName();
    }
}
