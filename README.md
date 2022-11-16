![테코몽](https://user-images.githubusercontent.com/35757620/195279963-b2716ff7-bc19-4ee7-ac2b-f3a8b1ca83f2.gif)

# <img align=top src="https://user-images.githubusercontent.com/35757620/201512077-b0623591-9459-49d4-96fd-61c86e98c1cb.png" width="50px" height="50px"/> TECOMOG(Tech Communication Blog) 
**자바**, **스프링**을 공부했던 것들을 기록하기 위해서 만든 블로그 기존의 다른 블로그들도 있지만 직접 내가 만들면서 다시 공부하자는 마음으로
프로젝트를 계획하게 되었습니다. 프로젝트는 [*자바 웹 개발 워크북*]에 있는 내용들을 참고하였습니다.

# :file_folder:TECOMOG 디렉토리 구조
```bash
.
|-- main
|   |               `-- generated
|   |               `-- java
|   |               `-- org
|   |               `-- zerock
|   |               `-- b01
|   |               |-- B01Application.java
|   |               |-- config
|   |               |   |-- CustomServletConfig.java
|   |               |   |-- RootConfig.java
|   |               |   `-- SwaggerConfig.java
|   |               |-- controller
|   |               |   |-- BoardController.java
|   |               |   |-- SampleController.java
|   |               |   `-- SampleJSONController.java
|   |               |-- domain
|   |               |   |-- BaseEntity.java
|   |               |   `-- Board.java
|   |               |-- dto
|   |               |   |-- BoardDTO.java
|   |               |   |-- PageRequestDTO.java
|   |               |   `-- PageResponseDTO.java
|   |               |-- repository
|   |               |   |-- BoardRepository.java
|   |               |   `-- search
|   |               |       |-- BoardSearch.java
|   |               |       `-- BoardSearchImpl.java
|   |               `-- service
|   |                   |-- BoardService.java
|   |                   `-- BoardServiceImpl.java
|   `-- resources
|       |-- application.properties
|       |-- static
|       |   |-- img
|       |   |   |-- home-bg.jpg
|       |   |   |-- modalcat.png
|       |   |   |-- post-bg.jpg
|       |   |   `-- teco.png
|       |   `-- js
|       |       `-- scripts.js
|       `-- templates
|           |-- board
|           |   |-- list.html
|           |   |-- modify.html
|           |   |-- read.html
|           |   `-- register.html
|           `-- layout
|               `-- basic.html
|-- test
|   |-- generated_tests
|   `-- java
|       `-- org
|           `-- zerock
|               `-- b01
|                   |-- B01ApplicationTests.java
|                   |-- DataSourceTests.java
|                   |-- repository
|                   |   `-- BoardRepositoryTests.java
|                   `-- service
|                       `-- BoardServiceTests.java
```
# :pencil: Tech Stack
<div>
  <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=OpenJDK&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=green"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
</div>

# :nut_and_bolt: Tool
<div>
  <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat&logo=IntelliJ IDEA&logoColor=white"/>
  <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat&logo=Bootstrap&logoColor=white"/>
</div>

# :bomb: ERRORS
 ## **Quill editor** 에러들
 + ### html 태그들의 그대로 적용되던 문제점
<img src="https://user-images.githubusercontent.com/35757620/201845835-f4a91fa8-ad94-434e-a2a6-f28c37c6e2be.jpg" width="100%" height="50%"/>
<img src="https://user-images.githubusercontent.com/35757620/201847844-8be60417-2720-4773-bb66-56438c43ec2e.png" width="100%" height="50%"/>

검색해본 결과 dangerouslySetInnerHTML 이라는 속성을 이용하면 해결이 가능 하지만 Thymeleaf에 th:utext 라는 속성을 사용해도 같은 효과를
볼 수 있다고 한다.

<img src="https://user-images.githubusercontent.com/35757620/201848973-4ef73bf5-d2d6-48cf-9925-faa87199a34c.png" width="70%" height="50%"/>
출처 : Thymeleaf 홈페이지(https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#what-is-thymeleaf)

<hr>

 + ### Quill 이미지 파일 길이 수정

<img src="https://user-images.githubusercontent.com/35757620/202071606-6e522c89-3a7e-41a3-b699-12fd5eb575d9.png" width="70%" height="50%"/>
quill editor에 이미지 한 개를 넣으면 이런식으로 긴 문자열인 상태로 변환되기 때문에 데이터베이스에 길이가 초과되어 에러가 발생한다.

![image](https://user-images.githubusercontent.com/35757620/202122702-a25b17a8-594a-432a-85f1-d5c9ef376e28.png)
![image](https://user-images.githubusercontent.com/35757620/202122929-5b0d412b-3c81-41bc-8c1e-bede7fb64b00.png)

content에 타입을 **text**로 바꾸어도 여러 개의 이미지를 넣으면 에러가 발생한다.

### :mag: https://myeongdev.tistory.com/49 블로그를 참고하였다.
quill-editor에 작성한 이미지는 ajax를 사용해 서버에 미리 저장 하고 저장된 이미지를 불러오는 것이다.
![image](https://user-images.githubusercontent.com/35757620/202124224-3314c610-7266-4976-adba-659ef09f026b.png)
![image](https://user-images.githubusercontent.com/35757620/202124354-6e898c7c-308b-4cb8-952e-2f4a1826e051.png)
![image](https://user-images.githubusercontent.com/35757620/202124490-5b4f4a50-dc8d-4f4a-bee1-8079c31fc5bb.png)

게시물을 등록하는것은 해결 했지만 수정시에는 서버에 저장된 이미지도 같이 삭제 해야 하는데 이건 좀 더 공부 해보고 해결해보겠습니다.

<hr>


4. 프로젝트 설치 및 실행 방법

5. 프로젝트 사용 방법
