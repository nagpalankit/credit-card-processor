# Credit Card CRUD Application

This application provides limited CRUD operations for credit card data, allowing users to create and read credit card information.

The application is built using Java and Spring Boot framework, Gradle for building and dependency management, and uses an H2 database for data storage.

## Requirements

For building and running the application you need:
- JDK 21
- Gradle 8.13 (The project uses Gradle Wrapper, so you don't need to install it separately)

## Running the application locally

Once the repository is cloned, there are a couple of ways to run a Spring Boot application on your local machine:

### IDE
1. Open the repository in the preferred IDE
2. Navigate to file `io.github.nagpalankit.creditcardprocessor.CreditCardProcessorApplication` and run or debug the `main` method.

### Command Line
1. Open the repository root directory in the preferred command line
2. Run the following command to start the application:
```bash
./gradlew bootRun
```
Observe the logs to verify that the application has started successfully. It should be similar to:
```bash
i.g.n.c.CreditCardProcessorApplication   : Started CreditCardProcessorApplication in 1.636 seconds (process running for 1.804)
```

**Note:**
1. If this is the first time you are running the application, it may take a while to download Gradle and rest of the project dependencies.
2. Ensure that port 8080 is available, as it will be used for serving the application APIs.
3. Application will use H2 an in-memory database for data storage. The database will not be persisted on process termination.

## Testing the application

### Manual Testing
Once the application is up and running:
- view the API documentation at [Swagger UI](http://localhost:8080/swagger-ui)
- view the H2 database console at [H2 Console](http://localhost:8080/h2-console) (Use `password` as the password for logging in, keeping rest of the details as default)

You can test the APIs directly from Swagger UI or using other preferred tools like Postman or curl. Example payloads for testing the APIs are provided as examples in the Swagger UI.

### Automated Testing
The project includes a set of unit tests and integration tests to verify the functionality of the application. You can run the tests from the project root directory using the following command:
```bash
./gradlew test
```

## References
- This application serves as a back-end to the [Credit Card Manager UI](http://localhost:8080/swagger-ui) application.

## Contact Information
If you are having any trouble with the application, please feel free to reach out to me at [nagpalankit@icloud.com](mailto:nagpalankit@icloud.com)
