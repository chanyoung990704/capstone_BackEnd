# 프로젝트 : 영화추천 웹사이트
종합설계(캡스톤디자인)때 진행한 영화추천 웹사이트 (Urmovies)의 백엔드 GitHub입니다.

## 프로젝트 설명
- 이 프로젝트는 Spring Boot 기반의 백엔드 어플리케이션으로, 영화 정보 제공 및 추천 웹사이트를 구축하는 것을 목표로 합니다.
- 주요 기능은 사용자 인증 및 관리, 영화 정보 제공, 영화 좋아요 및 추천 시스템입니다.
- 프로젝트는 도메인 중심 설계를 기반으로 하며, RESTful API를 통해 클라이언트(예: React 서버)와 통신합니다.
- 보안은 JWT와 Spring Security를 사용하여 구현되며, 사용자 인증 및 권한 관리를 위한 로직을 처리합니다.
- 데이터베이스는 PostgreSQL을 사용하며, DB 연동 및 관리는 Spring Data JPA를 통해 이루어집니다.

## 팀원 & 역할
- 이주한 : 추천 알고리즘 개발 및 데이터 수집

- 박찬영 : Backend & React

- Sophearun Chheng : React

## 사용 기술
- SpringBoot
- Spring Security
- Spring Data JPA
- JPA, Hibernate
- QueryDsl
- PostgreSQL

## 프로젝트 동작 영상
https://drive.google.com/file/d/1dx0GyOfEIVg9q3vhxRiWAoYwnBBK2iBQ/view?usp=drive_link

## 디렉토리 구조 및 DB 구조도
<details>
<summary>디렉토리 구조</summary>

<!-- summary 아래 한칸 공백 두어야함 -->
## 디렉터리 구조
```
src/
└── main/
    └── java/
        └── unit/
            └── capstone/
                ├── config/                # 설정 관련 클래스 (주로 JWT 로그인 설정)
                │   ├── JwtAuthentication.java              # JWT 인증 필터 및 CORS 설정
                │   └── MyUserDetailService.java            # 사용자 상세 정보 서비스
                │
                ├── controller/            # 컨트롤러 클래스
                │   ├── JwtAuthenticationResource.java      # JWT 토큰 생성 및 인증 API
                │   ├── MemberApiController.java            # 회원 관련 API 컨트롤러
                │   ├── MovieApiController.java             # 영화 관련 API 컨트롤러
                │   ├── LikeMovieApiController.java         # 영화 좋아요 관련 API 컨트롤러
                │   └── RecommendedMovieApiController.java  # 영화 추천 관련 API 컨트롤러
                │
                ├── service/               # 서비스 클래스
                │   ├── JwtTokenService.java                # JWT 토큰 서비스
                │   ├── MemberService.java                  # 회원 관련 서비스
                │   ├── MovieService.java                   # 영화 관련 서비스
                │   ├── LikeMovieService.java               # 영화 좋아요 관련 서비스
                │   └── RecommendedMovieService.java        # 영화 추천 관련 서비스
                │
                ├── domain/                # 도메인 클래스 (엔티티, 리포지토리 등)
                │   ├── Member.java                         # 사용자를 나타내는 도메인 클래스
                │   ├── Movie.java                          # 영화 도메인 클래스
                │   ├── LikeMovies.java                     # 영화 좋아요 도메인 클래스
                │   ├── MovieComment.java                   # 영화 코멘트 도메인 클래스
                │   └── RecommendedMovie.java               # 추천 영화 도메인 클래스
                │
                └── ...                    # 기타 필요한 클래스들 (DTO, 유틸리티, 예외 처리 등)
```

</details>

<details>
<summary>DB 구조</summary>

<!-- summary 아래 한칸 공백 두어야함 -->
## 데이터베이스 ERD

### 엔티티

#### 1. Member
- `member_id` (PK)
- `username`
- `email`
- `password`
- `authority`

#### 2. Movie
- `movie_id` (PK)
- `tmdb_id`
- `title`
- `genres`

#### 3. LikeMovies
- `like_movie_id` (PK)
- `member_id` (FK)
- `movie_id` (FK)

