package com.TejaCompany.handlers;

import com.TejaCompany.controller.StudentController;
import com.TejaCompany.DTO.StudentDTO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class AddStudentHandler implements HttpHandler {

    private final StudentController controller;

    public AddStudentHandler(StudentController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Only POST method allowed
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            return;
        }

        try {
            // 1️⃣ Read JSON body from request
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes());
            is.close();

            // 2️⃣ Convert JSON to DTO
            StudentDTO dto = JsonUtil.fromJson(body, StudentDTO.class);

            // 3️⃣ Call controller
            String response = controller.addStudent(dto);

            // 4️⃣ Send JSON response
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            byte[] respBytes = response.getBytes();
            exchange.sendResponseHeaders(200, respBytes.length);
            exchange.getResponseBody().write(respBytes);
            exchange.getResponseBody().close();

        } catch (Exception e) {
            // Optional: better error handling
            e.printStackTrace();
            String errorResponse = "{\"error\":\"" + e.getMessage() + "\"}";
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
            exchange.getResponseBody().write(errorResponse.getBytes());
            exchange.getResponseBody().close();
        }
    }
}
