package com.veve.glyphreport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImageServlet extends DatabaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.getInteger(req.getParameter("id"));
            PreparedStatement ps = conn.prepareStatement("original_page_col from reports_tbl where id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                resp.getOutputStream().write(resultSet.getBytes(1));
            } else {
                resp.getOutputStream().write(("No report with id = " + id).getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getOutputStream().write(e.getMessage().getBytes());
        } finally {
            resp.getOutputStream().close();
        }
    }

}