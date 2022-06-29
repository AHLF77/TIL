# 리눅스 서버 설정

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

※ 위와 같이 설정하지 않을 경우 최소 8자리 이상, 대/소문자 포함, 특수문자, 숫자 등을 포함 필요.



### MySQL 삭제





### putty 명령어

- java -version: 자바 버전 확인
- systemctl status mysqld: sql 구동 상태
- pwd: 현재 설정 경로 보여주기
- ls: 현재 있는 루트에 파일 목록 보여주기
- rm: ls로 파일 목록 확인하고 삭제(파일명, 확장자),(ex: rm day05.war)
- mv: 이동(이동할 대상, 디렉토리 이름: mv ROOT ROOT_BACK)
- CP: 복사 "(복사한)파일명, 확장자"(ex: cp /root/day051.war .) .는 현재경로



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