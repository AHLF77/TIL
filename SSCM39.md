## day0413

### src/main/java
#### com.multi.biz
- ProductBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.ProductMapper;
import com.multi.vo.ProductVO;

@Service("productbiz")
public class ProductBiz implements Biz<Integer,ProductVO>{

	@Autowired
	ProductMapper dao;
	
	@Override
	public void register(ProductVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(ProductVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public ProductVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ProductVO> get() throws Exception {
		return dao.selectall();
	}

}
```

#### com.multi.controller
- AJAXController
```java
package com.multi.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@RestController
public class AJAXController {

	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/chartimpl")
	public Object chartimpl() {
		//{'cate':['p1','p2','p3','p4','p5'],'data':[10000,30000,20000,40000,15000]}
		JSONObject jo = new JSONObject();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		List<ProductVO> list = null;
		try {
			list = pbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (ProductVO p : list) {
			ja1.add(p.getName());
			ja2.add(p.getPrice());
		}
		jo.put("cate", ja1);
		jo.put("data", ja2);
		return jo;
	}
}
```

- MainController
```java
package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.ProductBiz;

@Controller
public class MainController {
		
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/chart")
	public String chart() {
		return "chart";
	}

	
	
}
```

#### com.multi.frame
- Biz(interface)
```java
package com.multi.frame;

import java.util.List;

public interface Biz<K,V> {
	public void register(V v) throws Exception;
	public void modify(V v) throws Exception;
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}
```

#### com.multi.mapper
- ProductMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface ProductMapper {
	public void insert(ProductVO cust) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO cust) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List <ProductVO> selectall() throws Exception;
}

```

#### com.multi.mapper
- productmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.multi.mapper.ProductMapper">
	
	<select id="select" parameterType="int" resultType="productVO">
		SELECT * FROM product WHERE id=#{id}
	</select>
	<select id="selectall" resultType="productVO">
		SELECT * FROM product ORDER BY 1
	</select>
	<insert id="insert" parameterType="productVO">
		INSERT INTO product VALUES (NULL, #{name},#{price},SYSDATE(), #{rate})
	</insert>
	<update id="update" parameterType="productVO">
		UPDATE product SET name=#{name},price=#{price}, rate=#{rate} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM product WHERE id=#{id}
	</delete>
	
</mapper>
```

#### com.multi.vo
- ProductVO
```java
package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {
	private int id;
	private String name;
	private int price;
	private Date regdate;
	private double rate;
}
```

### src/main/resources
#### templates
- chart
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>


<style>
	#container{
		width:500px;
		height:400px;
		border:2px solid red;
	}
</style>
<script>

function display(gdata){
	Highcharts.chart('container', {
	    chart: {
	        type: 'line'
	    },
	    title: {
	        text: 'My Shop'
	    },
	    subtitle: {
	        text: 'Pants Shop'
	    },
	    xAxis: {
	        categories: gdata.cate
	    },
	    yAxis: {
	        title: {
	            text: 'Price'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: false
	        }
	    },
	    series: [{
	        name: 'Pants',
	        data: gdata.data
	    }]
	});
};

function getData(){
	$.ajax({
		url:'chartimpl',
		success:function(data){
			display(data);
		}
	});
};

$(document).ready(function(){
	getData();
});
</script>

</head>
<body>
	<h1> Chart Page</h1>
	<div id="container"></div>
</body>
</html>
```
- main
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Main Page</h1>
	<h3><a href="chart">Chart</a></h3>
</body>
</html>
```