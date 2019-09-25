package com.veve.glyphreport;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(UploadServlet.class);

    private ServletFileUpload upload;


    public void init() throws ServletException {
        super.init();
        DiskFileItemFactory factory;
        FileCleaningTracker fileCleaningTracker;
        // Create a factory for disk-based file items
        fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(this.getServletContext());
        factory = new DiskFileItemFactory();
        factory.setRepository(new File("."));
        factory.setFileCleaningTracker(fileCleaningTracker);
        // Create a new file upload handler
        upload = new ServletFileUpload(factory);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!! UPLOAD SERVLET");
        String sessionId = req.getSession().getId();
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> items = upload.parseRequest(req);
            for (Object itemObj : items) {
                FileItem item = (FileItem) itemObj;
                if (!item.isFormField()) {
                    System.out.println("Not form field");
                    System.out.println("item.getName() = " + item.getName());
                    System.out.println("item.getFieldName() = " + item.getFieldName());
                    InputStream is = item.getInputStream();
                    FileOutputStream fos = new FileOutputStream(new File(item.getName()));
                    byte[] buffer = new byte[1000];
                    while (is.read(buffer) != -1) {
                        fos.write(buffer);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                } else {
                    System.out.println("Form field");
                    System.out.println("item.getName() = " + item.getName());
                    System.out.println("item.getFieldName() = " + item.getFieldName());
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getRequestDispatcher("/list.jsp").forward(req,resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

}
