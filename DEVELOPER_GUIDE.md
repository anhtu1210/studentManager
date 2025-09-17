# 👨‍💻 Developer Guide - Student Management System

## 🏗️ Kiến trúc hệ thống

### Công nghệ sử dụng
- **Backend**: Spring Boot 3.5.5, Spring Security 6, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5.3.3, Font Awesome 6.4.0
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Java Version**: 17

### Cấu trúc thư mục
```
src/main/java/com/example/studentManager/
├── config/                 # Cấu hình Spring
│   └── SecurityConfig.java
├── controller/             # Controllers (MVC)
│   ├── AuthController.java
│   ├── StudentController.java
│   ├── ClassroomController.java
│   ├── SubjectController.java
│   ├── TeacherController.java
│   ├── EnrollmentController.java
│   ├── GradeController.java
│   ├── TranscriptController.java
│   ├── DashboardController.java
│   ├── ProfileController.java
│   ├── DataController.java
│   └── DebugController.java
├── entity/                 # JPA Entities
│   ├── Student.java
│   ├── Classroom.java
│   ├── Subject.java
│   ├── Teacher.java
│   ├── Enrollment.java
│   ├── Grade.java
│   ├── Transcript.java
│   └── User.java
├── repository/             # Data Access Layer
│   ├── StudentRepository.java
│   ├── ClassroomRepository.java
│   ├── SubjectRepository.java
│   ├── TeacherRepository.java
│   ├── EnrollmentRepository.java
│   ├── GradeRepository.java
│   ├── TranscriptRepository.java
│   └── UserRepository.java
├── service/                # Business Logic Layer
│   ├── StudentService.java
│   ├── StudentServiceImpl.java
│   ├── ClassroomService.java
│   ├── ClassroomServiceImpl.java
│   ├── SubjectService.java
│   ├── TeacherService.java
│   ├── TeacherServiceImpl.java
│   ├── EnrollmentService.java
│   ├── GradeService.java
│   ├── TranscriptService.java
│   ├── TranscriptServiceImpl.java
│   ├── UserService.java
│   ├── UserDetailsServiceImpl.java
│   ├── DashboardService.java
│   └── DataInitializationService.java
└── StudentManagerApplication.java
```

---

## 🗄️ Database Schema

### Bảng Users
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);
```

### Bảng Students
```sql
CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    classroom_id BIGINT,
    FOREIGN KEY (classroom_id) REFERENCES classrooms(id)
);
```

### Bảng Classrooms
```sql
CREATE TABLE classrooms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    class_code VARCHAR(20) UNIQUE NOT NULL,
    class_name VARCHAR(100),
    teacher_id BIGINT,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);
```

### Bảng Teachers
```sql
CREATE TABLE teachers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    teacher_code VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20)
);
```

### Bảng Subjects
```sql
CREATE TABLE subjects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(20) UNIQUE NOT NULL,
    subject_name VARCHAR(100) NOT NULL,
    credit INT,
    teacher_id BIGINT,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);
```

### Bảng Enrollments
```sql
CREATE TABLE enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    classroom_id BIGINT NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id),
    FOREIGN KEY (classroom_id) REFERENCES classrooms(id),
    UNIQUE KEY unique_enrollment (student_id, subject_id)
);
```

### Bảng Grades
```sql
CREATE TABLE grades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    grade_value DECIMAL(3,1) NOT NULL,
    grade_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);
```

### Bảng Transcripts
```sql
CREATE TABLE transcripts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    average_grade DECIMAL(3,1),
    letter_grade VARCHAR(2),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);
```

---

## 🔧 Cấu hình Development

### 1. Cấu hình IDE

#### IntelliJ IDEA
```xml
<!-- File: .idea/compiler.xml -->
<component name="CompilerConfiguration">
  <annotationProcessing>
    <profile name="Maven default annotation processors profile" enabled="true">
      <sourceOutputDir name="target/generated-sources/annotations" />
      <sourceOutputDir name="target/generated-test-sources/test-annotations" />
      <outputRelativeToContentRoot value="true" />
    </profile>
  </annotationProcessing>
</component>
```

#### VS Code
```json
// File: .vscode/settings.json
{
    "java.configuration.updateBuildConfiguration": "automatic",
    "java.compile.nullAnalysis.mode": "automatic",
    "spring-boot.ls.java.home": "/path/to/java17"
}
```

### 2. Cấu hình Maven

#### File pom.xml
```xml
<properties>
    <java.version>17</java.version>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 3. Cấu hình Database

#### Development Profile
```properties
# File: src/main/resources/application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/quanlysinhvien_dev?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=dev_password

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
```

#### Production Profile
```properties
# File: src/main/resources/application-prod.properties
spring.datasource.url=jdbc:mysql://localhost:3306/quanlysinhvien?useSSL=true&serverTimezone=UTC
spring.datasource.username=prod_user
spring.datasource.password=prod_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

logging.level.org.springframework.web=WARN
logging.level.org.springframework.security=WARN
```

---

## 🧪 Testing

### 1. Unit Tests

