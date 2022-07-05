# 0705 강의



## SpringBoot를 기반으로 카카오 API를 통한 키워드로 장소 검색하기

### 1. Spring Boot pom.xml파일 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.ncp</groupId>
	<artifactId>ncp</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>ncp</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
		<!-- Servlet -->

		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>


		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- json request -->   

		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1</version>
  		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```



### 2. ‘application.properties’을 이용한 포트 설정

-  server.port=8080



### 3. Controller 설정(src/main/java)

- MainController

```java
package com.ncp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	@RequestMapping("/kakao")
	public String kakao() {
		return "kakao";
	}
}
```



- AJAXController

```java
package com.ncp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncp.restapi.KakaoAPI;

@RestController
public class AJAXController {

	@Autowired
	KakaoAPI kakaoapi;
	
	@RequestMapping("kakaolocal")
	public Object kakaolocal(String keyword) throws Exception {
		System.out.println(keyword);
		String result = kakaoapi.kakaolocalapi(keyword);
		return result;
	}
}
```



### 4. KAKAOAPI 불러오기(src/main/java)

```java
package com.ncp.restapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

@Component
public class KakaoAPI {

	public String kakaolocalapi(String keyword) throws Exception {
		String address = "https://dapi.kakao.com/v2/local/search/keyword.JSON";
		
        String param = "query=" + keyword
                //+ "&category_group_code=" + "FD6"
                + "&x=" + "37.5606326"
                + "&y=" + "126.9433486"
                + "&radius=" + "1000"; // 반경 1KM 안에 있는 해당 장소
                
        String apiKey = "*******************************************";	//발급받은 restapi key
		
		
		
		URL url = new URL(address);  			//접속할 url 설정
		HttpURLConnection conn;					//httpURLConnection 객체
		conn = (HttpURLConnection) url.openConnection();	//접속할 url과 네트워크 커넥션을 연다.
		conn.setRequestMethod("POST");             
		conn.setDoOutput(true);
        conn.setUseCaches(false);
		conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);	//Property 설정

		OutputStreamWriter ds = new OutputStreamWriter(conn.getOutputStream());
		ds.write(param);
		ds.flush();
		ds.close();
		
		
		int responseCode = conn.getResponseCode();		//responseCode를 받아옴.
	
		InputStream inputStream = conn.getInputStream();	//데이터를 받아오기 위한 inputStream
		BufferedReader br;		//inputStream으로 들어오는 데이터를 읽기 위한 reader
		String json = null;
		Charset charset = Charset.forName("UTF-8");
		if(responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(inputStream,charset));
			json = br.readLine();
			br.close();
		}
		else {
			System.out.println(" ERROR !!! ");
		}
	
		inputStream.close();
		conn.disconnect();
		return json;
	}
}
```



### 5. Main Page(index) 설정(src/main/resources/templates)

```html
 <!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<h1>Main Page</h1>
	<h2><a href="/kakao">KAKAOTEST</h2>
</body>
</html>
```



### 6. Kakao Page(kakao) 설정(src/main/resources/templates)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>

</script>

</head>
<body>
	<h1>KaKao Page</h1>
	<input type="text" id="param">
	<button id="bt">Click</button>
</body>
</html>
```



```javascript
	$(document).ready(function(){
		$('#bt').click(function(){
			var param = $('#param').val();
			//alert(param);
			$.ajax({
				url:'/kakaolocal',
				data:{'keyword':param},
				success:function(data){
					alert(data);
				}
			});
		});
	});
```

- Input란에  키워드로 원하는 장소를 검색하여 카카오 API로 불러오는 기능을 해줌.



### 7. KAKAOAPI 테스트(src/test/java)

```java
package com.ncp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ncp.restapi.KakaoAPI;

@SpringBootTest
class KaKaoTests {
	
	@Autowired
	KakaoAPI kakaoapi;
	
	@Test
	void contextLoads() throws Exception {
		String result="";
		result = kakaoapi.kakaolocalapi("pharmacy");
		System.out.println(result);		
		
	}

}
```



- kakao.html의 fitness를 입력할 경우 해당 데이터들을 불러옴.

