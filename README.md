## 프로젝트 구성
- Gradle + Java 17
- SpringBoot
- Spring Data JPA - Mysql

### 도메인 중심 설계
- 변경에 용이하도록 비즈니스 로직을 JPA 의존성으로부터 격리하는 방식을 선택하였습니다.
- Component Layer를 활용하여 비즈니스 로직의 책임을 분리하였습니다.
- Usecase를 활용하여 Component들을 조립하여 비즈니스 로직을 완성하였습니다.

### Spring Data JPA
- Database의 안정적인 CRUD를 위해 사용하였습니다.
- DataSource Layer의 변경이 상위 계층에 영향을 주는 것을 최소화 하기 위해 DIP를 통하여 도메인 중심적인 계층 아키텍처로 구현하였습니다.

## 추가 적용 요구사항
#### trackingNo Validator 추가
- trackingNo 추가시 중복된 trackingNo를 방지하기 위해 Validator를 구현하였습니다.

## 고려사항
### 아키텍처
백엔드 서버에서 일반적으로 사용하는 레이어드 아키텍처를 기반으로 비즈니스 로직 내에서 구현 계층을 분리하여 비즈니스 로직을 명확하게 전달하는 데에 집중하였습니다.
또한 레이어드 아키텍처의 단점을 보환하기 위해 하향식 의존이 아닌 도메인 중심 의존을 만들어 비즈니스 로직을 보호하고 DataSource Layer와 Presentation Layer의 유연함을 보장하였습니다.
이를 통해 외부로 노출되는 Presentation 계층이 변경되거나 데이터에 대한 제어를 제공하는 주체가 변경되더라도 유연한 변경이 가능한 구조를 제공합니다.

### 의존성
Component Layer를 이용하여 서로 다른 도메인간 최소한의 개입을 고려할 수 있습니다. 
다른 도메인의 컴포넌트를 조립해 비즈니스 로직을 완성하는 것은 Usecase에 한정합니다
Service와 Controller간의 강결합을 UseCase를 사용하여 책임을 한가지씩 위임하여 해결하였습니다. 
또한 Service 처럼 여러 기능이 한 곳에 모여있지 않고 한 개의 UseCase가 단 하나의 비즈니스 로직을 담당하기 때문에 관련 없는 요구사항으로 인해 변경될 여지가 사라집니다.
각 UseCase는 한가지 책임만을 갖게되고, 상태를 갖지 않기 때문에 테스트 범위가 명확해지고 간편해집니다.

## 추가 개선 고려사항
- 테이블 구조
  - Table: shipment<br>
    Columns:<br>
    shipment_id bigint AI PK<br>
    status varchar(10)<br>
    tracking_no varchar(255)<br>
    <br>
  - Table: image<br>
    Columns:<br>
    image_id bigint AI PK<br>
    filename varchar(255)<br>
    type varchar(10)<br>
    shipment_id bigint
<br>

### DB Query Optimization
현재 요구사항에는 tracking_no를 통한 조회 기능이 없지만, tracking_no를 통한 조회가 빈번하게 일어날 것이라고 판단했습니다.<br> 
tracking_no 필드는 모든 값이 다 다른 값이기 때문에 카디널리티가 매우 높은 필드입니다.<br>

tracking_no에 인덱스를 추가함으로써 기존의 전체 테이블 스캔(full table scan)을 인덱스를 이용한 조회로 개선할 수 있습니다. 