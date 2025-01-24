package ru.kamil.APIProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kamil.APIProject.models.Student;
import ru.kamil.APIProject.services.StudentService;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @PostMapping("/save-student")
    public String saveStudent(@RequestBody  Student student){
        studentService.saveStudent(student);
        return HttpStatus.OK.toString();
    }
}
