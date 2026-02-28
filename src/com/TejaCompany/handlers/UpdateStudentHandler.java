package com.TejaCompany.handlers;

import com.TejaCompany.controller.StudentController;
import com.TejaCompany.DTO.StudentDTO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
// import java.io.OutputStream;
import java.util.Map;

public class UpdateStudentHandler implements HttpHandler {

    private final StudentController controller;

    public UpdateStudentHandler(StudentController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!"PUT".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        Map<String, String> params = QueryParamUtil.parse(exchange.getRequestURI());

        StudentDTO dto = new StudentDTO();
        dto.setId(Integer.parseInt(params.get("id")));
        dto.setName(params.get("name"));
        dto.setAge(Integer.parseInt(params.get("age")));
        dto.setNumber(Long.parseLong(params.get("number")));

        String response = controller.updateStudent(dto);

        exchange.sendResponseHeaders(200, response.getBytes().length);
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}
