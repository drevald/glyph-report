<%@ page import="java.io.*" %>
<head>
</head>
<body>
<pre>
<%

File dir = new File(".");
for(File file : dir.listFiles()) {
out.println(file.getPath());
}

%>
</pre>
</body>