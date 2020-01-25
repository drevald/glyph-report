package com.veve.glyphreport;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class LoaderServlet extends DatabaseServlet {

    private ServletFileUpload upload;

    public void init() throws ServletException {
        super.init();
        DiskFileItemFactory factory;
        FileCleaningTracker fileCleaningTracker;
        fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(this.getServletContext());
        factory = new DiskFileItemFactory();
        factory.setRepository(new File("."));
        factory.setFileCleaningTracker(fileCleaningTracker);
        upload = new ServletFileUpload(factory);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ByteArrayOutputStream originalImageStream = new ByteArrayOutputStream();
        ByteArrayOutputStream reflowedImageStream = new ByteArrayOutputStream();
        ByteArrayOutputStream glyphsStream = new ByteArrayOutputStream();
        String appVersion = null;
        String deviceModel = null;
        String sdk = null;
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> items = upload.parseRequest(req);
            for (Object itemObj : items) {
                FileItem item = (FileItem) itemObj;
                if (!item.isFormField()) {
                    System.out.println("Not form field");
                    System.out.println("item.getName() = " + item.getName());
                    System.out.println("item.getFieldName() = " + item.getFieldName());
                    if ("overturnedImage".equals(item.getFieldName())) {
                        IOUtils.copy(item.getInputStream(), reflowedImageStream);
                    } else if ("originalImage".equals(item.getFieldName())) {
                        IOUtils.copy(item.getInputStream(), originalImageStream);
                    } else if ("glyphs".equals(item.getFieldName())) {
                        IOUtils.copy(item.getInputStream(), glyphsStream);
                    }
                } else {
                    if ("version".equals(item.getFieldName())) {
                        appVersion = item.getString();
                    }
                    if ("deviceModel".equals(item.getFieldName())) {
                        deviceModel = item.getString();
                    }
                    if ("sdk".equals(item.getFieldName())) {
                        sdk = item.getString();
                    }
                    System.out.println("Form field");
                    System.out.println("item.getName() = " + item.getName());
                    System.out.println("item.getFieldName() = " + item.getFieldName());
                    System.out.println("item.getString() = " + item.getString());
                }
            }

            String stm = "INSERT INTO reports_tbl(original_page_col, reflowed_page_col, glyphs_col, created_col, app_version_col, device_model, sdk) VALUES (?,?,?,?,?,?,?) RETURNING id_col";
            PreparedStatement pst = conn.prepareStatement(stm);
            pst.setBytes(1, originalImageStream.toByteArray());
            pst.setBytes(2, reflowedImageStream.toByteArray());
            pst.setBytes(3, glyphsStream.toByteArray());
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pst.setString(5, appVersion);
            pst.setString(6, deviceModel);
            pst.setString(7, sdk);
            pst.execute();
            ResultSet resultSet = pst.getResultSet();
            int insertedId = -1;
            if (resultSet.next()) {
                insertedId = resultSet.getInt(1);
                System.out.println("Inserted row id is " + insertedId);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getOutputStream().write(insertedId);
            resp.flushBuffer();
            System.out.println("Response sent");
            //req.getRequestDispatcher("/list").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(originalImageStream);
            IOUtils.closeQuietly(reflowedImageStream);
            IOUtils.closeQuietly(glyphsStream);
        }

    }

}

