FROM tomcat

LABEL maintainer='jianqi.zhao.io@gmail.com'

ADD ./build/libs/Tradeit.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]