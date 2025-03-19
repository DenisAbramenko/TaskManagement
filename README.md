# Task Management System

## Описание

Это простая система управления задачами (Task Management System), реализованная с использованием **Java 17**, **Spring
Boot**, **Spring Security**, **JWT** и **PostgreSQL**. Система позволяет создавать, редактировать, удалять и
просматривать задачи, а также предоставляет функционал для управления пользователями с разными ролями (администратор и
обычный пользователь).

## Основные возможности:

- Аутентификация и авторизация с использованием **JWT**.
- Ролевая система:
    - **Администратор**: может управлять всеми задачами (создавать, редактировать, удалять), назначать исполнителей,
      менять статус и приоритет, оставлять комментарии.
    - **Пользователь**: может управлять только своими задачами, если он назначен исполнителем, менять статус и оставлять
      комментарии.
- Поддержка фильтрации и пагинации для задач.
- Использование **Swagger UI** для документации API.
- **Docker Compose** для локального развертывания сервиса и базы данных.

## Стек технологий

- **Java 17+**
- **Spring Boot** (Spring Security, Spring Data JPA, Spring Validation)
- **JWT** для аутентификации и авторизации
- **PostgreSQL** (в качестве базы данных)
- **Swagger UI** (для документации API)
- **Docker Compose** (для локального запуска)

## Установка

## 1. Клонирование репозитория

git clone https://github.com/your-username/task-management-system.git
cd task-management-system
## 2. Настройка проекта
### 2.1. Установка зависимостей
Для работы с проектом необходимо иметь установленный Maven.

mvn clean install
### 2.2. Настройка базы данных
Для локального запуска потребуется настроить PostgreSQL. В src/main/resources/application.properties должны быть следующие настройки:

spring.datasource.url=jdbc:postgresql://localhost:5432/task_management
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
## 3. Запуск с использованием Docker Compose
Для того чтобы запустить проект с PostgreSQL в контейнерах, используйте Docker Compose.

### 3.1. Запуск Docker Compose
Убедитесь, что Docker и Docker Compose установлены на вашем компьютере. В корневом каталоге проекта запустите команду:

docker-compose up --build
Это создаст и запустит контейнеры для PostgreSQL и вашего Spring Boot приложения.

### 3.2. Остановка Docker Compose
Чтобы остановить контейнеры, используйте команду:

docker-compose down
## 4. Запуск приложения
Если вы не хотите использовать Docker, вы можете запустить приложение напрямую через Maven:

mvn spring-boot:run
После запуска приложение будет доступно по адресу:

http://localhost:8080
## Использование API
API можно использовать через инструменты, такие как Postman или через встроенный Swagger UI.

## 1. Swagger UI
Документация API доступна по следующему адресу:

http://localhost:8080/swagger-ui/index.html
## 2. Пример работы с API
### 2.1. Регистрация пользователя
Для регистрации пользователя (администратора или обычного) нужно отправить POST запрос на /auth/register с данными:

{
  "email": "user@example.com",
  "password": "yourpassword",
  "role": "USER"  // или "ADMIN" для администратора
}
### 2.2. Вход в систему (получение JWT токена)
Для входа в систему отправьте POST запрос на /auth/login с данными:

{
  "email": "user@example.com",
  "password": "yourpassword"
}
Ответ:

{
  "token": "jwt_token"
}
### 2.3. Получение списка задач
Чтобы получить список задач, отправьте GET запрос на /tasks:

GET http://localhost:8080/tasks
Для фильтрации задач можно использовать параметры authorId или assigneeId:

GET http://localhost:8080/tasks?authorId=1
## 3. Примеры запросов
Создание задачи (администратор)
POST /tasks
{
  "title": "New Task",
  "description": "Description of the task",
  "status": "PENDING",
  "priority": "HIGH",
  "author": {
    "id": 1
  },
  "assignee": {
    "id": 2
  }
}
Обновление задачи (исполнитель)
PUT /tasks/1
{
  "status": "IN_PROGRESS"
}
Добавление комментария к задаче
POST /tasks/1/comments
{
  "content": "This is a comment"
}
Тестирование
Для тестирования основных операций, таких как создание, редактирование, удаление задач, а также для аутентификации и авторизации, используется JUnit 5 с MockMvc.

Пример теста для создания задачи:

@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateTask() throws Exception {
        String taskJson = "{\"title\": \"Test Task\", \"description\": \"Test description\"}";

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }
}
Примечания
Для запуска проекта потребуется Java 17 или выше.

Для взаимодействия с API можно использовать инструменты, такие как Postman или Swagger UI.

Для использования базы данных PostgreSQL создайте контейнер с помощью Docker Compose или настройте локальный сервер.