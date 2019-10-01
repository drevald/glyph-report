package com.veve.glyphreport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        List<Report> reports = new ArrayList<Report>();
        try {
            PreparedStatement ps = conn.prepareStatement("select id_col, octet_length(original_page_col), octet_length(reflowed_page_col), octet_length(glyphs_col), created_col from reports_tbl");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + "\t" +
                        resultSet.getInt(2) + "\t" +
                        resultSet.getInt(3) + "\t" +
                        resultSet.getInt(4) + "\t" +
                        resultSet.getTimestamp(5));
                reports.add(new Report(resultSet.getInt(1), resultSet.getTimestamp(5)));
            }
            req.setAttribute("reports", reports);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }
    }

}


