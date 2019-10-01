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
            System.out.println("req.getParameter(\"id\")"+req.getParameter("id"));
            String mode = req.getParameter("mode");
            String idString = req.getParameter("id");
            int id = 18;
            if (idString != null) {
                id = Integer.getInteger(idString);
            }
            byte[] data = getImage(id, mode);
            resp.getOutputStream().write(data);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getOutputStream().write(e.getMessage().getBytes());
        } finally {
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        }
    }

    public byte[] getImage(int id, String mode) throws Exception {
        PreparedStatement ps = null;
        byte[] result = null;
        if ("original".equals(mode)) {
            ps = conn.prepareStatement("SELECT original_page_col FROM reports_tbl WHERE id_col = ?");
        } else if ("reflowed".equals(mode)) {
            ps = conn.prepareStatement("SELECT reflowed_page_col FROM reports_tbl WHERE id_col = ?");
        }
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                result = resultSet.getBytes(1);
            }
            return result;

    }

}
