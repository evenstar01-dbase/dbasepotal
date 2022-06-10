1. 기본 뼈대 프레임워크 구성
   1. 기능
      1. Jpa
      2. Mybatis
      3. Swagger
   2. 사용 시 확인 사항
      1. H2 DB를 통한 sample 테스트 형태로 구성 됨
      2. 실제 사용 시 jpa.hibernate.ddl-auto 옵션 수정 필요함
      3. Mybatis 설정 파일은 config 폴더를 바라보게 되어 있음 실제 사용 시 폴더명 유지 필요
2. 사용 방법
   1. Jar 실행 예제
      1. java -jar -Dspring.profiles.active=local skel.jar -Dlogging.config=file:/config/logback-spring.xml -Dspring.config.location=file:/config/