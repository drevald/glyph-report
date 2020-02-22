FROM tomcat:latest

COPY target/glyph-report.war /usr/local/tomcat/webapps/
COPY target/glyph-report/WEB-INF/lib/postgresql-9.1-901-1.jdbc4.jar /usr/local/tomcat/lib

CMD ["catalina.sh", "run"]