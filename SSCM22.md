# 0503강의

## 외래키,  
-- DDL
```sql
DROP DATABASE IF EXISTS shoppingdb;
CREATE DATABASE shoppingdb;
USE shoppingdb;
```

-- 유령데이터를 먼저 지워야 한다.
```sql
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cust;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cate;
```

-- cust table
```sql
CREATE TABLE cust(
	id VARCHAR(10),
    name VARCHAR(20) NOT NULL,
    addr VARCHAR(100) NOT NULL,
    regdate DATE NOT NULL
);
ALTER TABLE cust ADD CONSTRAINT PRIMARY KEY(id); 
ALTER TABLE cust ALTER COLUMN addr SET DEFAULT 'Seoul';
```

```sql
-- cate table
CREATE TABLE cate(
	id INT,
    name VARCHAR(30) NOT NULL,
    pid INT
);
ALTER TABLE cate ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cate ADD CONSTRAINT UNIQUE (name);
ALTER TABLE cate ADD CONSTRAINT FOREIGN KEY (pid) REFERENCES cate(id);
```
-- ALTER TABLE cate CHANGE COLUMN name name VARCHAR(30) NOT NULL;

```sql
-- product table
CREATE TABLE product(
	id INT,
    name VARCHAR(20) NOT NULL,
    price INT NOT NULL,
    regdate DATE NOT NULL,
    cid INT 
);
```
```sql
ALTER TABLE product ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE product MODIFY id INT AUTO_INCREMENT;
ALTER TABLE product AUTO_INCREMENT = 1000;
ALTER TABLE product ADD CONSTRAINT CHECK (price > 0);

ALTER TABLE product ADD CONSTRAINT FOREIGN KEY (cid) REFERENCES cate(id);
```

```sql
-- cart table
CREATE TABLE cart(
	id INT,
    uid VARCHAR(10),
    pid INT,
    regdate DATE
);
```

```sql
ALTER TABLE cart ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE cart MODIFY id INT AUTO_INCREMENT;
ALTER TABLE cart AUTO_INCREMENT = 1000;
```

```sql
ALTER TABLE cart ADD FOREIGN KEY (uid) 
REFERENCES cust (id)
ON DELETE CASCADE
ON UPDATE CASCADE;
```

```sql
ALTER TABLE cart ADD FOREIGN KEY (pid) 
REFERENCES product (id);
```

-- cust data
```sql
SELECT * FROM cust;
INSERT INTO cust VALUES ('id01', 'lee','Busan','2019-03-02');
INSERT INTO cust (id, name, regdate) VALUES ('id02', 'kim','2020-05-02'); 
-- 만약 주소만 제외하고 싶을 경우
```

-- cate data
```sql
SELECT * FROM cate;
INSERT INTO cate VALUES (10, 'pants', NULL);
INSERT INTO cate VALUES (11, 'short pants', 10);

INSERT INTO cate VALUES (20, 'shirts', NULL);
INSERT INTO cate VALUES (21, 'short shirts', 20);

SELECT * FROM cate c1 INNER JOIN cate c2 ON c1.pid = c2.id;
```

-- product data
SELECT * FROM product;
INSERT INTO product VALUES (NULL, 'levis', 10000, curdate(), 11);
INSERT INTO product VALUES (NULL, 'bang', 20000, curdate(), 11);
INSERT INTO product VALUES (NULL, 'levis', 10000, curdate(), 21);
INSERT INTO product VALUES (NULL, 'bang', 20000, curdate(), 21);

-- 제품의 정보를 출력한다.
-- 카테고리 명도 출력한다.
SELECT * FROM product;
SELECT * FROM cate;
SELECT * FROM product p INNER JOIN cate c ON p.cid = c.id;

-- cart data
SELECT * FROM cart;
INSERT INTO cart VALUES (NULL, 'id01', 1000, CURDATE());
INSERT INTO cart VALUES (NULL, 'id01', 1001, CURDATE());
INSERT INTO cart VALUES (NULL, 'id01', 1003, CURDATE()); 

-- cart 정보 출력 하시오.
-- 사용자 이름, 상품 이름 가격, 카테코리 이름
SELECT c.id, cu.id, cu.name, ca.name, p.id, p.name, p.price, c.regdate FROM cart c
INNER JOIN cust cu ON c.uid = cu.id
INNER JOIN product p ON c.pid = p.id
INNER JOIN cate ca ON ca.id = p.cid;

SELECT c.id, cu.id AS uid, cu.name AS uname, ca.name AS cname, p.id AS pid, p.name AS pname, p.price, c.regdate 
FROM cart c
INNER JOIN cust cu ON c.uid = cu.id
INNER JOIN product p ON c.pid = p.id
INNER JOIN cate ca ON ca.id = p.cid;

-- Make View Table
CREATE VIEW v_cart
AS
SELECT c.id, cu.id AS uid, cu.name AS uname, ca.name AS cname, p.id AS pid, p.name AS pname, p.price, c.regdate 
FROM cart c
INNER JOIN cust cu ON c.uid = cu.id
INNER JOIN product p ON c.pid = p.id
INNER JOIN cate ca ON ca.id = p.cid;

-- DELETE FROM cart;
-- DELETE FROM cust WHERE id = 'id001';
-- UPDATE cust SET id='id001' WHERE id='id01';

UPDATE cart SET regdate = '2020-05-03' WHERE id = 1000;
SELECT * FROM v_cart;
UPDATE v_cart SET regdate='2019-05-04' WHERE id = 1001;

SELECT * FROM v_cart
WHERE uid = 'id01';