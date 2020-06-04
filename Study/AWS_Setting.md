# AWS Setting



### EC2 접속 Putty

1. putty 를 이용하여 서버에 접속한다.

   [다운로드](https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html) 를 하는데 윈도우의 경우 MSI로 32-bit, 64-bit 중 선택할 것.



2. 지급받은 RSA KEY를 puttyGen을 실행하여 `*.ppk` 파일로 변화하여 저장
   + `Save private key`



3. putty에서 서버 접속하는 방법
   + Session -> Host Name : (ubuntu@ip) 입력
   + SSH -> Auth에서 `*.ppk` 파일 선택
   + SSH 접속이므로 Port 번호는 22
   + Open 클릭



### MySQL 설치

1. MySQL를 다운

   ```
   $sudo apt-get update
   $sudo apt-get install mysql-server
   ```

   

2. MySQL 접속

   ```
   // 패스워드 입력하기
   $sudo mysql -u root -p
   ```

    

3. 패스워드 변경 및 저장

   ```
   // 계정에 대한 패스워드 수정 권한 주기
   update mysql.user set plugin='mysql_native_password' where user='root';
   
   // 패스워드 변경
   update mysql.user set authentication_string=PASSWORD('NEW_PASSWORD') where user='root';
   
   // 변경사항 저장 후 종료
   flush privileges;
   quit
   
   // 서버 재시작 후 재접속
   $sudo service mysql restart
   $sudo mysql -u root -p
   
   ```

4. 언어 한글 설정

   + 접속 후, `status`를 입력하면 latin1로 언어가 설정되어 있다.

   + vi 편집기로 mysql 설정 파일을 연다.

     ```
     $ sudo vim /etc/mysql/my.cnf
     
     // cnf 파일 설정
     // 아래 코드를 파일 맨 끝에 붙여넣기
     
     [client]
     default-character-set = utf8
     
     [mysqld]
     init_connect = SET collation_connection = utf8_general_ci
     init_connect = SET NAMES utf8
     character-set-server = utf8
     collation-server = utf8_general_ci
     
     [mysqldump]
     default-character-set = utf8
     
     [mysql]
     default-character-set = utf8
     
     // :wq 로 저장 후 편집기 종료
     ```

   + 다시 mysql을 재시작 해주면 변경사항이 저장되어 있다.

   

5. 외부 접속 허용

   ```
   $cd /etc/mysql/mysql.conf.d
   $sudo vim mysqld.cnf
   
   // address 설정
   bind-address = 0.0.0.0
   
   ```

   

6. MySQL 관련 명령어

   ```
   // 서비스 실행
   $ sudo systemctl start mysql.service
   
   // 설치 확인
   $dpkg -l | grep mysql-server
   
   // 구동 확인
   $ps -ef | grep mysql
   ```

   

### JDK 1.8 설치

```
$sudo apt-get install java-1.8.0-openjdk.x86_64
```



### Tomcat8 설치

```
  ## 톰캣 관련 명령어
 
  # 1. Tomcat 설치
  sudo apt-get install tomcat8
 
  # 2. Tomcat 제거
  sudo apt-get remove tomcat8
 
  # 3. Tomcat 상태 확인
  ps -ef | grep tomcat
 
  # 4. Tomcat 버전 확인
  sudo /usr/share/tomcat8/bin/version.sh
 
  # 5. Tomcat 시작
  sudo service tomcat8 start
 
  # 6. Tomcat 정지
  sudo service tomcat8 stop
 
  # 7. Tomcat 재시작
  sudo service tomcat8 restart
 
   #  Tomcat 설치경로
   /usr/share/tomcat8  →  executable &l ibraries
   /var/lib/tomcat8  →  conf & webapps

```



### Spring project 배포

1. 배포는 Filezlia를 이용하여 배포할 수 있다.
   + [Filezila 배포 방법](https://hyeonstorage.tistory.com/272?category=549781)
2. 관련 명령어

```
// build
Rus As -> Maven build

// target 폴더 안에 war파일로 저장
// 배포는 tomcat8 폴더 안의 wepapps에 한다.
cd var/lib/tomcat8/webapps

// 백그라운드 실행
$nohup java -jar war파일 &

// Port 관련
1. 포트 확인
sudo lsof -t -i:80  (누가 80번 port를 쓰고 있는지 확인)   
2. 포트 죽이기
sudo kill -9 569 (569번 포트를 강제로 죽) -9는 강제로 행하는 의미
```

