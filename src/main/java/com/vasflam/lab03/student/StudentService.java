package com.vasflam.lab03.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vasflam.lab03.group.Group;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Iterable<Student> getStudents() {
        return repository.findAll();
    }

    public Iterable<Student> getStudents(Group g) {
        return null;
    }
}
