<%@ page import="com.veve.glyphreport.Report" %>
<%@ page import="java.util.*" %>
<header>
<style>
.td {
  padding-right: 30px;
  padding-left: 80px;
}
</style>
</header>
<body>
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
            <td><a href="original_page.jsp?id=<%=report.getId()%>&mode=original">View Original</a><br/></td>
            <td><a href="glyph_page.jsp?id=<%=report.getId()%>&mode=glyphs">View Original with glyphs</a><br/></td>
            <td><a href="reflowed_page.jsp?id=<%=report.getId()%>&mode=reflowed">View Reflowed</a><br/></td>
            <td><a href="delete?id=<%=report.getId()%>">Delete</a><br/></td>
        </tr>
<%
    }
%>
</table>
</body>