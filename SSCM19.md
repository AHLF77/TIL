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

### GROUP BY절
- 그룹으로 묶어주는 역할
```sql

```

### DISTINCT
- 중복된 것을 하나로 합쳐주는 것.

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

### ROLLUP
- 분류 별로 합계 및 총합을 구할 시 사용
```sql

```

### 날짜 형식(date_format)
- 구분기호
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
WHERE addr = '서울'
AND birthYear > 1970
AND mobile1 IS NOT NULL;
```

```sql






SELECT * FROM usertbl
WHERE height = 182 OR height = 170 OR height = 172;

SELECT * FROM usertbl
WHERE height IN (182,170,172);

SELECT * FROM usertbl
WHERE date_format(mDate,'%Y') < '2010';







-- %Y %m %d %H %i %s

SELECT * FROM usertbl
WHERE name LIKE '%김%';

SELECT * FROM usertbl
WHERE name LIKE '_종_';

SELECT * FROM usertbl
WHERE name LIKE '_종%';

-- 윤종신 회원과 같은 지역 회원을 조회 하시오.
SELECT * FROM usertbl
WHERE name = '윤종신';

SELECT * FROM usertbl
WHERE addr = '경남';

SELECT * FROM usertbl
WHERE addr = (SELECT addr FROM usertbl
WHERE name ='윤종신');

-- 윤종신 회원보다 키가 큰 회원을 조회 하시오.
SELECT * FROM usertbl
WHERE height > (SELECT height FROM usertbl
WHERE name ='윤종신');

-- 경남 지역의 회원들 키와 동일한 회원들을 조회 하시오
SELECT * FROM usertbl
WHERE height IN (SELECT height FROM usertbl
WHERE addr = '경남');



SELECT * FROM usertbl 
ORDER BY height DESC, name ASC;

SELECT * FROM usertbl 
ORDER BY 3 DESC, 2 ASC;

SELECT * FROM usertbl
WHERE addr = '서울'
AND birthYear < 1980
ORDER BY name;

SELECT DISTINCT addr FROM usertbl;

SELECT * FROM usertbl
ORDER BY height
LIMIT 2,5;
```

```sql
```