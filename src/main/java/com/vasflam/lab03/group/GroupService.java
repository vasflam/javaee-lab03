package com.vasflam.lab03.group;

import com.vasflam.lab03.common.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;


    public Iterable<Group> getGroups() { return repository.findAll(); }

    public Group getGroup(Long id) throws Exception {
        Optional<Group> g = repository.findById(id);
        Utils.throwIfEmpty(g, String.format("Group with id='%d' not found", id));
        Group group = g.get();
        return group;
    }

    public Group deleteGroup(Long id) throws Exception {
        Group group = getGroup(id);
        return deleteGroup(group);
    }

    public Group deleteGroup(Group group) throws Exception {
        repository.deleteById(group.getId());
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
