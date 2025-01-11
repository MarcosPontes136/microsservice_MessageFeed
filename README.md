# Microsservice_MessageFeed

## Overview

The **Microsservice_MessageFeed** is a RESTful service developed with Spring Boot to manage user messages. This service allows users to post messages and retrieve all posted messages. It integrates with a SQL Server database for storage.

---

## Features

- **Create Messages**: Submit a message with a name and content.
- **Retrieve Messages**: Fetch all stored messages.
- Cross-Origin Resource Sharing (CORS) enabled for `http://localhost:4200`.

---

## Prerequisites

1. **Java Development Kit (JDK)**: Version 17 or higher.
2. **Maven**: For dependency management and building the project.
3. **SQL Server**: As the database for storing messages.
4. **IDE**: IntelliJ IDEA, Eclipse, or any other Java-compatible IDE.

---

## Database Schema

| Column   | Type           | Constraints  |
|----------|----------------|--------------|
| ID       | uniqueidentifier | Primary Key |
| NOME     | varchar(255)   | Not Null     |
| MENS     | varchar(255)   | Not Null     |
| STATUS   | varchar(50)    | Not Null     |

---

## MODEL

```java
@Data
@Entity
@Table(name = "MENSAGEM")
public class MessageModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID messageId;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "MENS")
	private String mensagem;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusMessage statusMessage;
		
}
```

---

## Controller

The `messageController` class defines endpoints for the API.

### Base URL

```
http://<host>:<port>/api
```

### Endpoints

#### 1. Send a Message

**POST** `/feeds`

**Description:** Save a new message.

**Request Body:**
```json
{
    "nome": "User Name",
    "mensagem": "This is a sample message."
}
```

**Response:**
- **201 Created**: When the message is successfully saved.

**Example Response:**
```json
{
    "messageId": 1,
    "nome": "User Name",
    "mensagem": "This is a sample message."
}
```

---

#### 2. Get All Messages

**GET** `/feed`

**Description:** Retrieve all saved messages.

**Response:**
- **200 OK**: Returns a list of messages.

**Example Response:**
```json
[
    {
        "messageId": 1,
        "nome": "User Name",
        "mensagem": "This is a sample message."
    },
    {
        "messageId": 2,
        "nome": "Another User",
        "mensagem": "Another sample message."
    }
]
```

---

## Running the Application

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:
    ```bash
    cd message-api
    ```

3. Build the project using Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

5. Access the application:
    ```
    http://localhost:8080
    ```

---

## Cross-Origin Resource Sharing (CORS)

The API is configured to allow requests from `http://localhost:4200`, enabling frontend integration.

---

## License

This project is licensed under the MIT License.

