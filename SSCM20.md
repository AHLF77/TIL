# 0502 강의

1. 직원정보를 출력 한다. 직원의 연봉 정보와 연봉의 세금 정보를 같이 출력 한다. (세금은 10%)
```sql
SELECT empname, salary, ROUND((salary*0.1),1) AS tax FROM emp;
```

2. 직원정보 중 2001 이전에 입사 하였고 월급이 4000만원 미만인 직원을 조회
```sql
SELECT hdate, salary FROM emp
WHERE DATE_FORMAT(hdate, '%Y') < 2001 AND salary < 4000;
```

3. manager가 있는 직원 중 이름에 '자' 가 들어가 있는 직원정보 조회
```sql
SELECT * FROM emp
WHERE manager IS NOT NULL AND empname LIKE '%자%'; 
```
4. 월급이 2000미만은 '하' 4000미만은 '중' 4000이상은 '고' 를 출력
```sql
SELECT empno, empname, salary, 
CASE
	WHEN salary < 2000 THEN '하'
    WHEN salary >= 2000 AND salary < 4000 THEN '중'
    WHEN salary >= 4000 THEN '상'
END AS grade    
FROM emp;
```

