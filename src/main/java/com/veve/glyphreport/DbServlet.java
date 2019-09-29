package com.veve.glyphreport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            System.out.println("JDBC_DATABASE_URL " + dbUrl);
            Connection conn = DriverManager.getConnection(dbUrl);
            System.out.println(conn.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
