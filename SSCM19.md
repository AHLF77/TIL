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