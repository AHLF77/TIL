# 0531강의



### day031
#### com.config
- mybatis
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org/DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.vo.UserVO" alias="user"/>
		<typeAlias type="com.vo.ProductVO" alias="product"/>
	</typeAliases>
	
	<mappers>
		<mapper resource="com/config/usermapper.xml"/>
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
		SELECT * FROM PRODUCT WHERE ID=#{obj}
	</select>
	<select id="selectall" resultType="product">
		SELECT * FROM PRODUCT
	</select>
	<insert id="insert" parameterType="product">
		INSERT INTO PRODUCT VALUES (NULL, #{name},#{price},SYSDATE(),#{rate})
	</insert>
	<update id="update" parameterType="product">
		UPDATE PRODUCT SET NAME=#{name}, PRICE=#{price}, RATE=#{rate} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM PRODUCT WHERE ID=#{obj}
	</delete>
	<select id="searchname" parameterType="String" resultType="product">
		SELECT * FROM PRODUCT WHERE NAME LIKE CONCAT('%',#{name},'%')
	</select>
	<select id="getrate" parameterType="double" resultType="product">
		SELECT * FROM PRODUCT WHERE RATE > #{rate}
	</select>
	
</mapper>
```
- usermapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mapper.UserMapper">
	
	<select id="select" parameterType="String" resultType="user">
		SELECT * FROM CUST WHERE ID=#{obj}
	</select>
	<select id="selectall" resultType="user">
		SELECT * FROM CUST
	</select>
	<insert id="insert" parameterType="user">
		INSERT INTO CUST VALUES (#{id},#{pwd},#{name})
	</insert>
	<update id="update" parameterType="user">
		UPDATE CUST SET PWD=#{pwd},NAME=#{name} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="String">
		DELETE FROM CUST WHERE ID=#{obj}
	</delete>
	<select id="searchname" parameterType="String" resultType="user">
		SELECT * FROM CUST WHERE NAME LIKE CONCAT('%',#{name},'%')
	</select>
	
</mapper>
```

#### com.frame
- Service(interface)
```java
package com.frame;

import java.util.List;

public interface Service<K,V> {
	public void register(V v) throws Exception;
	public void remove(K k) throws Exception;
	public void modify(V v) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}
```

#### com.mapper
- ProductMapper(interface)
```java
package com.mapper;

import java.util.List;

import com.vo.ProductVO;

public interface ProductMapper {
	public void insert(ProductVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ProductVO obj) throws Exception;
	
	public ProductVO select(int obj) throws Exception;
	public List<ProductVO> selectall() throws Exception;
	public List<ProductVO> searchname(String name) throws Exception;
	public List<ProductVO> getrate(double rate) throws Exception;

}
```

- UserMapper(interface)
```java
package com.mapper;

import java.util.List;

import com.vo.UserVO;

public interface UserMapper {
	public void insert(UserVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(UserVO obj) throws Exception;
	
	public UserVO select(String obj) throws Exception;
	public List<UserVO> selectall() throws Exception;
	public List<UserVO> searchname(String name) throws Exception;
}
```