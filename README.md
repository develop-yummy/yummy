![](https://img.shields.io/badge/내일배움캠프-Spring팀과제-white.svg)
:desktop_computer: 프로젝트 소개
---
+ 프로젝트 명 : yummy (음식 주문 어플리케이션)
+ 소개
    + 한 줄 정리 : 배달 주문 어플리케이션입니다.
    + 내용 : 다양한 음식점과 메뉴를 주문할 수 있고 주문한 메뉴에 대한 리뷰를 남길 수 있습니다.
      리뷰에 좋아요를 남길 수 있고 단골 음식점을 정할 수 있는 기능이 있습니다.
      관리자 권한을 얻으면 백오피스로 매출과 통계를 낼 수 있습니다.
      
---

# Development
## 디렉토리 구조
```bash
├── src                         # 소스 코드가 위치하는 디렉토리
│   ├── main                    # 메인 소스 코드가 위치하는 디렉토리
│   │   ├── java                # 자바 소스 코드가 위치하는 디렉토리
│   │   │   ├── com.six.yummy   # 기본 패키지 이름
│   │   │   │   ├── address     # 유저의 주소 관련 디렉토리
│   │   │   │   ├── backoffice  # 관리자 권한 사용자 통계 관련 디렉토리
│   │   │   │   ├── cartitem    # 장바구니 아이템 관련 디렉토리
│   │   │   │   ├── favorite    # 단골 음식점 관련 디렉토리
│   │   │   │   ├── global      # 설정, 예외 처리 관련 디렉토리
│   │   │   │   ├── like        # 리뷰 좋아요 관련 디렉토리
│   │   │   │   ├── menu        # 음식점 메뉴 관련 디렉토리
│   │   │   │   ├── order       # 주문 관련 디렉토리
│   │   │   │   ├── review      # 리뷰 관련 디렉토리
│   │   │   │   ├── user        # 유저 관련 디렉토리
│   │   │   │   └── restaurant  # 음식점 관련 디렉토리
│   │   └── resources           # 리소스 파일들이 위치하는 디렉토리
│   └── test                    # 테스트 소스 코드가 위치하는 디렉토리
├── build.gradle                # Gradle 빌드 스크립트 파일
├── settings.gradle             # Gradle 설정 파일
└── README.md                   # 프로젝트에 대한 설명이 담긴 마크다운 파일
```

## :gear: 개발 환경
+ JDK amazoncorretto 17.0.9
+ Spring Boot 3.2.3
+ Gradle 8.5
+ MySQL 8.0.35
+ Apache Tomcat 10.1.19
+ bootstrap 5.2.3
  
## Code Convension
+ [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
  
# API
[https://www.notion.so/e2bf19a87d054f2ba07255d0e5a82ed4?v=3d718bc194e04a4789183d80c4775415&pvs=4](https://www.notion.so/e2bf19a87d054f2ba07255d0e5a82ed4?v=3d718bc194e04a4789183d80c4775415&pvs=4)

# ERD
![yummy (3) (1)](https://github.com/develop-yummy/yummy/assets/154495684/967deaa8-3367-4879-bccc-0afdd74f789c)

# 진행 상황

## 필수 기능 구현
- [x]  **사용자 인증 기능**
    - [x] 회원가입 기능    
    - [x] 로그인 및 로그아웃 기능
- [ ]  **프로필 관리**
    - [x] 프로필 수정 기능
    - [x]비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.
    - [ ]최근 3번안에 사용한 비밀번호는 사용할 수 없도록 제한합니다.
- [x]  **주문 CRUD 기능**
    - [x] 배달앱일 경우 : 주문 작성, 조회, 수정, 삭제 기능
- [x]  **리뷰 CRUD 기능**
    - [x] 댓글 작성, 조회, 수정, 삭제 기능
    - [x] 배달앱일 경우 : 리뷰 작성, 조회, 수정, 삭제 기능
## 추가 기능 구현
- [ ]  **소셜 로그인 기능 구현**
- [ ]  **백오피스 기능 구현**
    - [ ] 유저 전체 목록을 조회하고 권한 수정/삭제 기능
    - [ ] 배달앱이라면 메뉴, 주문, 리뷰 전체 목록을 조회 생성/수정/삭제 기능
    - [x] 관리자 페이지 구성을 통해 어플에 대한 통계 확인 기능
- [x]  **좋아요 기능**
    - [x] 게시물 및 댓글 좋아요/좋아요 취소 기능
- [x]  **팔로우 기능 구현**
