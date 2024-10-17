# VoipFuture E2E Test Automation Project

This project is an End-to-End (E2E) test automation framework for the VoipFuture application, developed using Selenium WebDriver with Java, TestNG, and Maven. It focuses on automating the testing of various functionalities related to the Alias Name feature in the Qrystal application.

## Table of Contents
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [How to Run Tests](#how-to-run-tests)
- [Test Reports](#test-reports)
- [Customization](#customization)
- [Contributing](#contributing)


## Project Structure

```plaintext
qrystal_e2e/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.qrystal.pages/                  # Page Object Model (POM) classes for web pages
│   │   │       ├── AliasNameAddEditPage.java
│   │   │       ├── AliasNameDeletePage.java
│   │   │       ├── AliasNameListPage.java
│   │   │       ├── AliasNameListTablePage.java
│   │   │       ├── APIUtils.java                     # API utility for interacting with backend APIs
│   │   │       ├── LoginPage.java                    # Login page interactions
│   │   │       └── WebElementUtils.java              # Common WebDriver utilities
│   │   └── com.qrystal.utils/
│   │       ├── DriverInitialization.java              # Driver setup and initialization
│   │       └── ExcelUtils.java                        # Utility for reading data from Excel files
│   ├── test/
│   │   ├── java/
│   │   │   └── com.qrystal.tests/                     # Test classes
│   │   │       ├── AliasNameList_IP_AddTest.java
│   │   │       ├── AliasNameList_IP_DeleteTest.java
│   │   │       ├── AliasNameList_IP_ReadTest.java
│   │   │       ├── AliasNameList_IP_UpdateTest.java
│   │   │       └── BaseTestClass.java                 # Base test class for shared setup and teardown
│   └── resources/                                     # Additional resources (e.g., test data, config files)
├── pom.xml                                            # Maven configuration file
└── testng.xml                                         # TestNG suite configuration




## Key Components
- **Page Object Model (POM)**: Classes in `com.qrystal.pages` package represent the various pages of the application.
- **Utilities**: Helper classes to handle common functions like WebDriver initialization (`DriverInitialization`), Excel file handling (`ExcelUtils`), and API utilities (`APIUtils`).
- **Tests**: Test classes are organized in the `com.qrystal.tests` package, where each class targets specific operations like Add, Delete, Update, and Read for Alias Name functionalities.




## Prerequisites

- Java Development Kit (JDK) 1.8 or above
- Maven 3.5 or above
- Selenium WebDriver
- TestNG framework

Ensure that Java, Maven, and necessary browser drivers (e.g., ChromeDriver, GeckoDriver) are installed and configured in your system's PATH.




## Installation

1. Clone the repository:

    ```bash
    git clone <githuburl>
    cd voipfuture_e2e
    ```

2. Install the Maven dependencies:

    ```bash
    mvn clean install
    ```

## How to Run Tests

- To run all the tests defined in `testng.xml`, execute the following Maven command:

    ```bash
    mvn test
    ```
- To run all the tests as per group name, execute the following Maven command:

    ```bash
    mvn test -Dgroups="regression"
    ```

- To run individual test classes, specify the class name:

    ```bash
    mvn -Dtest=AliasNameList_IP_AddTest
    ```

## Test Reports

After the test execution, TestNG reports will be generated automatically in the `target/surefire-reports` folder. You can access detailed reports via the `testng-results.xml` file.





## Customization

- **Browser Configuration**: You can configure the browser type (e.g., Chrome, Firefox) by modifying the driver initialization in the `DriverInitialization.java` file.
- **Test Data**: Test data can be supplied via Excel sheets, and read using the `ExcelUtils.java` utility class.





## Contributing

If you'd like to contribute to this project, feel free to fork the repository and submit pull requests.

1. Fork the repo
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Open a pull request

---

