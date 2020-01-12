<%@ include file = "top.jsp"%>

<table width="100%">
<tr>
<td width="50%" valign="top">
<img src='image?id=<%=request.getParameter("id")%>&mode=reflowed' width="100%" border="1">
</td>
<td width="50%" valign="top">
<%@ include file="paging.jsp" %>
</td>
</tr>
</table>