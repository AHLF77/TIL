# 0602강의
## Springboot
- Springboot를 통해 SQL문을 연동하고, CRUD를 작성하는 것이 목표

### day033
#### com.config
- mybatis
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org/DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.vo.ProductVO" alias="product"/>
	</typeAliases>
	
	<mappers>
		<mapper resource="com/config/productmapper.xml"/>
	</mappers>
</configuration>
```

- productmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mapper.ProductMapper">
	
	<select id="select" parameterType="int" resultType="product">
		SELECT p.id, p.name, p.price, p.regdate, p.cid, c.name as catename
		FROM PRODUCT p
		INNER JOIN cate c ON p.cid = c.id
		WHERE p.id=#{id}
	</select>
	<select id="selectall" resultType="product">
		SELECT p.id, p.name, p.price, p.regdate, p.cid, c.name as catename
		FROM PRODUCT p
		INNER JOIN cate c ON p.cid = c.id
	</select>
	<insert id="insert" parameterType="product">
		INSERT INTO PRODUCT VALUES (NULL,#{name},#{price}, SYSDATE(), #{cid})
	</insert>
	<update id="update" parameterType="product">
		UPDATE CUST SET name=#{name}, price=#{price}, cid=#{cid} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM PRODUCT WHERE ID=#{id}
	</delete>

</mapper>
```

#### com.frame



#### com.mapper


#### com.service


#### com.test


#### com.vo