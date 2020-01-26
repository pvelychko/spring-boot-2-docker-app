## spring-boot-2-docker-app

### Docker
Run the following commands to start the application:
```$xslt
mvn clean install
docker build -t docker-app .
docker run -p 5000:8080 docker-app
```

### Testing

Open the following urls in a browser:
```$xslt
http://localhost:5000/greeting/personal?id=12
http://localhost:5000/greeting/business?type=big
http://localhost:5000/greeting/business?type=small
```