# 0502 배운 내용 요약

## JOIN 
- 두 개 이상의 테이블을 서로 묶어서 하나의 결과 집합으로 만들어 내는 것.
- 1대 다 관계

## OUTER JOIN
- 기준 테이블에만 데이터가 존재하면 조회가능

1. 사원 정보를 출력 하시오.
```sql
INSERT INTO emp VALUES ('1011', NULL, NULL, '이말자', NULL, 3000, SYSDATE());
INSERT INTO title VALUES ('50','인턴');
```

```sql
SELECT * FROM emp e
LEFT OUTER JOIN title t ON e.titleno = t.titleno;
```

```sql
SELECT * FROM emp e
RIGHT OUTER JOIN title t ON e.titleno = t.titleno;
```

## INNER JOIN
- 기준 테이블과 조인 테이블 모두 데이터가 존재해야 조회가능
```sql
SELECT d.deptname, AVG(e.salary) FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
GROUP BY d.deptname;
```

## FULL
- 전체 출력을 해야 할 경우 UNION을 사용.
```sql
SELECT * FROM emp e
LEFT OUTER JOIN title t ON e.titleno = t.titleno
UNION
SELECT * FROM emp e
RIGHT OUTER JOIN title t ON e.titleno = t.titleno;
```

## CROSS JOIN
```sql
SELECT * FROM emp e
CROSS JOIN title;
```
## SELF JOIN(중요)
- SELF JOIN을 사용하는 이유: 테이블을 바꿈으로써 트러블을 최소화하기 위해 테이블을 분리.
1. 사원 이름과 매니져 이름을 출력 하시오. (단, 모든 직원을 출력 하시오.)
```sql
SELECT e1.empname, e2.empname FROM emp e1
LEFT OUTER JOIN emp e2 ON e1.manager = e2.empno;
```

### WrokShop Part1
1. 연봉이 1000 ~ 3000 사이의 사람들의 정보를 조회하시오
```sql
SELECT salary, empname FROM emp
WHERE salary between 1000 AND 3000;
```

2. 이영업과 매니저가 같은 직원을 조회하여 그 직원들의 연봉 평균을 구하시오.
```sql
SELECT manager, ROUND(AVG(salary),1) AS salaryavg FROM emp
WHERE manager = (SELECT manager FROM emp
WHERE empname = '이영업'); 
```

3. 문제 성이 김씨인 직원들의 평균 연봉을 구하고 이들 평균 연봉 보다 낮은 연봉의 직원의 근속일수를 구하시오. (근속일수는 현재-입사일)
```sql
SELECT empname, salary, DATEDIFF(SYSDATE(),hdate) FROM emp
WHERE salary < (SELECT ROUND(AVG(salary),1) FROM emp
WHERE empname LIKE '%김%'); 
```

4. 이름이 '홍' 과 '이' 로 시작하는 사람들 평균 연봉들을 구하시오.
```sql
SELECT ROUND(AVG(salary),1) AS hlavg FROM emp
WHERE empname LIKE '%홍%' OR empname LIKE'%이%';
```

5. 사원 정보(이름, 부서명, 직급명)를 출력 하시오. (단, '이말자'도 출력)
```sql
SELECT e.empname, d.deptname, t.titlename FROM emp e
LEFT OUTER JOIN dept d ON e.deptno = d.deptno
LEFT OUTER JOIN title t ON e.titleno = t.titleno;
```

6. 직원 정보(이름, 부서명, 직급명, 매니저명)를 출력한다.(단, 모든 직원 정보를 출력)
```sql
SELECT e.empname, d.deptname, t.titlename, e.manager FROM emp e
LEFT OUTER JOIN dept d ON e.deptno = d.deptno
LEFT OUTER JOIN title t ON e.titleno = t.titleno
LEFT OUTER JOIN emp e2 ON e.manager = e2.empno;
```

### WrokShop Part2
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

### WrokShop Part3
1. 2000 년 이후 입사 한 사원들의 정보를 출력.(사번, 이름, 타이틀, 부서, 지역)
```sql
SELECT e.empno, e.empname, e.titleno, d.deptname, d.deptloc  FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
WHERE DATE_FORMAT(e.hdate, '%Y') >= '2000';
```

2. 부서 이름 별 월급의 평균을 구하시오. (단, 평균이 3000 이상인 부서만 출력)
```sql
SELECT d.deptname, ROUND(AVG(salary),1) AS salaryavg FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
GROUP BY d.deptname
HAVING ROUND(AVG(salary),1) >= 3000;
```

3. 대구 지역의 직원 들의 평균 연봉을 구하시오.
```sql
SELECT d.deptloc, ROUND(AVG(salary),1) AS salaryavg FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
WHERE d.deptloc = '대구'
GROUP BY d.deptno
HAVING ROUND(AVG(salary),1);
```

4. 홍영자 직원와 같은 부서 직원들의 근무 월수를 구하시오. 
```sql
SELECT e.empname, d.deptname, e.deptno, PERIOD_DIFF(DATE_FORMAT(NOW(), '%Y%m'), DATE_FORMAT(e.hdate, '%Y%m')) AS nwm FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
WHERE e.deptno = (SELECT e.deptno FROM emp e
WHERE e.empname = '홍영자');
```

5. 입사 년수가 가장 많은 직원 순으로 정렬 하여 순위를 정한다. (이름, 부서명, 직위, 년수)
```sql
SELECT e.empname, d.deptname, t.titlename, (YEAR(NOW())-YEAR(e.hdate)) AS rey FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
INNER JOIN title t ON e.titleno = t.titleno
ORDER BY rey DESC;
```