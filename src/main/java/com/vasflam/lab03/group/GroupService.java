package com.vasflam.lab03.group;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;

    private void throwIfEmpty(@NotNull Optional<Group> g, String msg) throws Exception {
        if (g.isEmpty()) {
            throw new Exception(msg);
        }
    }

    public Iterable<Group> getGroups() { return repository.findAll(); }

    public Group getGroup(Long id) throws Exception {
        Optional<Group> g = repository.findById(id);
        throwIfEmpty(g, String.format("Group with id='%d' not found", id));
        Group group = g.get();
        System.out.printf("%d - %s\n", group.getId(), group.getName());
        this.repository.deleteById(id);
        this.repository.delete(group);
        System.out.printf("%d - %s\n", group.getId(), group.getName());
        this.repository.deleteAll();
        return group;
    }


    @Transactional
    public Group deleteGroup(Group group) {
        repository.delete(group);
        return group;
    }

    public Group deleteGroup(Long id) throws Exception {
        Group group = getGroup(id);
        deleteGroup(group);
        return group;
    }

    public Group createGroup(GroupDTO g) throws Exception {
        Group group = new Group();
        group.setName(g.name);
        try {
            repository.save(group);
            return group;
        } catch (Exception e) {
            throw new Exception(String.format("Group '%s' already exists", g.name));
        }
    }
}
