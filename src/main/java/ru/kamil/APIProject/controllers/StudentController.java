package ru.kamil.APIProject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kamil.APIProject.DTO.StudentDTO;
import ru.kamil.APIProject.services.StudentService;
import ru.kamil.APIProject.util.GlobalExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    private final GlobalExceptionHandler exceptionHandler;

    @Autowired
    public StudentController(StudentService studentService, GlobalExceptionHandler exceptionHandler) {
        this.studentService = studentService;
        this.exceptionHandler = exceptionHandler;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<String> saveStudent(@Valid @RequestBody StudentDTO student, BindingResult bindingResult) {
        studentService.saveStudent(student);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED).body("Student saved");
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(studentDTO);
        return ResponseEntity.ok("Student updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }

        throw new IllegalArgumentException(errorMsg.toString());
    }
}
// TODO: auth