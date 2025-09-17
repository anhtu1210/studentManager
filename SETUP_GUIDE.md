# 🚀 Hướng dẫn Setup Nhanh - Student Management System

## ⚡ Cài đặt nhanh trong 5 phút

### 1. Yêu cầu tối thiểu
- ✅ Java 17+
- ✅ MySQL 8.0+
- ✅ Maven 3.6+

### 2. Cài đặt MySQL
```bash
# Windows (sử dụng Chocolatey)
choco install mysql

# macOS
brew install mysql
brew services start mysql

# Ubuntu/Debian
sudo apt install mysql-server
sudo systemctl start mysql
```

### 3. Tạo database
```sql
mysql -u root -p
CREATE DATABASE quanlysinhvien;
```

### 4. Cấu hình database
Chỉnh sửa file `src/main/resources/application.properties`:
```properties
spring.datasource.password=your_mysql_password
```

### 5. Chạy dự án
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

### 6. Truy cập ứng dụng
- 🌐 URL: http://localhost:8080
- 👤 Username: admin
- 🔑 Password: admin123

---

## 🔧 Cài đặt chi tiết

### Windows với Laragon/XAMPP

#### Bước 1: Cài đặt Laragon
1. Tải Laragon từ [laragon.org](https://laragon.org/)
2. Cài đặt và khởi động Laragon
3. Start Apache và MySQL

#### Bước 2: Cài đặt Java 17
1. Tải Java 17 từ [adoptium.net](https://adoptium.net/)
2. Cài đặt và thiết lập biến môi trường:
   - `JAVA_HOME`: C:\Program Files\Eclipse Adoptium\jdk-17.x.x.x-hotspot
   - Thêm `%JAVA_HOME%\bin` vào PATH

#### Bước 3: Cài đặt Maven
1. Tải Maven từ [maven.apache.org](https://maven.apache.org/download.cgi)
2. Giải nén vào `C:\maven`
3. Thiết lập biến môi trường:
   - `MAVEN_HOME`: C:\maven
   - Thêm `%MAVEN_HOME%\bin` vào PATH

#### Bước 4: Clone và chạy dự án
```cmd
git clone <repository-url>
cd studentManager
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

### macOS

#### Bước 1: Cài đặt Homebrew (nếu chưa có)
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

#### Bước 2: Cài đặt các công cụ cần thiết
```bash
brew install openjdk@17
brew install maven
brew install mysql
```

#### Bước 3: Khởi động MySQL
```bash
brew services start mysql
mysql -u root -p
CREATE DATABASE quanlysinhvien;
```

#### Bước 4: Chạy dự án
```bash
git clone <repository-url>
cd studentManager
./mvnw clean install
./mvnw spring-boot:run
```

### Ubuntu/Debian

#### Bước 1: Cập nhật hệ thống
```bash
sudo apt update
sudo apt upgrade
```

#### Bước 2: Cài đặt Java 17
```bash
sudo apt install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
```

#### Bước 3: Cài đặt Maven
```bash
sudo apt install maven
```

#### Bước 4: Cài đặt MySQL
```bash
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql_secure_installation
```

#### Bước 5: Tạo database
```bash
sudo mysql -u root -p
CREATE DATABASE quanlysinhvien;
CREATE USER 'studentuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON quanlysinhvien.* TO 'studentuser'@'localhost';
FLUSH PRIVILEGES;
```

#### Bước 6: Chạy dự án
```bash
git clone <repository-url>
cd studentManager
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🐛 Xử lý lỗi thường gặp

### Lỗi 1: Java version không đúng
```
Error: A JNI error has occurred, please check your installation
```
**Giải pháp:**
```bash
java -version  # Kiểm tra version
# Cài đặt Java 17 nếu cần
```

### Lỗi 2: MySQL connection failed
```
java.sql.SQLException: Access denied for user 'root'@'localhost'
```
**Giải pháp:**
1. Kiểm tra MySQL đang chạy: `sudo systemctl status mysql`
2. Kiểm tra password trong `application.properties`
3. Reset password MySQL nếu cần:
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';
```

### Lỗi 3: Port 8080 đã được sử dụng
```
Port 8080 was already in use
```
**Giải pháp:**
```bash
# Tìm process sử dụng port 8080
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # macOS/Linux

# Dừng process hoặc đổi port trong application.properties
server.port=8081
```

### Lỗi 4: Maven dependency
```
Could not resolve dependencies
```
**Giải pháp:**
```bash
mvn clean
mvn dependency:purge-local-repository
mvn install
```

---

## 📱 Truy cập ứng dụng

Sau khi chạy thành công, truy cập:

| Trang | URL | Mô tả |
|-------|-----|-------|
| 🏠 Dashboard | http://localhost:8080/dashboard | Trang chủ với thống kê |
| 👤 Đăng nhập | http://localhost:8080/login | Trang đăng nhập |
| 📝 Đăng ký | http://localhost:8080/register | Trang đăng ký tài khoản |
| 👥 Sinh viên | http://localhost:8080/students | Quản lý sinh viên |
| 🏫 Lớp học | http://localhost:8080/classrooms | Quản lý lớp học |
| 📚 Môn học | http://localhost:8080/subjects | Quản lý môn học |
| 👨‍🏫 Giảng viên | http://localhost:8080/teachers | Quản lý giảng viên |
| 📊 Bảng điểm | http://localhost:8080/transcripts | Xem bảng điểm |

---

## 🔑 Tài khoản mặc định

| Loại | Username | Password | Quyền |
|------|----------|----------|-------|
| 👑 Admin | admin | admin123 | Toàn quyền |
| 👤 User | user | user123 | Xem dữ liệu |

---

## 📞 Hỗ trợ

Nếu gặp vấn đề:
1. Kiểm tra log: `tail -f logs/app.log`
2. Tạo issue trên GitHub
3. Liên hệ qua email: support@example.com

**Chúc bạn setup thành công! 🎉**
