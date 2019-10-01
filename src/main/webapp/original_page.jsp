<%@ page import="com.veve.glyphreport.Report" %>
<%@ page import="java.util.*" %>
<img src='image?id=<%=request.getParameter("id")%>&mode=original' width="50%">
<a href="original_page.jsp?id=<%=report.getId()%>&mode=original">View Original</a><br/>
<a href="glyph_page.jsp?id=<%=report.getId()%>&mode=glyphs">View Original with glyphs</a><br/>
<a href="reflowed_page.jsp?id=<%=report.getId()%>&mode=reflowed">View Reflowed</a><br/>
