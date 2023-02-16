package com.vasflam.lab03.student;

import com.vasflam.lab03.common.BaseEntity;
import com.vasflam.lab03.common.ResourceAware;
import jakarta.persistence.*;
import com.vasflam.lab03.group.Group;

/**
 * @TODO: validation
 */
@Entity(name = "students")
public class Student extends BaseEntity<Long> implements ResourceAware<StudentResource> {
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    public String getFirstName() { return firstName; }
    public Student setFirstName(String firstName) { this.firstName = firstName; return this; }

    public String getLastName() { return lastName; }
    public Student setLastName(String lastName) { this.lastName = lastName; return this; }

    public String getEmail() { return email; }
    public Student setEmail(String email) { this.email = email; return this; }

    public Group getGroup() { return group; }
    public Student setGroup(Group group) { this.group = group; return this;}

    public StudentResource toResource() {
        return new StudentResource(this);
    }
}
