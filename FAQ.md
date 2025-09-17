# ❓ FAQ - Student Management System

## 🚀 Cài đặt và Chạy

### Q: Lỗi "Java version not supported" khi chạy dự án?
**A:** Dự án yêu cầu Java 17 hoặc cao hơn. Kiểm tra version:
```bash
java -version
```
Nếu chưa có Java 17, tải từ [adoptium.net](https://adoptium.net/) và cài đặt.

### Q: Lỗi kết nối database "Access denied for user 'root'@'localhost'"?
**A:** Kiểm tra:
1. MySQL đang chạy: `sudo systemctl status mysql` (Linux) hoặc `brew services list | grep mysql` (macOS)
2. Password trong `application.properties` đúng chưa
3. User có quyền truy cập database chưa:
```sql
GRANT ALL PRIVILEGES ON quanlysinhvien.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### Q: Port 8080 đã được sử dụng?
**A:** Có 2 cách:
1. **Đổi port**: Thêm vào `application.properties`:
```properties
server.port=8081
```
2. **Dừng process**: Tìm và dừng process đang dùng port 8080:
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# macOS/Linux
lsof -i :8080
kill -9 <PID>
```

### Q: Lỗi "Could not resolve dependencies" khi build Maven?
**A:** Thử các cách sau:
```bash
# Xóa cache Maven
mvn clean
rm -rf ~/.m2/repository

# Cập nhật dependencies
mvn dependency:resolve

# Build lại
mvn clean install
```

### Q: Ứng dụng chạy nhưng không hiển thị giao diện?
**A:** Kiểm tra:
1. Truy cập đúng URL: http://localhost:8080
2. Kiểm tra log để xem lỗi: `tail -f logs/app.log`
3. Kiểm tra database connection
4. Clear browser cache và thử lại

---

## 🔐 Đăng nhập và Bảo mật

### Q: Quên mật khẩu admin?
**A:** Có 2 cách:
1. **Reset trong database**:
```sql
UPDATE users SET password = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi' WHERE username = 'admin';
-- Mật khẩu mới: admin123
```

2. **Tạo user mới**:
```sql
INSERT INTO users (username, password, enabled) VALUES ('newadmin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', true);
```

### Q: Lỗi "Invalid username or password"?
**A:** Kiểm tra:
1. Username/password đúng chưa (admin/admin123)
2. User có enabled = true trong database
3. Kiểm tra log để xem chi tiết lỗi

### Q: Làm sao để thay đổi mật khẩu mặc định?
**A:** Cập nhật trong database:
```sql
-- Tạo password mới (ví dụ: newpassword123)
-- Sử dụng BCrypt encoder để hash password
UPDATE users SET password = '$2a$10$new_hashed_password' WHERE username = 'admin';
```

---

## 📊 Dữ liệu và Database

### Q: Làm sao để xóa tất cả dữ liệu và bắt đầu lại?
**A:** Có 2 cách:
1. **Xóa database và tạo lại**:
```sql
DROP DATABASE quanlysinhvien;
CREATE DATABASE quanlysinhvien CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. **Reset dữ liệu mẫu**: Restart ứng dụng, hệ thống sẽ tự động tạo lại dữ liệu mẫu.

### Q: Lỗi "Duplicate entry" khi thêm dữ liệu?
**A:** Kiểm tra các trường unique:
- **Student**: `student_code`, `email` phải duy nhất
- **Teacher**: `teacher_code`, `email` phải duy nhất
- **Subject**: `subject_code` phải duy nhất
- **Classroom**: `class_code` phải duy nhất

### Q: Làm sao để backup dữ liệu?
**A:** Sử dụng mysqldump:
```bash
# Backup toàn bộ database
mysqldump -u root -p quanlysinhvien > backup.sql

# Restore từ backup
mysql -u root -p quanlysinhvien < backup.sql
```

### Q: Lỗi "Table doesn't exist"?
**A:** Kiểm tra:
1. Database có tồn tại không
2. Cấu hình `spring.jpa.hibernate.ddl-auto=update` trong `application.properties`
3. Restart ứng dụng để tạo bảng tự động

---

## 🎨 Giao diện và UI

### Q: Giao diện bị lỗi, không hiển thị CSS/JS?
**A:** Kiểm tra:
1. Network tab trong Developer Tools xem có lỗi 404 không
2. Clear browser cache
3. Kiểm tra file static có tồn tại không
4. Kiểm tra cấu hình Thymeleaf

### Q: Lỗi "Whitelabel Error Page"?
**A:** Lỗi này thường do:
1. **Controller không tìm thấy**: Kiểm tra URL mapping
2. **Template không tồn tại**: Kiểm tra file template trong `templates/`
3. **Lỗi trong template**: Kiểm tra syntax Thymeleaf
4. **Lỗi database**: Kiểm tra log để xem chi tiết

### Q: Làm sao để thay đổi theme/giao diện?
**A:** Chỉnh sửa:
1. **CSS**: File trong `src/main/resources/static/css/`
2. **Templates**: File trong `src/main/resources/templates/`
3. **Bootstrap version**: Cập nhật CDN link trong templates

### Q: Lỗi "Bootstrap is not defined"?
**A:** Kiểm tra:
1. Bootstrap JS được load chưa
2. Thứ tự load script (Bootstrap phải load trước)
3. CDN link có đúng không

---

## 🔍 Tìm kiếm và Lọc

### Q: Tìm kiếm không hoạt động?
**A:** Kiểm tra:
1. **Backend**: Repository method có đúng không
2. **Frontend**: Form submit đúng không
3. **Database**: Dữ liệu có tồn tại không
4. **Log**: Xem log để debug

### Q: Lọc dữ liệu không chính xác?
**A:** Kiểm tra:
1. **Logic filter**: Service method có đúng không
2. **Database query**: SQL query có đúng không
3. **Frontend**: Parameter được gửi đúng không

---

## 📱 Responsive và Mobile

### Q: Giao diện không responsive trên mobile?
**A:** Kiểm tra:
1. **Viewport meta tag**: Có trong `<head>` không
2. **Bootstrap classes**: Sử dụng đúng responsive classes
3. **CSS media queries**: Có responsive CSS không

### Q: Lỗi "Touch events not working"?
**A:** Kiểm tra:
1. **Bootstrap JS**: Có load đầy đủ không
2. **Event listeners**: Có bind đúng không
3. **CSS**: Có `touch-action` properties không

---

## ⚡ Performance

### Q: Ứng dụng chạy chậm?
**A:** Tối ưu:
1. **Database**: Thêm indexes cho các trường thường query
2. **JPA**: Sử dụng `@Query` thay vì `findAll()`
3. **Caching**: Thêm `@Cacheable` cho các method thường gọi
4. **Lazy loading**: Sử dụng `@Lazy` cho các relationship

### Q: Lỗi "Out of memory"?
**A:** Tăng heap size:
```bash
java -Xms512m -Xmx2048m -jar app.jar
```

### Q: Database connection timeout?
**A:** Cấu hình connection pool:
```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
```

---

## 🧪 Testing

### Q: Unit test không chạy?
**A:** Kiểm tra:
1. **Dependencies**: Có `spring-boot-starter-test` không
2. **Test profile**: Có `application-test.properties` không
3. **Annotations**: Có `@SpringBootTest` không

### Q: Integration test fail?
**A:** Kiểm tra:
1. **Database**: Test database có sẵn sàng không
2. **Data setup**: Có `@Sql` annotations không
3. **Transactions**: Có `@Transactional` không

---

## 🚀 Deployment

### Q: Lỗi khi deploy lên server?
**A:** Kiểm tra:
1. **Java version**: Server có Java 17 không
2. **Database**: Server có MySQL không
3. **Port**: Port 8080 có mở không
4. **Firewall**: Có block port không

### Q: Lỗi "Class not found" khi chạy JAR?
**A:** Kiểm tra:
1. **JAR file**: Có build đúng không
2. **Dependencies**: Có include đầy đủ không
3. **Main class**: Có đúng main class không

### Q: Lỗi "Database connection failed" trên server?
**A:** Kiểm tra:
1. **Database server**: Có chạy không
2. **Network**: Có kết nối được không
3. **Credentials**: Username/password đúng không
4. **Firewall**: Port 3306 có mở không

---

## 🔧 Development

### Q: Lỗi "Cannot resolve symbol" trong IDE?
**A:** Kiểm tra:
1. **Maven**: Có refresh project không
2. **Dependencies**: Có download đầy đủ không
3. **IDE**: Có enable annotation processing không

### Q: Lỗi "Circular dependency"?
**A:** Giải quyết:
1. **Refactor**: Tách logic thành service riêng
2. **@Lazy**: Sử dụng lazy loading
3. **@PostConstruct**: Sử dụng lifecycle methods

### Q: Lỗi "Bean creation failed"?
**A:** Kiểm tra:
1. **Dependencies**: Có inject đúng không
2. **Configuration**: Có cấu hình đúng không
3. **Database**: Có kết nối được không

---

## 📞 Hỗ trợ

### Q: Làm sao để báo lỗi?
**A:** Cung cấp:
1. **Error message**: Chi tiết lỗi
2. **Steps to reproduce**: Các bước tái tạo lỗi
3. **Environment**: OS, Java version, MySQL version
4. **Log files**: File log liên quan

### Q: Làm sao để đóng góp code?
**A:** Quy trình:
1. **Fork** repository
2. **Create branch** cho feature mới
3. **Commit** changes với message rõ ràng
4. **Push** lên GitHub
5. **Create Pull Request**

### Q: Làm sao để liên hệ support?
**A:** Các kênh:
- **Email**: support@example.com
- **GitHub Issues**: [Tạo issue mới](https://github.com/your-repo/issues)
- **Documentation**: Đọc README.md và các guide khác

---

## 📚 Tài liệu bổ sung

- [README.md](README.md) - Tổng quan dự án
- [SETUP_GUIDE.md](SETUP_GUIDE.md) - Hướng dẫn cài đặt
- [USER_GUIDE.md](USER_GUIDE.md) - Hướng dẫn sử dụng
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) - Hướng dẫn phát triển

---

**Nếu câu hỏi của bạn không có trong FAQ, vui lòng tạo issue mới trên GitHub! 🚀**
