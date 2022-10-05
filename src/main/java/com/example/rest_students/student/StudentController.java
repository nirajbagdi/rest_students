package com.example.rest_students.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

// Maps the whole class to the endpoint "http://localhost:8080/api/v1/student"
@RequestMapping(path = "api/v1/student")

public class StudentController {

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private final StudentService studentService;

    // GET http://localhost:8080/api/v1/student
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // POST http://localhost:8080/api/v1/student
    // Payload = { name: String, email: String, dob: LocalDate }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    // DELETE http://localhost:8080/api/v1/student/{id}
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    // PUT http://localhost:8080/api/v1/student/{id}?name={name}&email={email}
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
