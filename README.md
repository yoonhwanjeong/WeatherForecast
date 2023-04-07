# 기상청 Open API 활용

## 빌드

`gradlew build`

## 실행

[application.properties](src/main/resources/application.properties) 수정 혹은 아래의 환경 변수 설정

```
// 기본: localhost
MYSQL_HOST
// 기본: 3306
MYSQL_PORT
MYSQL_USER
MYSQL_PASSWORD
// 공공데이터포털 서비스키
SERVICE_KEY
```

## 테스트

`gradlew test`

### 요구 사항

`docker` 설치 필요