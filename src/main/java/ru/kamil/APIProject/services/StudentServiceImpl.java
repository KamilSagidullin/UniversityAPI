package ru.kamil.APIProject.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kamil.APIProject.DTO.StudentDTO;
import ru.kamil.APIProject.models.Student;
import ru.kamil.APIProject.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentByEmail(String email) throws EntityNotFoundException {
        return studentRepository.findByEmail(email)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with email: " + email));
    }

    @Override
    @Transactional
    public void saveStudent(StudentDTO studentDTO) {
        Student student = mapToEntity(studentDTO); // Используем метод маппинга
        studentRepository.save(student);
    }

    @Transactional
    @Override
    public void updateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findByName(studentDTO.getName())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Optional.ofNullable(studentDTO.getEmail()).ifPresent(student::setEmail);
        Optional.ofNullable(studentDTO.getDateOfBirth()).ifPresent(student::setDateOfBirth);

        studentRepository.save(student);
    }


    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(Student student) {
        if (student == null) {
            return null; // или выбросьте исключение, если это необходимо
        }
        return new StudentDTO( student.getName(), student.getEmail(), student.getDateOfBirth());
    }
    // Метод для маппинга StudentDTO в Student
    private Student mapToEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null; // или выбросьте исключение, если это необходимо
        }
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        return student;
    }
}
