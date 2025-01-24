package ru.kamil.APIProject.services;

import ru.kamil.APIProject.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudent(String email);
    void saveStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
