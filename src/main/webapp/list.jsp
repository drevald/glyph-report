<%@ page import="com.veve.glyphreport.Report" %>
<%@ page import="java.util.*" %>
<%

    List<Report> reports = (List<Report>)request.getAttribute("reports");
    for (Report report : reports) {
%>
        <a href="image.jsp?id=<%=report.getId()%>"><%=report.getTimestamp()%></a><br/>
<%
    }
%>