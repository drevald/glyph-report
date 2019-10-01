package com.veve.glyphreport;

import com.fasterxml.jackson.core.type.TypeReference;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ImageServlet extends DatabaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("req.getParameter(\"id\")"+req.getParameter("id"));
            String mode = req.getParameter("mode");
            int id = Integer.parseInt(req.getParameter("id"));
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
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBytes(1);
            }
        } else if ("reflowed".equals(mode)) {
            ps = conn.prepareStatement("SELECT reflowed_page_col FROM reports_tbl WHERE id_col = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBytes(1);
            }
        } else if ("glyphs".equals(mode)) {
            ps = conn.prepareStatement("SELECT original_page_col, glyphs FROM reports_tbl WHERE id_col = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                InputStream is = new ByteArrayInputStream(resultSet.getBytes(1));
                BufferedImage buffOriginalImage = ImageIO.read(is);
                Graphics2D g = buffOriginalImage.createGraphics();
                g.setColor(Color.RED);
                g.fillRect(100, 200, 300, 400);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                System.out.println("os size before is " + os.toByteArray().length);
                ImageIO.write(buffOriginalImage, "jpg", os);
                System.out.println("os size now is " + os.toByteArray().length);
                os.close();
                System.out.println("os size finally is " + os.toByteArray().length);
                result = os.toByteArray();
            }
        }
        return result;
    }


//    List<PageGlyphRecord> glyphsRecordRestored = mapper.readValue(baos.toByteArray(), new TypeReference<List<PageGlyphRecord>>(){});


}
