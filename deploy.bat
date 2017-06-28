
call rmdir /s /q D:\apache-tomcat-7.0.69\webapps\watcher

call del D:\apache-tomcat-7.0.69\webapps\watcher.war

call rmdir /s /q D:\apache-tomcat-7.0.69\work\Catalina

call mvn clean install -Dmaven.test.skip=true

call copy D:\SpringInAction4\watcher\target\watcher-0.0.1-SNAPSHOT.war D:\apache-tomcat-7.0.69\webapps\watcher.war

call D:\apache-tomcat-7.0.69\bin\catalina jpda start


