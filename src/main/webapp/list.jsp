<%@ include file = "top.jsp"%>
<body>
<table>
        <tr>
            <th>Report Id</th>
            <th>Upload Time</th>
            <th>App. ver.</th>
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
            <td><%=report.getAppVersion()%></td>
            <td>&nbsp;<a href="original_page.jsp?id=<%=report.getId()%>&mode=original">View Original</a>&nbsp;</td>
            <td>&nbsp;<a href="glyph_page.jsp?id=<%=report.getId()%>&mode=glyphs">View Original with glyphs</a>&nbsp;</td>
            <td>&nbsp;<a href="reflowed_page.jsp?id=<%=report.getId()%>&mode=reflowed">View Reflowed</a>&nbsp;</td>
            <td>&nbsp;<a href="delete?id=<%=report.getId()%>">Delete</a><br/></td>
        </tr>
<%
    }
%>
</table>
</body>