# Amazon Price Tracker

Amazon Price Tracker is a Java-based Selenium automation project that monitors Amazon product prices and notifies users when products reach or fall below a predefined target price.

## Tech Stack

* Java
* Selenium WebDriver
* Gradle
* Page Object Model (POM)
* Data-Driven Testing (Apache POI)
* Excel
* Email

## Features

* Read product information from Excel
* Open Amazon product pages using product IDs (ASIN)
* Extract and validate current product prices
* Compare prices against user-defined target values
* Send notifications when prices meet the desired threshold
* Generate a mobile-friendly product summary page
* Scheduled execution for periodic price checks

## Future Enhancements

* Database integration
* Dashboard with price history
* Multiple notification channels
* Docker deployment
* CI/CD with GitHub Actions

-------------------------------------------------------------------
# Amazon Price Tracker

A Java-based Selenium automation project that tracks Amazon product prices and alerts users when prices drop below a target value.

## Features Completed

- Gradle project setup
- Selenium WebDriver integration
- Apache POI integration for Excel reading
- Read product details from Excel
- Read:
    - ASIN
    - Product Name
    - Target Price
- Generate Amazon product URLs from ASIN
- Launch Chrome browser using Selenium
- Open Amazon product pages automatically

## Tech Stack

- Java
- Gradle
- Selenium WebDriver
- Apache POI
- WebDriverManager

## Project Structure

src/main/java

- base
    - BaseClass.java
- data
    - ExcelReader.java
- pages
    - AmazonPage.java
- runner
    - PriceTrackerRunner.java

src/main/resources

- product_list.xlsx

## Next Steps

- Extract current Amazon price
- Compare actual price with target price
- Generate reports
- Send push notifications
- Create dashboard webpage
- Schedule execution every 3 hours