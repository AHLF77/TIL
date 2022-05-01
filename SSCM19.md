# 0429 강의

### SELECT절
- 테이블에서 원하는 정보를 추출하는 명령어

### WHERE절
- 조회하는 결과에 특정한 조건을 줘서 원하는 데이터만 보고 싶을 때 사용

```sql
SELECT * FROM usertbl
WHERE height = 182;
```
```sql
SELECT * FROM usertbl
WHERE height > 170 AND birthYear < 1970;
```
```sql
SELECT * FROM usertbl
WHERE height >= 180 AND height <= 183;
```
```sql
SELECT * FROM usertbl
WHERE name = '윤종신';
```
```sql
SELECT * FROM usertbl
WHERE addr = '경남';
```

### GROUP BY절
- 그룹으로 묶어주는 역할
- 집계 함수와 함께 쓰이며 데이터를 그룹화 해주는 역할을 함.

```sql
SELECT userID, AVG(price) FROM buytbl
GROUP BY userID;
```

```sql
SELECT userID, SUM(price) FROM buytbl
GROUP BY userID;
```

#### 회원별 구매 금액의 평균 구하기
```sql
SELECT userID, ROUND(AVG(price),1) FROM buytbl
GROUP BY userID; 
```

#### ROUND 함수
- ROUND("값", "자리수")
- 소수점 자리수를 지정

#### 집계함수
- AVG()
- MIN
- MAX
```sql
-- 각 지역별 가장 키가 큰 키들의 평균을 구하시오.
SELECT addr, MAX(height) FROM usertbl
GROUP BY addr;
```
- COUNT

### DISTINCT
- 중복된 것을 하나로 합쳐주는 것.
```sql
SELECT DISTINCT addr FROM usertbl;
```

#### groupName 별 구매 고객의 인원 수를 구하시오.
```sql
SELECT groupName, COUNT(DISTINCT(userID)) FROM buytbl
GROUP BY groupName;
```

### IN 연산자
- WHERE절 뒤에 붙여서 칼럼이 특정 값을 가지고 있는지 확인하는 용도
```sql
SELECT * FROM usertbl
WHERE height IN (182,170,172);
```

### BETWEEN A AND B
- A(180)와 B(183)사이의 조회
```sql
SELECT * FROM usertbl
WHERE height between 180 AND 183;
```
#### 가입년도가 2005년과 2008년 사이에 회원을 조회하기.
```sql
SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') BETWEEN '2005' AND '2008';
```

### ORDER BY
- 결과물에 미치지 않도록, 결과가 출력되는 순서
- ASC : 오름차순(DEFAULT)
- DESC : 내림차순

```sql
SELECT * FROM usertbl 
ORDER BY height DESC; 
```
```sql
SELECT * FROM usertbl 
ORDER BY height DESC, name ASC;
```
```sql
SELECT * FROM usertbl 
ORDER BY 3 DESC, 2 ASC;
```
```sql
SELECT * FROM usertbl
WHERE addr = '서울'
AND birthYear < 1980
ORDER BY name;
```

### ROLLUP
- 분류 별로 합계 및 총합을 구할 시 사용
```sql
SELECT num, groupName, SUM(price * amount) AS SUMAP FROM buytbl
GROUP BY groupName, num
WITH ROLLUP;

```

### 날짜 형식
- DATE_FORMAT(날짜 , 형식) : 날짜를 지정한 형식으로 출력
```sql
-- 가입년도가 2007, 2009년인 회원을 조회 하시오.
SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') IN ('2007', '2009');

```
```sql
-- 가입 월이 04월, 07월인 회원을 조회 하시오.
SELECT * FROM usertbl
WHERE date_format(mDate,'%m') IN ('04', '07');
```

```sql
SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') < '2010';
```

### OR 연산자
- 조건 중 하나라도 만족 시 그대로 반환
```sql
SELECT * FROM usertbl
WHERE height = 182 OR height = 170 OR height = 172;
```

### LIKE 연산자
- 문자열 패턴을 검색하는데 사용
```sql
SELECT * FROM usertbl
WHERE name LIKE '%김%';

SELECT * FROM usertbl
WHERE name LIKE '_종_';

SELECT * FROM usertbl
WHERE name LIKE '_종%';
```

### LIMIT 연산자
- 조회 결과 행수를 제한
```sql
SELECT * FROM usertbl
ORDER BY height
LIMIT 2,5;
```

### 조건문 NOT NULL
- NULL 값이 아닌 경우
```sql
SELECT * FROM usertbl
WHERE addr = '서울'
AND birthYear > 1970
AND mobile1 IS NOT NULL;
```

```sql
SELECT userID, groupName, SUM(price * amount) as usum FROM buytbl
GROUP BY userID, groupName
HAVING userID IN ('BBK','KBS')
AND groupNAME IS NOT NULL
ORDER BY userID;
```

