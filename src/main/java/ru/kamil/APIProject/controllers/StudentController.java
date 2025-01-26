package ru.kamil.APIProject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kamil.APIProject.DTO.StudentDTO;
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
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/save-student")
    public ResponseEntity<String> saveStudent(@Valid @RequestBody StudentDTO student) {
        studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student saved");
    }
    @PutMapping("/update-student")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentDTO studentDTO){
        studentService.updateStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student saved");
    }
    @DeleteMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return HttpStatus.ACCEPTED.toString();
    }
}
//TODO: идентификатор в dto - email. Нужно как-то уметь его поменять
//TODO: Auth