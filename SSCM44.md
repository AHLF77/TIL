# 리눅스 서버 설정

### putty 명령어

- java -version: 자바 버전 확인
- systemctl status mysqld: sql 구동 상태
- pwd: 현재 설정 경로 보여주기
- ls: 현재 있는 루트에 파일 목록 보여주기
- rm: ls로 파일 목록 확인하고 삭제(파일명, 확장자),(ex: rm day05.war)
- mv: 이동(이동할 대상, 디렉토리 이름: mv ROOT ROOT_BACK)
- CP: 복사 "(복사한)파일명, 확장자"(ex: cp /root/day051.war .) .는 현재경로
- cat: 파일 내용 확인
- ls: 간략히 보기(-al: 파일 종류, 접근권한, 링크수, 소유자, 소유그룹, 파일크기, 마지막 수정일시, 숨김파일 등)
- mkdir "디렉토리 이름":  디렉토리 생성
- rmdir "디렉토리 이름": 디렉토리 안에 다른 파일이나 디렉토리가 존재하지 않아야만 가능.
- 파일 찾기
  - find 검색시작위치 -name "파일명패턴"# 특정 파일명 패턴을 갖는 파일들을 검색해서 지우기find 검색시작위치 -name "파일명패턴" -delete
- wget: 파일 내려받기
- 압축하기
  - tar zcvf 생성될 압축파일 이름
- 해체하기
  - tar zxvf 압축파일_이름
    - c : 새로운 묶음 만듬
    - x : 묶인 파일 풀어줌
    - f : 묶음 파일의 이름 지정 옵션
    - v : 묶음 파일을 풀거나 묶을 때 과정을 화면에 출력

- zip 명령어 압축/해제

  - zip 압축파일이름 : 압축

  - unzip 파일이름: 해제



### MySQL8.0 설치

- Install
  - yum install -y https://dev.mysql.com/get/mysql80-community-release-el7-5.noarch.rpm
  - yum repolist enabled | grep "mysql.*"
  - yum install -y mysql-server
  - mysql -V

- Configuring Server
  - Start & Stop
    - systemctl enable mysqld: 재부팅 시 자동 시작하도록 설정
    - systemctl start mysqld: 서비스 시작
    - systemctl status mysqld: 서비스 구동 여부 확인
    - systemctl stop mysqld: 서비스 중지
  - settings root password
    - grep "temporary password" /var/log/mysqld.log
  - root로 접속 후 비밀 번호 변경
    - mysql -u root -p
    - ALTER USER 'root'@'localhost' IDENTIFIED BY '변경할 비밀번호';
  - 비밀번호 정책 변경
    - SHOW VARIABLES LIKE 'validate_password%';
    - SET GLOBAL validate_password.length = 5; : 최소 5자리 이상 설정해야 함.
    - SET GLOBAL validate_password.number_count = 0; : 숫자 필수는 아님.
    - SET GLOBAL validate_password.policy=LOW;: 정책 수준 낮음.
    - SET GLOBAL validate_password.mixed_case_count = 0;: 대소문자 미포함
    - SET GLOBAL validate_password.special_char_count = 0;: 특수문자 미포함
    - ※ 위와 같이 설정하지 않을 경우 최소 8자리 이상, 대/소문자 포함, 특수문자, 숫자 등을 포함 필요.
  - 사용자 생성 및 데이터베이스 생성
    - use mysql; : MySQL의 정보가 있는 mysql db를 선택
    - SELECT host, user FROM user;: 유저의 목록과 접속 허용된 IP를 볼 수 있음.
    - CREATE USER '아이디'@'%' identified by '비밀번호'; : 외부접속만 가능한 계정 생성
    - CREATE DATABASE DB이름 default character set utf8;: 테이블 생성 시, 기본 인코딩을 UTF-8으로 설정
    - GRANT ALL PRIVILEGES ON DB이름.* to '아이디'@'%';: 해당 DB에 대한 권한 부여
    - flush privileges; : 새로고침



### MySQL 삭제

- yum list installed | grep mysql: 설치된 패키지 목록 확인
- yum remove package_name: 원하는 패키지만 삭제
- yum remove -y mysql-community-*: 삭제
- rm -rf : 디렉토리 삭제 시, 삭제 확인 과정을 거치지 않음(-r: 비어 있지 않은 디렉토리 제거 시 사용)



#### putty-mysql 명령어

- mysql -u admin1 -p: mysql 접속법
- show databases; : 데이터베이스 출력
- select * from TEST; : TEST DB에 있는 목록 보여주기
- exit; : mysql 종료
- use mysql;:
- select host, user from user;:
- CREATE DATABASE shopdb default character set utf8;
- flush privileges;
- GRANT ALL PRIVILEGES ON shopdb.* to 'admin1'@'%';
- flush privileges;
- 



### Apache-Tomcat 설치

- 다운로드
  - wget http://archive.apache.org/dist/tomcat/tomcat-8/v8.5.27/bin/apache-tomcat-8.5.27.tar.gz
- 압축 해제
  - tar zxvf apache-tomcat-8.5.27.tar.gz
- 실행
  - cd apache-tomcat-8.5.27/ : 경로 설정(bin이나 webapps에서 cd.. 하면 여기로 돌아옴.)
  - bin# ./startup.sh : 실행(반드시 bin에서만 실행할 것)
  - bin# ./shutdown.sh : 정지(반드시 bin에서만 실행할 것)

- 실행 확인
  - ps -ef | grep tomcat