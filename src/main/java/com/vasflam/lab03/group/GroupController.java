package com.vasflam.lab03.group;

import com.vasflam.lab03.student.StudentResource;
import com.vasflam.lab03.student.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @GetMapping(path="")
    public @ResponseBody List<GroupResource> listGroups() {
        Iterable<Group> groups = groupService.getGroups();
        ArrayList<GroupResource> resources = new ArrayList<>();
        groups.forEach((group -> resources.add(group.toResource())));

        return resources;
    }

    @PutMapping(path="")
    @ResponseBody
    public GroupResource createGroup(@RequestBody @NotNull GroupDTO dto) throws ResponseStatusException {
        try {
            Group group = groupService.createGroup(dto);
            return group.toResource();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(path="/{id}")
    @ResponseBody
    public GroupResource viewGroup(@PathVariable Long id) throws ResponseStatusException {
        try {
            Group group = groupService.getGroup(id);
            return group.toResource();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody
    public GroupResource deleteGroup(@PathVariable Long id) throws Exception {
        Group deleted = groupService.deleteGroup(id);
        return deleted.toResource();
    }

    @GetMapping(path = "/{id}/students")
    @ResponseBody
    public List<StudentResource> getStudents(@PathVariable Long id) {
        return studentService
                .getStudents(id)
                .stream()
                .map((student -> { return student.toResource(); }))
                .toList();
    }
}
