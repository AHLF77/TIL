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