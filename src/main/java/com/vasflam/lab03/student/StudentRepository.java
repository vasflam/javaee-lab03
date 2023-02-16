package com.vasflam.lab03.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
    List<Student> findByGroupId(Long groupId);
    Optional<Student> findByIdAndGroupId(Long studentId, Long groupId);
}
