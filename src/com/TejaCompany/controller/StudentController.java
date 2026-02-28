package com.TejaCompany.controller;

import com.TejaCompany.DTO.StudentDTO;
import com.TejaCompany.service.StudentService;
import com.TejaCompany.handlers.JsonUtil;

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ================== ADD ==================
    public String addStudent(StudentDTO dto) {
        try {
            validateStudentInput(dto);
            studentService.addStudent(dto);

            return JsonUtil.toJson("Student added successfully");

        } catch (IllegalArgumentException e) {
            return JsonUtil.toJson("Invalid input: " + e.getMessage());

        } catch (Exception e) {
            return JsonUtil.toJson("Server side error: " + e.getMessage());
        }
    }

    // ================== GET BY ID ==================
    public String getStudentById(int id) {
        try {
            StudentDTO student = studentService.getStudentById(id);

            if (student == null) {
                return JsonUtil.toJson("Student not found");
            }

            return JsonUtil.toJson(student);

        } catch (Exception e) {
            return JsonUtil.toJson("Server side error: " + e.getMessage());
        }
    }

    // ================== GET ALL ==================
    public String getAllStudents() {
        try {
            return JsonUtil.toJson(studentService.getAllStudents());

        } catch (Exception e) {
            return JsonUtil.toJson("Server side error: " + e.getMessage());
        }
    }

    // ================== UPDATE ==================
    public String updateStudent(StudentDTO dto) {
        try {
            validateStudentInput(dto);
            studentService.updateStudent(dto);

            return JsonUtil.toJson("Student updated successfully");

        } catch (IllegalArgumentException e) {
            return JsonUtil.toJson("Invalid input: " + e.getMessage());

        } catch (Exception e) {
            return JsonUtil.toJson("Server side error: " + e.getMessage());
        }
    }

    // ================== DELETE ==================
    public String deleteStudent(int id) {
        try {
            studentService.deleteStudent(id);
            return JsonUtil.toJson("Student deleted successfully");

        } catch (Exception e) {
            return JsonUtil.toJson("Server side error: " + e.getMessage());
        }
    }

    // ================== VALIDATION ==================
    private void validateStudentInput(StudentDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Student object should not be null");
        }

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name should not be null or empty");
        }

        if (dto.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }

        if (dto.getNumber() <= 0) {
            throw new IllegalArgumentException("Mobile number should not be null or zero");
        }

        String mobile = String.valueOf(dto.getNumber());

        if (mobile.length() != 10) {
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits");
        }

        if (dto.getId() <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero");
        }
    }
}
