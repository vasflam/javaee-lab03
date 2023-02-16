package com.vasflam.lab03.student;

import com.vasflam.lab03.group.GroupResource;

public class StudentResource {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public GroupResource group;

    public StudentResource(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.group = student.getGroup().toResource();
    }
}
