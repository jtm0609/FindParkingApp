## 서비스 내용
목적지 주변의 주차장을 검색하고, 내비게이션 앱과 연동해 빠르게 찾아갈 수 있는 Android 애플리케이션입니다.

## 개발 목적
- 실무에서 사용하는 Clean Architecture 및 MVVM 패턴 실습
- 모듈화, 유지보수성, 확장성을 고려한 구조 설계

## 주요 기능
- **주차장 검색**
  - 목적지 주소 입력 시, 주변 공영/민영주차장 리스트 출력

- **주차장 상세 정보 제공**
  - 주소, 주차 가능 대수, 운영 시간, 요금 정보 등

- **네비게이션 연동**
  - 선택한 주차장 위치를 KakaoNavi/Tmap 앱으로 연동하여 길찾기 가능

## 기술 스택
- Kotlin
- Clean Architecture
- MVVM
- Retrofit
- RxJava
- Hilt
- DataBinding
- LiveData
- Room 

## 시스템 아키텍처
### MVVM 패턴
본 프로젝트는 **MVVM(Model–ViewModel–View) 패턴을 적용하여 UI와 비즈니스 로직을 분리하였습니다.
1. Model: 데이터 소스 및 비즈니스 로직 처리 (예: Repository, UseCase 등)
2. ViewModel: UI 상태 및 로직을 관리, View와 Model 사이의 다리 역할 수행
3. View: 사용자 인터페이스, ViewModel의 상태를 관찰하여 렌더링

![image](https://github.com/user-attachments/assets/a52f6aad-3b5e-4027-adfa-0716df450d35)


### Clean Architecture
본 프로젝트는 **Clean Architecture 원칙**을 기반으로, 관심사 분리(Separation of Concerns)를 철저히 지켜 설계되었습니다.
아키텍처는 크게 아래와 같은 세 가지 모듈로 구성됩니다:
- **Presentation Module**
  - UI(View)와 ViewModel 구성
  - 사용자 입력 처리 및 화면 렌더링
- **Domain Module**
  - 순수한 비즈니스 로직
  - UseCase를 통해 앱의 핵심 규칙 정의
- **Data Module**
  - Repository 구현체
  - 로컬 데이터베이스(Room), 네트워크 통신 등 데이터 소스와의 연결

![image](https://github.com/user-attachments/assets/3b411cfb-b185-48a9-8092-3680c9c63cba) 

## 스크린 샷
<p align="center">
  <img src="https://github.com/user-attachments/assets/80def636-ad4f-4c5a-a7f7-b8d3a9b6a4c3" width="30%" />
  <img src="https://github.com/user-attachments/assets/da4dfaa0-fdda-40ed-8ca1-91824e46d068" width="30%" /> 
  <img src="https://github.com/user-attachments/assets/a56e8660-ac2d-449a-84c6-c24ce87be9e8" width="30%" />
</p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/c21d0be6-6d69-47e5-b537-25d92c945e9f" width="30%" /> 
  <img src="https://github.com/user-attachments/assets/1611e95c-0ac8-4da4-ba19-3625ec5ead2d" width="30%" />
  <img src="https://github.com/user-attachments/assets/8254861a-4738-43ea-9245-df33dbedbedc" width="30%" /> 
</p>

## 기타
본 프로젝트는 1인 사이드 프로젝트로, UI/UX 설계부터 구조 설계, 기능 구현까지 전 과정을 직접 수행하였습니다.  
기술적인 고민과 학습하면서 배웠던 내용들은 기술 블로그에 정리해두었습니다.  
👉 [앱 아키텍처 가이드(1) - 개요](https://jtm0609.tistory.com/2)   
👉 [앱 아키텍처 가이드(2) - UI Layer](https://jtm0609.tistory.com/3)   
👉 [앱 아키텍처 가이드(3) - Data Layer](https://jtm0609.tistory.com/4)   
👉 [앱 아키텍처 가이드(4) - Domain Layer](https://jtm0609.tistory.com/5)   
👉 [Clean Architecture 개념](https://jtm0609.tistory.com/6)   
