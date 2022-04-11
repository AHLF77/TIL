# Java

### 1. switch 문
   - if 문과 같은 제어문
   - 특정 변수를 다양한 상황에서 비교할 수 있게 해줌
   - if문에 비해 가독성이 좋음
   - 컴파일러의 최적화를 쉽게 할 수 있어서 속도도 if문에 비해 빠름
   - 문법 
   ```java
   switch(변수){
    case 값1 : 
        실행문; 
        break;
    case 값2 : 
        실행문; 
        break;  
    default :
        실행문;    
}
    ```

### 2. for 문
   - 반복 횟수를 정확히 알고 있을 때 사용
   - while문 보다 가독성이 좋음
   - if문에 비해 가독성이 좋음
   - 문법
   ```java
  for(초기화식; 조건식; 증감식){
    실행문; 
}
    ```
   
### 3. while문
 - 문장이 true면 실행, false이면 종료
 - while문 빠져 나가기 break
 - while문 조건으로 돌아가기 continue
 - 문법
 ```java
 while (조건문) {
    <수행할 문장1>;
    <수행할 문장2>;
    ...
}
 ```
