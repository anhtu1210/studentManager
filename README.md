# Hệ thống Quản lý Sinh viên (Student Management System)

## 📋 Mô tả dự án

Hệ thống quản lý sinh viên được xây dựng bằng Spring Boot với các tính năng:
- Quản lý sinh viên, lớp học, môn học, giảng viên
- Quản lý đăng ký môn học và điểm số
- Tạo bảng điểm tự động
- Dashboard thống kê với biểu đồ
- Giao diện hiện đại với Bootstrap 5

## 🛠️ Công nghệ sử dụng

- **Backend**: Spring Boot 3.5.5, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5.3.3, Font Awesome 6.4.0
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Java Version**: 17

## 📋 Yêu cầu hệ thống

### Phần mềm cần thiết:
1. **Java 17** hoặc cao hơn
2. **Maven 3.6+**
3. **MySQL 8.0** hoặc cao hơn
4. **IDE** (IntelliJ IDEA, Eclipse, VS Code)

### Cấu hình tối thiểu:
- RAM: 4GB
- Dung lượng: 2GB trống
- Hệ điều hành: Windows 10+, macOS 10.15+, Ubuntu 18.04+

## 🚀 Hướng dẫn cài đặt và chạy dự án

### Bước 1: Clone dự án
```bash
git clone <repository-url>
cd studentManager
```

### Bước 2: Cài đặt Java 17
#### Windows:
1. Tải Java 17 từ [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) hoặc [OpenJDK](https://adoptium.net/)
2. Cài đặt và thiết lập biến môi trường `JAVA_HOME`
3. Thêm `%JAVA_HOME%\bin` vào `PATH`

#### macOS:
```bash
# Sử dụng Homebrew
brew install openjdk@17
export JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
```

#### Ubuntu/Linux:
```bash
sudo apt update
sudo apt install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

### Bước 3: Cài đặt Maven
#### Windows:
1. Tải Maven từ [Apache Maven](https://maven.apache.org/download.cgi)
2. Giải nén và thiết lập biến môi trường `MAVEN_HOME`
3. Thêm `%MAVEN_HOME%\bin` vào `PATH`

#### macOS:
```bash
brew install maven
```

#### Ubuntu/Linux:
```bash
sudo apt install maven
```

### Bước 4: Cài đặt và cấu hình MySQL

#### Cài đặt MySQL:
- **Windows**: Tải từ [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
- **macOS**: `brew install mysql`
- **Ubuntu**: `sudo apt install mysql-server`

#### Tạo database:
```sql
-- Đăng nhập MySQL
mysql -u root -p

-- Tạo database
CREATE DATABASE quanlysinhvien CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tạo user (tùy chọn)
CREATE USER 'studentuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON quanlysinhvien.* TO 'studentuser'@'localhost';
FLUSH PRIVILEGES;
```

### Bước 5: Cấu hình dự án

#### Cập nhật file `src/main/resources/application.properties`:
```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/quanlysinhvien?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Thymeleaf configuration
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.enabled=true

# Logging configuration
logging.level.org.springframework.security=DEBUG
logging.level.org.springframework.web=DEBUG
logging.file.name=logs/app.log

# Server configuration
server.error.include-message=always
server.error.include-binding-errors=always
server.error.include-stacktrace=always
```

### Bước 6: Build và chạy dự án

#### Cách 1: Sử dụng Maven Wrapper (Khuyến nghị)
```bash
# Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw clean install
./mvnw spring-boot:run
```

#### Cách 2: Sử dụng Maven
```bash
mvn clean install
mvn spring-boot:run
```

#### Cách 3: Chạy từ IDE
1. Mở dự án trong IDE
2. Tìm file `StudentManagerApplication.java`
3. Click chuột phải → Run 'StudentManagerApplication'

### Bước 7: Truy cập ứng dụng

Mở trình duyệt và truy cập:
- **URL**: http://localhost:8080
- **Trang chủ**: http://localhost:8080/dashboard
- **Đăng nhập**: http://localhost:8080/login
- **Đăng ký**: http://localhost:8080/register

## 👤 Tài khoản mặc định

Sau khi chạy lần đầu, hệ thống sẽ tự động tạo dữ liệu mẫu:
- **Username**: admin
- **Password**: admin123

## 📁 Cấu trúc dự án

```
studentManager/
├── src/
│   ├── main/
│   │   ├── java/com/example/studentManager/
│   │   │   ├── config/          # Cấu hình Spring Security
│   │   │   ├── controller/      # Controllers xử lý request
│   │   │   ├── entity/          # Các entity JPA
│   │   │   ├── repository/      # Repository interfaces
│   │   │   ├── service/         # Business logic
│   │   │   └── StudentManagerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/css/      # CSS files
│   │       └── templates/       # Thymeleaf templates
│   └── test/                    # Unit tests
├── target/                      # Compiled classes và JAR
├── logs/                        # Log files
├── pom.xml                      # Maven configuration
└── README.md                    # Tài liệu này
```

## 🔧 Cấu hình nâng cao

### Thay đổi port server:
```properties
server.port=8081
```

### Cấu hình logging:
```properties
logging.level.com.example.studentManager=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

### Cấu hình database khác:
```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/quanlysinhvien
spring.datasource.driver-class-name=org.postgresql.Driver

# H2 Database (cho development)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.h2.console.enabled=true
```

## 🐛 Troubleshooting

### Lỗi thường gặp:

#### 1. Lỗi kết nối database:
```
java.sql.SQLException: Access denied for user 'root'@'localhost'
```
**Giải pháp**: Kiểm tra username/password trong `application.properties`

#### 2. Lỗi Java version:
```
UnsupportedClassVersionError: class file version 61.0
```
**Giải pháp**: Cài đặt Java 17 hoặc cao hơn

#### 3. Lỗi port đã được sử dụng:
```
Port 8080 was already in use
```
**Giải pháp**: Thay đổi port trong `application.properties` hoặc dừng process đang sử dụng port

#### 4. Lỗi Maven:
```
Could not resolve dependencies
```
**Giải pháp**: 
```bash
mvn clean
mvn dependency:resolve
```

### Kiểm tra log:
```bash
# Xem log real-time
tail -f logs/app.log

# Windows
type logs\app.log
```

## 📚 API Endpoints

### Authentication:
- `GET /login` - Trang đăng nhập
- `POST /login` - Xử lý đăng nhập
- `GET /register` - Trang đăng ký
- `POST /register` - Xử lý đăng ký
- `POST /logout` - Đăng xuất

### Student Management:
- `GET /students` - Danh sách sinh viên
- `GET /students/new` - Form thêm sinh viên
- `POST /students/save` - Lưu sinh viên
- `GET /students/edit/{id}` - Form sửa sinh viên
- `GET /students/delete/{id}` - Xóa sinh viên

### Other Management:
- `GET /classrooms` - Quản lý lớp học
- `GET /subjects` - Quản lý môn học
- `GET /teachers` - Quản lý giảng viên
- `GET /enrollments` - Quản lý đăng ký
- `GET /grades` - Quản lý điểm số
- `GET /transcripts` - Quản lý bảng điểm

## 🤝 Đóng góp

1. Fork dự án
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 📄 License

Dự án này được phân phối dưới MIT License. Xem file `LICENSE` để biết thêm chi tiết.

## 📞 Hỗ trợ

Nếu gặp vấn đề, vui lòng tạo issue trên GitHub hoặc liên hệ:
- Email: support@example.com
- GitHub Issues: [Tạo issue mới](https://github.com/your-repo/issues)

---

**Chúc bạn sử dụng hệ thống thành công! 🎉**
