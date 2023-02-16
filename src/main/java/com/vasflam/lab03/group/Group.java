package com.vasflam.lab03.group;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@Entity(name="groups")
public class Group implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isNew() {
        return id != null && id > 0 ? true : false;
    }
}