### 윤종신 회원과 같은 주소지의 회원을 조회 하시오.
```sql
SELECT * FROM usertbl
WHERE addr = (SELECT addr FROM usertbl
WHERE name ='윤종신');
```

### 윤종신 회원보다 키가 큰 회원을 조회 하시오.
```sql
SELECT * FROM usertbl
WHERE height > (SELECT height FROM usertbl
WHERE name ='윤종신');
```

### 경남 지역의 회원들 키와 동일한 회원들을 조회 하시오.
```sql
SELECT * FROM usertbl
WHERE height IN (SELECT height FROM usertbl
WHERE addr = '경남');
```

### HAVING 절
- WHERE와 비슷한 개념으로 조건을 제한하는 것이지만, 집계 함수에 대해서 조건을 제한하는 것
- HAVING 절은 반듯이 GROUP BY절 뒤에 와야 한다.
```sql
SELECT userID, ROUND(AVG(price*amount),1) AS pavg FROM buytbl
GROUP BY userID
HAVING pavg > 100
ORDER BY pavg DESC;
```

#### kbs와 bbk의 데이터만 추출해라

```sql
SELECT userID, groupName, SUM(price * amount) as usum FROM buytbl
GROUP BY userID, groupName
HAVING userID IN ('BBK','KBS')
ORDER BY userID;
```

### COUNT 함수
- 테이블에 존재하는 데이터 갯수 조회
```sql
SELECT COUNT(DISTINCT(userID)) FROM buytbl;
```

### AVG 함수
- usertbl 회원 들의 평균 키보다 큰 회원을 조회하기
```sql
SELECT * FROM usertbl
WHERE height > (SELECT AVG(height) FROM usertbl);
```

### WorkShop Part1
- emp 테이블에서 진행할 것.
```sql
SELECT * FROM emp;
```

 1. 부서별, 직급별 연봉 평균을 구하시오.
```sql
SELECT deptno, titleno, ROUND(AVG(salary),1) AS salaryavg FROM emp
GROUP BY deptno, titleno;
```

 2. 입사 년도 별 월급의 평균을 구하시오.
```sql
SELECT date_format(hdate,'%Y') AS empdate, ROUND(AVG(salary),1)  AS salaryavg FROM emp
GROUP BY date_format(hdate,'%Y');
```

 3. 부서 별 입사 월을 기준으로 연봉의 합을 구하시오.
```sql
SELECT deptno, date_format(hdate,'%m') AS empdate, SUM(salary) AS salaryavg FROM emp
GROUP BY deptno,date_format(hdate,'%m');
```

 4. 이영업이 속한 부서의 연봉의 평균을 구하시오.
```sql
SELECT deptno, ROUND(AVG(salary),1) AS salaryavg FROM emp
WHERE deptno = (SELECT deptno FROM emp
WHERE empname = '이영업');
```

 5. 홍영자 직급과 같은 직원들의 연봉 평균보다 많이 받는 직원을 구하시오.  
```sql
SELECT * FROM emp
WHERE salary> (SELECT ROUND(AVG(salary),1) FROM emp
WHERE titleno = (SELECT titleno FROM emp
WHERE empname = '홍영자'));
```

 6. 회사 내 매니저는 총 몇명인지 구하시오.
```sql
SELECT COUNT(DISTINCT(manager)) FROM emp;
```

 7. 2000-01-01부터 2002-12-31일까지 입사한 직원들의 연봉 평균을 구하시오.
```sql
SELECT ROUND(AVG(salary),1) AS salaryavg FROM emp
WHERE hdate BETWEEN '2000-01-01' AND '2002-12-31';
```

### WITH 절
- CTE(기존의 뷰, 파생 테이블, 임시 테이블 등으로 사용되던 것을 대신할 수 있음.)를 표현
- 간단히 말해 이름이 부여된 서브쿼리
- 한번 실행할 쿼리문내에 정의되어 있을 경우, 그 쿼리문안에서만 실행된다는 차이점이 있음.
- 사용이유: 쿼리 성능을 높일 수 있도록, 자주 실행되는 구문을 한번만 parsing 될 수 있도록 함.

```sql
WITH temp(userID, total)
AS 
(SELECT userID, SUM(price*amount) FROM buytbl
GROUP BY userID)
SELECT total FROM temp;
```

```sql
WITH emp(userID, max)
AS 
(SELECT addr, MAX(height) FROM usertbl
GROUP BY addr)
SELECT COUNT(userid) FROM emp;
```

```sql
SELECT b.* FROM(
SELECT addr, MAX(height) FROM usertbl
GROUP BY addr) b;
```

```sql
SELECT AVG(a.hmax) FROM(
SELECT addr, MAX(height) AS hmax FROM usertbl
GROUP BY addr ) a;
```

