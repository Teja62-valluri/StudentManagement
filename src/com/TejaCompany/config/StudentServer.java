package com.TejaCompany.config;

import com.TejaCompany.controller.StudentController;
import com.TejaCompany.DAO.StudentDAO;
import com.TejaCompany.Repository.StudentRepository;
import com.TejaCompany.service.StudentService;
import com.TejaCompany.db.DBConnection;

import com.TejaCompany.handlers.AddStudentHandler;
import com.TejaCompany.handlers.UpdateStudentHandler;
import com.TejaCompany.handlers.GetStudentHandler;
import com.TejaCompany.handlers.GetAllStudentsHandler;
import com.TejaCompany.handlers.DeleteStudentHandler;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.sql.Connection;

public class StudentServer {

    private HttpServer server;

    public void start() throws Exception {

        // 1️⃣ One-time DB connection
        Connection connection = DBConnection.getConnection();

        // 2️⃣ Dependency wiring, singleton objects
        StudentDAO dao = new StudentDAO(connection);
        StudentRepository repository = new StudentRepository(dao);
        StudentService service = new StudentService(repository);
        StudentController controller = new StudentController(service);

        // 3️⃣ HTTP server
        server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 4️⃣ Handlers
        server.createContext("/addStudent", new AddStudentHandler(controller));
        server.createContext("/updateStudent", new UpdateStudentHandler(controller));
        server.createContext("/getStudent", new GetStudentHandler(controller));
        server.createContext("/getAllStudents", new GetAllStudentsHandler(controller));
        server.createContext("/deleteStudent", new DeleteStudentHandler(controller));

        server.setExecutor(null);
        server.start();

        System.out.println("Student Server started at http://localhost:8080");
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("Student Server stopped");
        }
    }
}
