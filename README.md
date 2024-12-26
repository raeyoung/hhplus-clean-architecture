# TDD & 클린 아키텍처

---
![ERD](https://github.com/user-attachments/assets/fd4f040a-3a8d-41d5-a800-cbdc8f0f4ff3)

### 각 테이블 정보
- `Users`: 사용자 정보
- `Lecture`: 특강 정보
- `LectureApplication`: 특강 신청 정보 

### 테이블 간 관계 정의
- `Users` - `LectureApplication` (1:N) : 한 사용자는 여러 특강에 신청할 수 있으며, 하나의 특강에 여러 사용자가 신청할 수 있습니다.
- `Lecture` - `LectureApplication`: 한 특강에 여러 사용자가 신청할 수 있으며, 하나의 신청은 하나의 특강에 속합니다. (1:N 관계)

### 관계 설명 
- `LectureApplication` 에는 신청상태를 관리하는 `applicationStatus`가 있으며, `SUCCESS` 또는 `FAIL`로 구분됩니다.
- `Lecture` 에는 특강상태를 관리하는 `lectureStatus`가 있으며 `OPENED` 또는 `CLOSED`로 구분됩니다. 

### 설계 개요 
각 테이블은 논리적 설계에만 관계가 있고, 물리적 설계에서는 관계가 되어 있지 않기 때문에 독립적인 구조로 설계되어 있습니다.
독립적인 구조는 테이블 간의 강한 결합을 피할 수 있어 특정 테이블에서 데이터를 삭제하거나 수정할 때, 
다른 테이블에 미치는 영향을 최소화 할 수 있기 때문에 유연성이 생깁니다. 
또한, 데이터베이스가 데이터의 일관성 및 무결성을 자동으로 처리하지 않기 때문에 어플리케이션을 보다 구체적으로 제어할 수 있습니다. 
<br>
<br>
총 3개의 엔티티인 User(사용자 정보), Lecture(특강 정보), LectureApplication(특강 신청 정보)로 나눈 이유는 
각 테이블이 <b>하나의 책임</b>을 가지고 <b>불필요한 데이터 중복</b>을 피하기 위함입니다.
특강에 대한 정보를 각 사용자마다 반복적으로 저장하는 것이 아니라 
`LectureApplication`에서 `Lecture`와 `Users`를 참조하는 방식으로 데이터를 효율적으로 관리할 수 있습니다. 
