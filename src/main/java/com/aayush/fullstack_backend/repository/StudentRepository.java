package com.aayush.fullstack_backend.repository;

import com.aayush.fullstack_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
