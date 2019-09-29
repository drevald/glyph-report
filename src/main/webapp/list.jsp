<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<head>
<title>Directory Content</title>
</head>
<body>
<pre>
<%

File dir = new File(".");
for(File file : dir.listFiles()) {
    out.println(file.getPath() + "\t" + new Date(file.lastModified()));
}

%>
</pre>
</body>