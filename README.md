# vmo-apartment-system

# Kiến Thức Tổng Quan: OOP, REST API, SOLID, Stateless/Stateful, IoC & DI

---

## 1. OOP (Object-Oriented Programming - Lập trình hướng đối tượng)

### Khái niệm

Là phương pháp lập trình sử dụng **đối tượng (object)** để mô hình hóa thực thể trong thế giới thực.

### 4 đặc tính chính

| Đặc tính          | Giải thích                                             | Ví dụ                                    |
| ----------------- | ------------------------------------------------------ | ---------------------------------------- |
| **Encapsulation** | Che giấu dữ liệu, chỉ cung cấp giao diện cần thiết.    | `private` fields, `public` getter/setter |
| **Inheritance**   | Lớp con kế thừa thuộc tính & phương thức từ lớp cha.   | `class Dog extends Animal`               |
| **Polymorphism**  | Giao diện giống nhau nhưng hành vi khác nhau.          | Overriding: `speak()` của `Dog` vs `Cat` |
| **Abstraction**   | Chỉ thể hiện những gì cần thiết, ẩn chi tiết phức tạp. | Interface `Shape` với `draw()`           |

### Ví dụ

```java
abstract class Animal {
    abstract void sound();
}

class Dog extends Animal {
    void sound() {
        System.out.println("Woof");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow");
    }
}
```

---

## 2. REST API

### Khái niệm

REST (Representational State Transfer) là một chuẩn kiến trúc dùng để thiết kế API dựa trên HTTP.

### Đặc điểm

* Giao tiếp qua HTTP (GET, POST, PUT, DELETE)
* Stateless (không lưu trạng thái)
* Dữ liệu trả về thường ở dạng JSON hoặc XML

### Ví dụ

| Phương thức | Đường dẫn  | Ý nghĩa                       |
| ----------- | ---------- | ----------------------------- |
| GET         | `/users`   | Lấy danh sách người dùng      |
| GET         | `/users/1` | Lấy thông tin user ID=1       |
| POST        | `/users`   | Tạo người dùng mới            |
| PUT         | `/users/1` | Cập nhật thông tin người dùng |
| DELETE      | `/users/1` | Xóa người dùng                |

---

## 3. SOLID Principles

Là 5 nguyên tắc thiết kế phần mềm hướng đối tượng giúp mã nguồn dễ bảo trì & mở rộng.

| Chữ cái | Ý nghĩa                         | Giải thích                                                   |
| ------- | ------------------------------- | ------------------------------------------------------------ |
| **S**   | Single Responsibility Principle | Một class chỉ nên có một lý do để thay đổi                   |
| **O**   | Open/Closed Principle           | Mở rộng được nhưng không sửa đổi code cũ                     |
| **L**   | Liskov Substitution Principle   | Lớp con có thể thay thế lớp cha mà không làm sai logic       |
| **I**   | Interface Segregation Principle | Tách interface lớn thành nhiều interface nhỏ                 |
| **D**   | Dependency Inversion Principle  | Phụ thuộc vào abstraction (interface) thay vì implementation |

### Ví dụ

```java
// Vi phạm SRP
class Report {
    void generate() {}
    void saveToFile() {}
}

// Tuân thủ SRP
class Report {
    void generate() {}
}

class ReportSaver {
    void saveToFile(Report report) {}
}
```

---

## 4. Stateless vs Stateful

| Đặc điểm        | Stateless                    | Stateful                        |
| --------------- |------------------------------|---------------------------------|
| Lưu trạng thái? | Không                        |  Có                             |
| Ví dụ           | REST API, HTTP               | WebSocket, Session, FTP         |
| Ưu điểm         | Đơn giản, dễ mở rộng         | Phù hợp ứng dụng thời gian thực |
| Nhược điểm      | Cần gửi đủ thông tin mỗi lần | Khó scale nếu lưu session       |

### Ví dụ

* **Stateless:** Mỗi request đến REST API cần chứa token xác thực.
* **Stateful:** Khi bạn đăng nhập, server lưu session, và bạn không cần gửi lại mật khẩu ở mỗi lần truy cập.

---

## 5. IoC (Inversion of Control) và DI (Dependency Injection)

### Inversion of Control (IoC)

Là nguyên lý mà **quyền kiểm soát việc tạo đối tượng được đảo ngược** từ chương trình sang framework.

### Dependency Injection (DI)

Là một kỹ thuật để **cung cấp dependencies (phụ thuộc)** cho một object thay vì nó tự tạo.

### Cơ chế hoạt động

* Dùng **constructor**, **setter**, hoặc **field** để "tiêm" dependency.
* DI thường được quản lý bởi container như Spring Framework.

### Ví dụ Java

```java
class Engine {
    void start() { System.out.println("Engine started"); }
}

class Car {
    private Engine engine;

    public Car(Engine engine) { // Dependency Injection
        this.engine = engine;
    }

    void drive() {
        engine.start();
        System.out.println("Car is moving");
    }
}

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Car car = new Car(engine); // Inject Engine
        car.drive();
    }
}
```
