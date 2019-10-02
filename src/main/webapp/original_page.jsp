<%@ include file = "top.jsp"%>

<table width="100%">
<tr>
<td width="50%" valign="top">
<img src='image?id=<%=request.getParameter("id")%>&mode=original' width="100%" border="1">
</td>
<td width="50%" valign="top">
<table>
<tr>
<td>
<%

    int Id = Integer.parseInt(request.getParameter("id"));
    int prevId = Id - 1 ;
    int nextId = Id + 1 ;

%>
<a href="original_page.jsp?id=<%=prevId%>&mode=original"><img src="left.jpg" height="100%"></a>
</td>
<td>
&nbsp;&nbsp;<a href="original_page.jsp?id=<%=request.getParameter("id")%>&mode=original">View Original</a><br/>
&nbsp;&nbsp;<a href="glyph_page.jsp?id=<%=request.getParameter("id")%>&mode=glyphs">View Original with glyphs</a><br/>
&nbsp;&nbsp;<a href="reflowed_page.jsp?id=<%=request.getParameter("id")%>&mode=reflowed">View Reflowed</a><br/>
</td>
<td>
<a href="original_page.jsp?id=<%=nextId%>&mode=original"><img src="left.jpg" height="100%"></a>
</td>
</tr>
</table>
</td>
</tr>
</table>