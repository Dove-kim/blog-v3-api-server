# 블로그 API SERVER
기존 ExpressJs를 이용한 서버를 Spring Boot로 리뉴얼 한다.<br>
부수적인 목표로 TDD를 적용, 연습한다.
---

## 목표와 목표가 아닌것
### 목표
1. Spring Boot를 사용해 API 서버 구현
2. TDD 적용 및 공부
3. Spring Rest Docs 적용 및 공부
3. SOLID 원칙 준수
4. 구글 자바 컨벤션 준수 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
5. jenkins을 이용한 CI/CD자동화

### 목표가 아닌것
1. Spring Security는 회원(본인)의 로그인, 글작성 API에 적용
2. Java 17의 신규기능 적용 및 공부

---
## 목차

---
### Server

#### 서버 정보

- Framework: Spring Boot(2.7.5), Java(17)
- 사용한 주요 라이브러리: JPA, QueryDsl, Mockito, Rest Docs

---

### DataBase
![blog_db_v3 drawio](https://user-images.githubusercontent.com/26616293/203071531-525318a8-7db8-42f4-bb47-8c05a63fe7f3.png)

#### 테이블 정의서
1. user
> 회원정보를 저장한다.<br> 
> 개인 기술 블로그의 특성상 작성자는 1명(본인)만 존재한다.
> 
| 구분 |    컬럼명          |     타입      |           상태           |    비고     |
|:---:|:-----------------:|:-----------:|:----------------------:|:----------:|
|  1  |      user_id      |     int     | `pk`, `nn`, `uq`, `ai` |           |
|  2  |       name        | varchar(20) |          `nn`          |   사용자명  |
|  3  |     nick_name     | varchar(20) |          `nn`          |  사용자 별명 |
|  4  |     login_id      | varchar(20) |   `nn`, `uq`, `idx`    |  로그인 ID   |
|  5  |  encrypt_password |  char(64)   |          `nn`          |  로그인 ID   |
|  6  | created_date_time |  datetime   |          `nn`          | 생성일시(KST) |
|  7  | updated_date_time |  datetime   |          `nn`          | 변경일시(KST) |

2. category
> 카테고리를 저장한다<br>
> parent_category_id를 통해 셀프조인을 한다. <br>
> category_level값에 따라 카테고리의 depth가 결정된다.
> - 1: 최상의 카테고리
> - 2: 1레벨 카테고리의 자녀 카테고리
> - n: n-1 레벨 카테고리의 자녀 카테고리

| 구분  |      컬럼명        |     타입      |           상태           |     비고     |
|:---:|:------------------:|:-----------:|:----------------------:|:----------:|
|  1  |    catrgory_id     |     int     | `pk`, `nn`, `uq`, `ai` |            |
|  2  | parent_category_id |     int     |         `idx`          | 부모 카테고리 ID |
|  3  |   category_level   |   tinyint   |          `nn`          | 카테고리 level |
|  4  |        name        | varchar(20) |          `nn`          |   카테고리 명   |
|  5  |  created_user_id   |     int     |          `nn`          |   생성자 ID   |
|  6  |  updated_user_id   |     int     |          `nn`          |   변경자 ID   |
|  7  | created_date_time  |  datetime   |          `nn`          | 생성일시(KST)  |
|  8  | updated_date_time  |  datetime   |          `nn`          | 변경일시(KST)  |

3. blog_post
> 

| 구분  |      컬럼명        |     타입      |           상태           |     비고     |
|:---:|:------------------:|:-----------:|:----------------------:|:----------:|
|  1  |    blog_post_id     |     int     | `pk`, `nn`, `uq`, `ai` |            |
|  2  | category_id |     int     |         `idx`          | 부모 카테고리 ID |
|  3  |   user_id   |   tinyint   |          `nn`          | 카테고리 level |
|  4  |        title        | varchar(20) |          `nn`          |   카테고리 명   |
|  4  |        body        | varchar(20) |          `nn`          |   카테고리 명   |
|  5  |  created_user_id   |     int     |          `nn`          |   생성자 ID   |
|  6  |  updated_user_id   |     int     |          `nn`          |   변경자 ID   |
|  7  | created_date_time  |  datetime   |          `nn`          | 생성일시(KST)  |
|  8  | updated_date_time  |  datetime   |          `nn`          | 변경일시(KST)  |

4. tag

5. blog_post_tag

6. register_code

