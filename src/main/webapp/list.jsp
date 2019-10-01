<%@ page import="com.veve.glyphreport.Report" %>
<%@ page import="java.util.*" %>
<table>
        <tr>
            <th>Report Id</th>
            <th>Upload Time</th>
            <th>Application version</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>

<%

    List<Report> reports = (List<Report>)request.getAttribute("reports");
    for (Report report : reports) {
%>
        <tr>
            <td><%=report.getId()%></td>
            <td><%=report.getTimestamp()%></td>
            <td>undefined</td>
            <td><a href="image?id=<%=report.getId()%>">View Original</a><br/></td>
            <td><a href="image?id=<%=report.getId()%>">View Original with glyphs</a><br/></td>
            <td><a href="image?id=<%=report.getId()%>">View Reflowed</a><br/></td>
            <td><a href="image?id=<%=report.getId()%>">Delete</a><br/></td>
        </tr>
<%
    }
%>
</table>