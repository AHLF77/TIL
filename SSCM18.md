# 0428 강의

## SQL 강의
1. 시스템 구축 요약
- 분석, 설계, 구현, 시험, 유지보수

2. 데이터베이스 모델링
- 현실세계에서 사용되는 데이터를 MySQL에 어떻게 옮겨 놓을 건이지 정하는 과정 

3. DDL, DML
- DDL: CREATE, DROP, ALTER
- DML: SELECT, INSERT, DELETE, UPDATE

3. ERD
- 요구사항 정의
- 테이블을 설계하고 구축
- 안전성

4. 스키마 생성법
```sql
CREATE SCHEMA companydb;
```

5. 현재 데이터 베이스 보기
```sql
SHOW DATABASES; 
```

6. CMD 창으로 SQL 접속
```sql
mysql -u admin1 -p
mysql -u root -p
```

7. CMD 창으로 SQL 접속
```sql
mysql -u admin1 -p
mysql -u root -p
```

7. DB 덤프를 받아 적용 시키기 & CMD 창 실행
```sql

use companydb;
select * from emp;
SELECT * FROM emp;
SELECT * FROM dept;

CREATE VIEW v_emp
AS
SELECT empno, empname FROM emp;
dir
mysql -u admin1 -p
 use companydb;
  show tables;
   source dump.sql
    show tables;


    SELECT * FROM emp;
SELECT * FROM dept;

CREATE VIEW v_emp
AS
SELECT empno, empname FROM emp;

SELECT * FROM v_emp;


SELECT empno, empname AS name FROM emp;
SELECT empno, empname, salary * 0.2 FROM emp;
SELECT empno, empname, salary * 0.2 AS fee FROM emp;
show table status;
SOURCE sqlDB.sql

```



## SQL WS
1. 직원 중에 manager가 없는 직원을 조회 하시오.

```sql
SELECT * FROM emp
WHERE manager is NULL;
```

2. 직원들의 월급과 세금(*0.15)을 조회 하시오.
```sql
SELECT salary AS month, salary * 0.15 AS fee FROM emp
ORDER BY fee DESC;
```
3. 01월과 12월에 입사는 직원들 중 이씨를 조회 하시오.
```sql
SELECT * FROM emp
WHERE date_format(hdate,'%m') IN ('01', '12') AND empname LIKE '이%';
```
4. 홍영자 직원의 입사 일보다 나중에 입사한 직원들을 조회 하시오.
```sql
SELECT * FROM emp
WHERE hdate > (SELECT hdate FROM emp
WHERE empname ='홍영자');
```

5. 월급의 범위가 3000에서 4500인 직원 중 manager가 김생산과 같은 직원들을 조회 하시오.
```sql
SELECT * FROM emp
WHERE salary BETWEEN 3000 AND 4500 
AND manager = (SELECT manager FROM emp WHERE empname='김생산');
```