package com.veve.glyphreport;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;


public class DetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(DetailsServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!! DETAILS SERVLET - HEADERS:");
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ":" + req.getHeader(headerName));
        }
        System.out.println("!!! DETAILS SERVLET - BODY:");
        InputStream is = req.getInputStream();
        byte[] buffer = new byte[100];
        while(is.read(buffer) != -1) {
            System.out.println(new String(buffer));
        }
        System.out.println("!!! DETAILS SERVLET - END");

    }

}
