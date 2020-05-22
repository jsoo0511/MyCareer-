# Backend

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

   | methods       | POST        | GET                  | PUT                  | DELETE               |
   | ------------- | ----------- | -------------------- | -------------------- | -------------------- |
   | /users        | 사용자 등록 | 사용자 전체 조회     |                      |                      |
   | /users/userid |             | 사용자 ‘userid’ 조회 | 사용자 ‘userid’ 수정 | 사용자 ‘userid’ 삭제 |

   

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