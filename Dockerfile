FROM tomcat:latest

COPY target/glyph-report.war /usr/local/tomcat/webapps/
COPY target/glyph-report/WEB-INF/lib/postgresql-42.3.3.jar /usr/local/tomcat/lib

CMD ["catalina.sh", "run"]