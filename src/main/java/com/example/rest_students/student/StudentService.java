package com.example.rest_students.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Contains The Actual Business Logic related to Students
@Service
public class StudentService {

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    private boolean checkEmailExists(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        return studentOptional.isPresent();
    }

    public void addNewStudent(Student student) {
        if (checkEmailExists(student.getEmail())) {
            throw new IllegalStateException("Email already exists");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("Student with ID " + studentId + " does not exists!");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with ID " + studentId + " does not exists!"
                ));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            if (checkEmailExists(email)) {
                throw new IllegalStateException("Email already exists");
            }

            student.setEmail(email);
        }
    }

}
