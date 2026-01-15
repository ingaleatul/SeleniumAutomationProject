# Selenium WebDriver Test Automation Framework

A robust test automation framework built with Selenium WebDriver, TestNG, and Maven for testing web applications, with a focus on the Saucedemo application.

## 🚀 Features

- **Page Object Model (POM) Design Pattern** for better test maintenance and reusability
- **Data-Driven Testing** using TestNG DataProvider
- **Allure Reporting** for comprehensive test reporting
- **Maven** for dependency management
- **WebDriverManager** for automatic driver management
- **Extent Reports** for test execution reports
- **Cross-browser testing** support (Chrome by default)

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Chrome/Firefox browser (depending on your test requirements)
- Internet connection (for downloading dependencies)

## 🛠️ Setup

1. **Clone the repository**
   ```bash
   git clone [your-repository-url]
   cd selenium-automation
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

## 🚦 Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=com.orangehrm.tests.LoginTest
```

### Run tests in headed mode (visible browser)
Tests run in headed mode by default. To run in headless mode, modify the `WebDriverFactory` class.

### Run with specific browser
Currently configured for Chrome. Modify `WebDriverFactory` to add support for other browsers.

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/orangehrm/
│   │       ├── base/           # Base classes and utilities
│   │       ├── pages/          # Page Object classes
│   │       └── utils/          # Helper classes and utilities
│   └── resources/              # Configuration files
└── test/
    ├── java/
    │   └── com/orangehrm/
    │       └── tests/          # Test classes
    └── resources/              # Test resources (test data, etc.)
```

## 📊 Test Reports

### Allure Reports
Generate Allure report:
```bash
mvn allure:serve
```

### Extent Reports
Find Extent Reports in the `test-output` directory after test execution.

## 🛠️ Dependencies

- Selenium WebDriver
- TestNG
- WebDriverManager
- Allure TestNG
- Extent Reports
- Apache POI (for Excel operations)
- SLF4J (for logging)

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

For any queries, please contact [Your Name] at [your.email@example.com]