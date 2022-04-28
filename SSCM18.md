# 0428 강의

## SQL
1. 시스템 구축 - MySQL
2. DDL, DML
- DDL: CREATE, DROP, ALTER
- DML: SELECT, INSERT, DELETE, UPDATE

3. ERD
- 요구사항 정의
- 테이블을 설계하고 구축
- 안전성

```sql
CREATE SCHEMA companydb;
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
```