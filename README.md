# assignment
> 과제 테스트 
<br/>

## 1. 사용 기술 
<b>```Back-end```<b/>
* Java11
* Spring Boot 2.7.5
* Gradle 7.5.1
* jsoup 1.15.3
* Lombok

<b>```Front-end```<b/>
* HTML
* JavaScript
* Bootstrap 5.2.3
* Ajax 

<br/>

## 2. 결과물
<img width="500" alt="스크린샷 2022-11-26 오전 1 56 46" src="https://user-images.githubusercontent.com/84092014/204029384-40fb815e-1221-4dfd-92d4-f81ab7280135.png">

## 3. Front-end
1. URL 을 입력받음
   - 입력 값이 http 나 https 로 시작하지 않는 경우 안내 문구 출력
  <img width="500" alt="스크린샷 2022-11-26 오전 2 21 30" src="https://user-images.githubusercontent.com/84092014/204032464-88ce5393-7b21-4c2c-a236-1bb0f2d742eb.png">
  
2. Type 을 Select form 을 이용하여 HTML 태그 제외 / text 전체 중 택 1 을 선택받음

3. 출력 묶음 단위를 입력 받음
  - 입력 값이 1 이상의 자연수가 아닌 경우 안내 문구 출력
  <img width="500" alt="스크린샷 2022-11-26 오전 2 19 41" src="https://user-images.githubusercontent.com/84092014/204032234-2534e7d4-a56a-4111-9ba2-f0058bbf9b88.png">

4. 출력 버튼 클릭 시 모든 입력 값이 올바르지 않을 경우 2초동안 알림 출력 
<img width="1423" alt="스크린샷 2022-11-26 오전 2 24 11" src="https://user-images.githubusercontent.com/84092014/204032773-7b571ac1-b116-4228-872f-ee7a5fd9eef3.png">

5. 모든 값이 올바르게 입력되었다면 ajax 를 통해 POST 요청 전달 
```
      5-1) 응답 코드가 2XX 인 경우 몫과 나머지를 출력 

      5-2) 응답 코드가 2XX 이 아닌 경우 에러 메세지를 출력 
```       
   
       
## 4. Back-end
1. Controller 단에서 DTO를 통해 요청에 대한 데이터를 받아옴

2. DTO를 Service 단으로 전달

3. 요청 받은 Url 을 웹 스크래핑 진행 
  - 만약 url 이 옳지 않은 경우 -> 404 NOT FOUND URL exception throw
  ```
      3-1) type 이 html 인 경우 : 받아온 Document 전체를 String 형태로 return

      3-2) type 이 text 인 경우 : Document 내 html tag 를 모두 제거하고 순수 문자열만 return
 ```
  
4. 3번 과정을 통해 얻어온 데이터 중 영문, 숫자만 남김 

5. 4번 과정을 통해 얻어온 문자열을 대문자 문자열, 소문자 문자열, 숫자 문자열로 분리하여 정렬 진행

6. 정렬된 각 문자열을 대문자 → 소문자, 숫자 순으로 정렬 
   - 대문자 소문자 숫자 를 반복하여 교차 출력
   
7. 6번 과정을 거쳐 완성된 문자열을 요청 받은 출력 묶음 단위에 맞게 몫과 나머지로 분리하여 응답 DTO에 담음

<br/>

[성공 응답 예시]
<img width="1017" alt="스크린샷 2022-11-26 오전 2 37 11" src="https://user-images.githubusercontent.com/84092014/204034199-1bc77f33-6855-4fd8-80f2-7097ba395d1d.png">

<br/>

[실패 응답 예시 - http://localhost:8081 를 구동하지 않고 요청]
<img width="1033" alt="스크린샷 2022-11-26 오전 2 36 24" src="https://user-images.githubusercontent.com/84092014/204034121-95b8dae7-9bbe-4b67-9e76-c923ecd84643.png">
