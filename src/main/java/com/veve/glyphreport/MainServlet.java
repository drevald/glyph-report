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
import java.io.IOException;
import java.util.List;


public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(MainServlet.class);

    private ServletFileUpload upload;
    private DiskFileItemFactory factory;
    private FileCleaningTracker fileCleaningTracker;

    public void init() throws ServletException {

        super.init();

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
        String sessionId = req.getSession().getId();
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> items = upload.parseRequest(req);
            for (Object itemObj : items) {
                FileItem item = (FileItem) itemObj;
                if (!item.isFormField()) {
                    logger.debug("Not form field");
                    logger.debug("item.getName() = " + item.getName());
                    logger.debug("item.getFieldName() = " + item.getFieldName());
                } else {
                    logger.debug("Form field");
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getRequestDispatcher("/upload.jsp").forward(req,resp);
        } catch (FileUploadException e) {
            logger.error(e, e);
        }  finally {

        }
    }

}
