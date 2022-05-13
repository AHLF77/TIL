# 0512강의(CSS)

## CSS3 기초
- 특정 HTML 태그를 선택할 때 주로 사용
ex)
h1{color:red;}
- h1: 선택자
- color: 스타일 속성
- red: 스타일 값

### CSS3 선택자 종류
- 전체 선택자: *(ex: *)
- 태그 선택자: 태그(ex: h1)
- 아이디 선택자: #아이디(ex: #id)
- 클래스 선택자: .클래스(ex: .header)
- 후손 선택자: 선택자 선택자(ex: header h1)
- 자손 선택자: 선택자 > 선택자(ex: header > h1)
- 구조 선택자: 선택자:nth-child(수열)(ex: li:nth-child(2n+1))
※ 다 외우기는 어려우므로 w3c 공식 문서(http://www.w3.org/TR/CSS/)를 참고 바람.


## 기본 선택자
- 전체 선택자: HTML 페이지 내부의 태그를 모두 선택
- 태그 선택자: HTML 페이지 내부의 특정 태그 하나를 선택할 때 사용
- 아이디 선택자: 특정 ID 속성이 있는 태그 선택, 웹 표준에 ID 속성은 웹 페이지 내부에서 중복되면 안된다는 규정이 있으므로 아이디 선택지는 특정 태그 하나를 선택할 때 사용.
- 클래스: 특정 클래스가 있는 태그 선택

## 속성 선택자
- 선택자[속성]: 특정한 속성이 있는 태그 선택
- 선택자[속성=값]: 특정한 속성 내부 값이 특정 값과 같은 태그 선택
- input 태그는 이름이 모두 같지만 type 속성에 따라 형태가 다릅니다. 그래서 input 태그를 선택할 때는 속성 선택자를 많이 사용합니다.
- ex) <input type="password">

## 후손 선택자
- 특정한 태그의 후손을 선택할 때 사용
- 선택자A 선택자B: 선택자A의 후손인 선택자B 선택

## 자손 선택자
- 특정한 태그의 자손을 선택할 때 사용
- 선택자A > 선택자B: 선택자A의 자손인 선택자B 선택

## 반응 선택자
- 사용자 반응으로 생성되는 특정한 상태를 선택
- :active: 사용자가 마우스로 클릭한 태그 선택
- :hover: 사용자가 마우스 커서를 올린 태그 선택

## 구조 선택자
- 특정한 위치에 있는 태그를 선택할 때 사용
- :first-child: 형제 관계에서 첫 번째로 등장하는 태그 선택
- :last-child: 형제 관계에서 마지막으로 등장하는 태그 선택
- :nth-child(수열): 형제 관계에서 앞에서 수열 번째로 등장하는 태그 선택
- :nth-last-child(수열): 형제 관계에서 뒤에서 수열 번째로 등장하는 태그 선택


#### P135
- focus: 사용자가 입력 시 집중할 수 있도록 하는 문법
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	input[type="text"]{
		background:pink;
	}
	input[name="pwd"]{
		background:pink;
	}
	input[type="submit"]{
		color:white;
		background:black;
	}
	input:focus{
		background:blue;
	}
</style>
</head>
<body>
	<h1>P135</h1>
	<form>
		ID<input type="text" name="id"><span>Mandatory</span><br>
		PWD<input type="password" name="pwd"><span>Mandatory</span><br>
		<input type="submit" value="login"><br>
	</form>
</body>
</html>
```


#### P139
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/p139.css">
<style>
	 
</style>
</head>
<body>
	<h1>p139</h1>
	<!-- HTML Comments -->
	<table id="tb1">
		<thead>
			<tr><th>header1</th><th>header2</th><th>header3</th></tr>
		</thead>
		<tbody>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		</tbody>	
	</table>
</body>
</html>
- <thead> 태그는 HTML 테이블에서 헤더 콘텐츠(header content)들을 하나의 그룹으로 묶을 때 사용
- <tbody> 태그는 HTML 테이블에서 내용 콘텐츠(body content)들을 하나의 그룹으로 묶을 때 사용

```css
@charset "EUC-KR";
@import url('https://fonts.googleapis.com/css2?family=Koulen&family=Oleo+Script+Swash+Caps:wght@700&display=swap');/*구글에서 내려받은 폰트 import*/
/* 2022.05.12 */

*{
 		margin:0;
 		padding:0;
 	  }
 	  #tb1{
 	  	border-collapse: collapse;
 	  	width:300px;
 	  	height:150px;
 	  	border:1px solid black;
 	  	font-family: 'Koulen', cursive;
 	  }
 	  #tb1 > thead > tr{
 	  	background:black;
 	  	color:white;
 	  	font-family: 'Koulen', cursive;
 	  }
 	  #tb1 > tbody > tr:nth-child(2n+1){
 	  	background:gray;
 	  	color:black;
 	  }
 	  #tb1 > tbody > tr > td:nth-child(3){
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	text-align: right;
 	  }
 	  #tb1 > tbody > tr:hover{
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	font-size: 1.2em;
 	  	font-weight:bold;
 	  	background:#9FC93C;
 	  	color:blue;
 	  }
 	  /* CSS Comments */
 	  body{
 	  	background-color: #5CD1E5;
 	  	background-image: url('img/bg.jpg');
 	  }
```