#### Test Controller
```java
@SpringBootTest
@AutoConfigureTestDatabase
class StudentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetStudents() throws Exception {
        mockMvc.perform(get("/students"))
               .andExpect(status().isOk())
               .andExpect(view().name("students/students"))
               .andExpect(model().attributeExists("students"));
    }
}
```

#### Test Service
```java
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    
    @Mock
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentServiceImpl studentService;
    
    @Test
    void testGetAllStudents() {
        // Given
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);
        
        // When
        List<Student> result = studentService.getAllStudents();
        
        // Then
        assertEquals(2, result.size());
        verify(studentRepository).findAll();
    }
}
```

### 2. Integration Tests

#### Test Database
```java
@SpringBootTest
@Transactional
class StudentRepositoryTest {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Test
    void testFindByStudentCode() {
        // Given
        Student student = new Student();
        student.setStudentCode("SV001");
        studentRepository.save(student);
        
        // When
        Optional<Student> found = studentRepository.findByStudentCode("SV001");
        
        // Then
        assertTrue(found.isPresent());
        assertEquals("SV001", found.get().getStudentCode());
    }
}
```

### 3. Test Configuration

#### Test Properties
```properties
# File: src/test/resources/application-test.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

---

## 🔒 Security Configuration

### 1. Spring Security Setup

#### SecurityConfig.java
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/api/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll());
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### 2. User Authentication

#### UserDetailsServiceImpl.java
```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles("USER")
            .build();
    }
}
```

---

## 📊 API Documentation

### 1. REST Endpoints

#### Student API
```java
@RestController
@RequestMapping("/api/students")
public class StudentApiController {
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        // Implementation
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        // Implementation
    }
    
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Implementation
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        // Implementation
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        // Implementation
    }
}
```

### 2. API Response Format

#### Success Response
```json
{
    "success": true,
    "data": {
        "id": 1,
        "studentCode": "SV001",
        "fullName": "Nguyễn Văn A",
        "email": "nguyenvana@example.com"
    },
    "message": "Student retrieved successfully"
}
```

#### Error Response
```json
{
    "success": false,
    "error": {
        "code": "STUDENT_NOT_FOUND",
        "message": "Student with id 1 not found"
    },
    "timestamp": "2024-01-01T12:00:00Z"
}
```

---

## 🚀 Deployment

### 1. Build Application

#### Maven Build
```bash
# Clean and build
mvn clean package

# Build with specific profile
mvn clean package -Pprod

# Build without tests
mvn clean package -DskipTests
```

#### Docker Build
```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/studentManager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2. Production Configuration

#### Environment Variables
```bash
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/quanlysinhvien
export SPRING_DATASOURCE_USERNAME=prod_user
export SPRING_DATASOURCE_PASSWORD=prod_password
export SERVER_PORT=8080
```

#### JVM Options
```bash
java -Xms512m -Xmx1024m -XX:+UseG1GC -jar studentManager-0.0.1-SNAPSHOT.jar
```

---

## 🔧 Development Tools

### 1. Code Quality

#### Checkstyle
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <configLocation>checkstyle.xml</configLocation>
    </configuration>
</plugin>
```

#### SpotBugs
```xml
<plugin>
    <groupId>com.github.spotbugs</groupId>
    <artifactId>spotbugs-maven-plugin</artifactId>
    <version>4.7.3.0</version>
</plugin>
```

### 2. Code Coverage

#### JaCoCo
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

---

## 📝 Coding Standards

### 1. Java Code Style

#### Naming Conventions
```java
// Classes: PascalCase
public class StudentController {}

// Methods: camelCase
public void getAllStudents() {}

// Constants: UPPER_SNAKE_CASE
public static final String DEFAULT_PAGE_SIZE = "10";

// Variables: camelCase
private String studentCode;
```

#### Annotation Order
```java
@RestController
@RequestMapping("/api/students")
@Validated
@Slf4j
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Student>> getAllStudents() {
        // Implementation
    }
}
```

### 2. Database Conventions

#### Table Naming
```sql
-- Use snake_case for table names
CREATE TABLE student_enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL
);
```

#### Column Naming
```sql
-- Use snake_case for column names
CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

---

## 🐛 Debugging

### 1. Logging Configuration

#### Logback Configuration
```xml
<!-- File: src/main/resources/logback-spring.xml -->
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/app.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>10MB</maxFileSize>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

### 2. Debugging Tips

#### Enable Debug Logging
```properties
# File: application.properties
logging.level.com.example.studentManager=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
```

#### JVM Debug Options
```bash
# Enable remote debugging
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar app.jar
```

---

## 📚 Resources

### 1. Documentation
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Bootstrap Documentation](https://getbootstrap.com/docs/5.3/)

### 2. Tools
- [Spring Boot CLI](https://docs.spring.io/spring-boot/docs/current/reference/html/cli.html)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [H2 Database Console](https://www.h2database.com/html/cheatSheet.html)

### 3. IDE Plugins
- **IntelliJ IDEA**: Spring Boot, Lombok, Database Navigator
- **VS Code**: Extension Pack for Java, Spring Boot Extension Pack
- **Eclipse**: Spring Tools 4, Lombok

---

**Happy Coding! 🚀**
