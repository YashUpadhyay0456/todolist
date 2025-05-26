# 📝 Todo List REST API

This is a **Todo List Backend REST API** built using **Spring Boot**, **Spring Security**, and **PostgreSQL**. It supports **user authentication** and **CRUD operations** for managing tasks. Each user can register, log in, and manage their own tasks.

---

## 🚀 Features

- User registration and login (JWT-based authentication)
- Create, view, update, and delete tasks
- Toggle task completion status
- Authorization checks for task ownership
- Role-based HTTP status code responses (403, 404, etc.)
- Secure endpoints using Spring Security

---

## 🛠️ Tech Stack

| Layer        | Technology                  |
|--------------|-----------------------------|
| Backend      | Java, Spring Boot           |
| Database     | PostgreSQL                  |
| Security     | Spring Security, JWT        |
| ORM          | Hibernate / JPA             |
| Build Tool   | Maven                       |

---

## 📂 Project Structure

```
src/
├── controller/        # REST endpoints
├── service/           # Business logic
├── repository/        # JPA repositories
├── entities/          # JPA entities
├── security/          # JWT & authentication logic
└── config/            # Security and application configuration
```

---

## 🔐 Authentication

- Uses JWT (JSON Web Token) for secure authentication.
- Access to endpoints like `/tasks/**` requires a valid token.
- Add the token in the request header:  
  ```
  Authorization: Bearer <your-token>
  ```

---

## 📦 API Endpoints

### 🔑 Auth

| Method | Endpoint       | Description         |
|--------|----------------|---------------------|
| POST   | `/auth/register` | Register new user  |
| POST   | `/auth/login`    | Login and get token|

### ✅ Tasks

| Method | Endpoint             | Description                     |
|--------|----------------------|---------------------------------|
| POST   | `/tasks/create`      | Create a new task               |
| GET    | `/tasks/myTasks`     | Get all tasks for logged-in user|
| PATCH  | `/tasks/toggle/{id}` | Toggle task completion status   |
| DELETE | `/tasks/delete/{id}` | Delete a task by ID             |

---

## 🧪 Example Request

### Register a new user
```bash
curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{
  "username": "john",
  "password": "password",
  "name": "John Doe"
}'
```

### Create a task
```bash
curl -X POST http://localhost:8080/tasks/create -H "Authorization: Bearer <token>" -H "Content-Type: application/json" -d '{
  "title": "Learn Spring Boot",
  "description": "Finish building the todo app",
  "completed": false
}'
```

---

## 🖥️ How to Run Locally

### 1. Clone the repository
```bash
git clone https://github.com/YashUpadhyay0456/todolist.git
cd todolist
```

### 2. Configure PostgreSQL

- Create a PostgreSQL database (e.g., `todolist`)
- Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

### 3. Run the application
```bash
./mvnw spring-boot:run
```

---

## 📄 License

This project is licensed under the MIT License.

---

## 🙋‍♂️ Author

[Yash Upadhyay](https://github.com/YashUpadhyay0456)

---

## 📬 Feedback

Feel free to [open an issue](https://github.com/YashUpadhyay0456/todolist/issues) or reach out if you'd like to contribute or suggest improvements.
