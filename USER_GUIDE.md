# 📖 Hướng dẫn sử dụng - Student Management System

## 🎯 Tổng quan hệ thống

Hệ thống Quản lý Sinh viên cung cấp các tính năng:
- ✅ Quản lý thông tin sinh viên, lớp học, môn học, giảng viên
- ✅ Đăng ký môn học và quản lý điểm số
- ✅ Tạo bảng điểm tự động
- ✅ Dashboard thống kê với biểu đồ
- ✅ Giao diện hiện đại, responsive

---

## 🔐 Đăng nhập hệ thống

### 1. Truy cập trang đăng nhập
- URL: http://localhost:8080/login
- Hoặc click "Đăng nhập" từ trang chủ

### 2. Nhập thông tin
- **Username**: admin
- **Password**: admin123

### 3. Đăng xuất
- Click vào tên người dùng ở góc phải
- Chọn "Đăng xuất"

---

## 🏠 Dashboard

### Thống kê tổng quan
- **Tổng sinh viên**: Số lượng sinh viên trong hệ thống
- **Tổng lớp học**: Số lượng lớp học
- **Tổng môn học**: Số lượng môn học
- **Tổng giảng viên**: Số lượng giảng viên

### Thống kê học tập
- **Xuất sắc**: Sinh viên có điểm TB ≥ 8.0
- **Khá**: Sinh viên có điểm TB 6.5-7.9
- **Trung bình**: Sinh viên có điểm TB 5.0-6.4
- **Yếu**: Sinh viên có điểm TB < 5.0
- **Chưa có điểm**: Sinh viên chưa có điểm nào

### Biểu đồ thống kê
- **Phân bố điểm theo môn học**: Hiển thị điểm trung bình của từng môn

---

## 👥 Quản lý Sinh viên

### Xem danh sách sinh viên
1. Truy cập **Dashboard** → **Quản lý Sinh viên**
2. Hoặc URL: http://localhost:8080/students

### Thêm sinh viên mới
1. Click nút **"Thêm sinh viên"**
2. Điền thông tin:
   - **Mã sinh viên** (bắt buộc, duy nhất)
   - **Họ và tên** (bắt buộc)
   - **Email** (bắt buộc, duy nhất)
   - **Lớp học** (chọn từ dropdown)
3. Click **"Thêm sinh viên"**

### Sửa thông tin sinh viên
1. Click nút **"Chỉnh sửa"** (biểu tượng bút) trong danh sách
2. Cập nhật thông tin cần thiết
3. Click **"Cập nhật"**

### Xóa sinh viên
1. Click nút **"Xóa"** (biểu tượng thùng rác)
2. Xác nhận xóa trong popup

### Tìm kiếm sinh viên
- Sử dụng ô **"Tìm kiếm"** để tìm theo:
  - Mã sinh viên
  - Họ tên
  - Email
  - Lớp học

---

## 🏫 Quản lý Lớp học

### Xem danh sách lớp học
1. Truy cập **Dashboard** → **Quản lý Lớp học**
2. Hoặc URL: http://localhost:8080/classrooms

### Thêm lớp học mới
1. Click nút **"Thêm lớp học"**
2. Điền thông tin:
   - **Mã lớp** (bắt buộc, duy nhất)
   - **Tên lớp**
   - **Giảng viên phụ trách** (chọn từ dropdown)
3. Click **"Thêm lớp học"**

### Sửa thông tin lớp học
1. Click nút **"Chỉnh sửa"** trong danh sách
2. Cập nhật thông tin
3. Click **"Cập nhật"**

---

## 📚 Quản lý Môn học

### Xem danh sách môn học
1. Truy cập **Dashboard** → **Quản lý Môn học**
2. Hoặc URL: http://localhost:8080/subjects

### Thêm môn học mới
1. Click nút **"Thêm môn học"**
2. Điền thông tin:
   - **Mã môn học** (bắt buộc, duy nhất)
   - **Tên môn học** (bắt buộc)
   - **Số tín chỉ**
   - **Giảng viên phụ trách** (chọn từ dropdown)
3. Click **"Thêm môn học"**

---

## 👨‍🏫 Quản lý Giảng viên

### Xem danh sách giảng viên
1. Truy cập **Dashboard** → **Quản lý Giảng viên**
2. Hoặc URL: http://localhost:8080/teachers

### Thêm giảng viên mới
1. Click nút **"Thêm giảng viên"**
2. Điền thông tin:
   - **Mã giảng viên** (bắt buộc, duy nhất)
   - **Họ và tên** (bắt buộc)
   - **Email** (bắt buộc, duy nhất)
   - **Số điện thoại**
3. Click **"Thêm giảng viên"**

---

## 📝 Quản lý Đăng ký môn học

### Xem danh sách đăng ký
1. Truy cập **Dashboard** → **Quản lý Đăng ký**
2. Hoặc URL: http://localhost:8080/enrollments

### Thêm đăng ký mới
1. Click nút **"Thêm đăng ký"**
2. Chọn:
   - **Sinh viên** (từ dropdown)
   - **Môn học** (từ dropdown)
   - **Lớp học** (từ dropdown)
