package com.example.Student.Registry.service;

import com.example.Student.Registry.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    Student saveStudent(Student student);

    Page<Student> getAllStudents(
            int page,
            int size,
            String sortBy,
            String direction
    );

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
