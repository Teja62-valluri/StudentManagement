package com.TejaCompany;

import com.TejaCompany.config.StudentServer;

public class MainApplication {

    public static void main(String[] args) {
        try {
            StudentServer server = new StudentServer();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
