# 0428 강의

## SQL 강의
1. 시스템 구축 요약
- 분석, 설계, 구현, 시험, 유지보수

2. 데이터베이스 모델링
- 현실세계에서 사용되는 데이터를 MySQL에 어떻게 옮겨 놓을 건이지 정하는 과정 

3. DDL, DML
- DDL(Data Definition Language): CREATE, DROP, ALTER()
 1. 데이터베이스, 테이블, 뷰, 인덱스 등의 데이터베이스 개체를 생성/삭제/변경하는 역할을 함.
 2. 트랜잭션이 발생하지 않음.
 3. ROLLBACK, COMMIT을 시킬 수 없음.

- DML: SELECT, INSERT(삽입하는 명령어), DELETE(행 단위 삭제), UPDATE(데이터의 수정)
 1. 데이터를 조작하는데 사용되는 언어
 2. 사용 전에 테이블이 정의되어 있어야 함.
 3. 트랜잭션이 발생할 수 있음.

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

7. CMD: 데이터베이스 변경
```sql
use companydb;
```

8. 테이블 조회
```sql
select * from emp;
SELECT * FROM emp;
SELECT * FROM dept;
```

9. 뷰 생성
```sql
CREATE VIEW v_emp
AS
SELECT empno, empname FROM emp;
```

10. 테이블 보여주기
```sql
show tables;
```

11.  sql 집 모든 DB 복구
```sql
source dump.sql
```

12. 테이블 상태 출력 
```sql
show table status;
```

13. AUTO_INCREMENT
- 자동으로 1부터 증가하는 값을 입력해 줌.
- 아래 예제는 초기값을 1000으로 설정을 하였을 경우이다.
```sql
ALTER TABLE testTbl3 AUTO_INCREMENT=1000;
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