@echo off
call mvn clean package
call docker build -t ucb.login/app .
call docker rm -f app
call docker run -d -p 8080:9080 --name app ucb.login/app