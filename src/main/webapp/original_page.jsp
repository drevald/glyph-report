<%@ page import="com.veve.glyphreport.Report" %>
<%@ page import="java.util.*" %>
<table width="100%">
<tr>
<td width="50%" valign="top">
<img src='image?id=<%=request.getParameter("id")%>&mode=original' width="100%" border="1">
</td>
<td width="50%" valign="top">
&nbsp;&nbsp;<a href="original_page.jsp?id=<%=request.getParameter("id")%>&mode=original">View Original</a><br/>
&nbsp;&nbsp;<a href="glyph_page.jsp?id=<%=request.getParameter("id")%>&mode=glyphs">View Original with glyphs</a><br/>
&nbsp;&nbsp;<a href="reflowed_page.jsp?id=<%=request.getParameter("id")%>&mode=reflowed">View Reflowed</a><br/>
</td>
</tr>
</table>