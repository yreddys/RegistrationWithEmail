

---

We have three APIs in the application:

1. **Registration API** – Accepts user details and registers the user. Upon successful registration, an email notification is sent to the user's email address.
2. **Login API** – Authenticates the user using JWT token-based authentication.
3. **Update API** – Allows updating the details of an already registered user.

### Dependencies used in the project:

- **Spring Boot Starter Web** – For building RESTful APIs.
- **Spring Boot Starter Mail** – For sending email notifications.
- **Spring Boot Starter Security** – For implementing authentication and authorization.
- **Spring Boot Starter Data JPA** – For database interactions using Java Persistence API.
- **MySQL Connector** – For connecting the Spring Boot application with a MySQL database.
- **jjwt (Java JWT)** – For creating and validating JWT tokens.
- **Lombok** – To reduce boilerplate code using annotations like `@Slf4j`, `@Data`, `@Builder`, etc.