#### 4. MovieComment
- `movie_comment_id` (PK)
- `comment`
- `create_date`
- `rating`
- `member_id` (FK)
- `movie_id` (FK)

#### 5. RecommendedMovie
- `recommended_movie_id` (PK)
- `member_id` (FK)
- `movie_id` (FK)

### 관계

- **Member**와 **LikeMovies**: 일대다 (One-to-Many)
- **Member**와 **MovieComment**: 일대다 (One-to-Many)
- **Member**와 **RecommendedMovie**: 일대다 (One-to-Many)
- **Movie**와 **LikeMovies**: 일대다 (One-to-Many)
- **Movie**와 **MovieComment**: 일대다 (One-to-Many)
- **Movie**와 **RecommendedMovie**: 일대다 (One-to-Many)

### ERD 시각화

```
Member                Movie
+----------------+    +----------------+
| member_id (PK) |<---| movie_id (PK)  |
| username       |    | tmdb_id        |
| email          |    | title          |
| password       |    | genres         |
| authority      |    +----------------+
+----------------+
       ^
       |
       |
+------|-----+     +-----------------------+
| LikeMovies |     | MovieComment          |
+------------+     +-----------------------+
| like_movie_id (PK)| movie_comment_id (PK) |
| member_id (FK)    | comment               |
| movie_id (FK)     | create_date           |
+------------+     | rating                |
       |           | member_id (FK)        |
       |           | movie_id (FK)         |
       |           +-----------------------+
       |
       |    +---------------------+
       +--->| RecommendedMovie    |
            +---------------------+
            | recommended_movie_id (PK) |
            | member_id (FK)            |
            | movie_id (FK)             |
            +---------------------+
```

</details>

## API Endpoints

### Authentication
- POST `/api/authenticate` - 사용자 인증 및 JWT 토큰 생성

### Member Management
- POST `/api/members` - 사용자 회원가입
- GET `/api/members` - 모든 회원 정보 조회
- GET `/api/members/likes/movies` - 사용자의 '좋아요' 한 영화 목록 조회
- GET `/api/members/recommendations/movies` - 사용자에게 추천된 영화 목록 조회
- GET `/api/admin/check` - 관리자 권한 체크

### Movie Information
- GET `/api/movies/{id}` - 특정 ID 영화 정보 조회
- GET `/api/movies/search/autocomplete` - 영화 제목 자동완성 검색

### Movie Likes
- POST `/api/movies/{tmdbId}/likes` - 영화 '좋아요' 등록
- DELETE `/api/movies/{tmdbId}/likes` - 영화 '좋아요' 취소

### Movie Recommendations
- POST `/api/movies/recommendations` - 맞춤 영화 추천 제공

### Movie Comment
- POST `/api/movie/{tmdbId}/comments` - 특정 ID 영화 페이지에 댓글 작성
- GET `/api/movie/{tmdbId}/comments` - 특정 ID 영화 페이지에 작성된 댓글 조회



## 로그인 및 인증 시스템
- **Spring Security와 JWT를 통한 인증 및 보안 메커니즘 구현**
  - `JwtAuthentication`: JWT 기반 인증 로직 및 CORS 설정
  - `JwtAuthenticationResource`: JWT 토큰 생성 및 인증 처리
  - `JwtTokenService`: JWT 토큰 생성 및 검증
  - `MyUserDetailService`: 사용자 상세 정보 로드 및 인증

## 피드백 및 개선점
- 다양한 상황에 적합한 예외 처리 클래스를 구현하고 적용할 필요가 있음.
- DB 쿼리 최적화에 대한 고려가 필요함.
- 예외 처리를 서비스 레이어에 집중하였으나, 컨트롤러 레벨에서도 추가적인 예외 처리가 필요함.
- 초기 설계 시 DB 및 도메인 구조에 대한 확장성을 고려하지 못함. 향후 리팩토링을 통해 이를 개선할 필요가 있음.
- TMDb API와 DB 내 영화 ID 간의 일치성 문제 해결이 필요함.
- CascadeType 사용에 대한 명시적인 규정과 주의가 필요함.
- AWS를 사용한 빌드가 필요할 경우 빌드를 해야 함.
- CI/CD를 사용해 푸시후 자동 테스팅 & 빌드가 되게 해야 함.
