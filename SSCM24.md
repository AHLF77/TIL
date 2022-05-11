# 0510강의

## 인터넷과 웹 시작
- 인터넷은 전 세계를 연결하는 국제 정보 통신망으로, 컴퓨터나 스마트폰 같은 디지털 기기로 연결되어 사람들이 정보를 공유할 수 있는 공간
- 2014년 10월 부터는 HTML5 버전을 사용하고 있음. 

## 웹 동작
- 웹은 요청과 응답이라는 간단한 형태로 동작
- 웹에서 어떤 대상을 구분하는 주소를 URL이라고 한다.
- URI: 웹에서 어떤 대상을 구분하는 주소를 URL 이라고 합니다.(URL도 포함)
- 사용자: 요청하는 쪽을 클라이언트
- 제공자: 응답하는 쪽
- 서버 프로그램은 백엔드 담당, 클라이언트 프로그램은 프론트 엔드 담당

## 웹 표준 기준
- HTML5(CSS와 자바 스크립트를 포함)
 - 멀티미디어 기능 
 - 그래픽 기능
- CSS는 HTML 페이지에 스타일을 지정하는 스타일 시트를 작성할 때 사용하는 언어

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

```xml
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

#### html 구성
- html1/src/main/webapp/main.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	h1{
		color:red;
		background:black;
	}
</style>
<script>
	function go(){
		alert('Clicked');
	};
</script>
</head>
<body>
	<h1>Main Page</h1>
	<button onclick="go()">Click</button>
	<img src = "img/logo.png">
</body>
</html>
```

- html1/src/main/webapp/p52.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/p52.css">
	<script src="js/p52.js"></script>
</head>
<body>
	<!-- Comments -->
	<button onclick = "go()">Click</button>
	<h1>Header1</h1>
	<h2>Header2</h2>
	<img src="img/logo.png">
</body>
</html>
```

#### png
- html1/src/main/resources/static/img/logo.png

#### css
- html1/src/main/resources/static/css/p52.css
```css
@charset "EUC-KR";

h1{
	color:red;
	}
	h2{
	color:blue;
	}

```

#### java script
- html1/src/main/resources/static/css/p52.js
```javascript
/**
 * 2020.05.10
 */
 
 	function go(){
		alert("Clicked");
	};
```