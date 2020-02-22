FROM tomcat:latest

COPY target/glyph-report.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]