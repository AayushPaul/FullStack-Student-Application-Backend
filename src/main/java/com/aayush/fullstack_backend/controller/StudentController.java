package com.aayush.fullstack_backend.controller;

import com.aayush.fullstack_backend.exception.UserNotFoundException;
import com.aayush.fullstack_backend.model.Student;
import com.aayush.fullstack_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @CrossOrigin("https://deanslist-application.netlify.app")
    @PostMapping("/user")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @CrossOrigin("https://deanslist-application.netlify.app")
    @GetMapping("/users")
    List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @CrossOrigin("https://deanslist-application.netlify.app")
    @GetMapping("/user/{id}")
    Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @CrossOrigin("https://deanslist-application.netlify.app")
    @PutMapping("/user/{id}")
    Student updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setEmail(newStudent.getEmail());
                    student.setGpa(newStudent.getGpa());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @CrossOrigin("https://deanslist-application.netlify.app")
    @DeleteMapping("/user/{id}")
    String deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        studentRepository.deleteById(id);
        return "User with id " + id + " was deleted";
    }
}
