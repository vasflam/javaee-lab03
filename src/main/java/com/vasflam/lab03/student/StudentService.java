package com.vasflam.lab03.student;

import java.util.Optional;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.vasflam.lab03.common.Utils;
import com.vasflam.lab03.group.Group;
import com.vasflam.lab03.group.GroupRepository;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudents(Group g) {
        return studentRepository.findByGroupId(g.getId());
    }

    public List<Student> getStudents(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }

    public Student getStudent(Long id) throws Exception {
        Optional<Student> os = studentRepository.findById(id);
        Utils.throwIfEmpty(os, String.format("Student with id='%d' not found.", id));
        return os.get();
    }

    public Student getStudent(Long studentId, Long groupId) throws Exception {
        Optional<Student> os = studentRepository.findByIdAndGroupId(studentId, groupId);
        Utils.throwIfEmpty(os, String.format("Student with id='%d',group_id='%d' not found.", studentId, groupId));
        return os.get();
    }

    public Student createStudent(@NotNull StudentDTO dto) throws Exception {
        Student student = new Student();
        student.setFirstName(dto.firstName)
                .setLastName((dto.lastName))
                .setEmail(dto.email)
                .setGroup(dto.group);
        try {
            student = studentRepository.save(student);
            return student;
        } catch (DataIntegrityViolationException e) {
            throw new Exception(String.format("Student with email='%s' already exists.", dto.email));
        } catch (Exception e) {
            throw e;
        }
    }

    public Student updateStudent(Long id, StudentDTO dto) throws Exception {
        Student student = getStudent(id);
        student.setFirstName(dto.firstName)
                .setLastName(dto.lastName)
                .setGroup(dto.group);
        student = studentRepository.save(student);
        return student;
    }

    public Student deleteStudent(Long id) throws Exception {
        Student student = getStudent(id);
        return deleteStudent(student);
    }

    public Student deleteStudent(Student student) {
        studentRepository.delete(student);
        return student;
    }
}
