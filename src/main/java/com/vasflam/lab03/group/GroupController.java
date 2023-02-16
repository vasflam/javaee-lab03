package com.vasflam.lab03.group;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Controller
@RequestMapping(path="/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping(path="")
    public @ResponseBody Iterable<GroupResource> listGroups() {
        Iterable<Group> groups = groupService.getGroups();
        ArrayList<GroupResource> resources = new ArrayList<>();
        groups.forEach((group -> resources.add(new GroupResource(group))));

        return resources;
    }

    @PutMapping(path="")
    @ResponseBody
    public GroupResource createGroup(@RequestBody @NotNull GroupDTO dto) throws ResponseStatusException {
        try {
            Group group = groupService.createGroup(dto);
            return new GroupResource(group);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(path="/{id}")
    @ResponseBody
    public GroupResource viewGroup(@PathVariable Long id) throws ResponseStatusException {
        try {
            Group group = groupService.getGroup(id);
            return new GroupResource(group);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody
    public GroupResource deleteGroup(@PathVariable Long id) throws Exception {
        Group deleted = groupService.deleteGroup(id);
        return new GroupResource(deleted);
    }
}
