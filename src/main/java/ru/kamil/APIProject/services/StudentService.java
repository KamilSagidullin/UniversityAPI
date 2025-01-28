package ru.kamil.APIProject.services;

import jakarta.persistence.EntityNotFoundException;
import ru.kamil.APIProject.DTO.StudentDTO;
import ru.kamil.APIProject.models.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentByEmail(String email) throws EntityNotFoundException;
    void saveStudent(StudentDTO student);
    void updateStudent(StudentDTO student) throws EntityNotFoundException;
    void deleteStudent(Long id);
}