3. Click **"Thêm đăng ký"**

### Tìm kiếm đăng ký
- Sử dụng ô **"Tìm kiếm"** để tìm theo:
  - Tên sinh viên
  - Mã sinh viên
  - Tên môn học

---

## 📊 Quản lý Điểm số

### Xem danh sách điểm
1. Truy cập **Dashboard** → **Quản lý Điểm số**
2. Hoặc URL: http://localhost:8080/grades

### Thêm điểm mới
1. Click nút **"Thêm điểm"**
2. Chọn:
   - **Sinh viên** (từ dropdown)
   - **Môn học** (từ dropdown)
   - **Điểm số** (0-10)
3. Click **"Thêm điểm"**

### Sửa điểm
1. Click nút **"Chỉnh sửa"** trong danh sách
2. Cập nhật điểm số
3. Click **"Cập nhật"**

---

## 📋 Quản lý Bảng điểm

### Xem danh sách bảng điểm
1. Truy cập **Dashboard** → **Quản lý Bảng điểm**
2. Hoặc URL: http://localhost:8080/transcripts

### Xem bảng điểm của sinh viên
1. Từ danh sách sinh viên, click **"Bảng điểm"**
2. Hoặc URL: http://localhost:8080/transcripts/student/{id}

### Lọc bảng điểm
- **Tìm kiếm**: Theo tên sinh viên, mã SV, môn học
- **Xếp loại**: A, B, C, D, F
- **Khoảng điểm**: Xuất sắc, Khá, Trung bình, Yếu

### Tính năng bảng điểm
- **Tự động tính điểm TB**: Dựa trên các điểm đã nhập
- **Xếp loại tự động**: A (8.0+), B (6.5-7.9), C (5.0-6.4), D (3.5-4.9), F (<3.5)
- **Xuất bảng điểm**: In hoặc xuất PDF (nếu có)

---

## 🔍 Tìm kiếm và Lọc

### Tìm kiếm chung
- Hầu hết các trang đều có ô **"Tìm kiếm"**
- Tìm kiếm theo từ khóa liên quan
- Kết quả hiển thị real-time

### Lọc dữ liệu
- **Bảng điểm**: Lọc theo xếp loại, khoảng điểm
- **Sinh viên**: Lọc theo lớp học
- **Đăng ký**: Lọc theo môn học, lớp học

---

## 📱 Responsive Design

### Desktop
- Giao diện đầy đủ với sidebar
- Bảng dữ liệu rộng rãi
- Hover effects và animations

### Tablet
- Layout tự động điều chỉnh
- Bảng có thể scroll ngang
- Buttons và forms tối ưu

### Mobile
- Layout dạng card
- Navigation collapse
- Touch-friendly buttons

---

## ⚙️ Cài đặt và Cấu hình

### Thay đổi thông tin cá nhân
1. Click vào tên người dùng ở góc phải
2. Chọn **"Hồ sơ cá nhân"**
3. Cập nhật thông tin
4. Click **"Cập nhật"**

### Đổi mật khẩu
1. Vào **"Hồ sơ cá nhân"**
2. Nhập mật khẩu cũ
3. Nhập mật khẩu mới
4. Xác nhận mật khẩu mới
5. Click **"Đổi mật khẩu"**

---

## 🚨 Xử lý lỗi thường gặp

### Lỗi 404 - Không tìm thấy trang
- Kiểm tra URL có đúng không
- Refresh trang
- Đăng nhập lại

### Lỗi 500 - Lỗi server
- Kiểm tra log: `logs/app.log`
- Restart ứng dụng
- Liên hệ admin

### Lỗi validation
- Kiểm tra các trường bắt buộc
- Kiểm tra format email
- Kiểm tra độ dài chuỗi

### Lỗi kết nối database
- Kiểm tra MySQL đang chạy
- Kiểm tra cấu hình database
- Liên hệ admin

---

## 💡 Mẹo sử dụng

### 1. Tìm kiếm nhanh
- Sử dụng phím tắt `Ctrl + F` để tìm kiếm trong trang
- Sử dụng ô tìm kiếm của từng module

### 2. Thao tác nhanh
- Click đúp vào hàng để xem chi tiết
- Sử dụng phím `Enter` để submit form
- Sử dụng `Tab` để di chuyển giữa các trường

### 3. Quản lý dữ liệu
- Thường xuyên backup dữ liệu
- Kiểm tra tính nhất quán của dữ liệu
- Xóa dữ liệu cũ không cần thiết

### 4. Bảo mật
- Đổi mật khẩu định kỳ
- Không chia sẻ tài khoản
- Đăng xuất khi không sử dụng

---

## 📞 Hỗ trợ

### Liên hệ kỹ thuật
- **Email**: support@example.com
- **Phone**: 0123-456-789
- **GitHub**: [Tạo issue](https://github.com/your-repo/issues)

### Tài liệu bổ sung
- [API Documentation](API_DOCS.md)
- [Developer Guide](DEVELOPER_GUIDE.md)
- [FAQ](FAQ.md)

---

**Chúc bạn sử dụng hệ thống hiệu quả! 🎉**
