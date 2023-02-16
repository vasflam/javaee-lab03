package com.vasflam.lab03.group;
import com.vasflam.lab03.common.ResourceAware;
import jakarta.persistence.*;
import java.util.Set;
import com.vasflam.lab03.common.BaseEntity;
import com.vasflam.lab03.student.Student;

/**
 * @TODO: validation
 */
@Entity(name="groups")
public class Group extends BaseEntity<Long> implements ResourceAware<GroupResource> {
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private Set<Student> students;

    @Column(unique = true)
    private String name;

    public String getName() { return name; }
    public Group setName(String name) { this.name = name; return this; }

    public GroupResource toResource() {
        return new GroupResource(this);
    }
}
