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
- HTML5(CSS와 자바 스크립트를 포함) 기능
 1. 멀티미디어 기능 
 2. 그래픽 기능
 3. 통신
 4. 장치 접근
 5. 오프라인 및 저장소
 6. 시맨틱
 7. CSS3 스타일시트
 8. 웹의 성능 극대화 및 통합
- CSS는 HTML 페이지에 스타일을 지정하는 스타일 시트를 작성할 때 사용하는 언어

## HTML 기본 용어
1. 요소
- 객체
- 본문, 제목, 이미지 등 포함

2. 태그
- 이러한 요소를 만들 때 사용하는 작성 방법
<h1></h1>
<div></div>
<audio></audio>
<video></video>

- </> 필요없는 태그
<img>
<br>
<hr>

3. 속성
- 태그에 추가 정보를 부여할 때 사용하는 것
<img src="image.png">

4. 주석
- 설명을 기록
<!-- 주석 -->

## HTML5 페이지의 구조
<!DOCTYPE html> : 웹 브라우저에 HTML5 문서라는 것을 알리기 위해 반드시 첫 행에 나와야 하는 것
<head></head> : body 태그에 필요한 스타일시트와 자바스크립트를 제공
<title></title> : 웹 브라우저에 표시하는 제목을 지정
<body></body> : 사용자에게 실제로 보이는 부분을 작성하는 곳
<html></html> : 모든 HTML 페이지의 기본 요소로, 모든 태그는 이 HTML 태그 내부에 작성

## lang
<html lang="ko">
- 한국어

## head 태그 내부에 넣을 수 있는 태그
- meta: 웹 페이지에 추가 정보 전달
- title: 페이지 제목 지정
- script: 웹 페이지에 스크립트 추가
- link: 웹 페이지에 다른 파일 추가
- style: 웹 페이지에 스타일 시트 추가
- base: 웹 페이지의 기본 경로 지정

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

#### html 작성과 실행

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
	<!-- go 함수 불러오기 -->
	<h1>Main Page</h1>
	<button onclick="go()">Click</button>
	<!-- 사진 불러오기 -->
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

<!-- 외부 스타일리스트를 불러오게 함 -->
<!-- h1을 불러가기 할 시 빨강 -->
<!-- h2를 불러가기 할 시 파랑 -->
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

<!-- 버튼 클릭 시, 경고 창을 출력하는 것 -->
<!-- go() 함수로 저장 -->
 	function go(){
		alert("Clicked");
	};
```

### 오류와 검증
- chrome에서 f12를 눌러서 Elements와 Console 탭만을 사용하여 오류를 잡아냄.
- Elements 탭에서는 현재 HTML 페이지의 계층 구조와 각 태그에 적용된 스타일을 파악할 수 있습니다.
- Console 탭은 오류를 확인하거나 자바스크립트 코드를 추라고 입력할 때 사용합니다.