<aside> 📍 해당 결과 값 {"documents":[{"address_name":"서울 중구 남대문로3가 30-15","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"8886498","phone":"02-753-7575","place_name":"남시약국","place_url":"http://place.map.kakao.com/8886498","road_address_name":"서울 중구 남대문로 18","x":"126.97774853256885","y":"37.56077835029789"},{"address_name":"서울 중구 태평로1가 84","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"13513669","phone":"02-779-7697","place_name":"에이스약국","place_url":"http://place.map.kakao.com/13513669","road_address_name":"서울 중구 세종대로 136","x":"126.97774173003772","y":"37.56842600499118"},{"address_name":"서울 용산구 동자동 43-205","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"11602516","phone":"02-313-4483","place_name":"서울중앙약국","place_url":"http://place.map.kakao.com/11602516","road_address_name":"서울 용산구 청파로 378","x":"126.97048737272486","y":"37.55361923561954"},{"address_name":"서울 서초구 서초동 1306-4","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"695043199","phone":"02-535-8233","place_name":"굿약국","place_url":"http://place.map.kakao.com/695043199","road_address_name":"서울 서초구 서초대로77길 24","x":"127.02598800338","y":"37.5000248610428"},{"address_name":"서울 강남구 도곡동 950-8","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"717110856","phone":"02-6401-9002","place_name":"스토리약국","place_url":"http://place.map.kakao.com/717110856","road_address_name":"서울 강남구 남부순환로359길 27","x":"127.03592548459473","y":"37.4864720109638"},{"address_name":"서울 강남구 일원동 715-1","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"9499539","phone":"02-459-3702","place_name":"천사약국","place_url":"http://place.map.kakao.com/9499539","road_address_name":"서울 강남구 일원로 95","x":"127.083453216988","y":"37.4856542114117"},{"address_name":"서울 영등포구 영등포동7가 204","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"1487898328","phone":"02-2633-0308","place_name":"별담은온누리약국","place_url":"http://place.map.kakao.com/1487898328","road_address_name":"서울 영등포구 국회대로54길 10","x":"126.90610790749588","y":"37.52303358536511"},{"address_name":"경기 고양시 일산서구 대화동 2250-6","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"10748610","phone":"031-915-2400","place_name":"인제조은약국","place_url":"http://place.map.kakao.com/10748610","road_address_name":"경기 고양시 일산서구 호수로856번길 73-2","x":"126.749812508007","y":"37.6737332687028"},{"address_name":"인천 계양구 계산동 925-9","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"12681887","phone":"032-543-7036","place_name":"유영약국","place_url":"http://place.map.kakao.com/12681887","road_address_name":"인천 계양구 하느재로20번길 1","x":"126.725206767953","y":"37.5454572897296"},{"address_name":"경기 고양시 일산서구 대화동 2250-5","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"12357903","phone":"031-921-6622","place_name":"큰믿음약국","place_url":"http://place.map.kakao.com/12357903","road_address_name":"경기 고양시 일산서구 호수로856번길 73-4","x":"126.74970793393231","y":"37.67381954065568"},{"address_name":"경기 고양시 일산서구 대화동 2702","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"734469903","phone":"031-923-0992","place_name":"애플약국","place_url":"http://place.map.kakao.com/734469903","road_address_name":"경기 고양시 일산서구 킨텍스로 217-23","x":"126.74550392649627","y":"37.66279518613446"},{"address_name":"경기 고양시 일산서구 대화동 2248","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"9509049","phone":"031-916-4688","place_name":"평화약국","place_url":"http://place.map.kakao.com/9509049","road_address_name":"경기 고양시 일산서구 호수로856번길 74-1","x":"126.7500690756693","y":"37.673616685604756"},{"address_name":"경기 고양시 일산서구 대화동 2248-2","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"8908343","phone":"031-923-5400","place_name":"대한약국","place_url":"http://place.map.kakao.com/8908343","road_address_name":"경기 고양시 일산서구 호수로856번길 74-7","x":"126.750339123945","y":"37.6735361695764"},{"address_name":"경기 김포시 걸포동 389-17","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"16801487","phone":"031-989-8808","place_name":"금파약국","place_url":"http://place.map.kakao.com/16801487","road_address_name":"경기 김포시 감암로 7","x":"126.711271127067","y":"37.6319875140694"},{"address_name":"경기 김포시 양촌읍 양곡리 415-4","category_group_code":"PM9","category_group_name":"약국","category_name":"의료,건강 \u003e 약국","distance":"","id":"324344934","phone":"031-981-2065","place_name":"한성약국","place_url":"http://place.map.kakao.com/324344934","road_address_name":"경기 김포시 양촌읍 양곡1로 44","x":"126.623770844752","y":"37.6545555447785"}],"meta":{"is_end":false,"pageable_count":45,"same_name":{"keyword":"pharmacy","region":[],"selected_region":""},"total_count":50}}

</aside>



## 키워드로 장소 검색

질의어에 매칭된 장소 검색 결과를 지정된 정렬 기준에 따라 제공합니다. 현재 위치 좌표, 반경 제한, 정렬 옵션, 페이징 등의 기능을 통해 원하는 결과를 요청 할 수 있습니다.

앱 REST API 키를 헤더에 담아 `GET`으로 요청합니다. 원하는 검색어와 함께 결과 형식 파라미터의 값을 선택적으로 추가할 수 있습니다.

응답은 `JSON`과 `XML` 형식을 지원합니다. 요청 시 URL의 `${FORMAT}` 부분에 원하는 응답 형식을 지정할 수 있습니다. 별도로 포맷을 지정하지 않은 경우 응답은 `JSON` 형식으로 반환됩니다.

요청 성공 시 응답의 장소 정보는 이름, 주소, 좌표, 카테고리 등의 기본 정보와 다양한 부가정보, 카카오 맵의 장소 상세 페이지로 연결되는 URL을 제공합니다.



- Parameter

| Name                | Type    | Description                                                  | Required |
| ------------------- | ------- | ------------------------------------------------------------ | -------- |
| query               | String  | 검색을 원하는 질의어                                         | O        |
| category_froup_code | String  | 카테고리 그룹 코드<br />(카테고리로 결과 필터링을 원하는 경우 사용) | X        |
| x                   | String  | 중심 좌표의 X 혹은 경도(longitude) 값<br />특정 지역을 중심으로 검색할 경우 `radius`와 함께 사용 가능 | X        |
| y                   | String  | 중심 좌표의 Y 혹은 위도(latitude) 값<br/>특정 지역을 중심으로 검색할 경우 `radius`와 함께 사용 가능 | X        |
| radius              | Integer | 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 중심좌표로 쓰일 x,y와 함께 사용<br/>(단위: 미터(m), 최소: `0`, 최대: `20000`) | X        |
| rect                | String  | 사각형의 지정 범위 내 제한 검색을 위한 좌표<br/>지도 화면 내 검색 등 제한 검색에서 사용 가능<br/>좌측 X 좌표, 좌측 Y 좌표, 우측 X 좌표, 우측 Y 좌표 형식 | X        |
| page                | Integer | 결과 페이지 번호<br/>(최소: `1`, 최대: `45`, 기본값: `1`)    | X        |
| size                | Integer | 한 페이지에 보여질 문서의 개수<br/>(최소: `1`, 최대: `45`, 기본값: `15`) | X        |
| sort                | String  | 결과 정렬 순서<br/>`distance` 정렬을 원할 때는 기준 좌표로 쓰일 `x`, `y`와 함께 사용<br/>`distance` 또는 `accuracy` | X        |



- Response

  - meta

    | Name           | Type       | Description                                                  |
    | -------------- | ---------- | ------------------------------------------------------------ |
    | total_count    | Integer    | 검색어에 검색된 문서 수                                      |
    | pageable_count | Integer    | `total_count` 중 노출 가능 문서 수 (최대: `45`)              |
    | is_end         | Boolean    | 현재 페이지가 마지막 페이지인지 여부<br/>값이 `false`면 다음 요청 시 `page` 값을 증가시켜 다음 페이지 요청 가능 |
    | same_name      | Refioninfo | 질의어의 지역 및 키워드 분석 정보                            |

  - same_name

    | Name            | Type     | Description                                                  |
    | --------------- | -------- | ------------------------------------------------------------ |
    | region          | String[] | 질의어에서 인식된 지역의 리스트<br/>예: '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트 |
    | keyword         | String   | 질의어에서 지역 정보를 제외한 키워드<br/>예: '중앙로 맛집' 에서 '맛집' |
    | selected_region | String   | 인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보          |

  - documents

    | Name                | Type   | Description                                                  |
    | ------------------- | ------ | ------------------------------------------------------------ |
    | query               | String | 검색을 원하는 질의어                                         |
    | category_froup_code | String | 카테고리 그룹 코드<br />(카테고리로 결과 필터링을 원하는 경우 사용) |

