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
        StringBuilder sb = new StringBuilder();
        sb.append("!!! DETAILS SERVLET - HEADERS:");
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            sb.append(headerName + ":" + req.getHeader(headerName)+"\n");
        }
        sb.append("!!! DETAILS SERVLET - BODY:");
        InputStream is = req.getInputStream();
        byte[] buffer = new byte[1];
        while(is.read(buffer) != -1) {
            sb.append(new String(buffer));
        }
        sb.append("!!! DETAILS SERVLET - END");
        System.out.println(sb.toString());
        resp.getOutputStream().write(sb.toString().getBytes());
        resp.getOutputStream().close();
    }

}
