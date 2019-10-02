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

public class ListServlet extends DatabaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Report> reports = new ArrayList<Report>();
        try {
            PreparedStatement ps = conn.prepareStatement("select id_col, app_version_col, created_col from reports_tbl");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                reports.add(new Report(
                        resultSet.getInt("id_col"),
                        resultSet.getString("app_version_col"),
                        resultSet.getTimestamp("created_col")));
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