### CONCAT 문자열 연결 합치기
- 여러 문자열을 하나로 합쳐주는 역할
```sql
SELECT CONCAT (prodName, groupName) FROM buytbl;
```

### IF문 & Case ~ When문
- 쉽게 말해 조건문임.
```sql
SELECT userID, price * amount AS tt, IF (price * amount > 500, 'Hight', 'Low') AS level FROM buytbl;
```

```sql
SELECT userID, amount, 
CASE
	WHEN amount >= 1 AND amount < 2 THEN 'C'
    WHEN amount >= 2 AND amount < 4 THEN 'B'
    WHEN amount >= 4 AND amount < 6 THEN 'A'
    ELSE 'C'
END AS level    
FROM buytbl;
```

### IFNULL
- 해당 Column의 값이 NULL을 반환할 때, 다른 값으로 출력할 수 있도록 하는 함수
- 기본구조
```sql
SELECT IFNULL(Column명, "Null일 경우 대체 값") FROM 테이블명; 
```
```sql
SELECT prodName, IFNULL(groupName, 'None') FROM buytbl;
```

### FORMAT 함수
- 서식이 지정된 값을 반환
```sql
SELECT FORMAT(123456.123456,5);
```

### INTERVAL, ADDDATE(날짜, 차이), SUBDATE(날짜, 차이)
- INTERVAL: 두 Datetime 값의 차이를 저장하기 위해 사용
- ADDDATE: 날짜/시간을 기준으로 시간을 더한 결과
- SUBDATE: 날짜/시간을 기준으로 시간을 뺀 결과
```sql
SELECT mDate, ADDDATE(mDate, INTERVAL 30 DAY), 
SUBDATE(mDate, INTERVAL 30 DAY) 
FROM usertbl;
```
```sql
SELECT mDate, ADDDATE(mDate, INTERVAL 1 YEAR), 
SUBDATE(mDate, INTERVAL 1 YEAR) 
FROM usertbl;
```

### 현재 연-월-일
```sql
SELECT CURDATE();
```

### 현재 시:분:초
```sql
SELECT CURTIME();
```

### 현재(연-월-일 시:분:초), 현재 년도
```sql
SELECT NOW();
SELECT SYSDATE();
SELECT YEAR(SYSDATE());
```

### 년,월 출력
```sql
SELECT YEAR(mDate) FROM usertbl;
SELECT DATE_FORMAT(mDate, '%Y%m') FROM usertbl;
```

### DATEDIFF(날짜1, 날짜2)
- 날짜1-날짜2를 일수로 결과를 나타냄
- 즉, 날짜2에서 날짜1까지 차이
```sql
SELECT mDate, DATEDIFF(NOW(), mDate) FROM usertbl;
```

### PERIOD_DIFF(연월1, 연월2)
- 주 인자의 개월수 차이를 구함(연월1 - 연월2)
```sql
SELECT mDate, PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m'), DATE_FORMAT(mDate, '%Y%m')) 
FROM usertbl;
```


### WorkShop Part2

```sql
SELECT * FROM emp;
```
1. 오늘 날짜 기준으로 입사 일부터 몇 일이 지났고 몇 달이 지났는지 출력 하시오.
```sql
SELECT hdate, DATEDIFF(NOW(), hdate) AS Cday, 
PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m'), 
DATE_FORMAT(hdate, '%Y%m')) 
AS Cmonth
FROM emp;
```

2. 직원들 연봉이 4000이상이면 high, 2500 이상이면 middle, 2500이하면 low 출력
```sql
SELECT empno, salary, 
CASE
	WHEN salary < 2500 THEN 'low'
    WHEN salary >= 2500 AND salary < 4000 THEN 'middle'
    WHEN salary >= 4000 THEN 'high'
END AS level    
FROM emp;
```

```sql
3. 부서별 연봉 평균의 합을 구하시오.
SELECT SUM(a.salaryavg) FROM (SELECT AVG(salary) AS salaryavg FROM emp
GROUP BY deptno) a;
```

```sql
4. 부서 별 오늘날짜 기준으로 입사일 평균을 구하시오.
SELECT deptno, AVG(DATEDIFF(NOW(), hdate)) AS Cdateavg FROM emp
GROUP BY deptno;
```

```sql
5. 이말숙 직원과 같은 해에 입사한 직원을 조회 하시오.
SELECT empname, hdate FROM emp
WHERE YEAR(hdate) = (SELECT YEAR(hdate) FROM emp
WHERE empname = '이말숙');
```

```sql
6. 부서별 최고 임금을 받는 직원의 평균을 구하고 그 평균 보다 많이 받는 직원을 조회 하시오.
WITH temp(deptno, max)
AS
(SELECT deptno, MAX(salary) FROM emp
GROUP BY deptno)
SELECT empname, salary FROM emp
WHERE salary > (SELECT AVG(max) FROM temp);
```