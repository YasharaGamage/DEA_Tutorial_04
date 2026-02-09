package com.example.Student.Registry.service;

import com.example.Student.Registry.entity.Student;
import com.example.Student.Registry.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student saveStudent(Student student) {
        if (repo.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return repo.save(student);
    }

    @Override
    public Page<Student> getAllStudents(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable);
    }

    @Override
    public Student getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setBatch(student.getBatch());
        existing.setGpa(student.getGpa());

        return repo.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        repo.delete(getStudentById(id));
    }
}
