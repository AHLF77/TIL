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

5. 부서 별 월급의 평균을 구하시오. (단, 평균이 3000 이상인 부서만 출력)
```sql
SELECT e.deptno, d.deptno, ROUND(AVG(salary),1) AS salaryavg FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
GROUP BY d.deptno
HAVING ROUND(AVG(salary),1) >= 3000;
```

6. 부서 별 대리와 사원 연봉의 평균을 구하시오. (단, 평균이 2500 이상인 부서만 출력)
```sql
SELECT e.deptno, d.deptname, ROUND(AVG(salary),1) AS salaryavg FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
WHERE t.titlename IN ('대리','사원')
GROUP BY d.deptno
HAVING ROUND(AVG(salary),1) >= 2500;
```

7. 2000년 부터 2002년에 입사는 직원들의 월급의 평균을 구하시오.
```sql
SELECT ROUND(AVG(salary),1) AS salaryavg FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
WHERE DATE_FORMAT(hdate, '%Y') BETWEEN '2000' AND '2002';
```

8. 부서 별 월급의 합의 ranking을 1위부터 조회 하시오.
```sql
SELECT b.deptno, b.deptname, b.salarysum FROM
(SELECT e.deptno, d.deptname, SUM(e.salary) AS salarysum FROM emp e
LEFT OUTER JOIN dept d ON e.deptno = d.deptno
LEFT OUTER JOIN title t ON e.titleno = t.titleno
GROUP BY e.deptno) b
ORDER BY b.salarysum DESC;
```

9. 서울에서 근무하는 직원들을 조회 하시오.
```sql
SELECT * FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
WHERE d.deptloc = '서울';
```

10. 이영자가 속한 부서의 직원들을 조회 하시오.
```sql
SELECT e.empname,d.deptname FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
WHERE d.deptno = (SELECT deptno FROM emp
WHERE empname = '이영자');
```

11. 김강국의 타이틀과 같은 직원들을 조회 하시오.
```sql
SELECT e.empname, t.titlename  FROM emp e
INNER JOIN title t ON e.titleno = t.titleno
WHERE e.titleno = (SELECT titleno FROM emp
WHERE empname = '김강국');
```
