package com.TejaCompany.Repository;

import com.TejaCompany.Entity.StudentEntity;
import com.TejaCompany.DAO.StudentDAO;

import java.util.List;

public class StudentRepository {

    private final StudentDAO dao;   // loose coupling

    public StudentRepository(StudentDAO dao) {
        this.dao = dao;
    }

    // ================== CREATE ==================
    public boolean saveStudent(StudentEntity entity) {
        return dao.insertStudent(entity);
    }

    // ================== EXISTS ==================
    public boolean existsById(int id) {
        return dao.existsById(id);
    }

    // ================== FIND BY ID ==================
    public StudentEntity findById(int id) {
        return dao.findStudentById(id);
    }

    // ================== FIND ALL ==================
    public List<StudentEntity> findAllStudents() {
        return dao.findAllStudents();
    }

    // ================== UPDATE ==================
    public boolean updateStudent(StudentEntity entity) {
        return dao.updateStudent(entity);
    }

    // ================== DELETE ==================
    public boolean deleteStudent(int id) {
        return dao.deleteStudent(id);
    }
}
