package com.TejaCompany.handlers;

import com.TejaCompany.controller.StudentController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class DeleteStudentHandler implements HttpHandler {

    private final StudentController controller;

    public DeleteStudentHandler(StudentController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!"DELETE".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        String query = exchange.getRequestURI().getQuery(); // id=1
        int id = Integer.parseInt(query.split("=")[1]);

        String response = controller.deleteStudent(id);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        byte[] respBytes = response.getBytes();
        exchange.sendResponseHeaders(200, respBytes.length);
        exchange.getResponseBody().write(respBytes);
        exchange.getResponseBody().close();
    }
}
