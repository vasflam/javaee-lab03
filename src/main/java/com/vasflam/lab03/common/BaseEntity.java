package com.vasflam.lab03.common;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
abstract public class BaseEntity<ID> implements Persistable<ID> {
    @Transient
    private boolean isNew = true;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
    public boolean isNew() {
        return isNew;
    }
    @PrePersist
    @PostLoad
    private void markNotNew() {
        isNew = false;
    }
    public ID getId() { return id; }
    public void setID(ID id) { this.id = id; }
}
