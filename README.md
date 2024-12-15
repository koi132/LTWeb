# Dự án Web Thuê Sân Bóng Đá

Dự án này là một ứng dụng web cho phép người dùng thuê sân bóng, quản lý đặt sân, và xem lịch sử thanh toán.

## Cài đặt

Để thiết lập dự án trên máy tính cá nhân, thực hiện các bước sau:

### Yêu cầu

- Java Development Kit (JDK) 11 trở lên  
- Apache Maven  
- SQL Server hoặc bất kỳ cơ sở dữ liệu quan hệ nào khác  
- Một IDE như IntelliJ IDEA hoặc Eclipse  

### Các bước thực hiện

1. **Clone repository:**

   ```bash
   git clone https://github.com/koi132/LTWeb.git
   cd LTWeb

   ```
2. **Set up database:**

   - Tạo một cơ sở dữ liệu mới trong SQL Server (hoặc cơ sở dữ liệu bạn chọn)
   - Cập nhật file application.properties với cấu hình cơ sở dữ liệu của bạn:

   ```
   
   spring.datasource.url=jdbc:sqlserver://localhost;databaseName=your_database_name;
   trustServerCertificate=true;
   spring.datasource.username=your_database_username
   spring.datasource.password=your_database_password
   
   ```
3. **Build dự án:**
  ```bash
  mvn clean install
  ```
4. **Run:**
  ```bash
  mvn spring-boot:run
  ```
5. **Truy cập vào Web**

Mở trình duyệt web của bạn và truy cập vào http://localhost:9900

6. **Giao diện của Web**

***Đăng kí***

***Đăng nhập***

***Trang chủ***
![image](https://github.com/user-attachments/assets/cb455e0d-e783-4881-ad29-7837bb5d05a0)

***Đặt sân***
![image](https://github.com/user-attachments/assets/929f2fbe-d15e-4679-b130-f958426aef72)
![image](https://github.com/user-attachments/assets/6b951d60-94d7-422e-897f-2999f2e62360)

***Lịch sử thanh toán***
![image](https://github.com/user-attachments/assets/e105e7f7-609d-4e41-bd7a-ff226d24dd40)

***Danh sách các sân đã đặt***
![image](https://github.com/user-attachments/assets/086df858-d858-44e2-aac0-d3274ffc9dc4)

***Trang quản lí***
![image](https://github.com/user-attachments/assets/761a4f01-9e0c-46df-ae13-25950557a347)

***Thêm sân mới***
![image](https://github.com/user-attachments/assets/0652ff74-415e-487a-8f87-fb0f95c9ffb8)

***Doanh thu***

![image](https://github.com/user-attachments/assets/606fd242-a012-4cda-a985-36c48a6d89c8)


***Trang quản lí người dùng***
![image](https://github.com/user-attachments/assets/63e415fa-42f0-4145-8df7-07651c3bc9de)


   
