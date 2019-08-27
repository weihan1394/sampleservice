# Data Source Service
## Install
- Install java 8
- Install docker/docker-compose

```bash
brew install maven
```

## Intellij
- Add lombok plugin
- Enable annotation processors

## Start
```bash
mvn install -DskipTests               # Build project

cd provisioning/local/sampleservice
docker-compose up                     # Start database
mvn flyway:clean; mvn flyway:migrate  # Drop all tables, Perform Database Migration
mvn spring-boot:run                   # Start server

mvn test # Run tests
mvn test -Dquick_test=true # Omits flyway migration
mvn surefire:test -Dtest=XxxTest # Run single test

# Generate code coverage report
# Visit target/site/jacoco
mvn install -P test-coverage
```

# Linter

```bash
mvn site # Generates report at target/site folder 
mvn checkstyle:check # Check code style

# Format code
mvn com.coveo:fmt-maven-plugin:format
```

# Sonarqube(local)
```bash
docker run -d --name sonarqube-local -p 9000:9000 sonarqube
mvn install sonar:sonar -Dsonar.host.url=http://localhost:9000
```
- Visit http://localhost:9000
- Login with admin:admin

# Swagger Api Doc
```
http://localhost:8080/v2/api-docs
http://localhost:8080/swagger-ui.html
```

# RabbitMq
```
http://localhost:15672
username:guest
password:guest
```