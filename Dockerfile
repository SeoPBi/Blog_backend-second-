# 베이스 이미지 설정
FROM openjdk:17-jdk-alpine

# 절대경로로 작업공간 설정
WORKDIR /usr/src/app

# 프로젝트의 jar 파일을 Docker 컨테이너에 추가
COPY build/libs/board-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너가 시작될 때 실행될 명령어 설정
ENTRYPOINT ["java","-jar","/app.jar"]
