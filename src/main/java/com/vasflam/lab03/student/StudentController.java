package com.vasflam.lab03.student;

import java.util.List;

import com.vasflam.lab03.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path = "/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(path = "")
    @ResponseBody
    public List<StudentResource> listStudents() {
        List<Student> students = studentService.getStudents();
        return students.stream().map((student) -> {
            return student.toResource();
        }).toList();
    }

    @PutMapping(path = "")
    @ResponseBody
    public StudentResource createStudent(@RequestBody StudentDTO dto) {
        Group group = new Group();
        try {
            Student student = studentService.createStudent(dto);
            return student.toResource();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public StudentResource viewStudent(@PathVariable Long id) throws Exception {
        Student student = studentService.getStudent(id);
        return student.toResource();
    }

    @PutMapping(path = "/{id}")
    @ResponseBody
    public StudentResource updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) throws Exception {
        try {
            Student student = studentService.updateStudent(id, dto);
            return student.toResource();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public StudentResource deleteStudent(@PathVariable Long id) {
        try {
            Student student = studentService.deleteStudent(id);
            return student.toResource();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
