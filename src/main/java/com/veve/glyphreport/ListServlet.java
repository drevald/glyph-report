package com.veve.glyphreport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListServlet extends HttpServlet {

    Connection conn;

    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.postgresql.Driver");
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            conn = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id_col, " +
                    "octet_length(original_page_col), " +
                    "octet_length(reflowed_page_col), " +
                    "octet_length(glyphs), " +
                    "created_col) from reports_tbl");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + "\t" +
                        resultSet.getInt(2) + "\t" +
                        resultSet.getInt(3) + "\t" +
                        resultSet.getTimestamp(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


