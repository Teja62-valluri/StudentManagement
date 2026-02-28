package com.TejaCompany.service;

import com.TejaCompany.DTO.StudentDTO;
import com.TejaCompany.Entity.StudentEntity;
import com.TejaCompany.Repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final StudentRepository repository;

    // inject repository
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // ================== ADD ==================
    public void addStudent(StudentDTO dto) {

        if (dto.getAge() < 5) {
            throw new IllegalArgumentException("Student too young");
        }

        if (repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Student already exists with this ID, change ID");
        }

        validateMobile(dto);

        StudentEntity entity = convertToEntity(dto);

        repository.saveStudent(entity);
    }

    // ================== GET BY ID ==================
    public StudentDTO getStudentById(int id) {

        StudentEntity entity = repository.findById(id);

        if (entity == null) {
            return null;
        }

        return convertToDTO(entity);
    }

    // ================== GET ALL ==================
    public List<StudentDTO> getAllStudents() {

        List<StudentEntity> entities = repository.findAllStudents();

        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ================== UPDATE ==================
    public void updateStudent(StudentDTO dto) {

        if (!repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Student not found with this ID");
        }

        validateMobile(dto);

        StudentEntity entity = convertToEntity(dto);

        repository.updateStudent(entity);
    }

    // ================== DELETE ==================
    public void deleteStudent(int id) {

        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with this ID");
        }

        repository.deleteStudent(id);
    }

    // ================== HELPER METHODS ==================

    private void validateMobile(StudentDTO dto) {

        if (dto.getNumber() <= 0) {
            throw new IllegalArgumentException("Mobile number invalid");
        }

        String mobile = String.valueOf(dto.getNumber());

        if (mobile.length() != 10) {
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits");
        }
    }

    private StudentEntity convertToEntity(StudentDTO dto) {

        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setNumber(dto.getNumber());

        return entity;
    }

    private StudentDTO convertToDTO(StudentEntity entity) {

        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setNumber(entity.getNumber());

        return dto;
    }
}
