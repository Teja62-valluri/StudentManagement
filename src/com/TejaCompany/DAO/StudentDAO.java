package com.TejaCompany.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.TejaCompany.Entity.StudentEntity;

public class StudentDAO {

    private final Connection connection; // loose coupling 

    // constructor injection
    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    // ================== INSERT ==================
    public boolean insertStudent(StudentEntity entity) {

        String sql = "INSERT INTO student(id, name, age, number) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getAge());
            ps.setLong(4, entity.getNumber());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Error inserting student", e);
        }
    }

    // ================== EXISTS ==================
    public boolean existsById(int id) {

        String sql = "SELECT 1 FROM student WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error checking student ID", e);
        }
    }

    // ================== FIND BY ID ==================
    public StudentEntity findStudentById(int id) {

        String sql = "SELECT * FROM student WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    StudentEntity entity = new StudentEntity();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setAge(rs.getInt("age"));
                    entity.setNumber(rs.getLong("number"));

                    return entity;
                }

                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching student by ID", e);
        }
    }

    // ================== FIND ALL ==================
    public List<StudentEntity> findAllStudents() {

        String sql = "SELECT * FROM student";

        List<StudentEntity> students = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                StudentEntity entity = new StudentEntity();
                entity.setId(rs.getInt("id"));
                entity.setName(rs.getString("name"));
                entity.setAge(rs.getInt("age"));
                entity.setNumber(rs.getLong("number"));

                students.add(entity);
            }

            return students;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching all students", e);
        }
    }

    // ================== UPDATE ==================
    public boolean updateStudent(StudentEntity entity) {

        String sql = "UPDATE student SET name = ?, age = ?, number = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getAge());
            ps.setLong(3, entity.getNumber());
            ps.setInt(4, entity.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Error updating student", e);
        }
    }

    // ================== DELETE ==================
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Error deleting student", e);
        }
    }
}
