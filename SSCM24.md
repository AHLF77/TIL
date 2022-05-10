# 0510강의

### 이클립스 초기 설정
1. new project
2. Spring Boot > Spring Starter Project 선택
3. 프로젝트명 입력
4. Group과 Package 명 선택, 반드시 두개 이상의 package 명으로 입력
5. Dependencies 선택
 1) Spring Boot DevTools 
 2) Spring Web
6. pom.xml 추가
 - 42번 째 줄에 추가
```html
        <!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
		<!-- Servlet -->

		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>


		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- json request -->   

		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1</version>
  		</dependency>
```

7. src/main/resources 에 applications.properties 파일 수정 (반드시 메모장으로 열것)

C드라이브 경로: C:\web\html3\src\main\resources
※ 중간에 html3만 자바 프로젝트명 기입할 것

server.port=80
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp

8. 80 port 문제 시 (윈도우 11은 해당 안됨) 

9. 제어판 > 시스템 및 보안 > 관리도구 > 서비스 > World Wide Web Publishibg  서비스 

10. 서버 IP: 127.0.0.1

11. 오류 메시지는 f12에 콘솔에서 확인이 가능.

