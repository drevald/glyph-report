<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<head>
<title>Directory Content</title>
</head>
<body>
<pre>
<%

        try {
            Class.forName("org.postgresql.Driver");
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            conn = DriverManager.getConnection(dbUrl);




        } catch (Exception e) {
            e.printStackTrace();
        }

%>
</pre>
</body>