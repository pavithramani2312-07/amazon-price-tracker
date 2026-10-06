# Amazon Price Tracker

A Java + Selenium automation project that monitors Amazon product prices and notifies users when a product's current price falls below the configured target price.

---

## Features

- Read product details from Excel
- Open Amazon product pages using ASIN
- Fetch current product price
- Compare current price with target price
- Detect unavailable products
- Retry mechanism for temporary failures
- Telegram notification for price drops
- Email notification with reports
- CSV Price Report generation
- HTML Dashboard generation
- Extent Report generation
- Screenshot capture on failures
- Logging using SLF4J + Logback
- Jenkins scheduled execution every 3 hours

---

## Tech Stack

- Java 23
- Selenium WebDriver
- Gradle
- Apache POI
- TestNG
- WebDriverManager
- Extent Reports
- SLF4J + Logback
- Jenkins

---

## Project Flow

```text
Excel Input
    ↓
Read Product Data
    ↓
Open Amazon Product Page
    ↓
Validate Product Availability
    ↓
Fetch Current Price
    ↓
Compare Current Price vs Target Price
    ↓
+---------------------------+
| Price <= Target Price ?   |
+---------------------------+
       ↓ Yes                     ↓ No
Send Telegram Alert       Log Status
Send Email Alert          Mark as HIGHER
       ↓
Generate Price Report
       ↓
Generate Dashboard
       ↓
Generate Extent Report
       ↓
Email Reports
```

---

## Excel Format

| ASIN | Product Name | Target Price |
|--------|--------|--------|
| B0DZ2RMZQF | Sightbomb Top | 250 |
| B0FBKCDQ9X | Shoe Rack | 4000 |

---

## Generated Outputs

### Price Report

```text
reports/priceReport.csv
```

Contains:

- Product Name
- Target Price
- Current Price
- Status
- Product URL

---

### Dashboard

```text
dashboard.html
```

Provides a user-friendly HTML summary of all tracked products.

---

### Extent Report

```text
reports/ExtentReport.html
```

Provides execution details including:

- Product execution status
- Price comparison results
- Failures
- Logs

---

### Screenshots

```text
screenshots/
```

Screenshots are captured automatically whenever failures occur.

---

## Notifications

### Telegram Notification

Triggered when:

```text
Current Price <= Target Price
```

Includes:

- Product Name
- Current Price
- Target Price
- Product URL

---

### Email Notification

Email contains:

- priceReport.csv
- dashboard.html
- ExtentReport.html

---

## Jenkins Scheduling

The project is configured in Jenkins and runs automatically every 3 hours.

Example Cron Expression:

```text
H */3 * * *
```

---

## Logging

Implemented using:

- SLF4J
- Logback

Logs include:

- Product URL
- Product Availability
- Current Price
- Target Price
- Notification Status
- Failures and Exceptions

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   ├── base
│   │   ├── data
│   │   ├── model
│   │   ├── pages
│   │   ├── runner
│   │   ├── service
│   │   └── util
│   └── resources
│       └── product_list.xlsx
│
└── test
    └── java
        ├── listeners
        └── tests
```

---

## Future Enhancements

- Custom Exception Handling
- Database Integration
- Multi-platform Price Tracking
- Docker Support
- CI/CD Enhancements

---

## Author

**Pavithra**

Amazon Price Tracker – Selenium Automation Framework