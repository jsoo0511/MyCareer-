# Backend

1. SWAGGER-UI

```
http://13.124.227.192:8080/swagger-ui.html
```

2. [AWS 배포 방법](../Study/AWS_Setting.md)



> ### Project

1. Directory Structure

   ```
   |   +---main
   |   |   +---java
   |   |   |   \---com
   |   |   |       \---mycareer
   |   |   |           +---controller
   |   |   |           +---model
   |   |   |           |   +---dto
   |   |   |           |   |   \---user
   |   |   |           |   +---repo
   |   |   |           |   \---service
   |   |   |           \---util
   ```

   

   

2. URL Rules

   + 마지막에 `/`를 포함하지 않기.

     ```java
     // Bad
     http://api.test.com/users/
     
     // Godd
     http://api.test.com/users
     ```

   + `_` 대신에 `-` 사용

   + 소문자를 사용

   + method는 가급적 포함시키지 않기.

   

3. Response Rules

   + ```java
     class Response {
     	int code;
     	String message;
     	T data;
     }
     
     // 혹은,
     
     ResponseEntity<Object> ( Object, HttpStatus.State )
     ```

4. Content-Type

   + application/json 형식

     

5. Http methods

   + _User_ : 개발자가 가지고 있는 이력사항 등을 기록

   | methods              | POST            | GET                  | PUT                  | DELETE               |
   | -------------------- | --------------- | -------------------- | -------------------- | -------------------- |
   | /users               | 사용자 등록     | 사용자 전체 조회     |                      |                      |
   | /users/{userid}      |                 | 사용자 ‘userid’ 조회 | 사용자 ‘userid’ 수정 | 사용자 ‘userid’ 삭제 |
   | /users/award         | 수상 경력 등록  | 수상경력 전체 조회   |                      |                      |
   | /users/career        | 경력 사항 등록  | 경력 사항 조회       | 경력 사항 수정       | 경력 사항 삭제       |
   | /users/language      | 사용 언어 등록  | 사용 언어 조회       | 사용 언어 수정       | 사용 언어 삭제       |
   | /users/qualification | 자격 사항 등록  | 자격 사항 조회       | 자격 사항 수정       | 자격 사항 삭제       |
   | /users/url           | 블로그 url 등록 | 블로그 url  조회     | 블로그 url 수정      | 블로그 url 삭제      |

   

   + _Project_ : 유저가 개발했던 프로젝트에 관한 내용을 기록

   | methods                        | POST                            | GET                                   | PUT                | DELETE             |
   | ------------------------------ | ------------------------------- | ------------------------------------- | ------------------ | ------------------ |
   | /project/{userNo}              | 프로젝트 등록                   | 사용자에 대한 프로젝트 리스트 조회    |                    |                    |
   | /project/{userNo}&{projectNo}  |                                 | 해당 프로젝트 상세 조회               | 해당 프로젝트 수정 | 해당 프로젝트 삭제 |
   | /project/role/{projectNo}      | 해당 프로젝트에 대한 역할 등록  | 해당 프로젝트에 대한 역할 리스트 조회 | 역할 수정          | 역할 삭제          |
   | /project/role/develop/{roleNo} | 해당 역할에 대한 구현 내용 추가 | 해당 역할로 한 구현 내용 리스트 조회  | 개발 내역 수정     | 개발 내역 삭제     |
   | /project/api/{apiNo}           | 프로젝트에 대한 대표 기능 추가  | 프로젝트에 대한 대표 기능 리스트 조회 | 기능 수정          | 기능 삭제          |
   | /project/tech/{projectNo}      | 프로젝트에 대한 사용 기술 추가  | 프로젝트에 대한 사용 기술 리스트 조회 | 사용 기술 수정     | 사용 기술 삭제     |
   | /project/img/                  |                                 |                                       |                    |                    |

     

   

6. HTTP status code

   + Success response
     + 200 : OK
     + 201 : Created
       + post, put
     + 202 : Accepted
       + 요청은 유효하나 아직 서버에서 처리 안됨.
     + 204 : No Conten
   + Fail response
     + 400 : Bad Request
       + 클라이언트의 요청이 미리 정의된 파라미터와 일치하지 않는 경우
     + 401 : Unauthorized
     + 403 : Forbidden
       + 해당 요청은 유효하나 서버 작업 중 접근이 허용되지 않는 자원을 조회할 때,
     + 404 : Not Found
     + 405 : Method Not Allowed
     + 409 : Conflict
     + 429 : Too Many Request
   + `5xx`에러는 절대 사용자에게 날리면 안된다.
     + API Server level에서 `5xx`에러는 서비스 장애이다.
     + 웹 서버의 오류만이 허용된다.
   
   
   
7.  구현할 기능

   + 0518 ~ 0522
     + Spring Data JPA  학습을 위주로 진행
       + 학습내용은 Study 폴더에서 공유.
     + User를 기반으로 한 관련 RestAPI를 구현
       + Award : 수상 내역
       + Qualification : 자격 내역
       + Career : 경력 사항
       + Url : 본인의 개인 블로그, 메일 주소 등
       + Language : 사용 가능한 프로그래밍 언어
       + 추후 Front와 연결하면서 수정할 수도 있음
   + 0525 ~ 0529
     + Spring Data JPA 학습
     + AWS를 이용한 백엔드 배포
     + Project를 기반으로 한 관련 RestAPI를 구현
       + Project : 프로젝트에 대한 기본 정보 표시
       + Role : 프로젝트에서 어떤 역할을 했는지
       + Role_Develop : 맡은 역할에서 어떤 기능들을 구현했는지
       + Api : 프로젝트에서 대표할 수 있는 기능들이 어떤 것이 있는지
       + Tech : 프로젝트에서 사용했던 Framework 등 어떤 것을 사용했는지
       + Project_Img : 프로젝트의 캡쳐화면을 저장
   + 다음 스프린트에 구현할 기능
     + img 업로드 기능 ( 수정 / 삭제/ 등록 )
     + try - catch 구문에 대해 예외 처리 작업
     + 세세하게 구분된 Controller 기능을 service 내에서 일괄 처리할 수 있게 Logic 구현하기
     + Etc





